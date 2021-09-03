package repertapp.repertapp.repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Band;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    Page<Band> findByNameLike(@NotNull String name, Pageable page);
    
    Boolean existsByName(@NotBlank String name);
}
