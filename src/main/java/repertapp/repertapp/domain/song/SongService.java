package repertapp.repertapp.domain.song;

import java.util.List;

import org.apache.commons.text.WordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.core.exception.ResourceNotFoundException;
import repertapp.repertapp.core.mapper.SongMapper;
import repertapp.repertapp.domain.tag.Tag;
import repertapp.repertapp.domain.tag.TagRepository;

@RequiredArgsConstructor
@Service
public class SongService {
    
    private final SongRepository songRepository;
    private final TagRepository tagRepository;
    
    private Song findByIdOrThrowResourceNotFoundException(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
    }
    
    /**
     * Creates a new song and respond its body
     * @param songRequest
     * @return
     */
    public SongResponseBody addSong(SongPostRequestBody songRequest) {
        songRequest.setArtist(WordUtils.capitalizeFully(songRequest.getArtist()));
        songRequest.setName(WordUtils.capitalizeFully(songRequest.getName()));

        SongRequestValidation.valideAdd(songRequest, songRepository);

        Song song = SongMapper.INSTANCE.toSong(songRequest);
            
        Song songSaved = songRepository.save(song);

        SongResponseBody songResponse = SongMapper.INSTANCE.toSongResponseBody(songSaved);
        
        return songResponse;
    }
    
    /**
     * Returns a song by ID
     * @param id
     * @return
     */
    public SongResponseBody getSong(Long id) {
        Song song = findByIdOrThrowResourceNotFoundException(id);

        SongResponseBody songResponse = SongMapper.INSTANCE.toSongResponseBody(song);

        return songResponse;
    }
        
    /**
     * Updates a song
     * @param songRequest
     */
    public void updateSong(SongPutRequestBody songRequest) {
        songRequest.setArtist(WordUtils.capitalizeFully(songRequest.getArtist()));
        songRequest.setName(WordUtils.capitalizeFully(songRequest.getName()));

        Song song = findByIdOrThrowResourceNotFoundException(songRequest.getId());

        SongRequestValidation.valideUpdate(songRequest, song, songRepository);
        
        Song songSaved = SongMapper.INSTANCE.toSong(songRequest);
        
        songRepository.save(songSaved);
    }
    
    /**
     * Delete a song
     * @param id
     */
    public void deleteSong(Long id) {
        Song song = findByIdOrThrowResourceNotFoundException(id);
        
        songRepository.delete(song);
    }

    /**
     * Returns a page object with songs
     * @param pageable
     * @return
     */
    public Page<SongResponseBody> getAllSongs(Pageable pageable) {
        Page<Song> songs = songRepository.findAll(pageable);

        Page<SongResponseBody> songsResponse = songs.map(song -> 
            SongMapper.INSTANCE.toSongResponseBody(song)
        );
        
        return songsResponse;
    }
    
    /**
     * Returns a page object with songs by list of tags
     * @param tags
     * @param pageable
     * @return
     */
    public Page<SongResponseBody> getSongsByTags(List<Tag> tags, Pageable pageable) {
        tags.stream().forEach(tag -> tagRepository.findById(tag.getId()).orElseThrow(
            () -> new ResourceNotFoundException("Tag", "id", tag.getId())));
    
        Page<Song> songs = songRepository.findDistinctSongsByTagsIn(tags, pageable);

        Page<SongResponseBody> songsResponse = songs.map(song ->
            SongMapper.INSTANCE.toSongResponseBody(song)
        );
    
        return songsResponse;
    }
}
