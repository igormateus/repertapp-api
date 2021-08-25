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
    Page<Song> findByName(String name, Pageable pageable);
    Page<Song> findByTagsIn(List<Tag> tags, Pageable pageable);
    List<Song> findByNameAndArtist(String name, String artist);
    List<Song> findByYoutubeLink(String youtubeLink);
    List<Song> findBySpotifyLink(String spotifyLink);
}
