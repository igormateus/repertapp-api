package repertapp.repertapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.SongMapper;
import repertapp.repertapp.payload.SongRequest;
import repertapp.repertapp.payload.SongResponse;
import repertapp.repertapp.repository.SongRepository;
import repertapp.repertapp.repository.TagRepository;
import repertapp.repertapp.validation.SongRequestValidation;

@RequiredArgsConstructor
@Service
public class SongService {
    
    private final SongRepository songRepository;
    private final TagRepository tagRepository;
    
    public Page<Song> getAllSongs(Pageable pageable) {
        Page<Song> songs = songRepository.findAll(pageable);
        
        return songs;
    }
    
    public Song getSong(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
    }
    
    @Transactional
    public SongResponse addSong(SongRequest songRequest) {
        SongRequestValidation.validePost(songRequest, songRepository);

        Song song = SongMapper.INSTANCE.toSong(songRequest);
            
        Song newSong = songRepository.save(song);
        
        SongResponse songResponse = SongMapper.INSTANCE.toSongResponse(newSong);
        
        return songResponse;
    }
        
    @Transactional
    public void updateSong(Long id, SongRequest newSongRequest) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));

        SongRequestValidation.valideUpdate(song, newSongRequest, songRepository);
        
        song.setName(newSongRequest.getName());
        song.setArtist(newSongRequest.getArtist());
        song.setYoutubeLink(newSongRequest.getYoutubeLink());
        song.setSpotifyLink(newSongRequest.getSpotifyLink());
        song.setTone(newSongRequest.getTone());
        song.setTags(newSongRequest.getTags());
        
        songRepository.save(song);
    }
    
    @Transactional
    public void deleteSong(Long id) {
        Song song = songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
        
        songRepository.delete(song);
    }
    
    public Page<Song> getSongsByTag(List<Tag> tags, Pageable pageable) {
        tags.stream().forEach(tag -> tagRepository.findById(tag.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Tag", "id", tag.getId())));
    
        Page<Song> songs = songRepository.findDistinctSongsByTagsIn(tags, pageable);
    
        return songs;
    }
}
