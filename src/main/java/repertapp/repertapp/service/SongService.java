package repertapp.repertapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.SongMapper;
import repertapp.repertapp.payload.SongPostRequestBody;
import repertapp.repertapp.payload.SongPutRequestBody;
import repertapp.repertapp.repository.SongRepository;
import repertapp.repertapp.repository.TagRepository;
import repertapp.repertapp.validation.SongRequestValidation;

@RequiredArgsConstructor
@Service
public class SongService {
    
    private final SongRepository songRepository;
    private final TagRepository tagRepository;
    
    private Song findByIdOrThrowResourceNotFoundException(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
    }

    public Page<Song> getAllSongs(Pageable pageable) {
        Page<Song> songs = songRepository.findAll(pageable);
        
        return songs;
    }
    
    public Song getSong(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
    }
    
    @Transactional
    public Song addSong(SongPostRequestBody songRequest) {
        SongRequestValidation.valideAdd(songRequest, songRepository);

        Song song = SongMapper.INSTANCE.toSong(songRequest);
            
        Song newSong = songRepository.save(song);
        
        return newSong;
    }
        
    @Transactional
    public void updateSong(SongPutRequestBody songRequest) {
        Song song = findByIdOrThrowResourceNotFoundException(songRequest.getId());

        SongRequestValidation.valideUpdate(songRequest, song, songRepository);
        
        Song songSaved = SongMapper.INSTANCE.toSong(songRequest);
        
        songRepository.save(songSaved);
    }
    
    @Transactional
    public void deleteSong(Long id) {
        Song song = findByIdOrThrowResourceNotFoundException(id);
        
        songRepository.delete(song);
    }
    
    public Page<Song> getSongsByTags(List<Tag> tags, Pageable pageable) {
        tags.stream().forEach(tag -> tagRepository.findById(tag.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Tag", "id", tag.getId())));
    
        Page<Song> songs = songRepository.findDistinctSongsByTagsIn(tags, pageable);
    
        return songs;
    }
}
