package repertapp.repertapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.Song;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Boolean existsByBandAndSong(Band band, Song song);
}
