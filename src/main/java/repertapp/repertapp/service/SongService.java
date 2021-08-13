package repertapp.repertapp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.mapper.SongMapper;
import repertapp.repertapp.repository.SongRepository;
import repertapp.repertapp.requests.SongPostRequestBody;

@Service
@RequiredArgsConstructor
public class SongService {
    
    private final SongRepository songRepository;

    public Page<Song> listAll(Pageable pageable) {
        return songRepository.findAll(pageable);
    }

    @Transactional
    public Song save(SongPostRequestBody songPostRequestBody) {
        return songRepository.save(SongMapper.INSTANCE.toSong(songPostRequestBody));
    }
}
