package repertapp.repertapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import repertapp.repertapp.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByName(String name);

}
