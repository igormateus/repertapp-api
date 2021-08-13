package repertapp.repertapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import repertapp.repertapp.domain.Song;

public interface SongRepository extends JpaRepository<Song, Long>{
    
}
