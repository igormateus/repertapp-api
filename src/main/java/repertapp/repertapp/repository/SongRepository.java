package repertapp.repertapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tag;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
    Page<Song> findByTagsIn(List<Tag> tags, Pageable pageable);

}
