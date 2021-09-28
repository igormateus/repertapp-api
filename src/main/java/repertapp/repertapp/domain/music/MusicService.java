package repertapp.repertapp.domain.music;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.core.exception.NoPermissionException;
import repertapp.repertapp.core.exception.ResourceNotFoundException;
import repertapp.repertapp.core.mapper.MusicMapper;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.band.BandService;

@RequiredArgsConstructor
@Service
public class MusicService {

    private final MusicRepository musicRepository;

    private final BandService bandService;

    private Music findByIdOrThrowResourceNotFoundException(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Music", "id", id));
    }

    @Transactional
    public MusicResponseBody addMusic(
        @Valid MusicPostRequestBody musicRequest,
        Long bandId, RepertappUser user
    ) {
        Band band = bandService.getBandByUser(bandId, user);

        musicRequest.setBand(band);

        MusicRequestValidation.valideAdd(musicRequest, musicRepository);

        Music music = MusicMapper.INSTANCE.toMusic(musicRequest);

        Music musicSaved = musicRepository.save(music);

        MusicResponseBody musicResponse = MusicMapper.INSTANCE.toMusicResponseBody(musicSaved);

        return musicResponse;
    }

    private Music findByIdAndValidAccess(Long id, Band band) {
        Music music = findByIdOrThrowResourceNotFoundException(id);

        if (band.getMusics().stream().noneMatch(m -> m.getId() == music.getId()))
            throw new NoPermissionException("Band", band.getName(), "Music", music.getId());
        
        return music;
    }


    @Transactional
    public void updateMusic(@Valid MusicPutRequestBody musicRequest, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);
        
        Music music = findByIdAndValidAccess(musicRequest.getId(), band);

        if (!(music.getBand().getId() == band.getId()))
            throw new RuntimeException("You are not allow to change Band in this Music");

        MusicRequestValidation.valideUpdate(musicRequest, music, musicRepository);

        Music musicToBeSaved = MusicMapper.INSTANCE.toMusic(musicRequest);

        musicRepository.save(musicToBeSaved);
    }

    @Transactional
    public void deleteMusic(Long musicId, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        Music music = findByIdAndValidAccess(musicId, band);
        
        musicRepository.delete(music);
    }

    public Page<Music> getAllMusicsByBand(Long bandId, RepertappUser user, Pageable pageable) {
        Band band = bandService.getBandByUser(bandId, user);

        Page<Music> musics = musicRepository.findByBand(band, pageable);

        return musics;
    }

    public Music getMusicByBand(Long id, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        Music music = findByIdAndValidAccess(id, band);
        
        return music;
    }
}
