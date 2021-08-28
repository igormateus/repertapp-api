package repertapp.repertapp.repository;

import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.repository.JpaRepository;

import repertapp.repertapp.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(@NotBlank String name);

    Boolean existsByName(@NotBlank String name);
}
