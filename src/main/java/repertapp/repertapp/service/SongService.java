package repertapp.repertapp.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.exception.BadRequestException;
import repertapp.repertapp.projection.SongProjection;
import repertapp.repertapp.repository.SongRepository;

@Service
@RequiredArgsConstructor
public class SongService {
    
    private final SongRepository songRepository;

    public Page<Song> listAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    public List<Song> listAll() {
        return songRepository.findAll();
    }

    public Page<SongProjection> findAllSongs(Pageable pageable) {
        return songRepository.findAllSongs(pageable);
    }

    public List<SongProjection> findAllSongs() {
        return songRepository.findAllSongs();
    }

    @Transactional
    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void replace(Song song) {

        findByIdOrThrowBadRequestException(song.getId());
        this.songRepository.save(song);

    }

    public Song findByIdOrThrowBadRequestException(Long id) {
        
        return songRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Song not found"));
    }
}
