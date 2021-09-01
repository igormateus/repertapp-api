package repertapp.repertapp.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.MusicMapper;
import repertapp.repertapp.payload.MusicPostRequestBody;
import repertapp.repertapp.payload.MusicPutRequestBody;
import repertapp.repertapp.repository.MusicRepository;
import repertapp.repertapp.validation.MusicRequestValidation;

@RequiredArgsConstructor
@Service
public class MusicService {

    private final MusicRepository musicRepository;

    private Music findByIdOrThrowResourceNotFoundException(Long id) {
        return musicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Music", "id", id));
    }

    @Transactional
    public Music addMusic(@Valid MusicPostRequestBody musicRequest) {
        MusicRequestValidation.valideAdd(musicRequest, musicRepository);

        Music music = MusicMapper.INSTANCE.toMusic(musicRequest);

        Music musicSaved = musicRepository.save(music);

        return musicSaved;
    }

    @Transactional
    public void updateMusic(@Valid MusicPutRequestBody musicRequest) {
        Music music = findByIdOrThrowResourceNotFoundException(musicRequest.getId());

        MusicRequestValidation.valideUpdate(musicRequest, music, musicRepository);

        Music musicToBeSaved = MusicMapper.INSTANCE.toMusic(musicRequest);

        musicRepository.save(musicToBeSaved);
    }

    @Transactional
    public void deleteMusic(Long id) {
        Music music = findByIdOrThrowResourceNotFoundException(id);
        
        musicRepository.delete(music);
    }

    public Page<Music> getAllMusics(Pageable pageable) {
        Page<Music> musics = musicRepository.findAll(pageable);

        return musics;
    }

    public Music getMusic(Long id) {
        Music music = findByIdOrThrowResourceNotFoundException(id);
        
        return music;
    }
}
