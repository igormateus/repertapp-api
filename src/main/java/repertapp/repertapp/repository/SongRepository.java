package repertapp.repertapp.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tag;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Page<Song> findByName(@NotBlank String name, Pageable pageable);
    Optional<Song> findByNameAndArtist(@NotBlank String name, @NotBlank String artist);
    Optional<Song> findByYoutubeLink(@NotBlank @URL String youtubeLink);
    Optional<Song> findBySpotifyLink(@NotBlank @URL String spotifyLink);
    
    Page<Song> findDistinctSongsByTagsIn(@NotEmpty List<Tag> tags, Pageable pageable);

    Boolean existsByNameAndArtist(@NotBlank String name, @NotBlank String artist);
    Boolean existsByYoutubeLink(@NotBlank @URL String youtubeLink);
    Boolean existsBySpotifyLink(@NotBlank @URL String spotifyLink);
}
