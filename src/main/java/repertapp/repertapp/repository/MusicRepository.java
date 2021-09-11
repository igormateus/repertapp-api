package repertapp.repertapp.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.Song;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Page<Music> findByBand(@NotNull Band band, Pageable page);

    Boolean existsByBandAndSong(Band band, Song song);
}
