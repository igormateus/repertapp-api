package repertapp.repertapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.projection.TagResumeProjection;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByName(String name);

    List<Tag> getAllTagsBySongsName(String songName);

    @Query(value = "select * from tag", nativeQuery = true)
    Page<TagResumeProjection> findAllSongs(Pageable pageable);
    
    @Query(value = "select * from tag", nativeQuery = true)
    List<TagResumeProjection> findAllSongs(); 

}
