package repertapp.repertapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import repertapp.repertapp.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
