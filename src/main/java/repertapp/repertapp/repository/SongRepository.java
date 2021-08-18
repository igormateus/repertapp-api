package repertapp.repertapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.projection.SongProjection;

public interface SongRepository extends JpaRepository<Song, Long> {

    @Query(value = "select * from song", nativeQuery = true)
    Page<SongProjection> findAllSongs(Pageable pageable);
    
    @Query(value = "select * from song", nativeQuery = true)
    List<SongProjection> findAllSongs(); 

}
