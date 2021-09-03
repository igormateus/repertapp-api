package repertapp.repertapp.repository;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import repertapp.repertapp.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Page<Tag> findByNameLike(@NotBlank String name, Pageable page);

    Boolean existsByName(@NotBlank String name);
}
