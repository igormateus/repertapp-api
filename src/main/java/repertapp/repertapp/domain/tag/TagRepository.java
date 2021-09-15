package repertapp.repertapp.domain.tag;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findByNameLike(@NotBlank String name, Pageable page);

    Boolean existsByName(@NotBlank String name);
}
