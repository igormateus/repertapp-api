package repertapp.repertapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Music;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    
}
