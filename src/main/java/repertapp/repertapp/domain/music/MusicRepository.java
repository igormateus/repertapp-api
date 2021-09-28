package repertapp.repertapp.domain.music;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.tag.Tag;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Page<Music> findByName(@NotBlank String name, Pageable pageable);
    Page<Music> findByArtist(@NotBlank String artist);
    Page<Music> findByBand(@NotNull Band band, Pageable page);
    
    Optional<Music> findByNameAndArtist(@NotBlank String name, @NotBlank String artist);
    Optional<Music> findByYoutubeLink(@NotBlank @URL String youtubeLink);
    Optional<Music> findBySpotifyLink(@NotBlank @URL String spotifyLink);
    
    Page<Music> findDistinctSongsByTagsIn(@NotEmpty List<Tag> tags, Pageable pageable);

    Boolean existsByNameAndArtist(@NotBlank String name, @NotBlank String artist);
    Boolean existsByYoutubeLink(@NotBlank @URL String youtubeLink);
    Boolean existsBySpotifyLink(@NotBlank @URL String spotifyLink);
}