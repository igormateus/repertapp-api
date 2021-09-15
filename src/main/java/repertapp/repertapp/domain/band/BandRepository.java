package repertapp.repertapp.domain.band;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.user.RepertappUser;

@Repository
public interface BandRepository extends JpaRepository<Band, Long> {
    Page<Band> findByNameLike(@NotBlank String name, Pageable page);
    Page<Band> findByMembers(@NotNull RepertappUser member, Pageable page);

    Boolean existsByName(@NotBlank String name);
}
