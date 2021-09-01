package repertapp.repertapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Setlist;

@Repository
public interface SetlistRepository extends JpaRepository<Setlist, Long> {
    
}
