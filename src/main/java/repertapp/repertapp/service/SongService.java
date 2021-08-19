package repertapp.repertapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.SongMapper;
import repertapp.repertapp.payload.SongRequest;
import repertapp.repertapp.payload.SongResponse;
import repertapp.repertapp.repository.SongRepository;

@RequiredArgsConstructor
@Service
public class SongService {

    private final SongRepository songRepository;
    
    public Page<Song> getAllSongs(Pageable pageable) {
        Page<Song> songs = songRepository.findAll(pageable);

        return songs;
    }

    public Song getSong(Long id) {
        return songRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Song", "id", id));
    }

    public SongResponse addSong(SongRequest songRequest) {
        Song song = SongMapper.INSTANCE.toSong(songRequest);

        Song newSong = songRepository.save(song);

        SongResponse songResponse = SongMapper.INSTANCE.toSongResponse(newSong);

        return songResponse;
    }
}
