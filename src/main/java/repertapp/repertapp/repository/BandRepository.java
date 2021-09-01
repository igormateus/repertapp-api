package repertapp.repertapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Band;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    
}
