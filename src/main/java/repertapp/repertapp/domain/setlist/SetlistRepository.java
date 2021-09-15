package repertapp.repertapp.domain.setlist;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.band.Band;

@Repository
public interface SetlistRepository extends JpaRepository<Setlist, Long> {

    Page<Setlist> findByBand(@NotNull Band band, Pageable pageable);
    
}
