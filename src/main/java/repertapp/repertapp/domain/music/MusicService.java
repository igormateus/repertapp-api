package repertapp.repertapp.domain.music;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.core.exception.NoPermissionException;
import repertapp.repertapp.core.exception.ResourceNotFoundException;
import repertapp.repertapp.core.mapper.MusicMapper;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.band.BandService;
import repertapp.repertapp.domain.user.RepertappUser;

@RequiredArgsConstructor
@Service
public class MusicService {

    private final MusicRepository musicRepository;

    private final BandService bandService;

    private Music findByIdOrThrowResourceNotFoundException(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Music", "id", id));
    }

    public Music findByIdAndValidAccess(Long id, Band band) {
        Music music = findByIdOrThrowResourceNotFoundException(id);

        if (music.getBand().getId() != band.getId())
            throw new NoPermissionException("Band", band.getName(), "Music", music.getId());
        
        return music;
    }

    /**
     * Creates a music validating access and respond his body
     * @param musicRequest
     * @param bandId
     * @param user
     * @return
     */
    public MusicResponseBody addMusic(
        @Valid MusicPostRequestBody musicRequest,
        Long bandId,
        RepertappUser user
    ) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        musicRequest.setBand(band);

        MusicRequestValidation.valideAdd(musicRequest, musicRepository);

        Music music = MusicMapper.INSTANCE.toMusic(musicRequest);

        Music musicSaved = musicRepository.save(music);

        MusicResponseBody musicResponse = MusicMapper.INSTANCE.toMusicResponseBody(musicSaved);

        return musicResponse;
    }

    /**
     * Valid user and band has access to music and return his body
     * @param id
     * @param bandId
     * @param user
     * @return
     */
    public MusicResponseBody getMusicByBand(Long id, Long bandId, RepertappUser user) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        Music music = findByIdAndValidAccess(id, band);

        MusicResponseBody response = MusicMapper.INSTANCE.toMusicResponseBody(music);
        
        return response;
    }

    /**
     * Update a music
     * @param musicRequest
     * @param bandId
     * @param user
     */
    public void updateMusic(@Valid MusicPutRequestBody musicRequest, Long bandId, RepertappUser user) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        musicRequest.setBand(band);
        
        Music music = findByIdAndValidAccess(musicRequest.getId(), band);

        MusicRequestValidation.valideUpdate(musicRequest, music, musicRepository);

        Music musicToBeSaved = MusicMapper.INSTANCE.toMusic(musicRequest);

        musicRepository.save(musicToBeSaved);
    }

    /**
     * Delete music validating user and band
     * @param musicId
     * @param bandId
     * @param user
     */
    public void deleteMusic(Long musicId, Long bandId, RepertappUser user) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        Music music = findByIdAndValidAccess(musicId, band);
        
        musicRepository.delete(music);
    }

    /**
     * Return all music by a band on page object
     * @param bandId
     * @param user
     * @param pageable
     * @return
     */
    public Page<MusicResponseBody> getAllMusicsByBand(Long bandId, RepertappUser user, Pageable pageable) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        Page<Music> musics = musicRepository.findByBand(band, pageable);

        Page<MusicResponseBody> musicsResponse = musics.map(music ->
            MusicMapper.INSTANCE.toMusicResponseBody(music));

        return musicsResponse;
    }
}
