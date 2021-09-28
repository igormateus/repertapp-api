package repertapp.repertapp.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import repertapp.repertapp.domain.tag.Tag;
// import repertapp.repertapp.domain.song.Song;
// import repertapp.repertapp.domain.song.SongRepository;
import repertapp.repertapp.domain.tag.TagRepository;
// import repertapp.repertapp.util.SongCreator;
import repertapp.repertapp.util.TagCreator;

@DataJpaTest
@DisplayName("Tests For Song Repository")
public class SongRepositoryTest {

    // @Autowired
    // // private SongRepository songRepository;

    // @Autowired
    // private TagRepository tagRepository;

    @Test
    @DisplayName("save persists song when successful")
    public void save_persistSong_whenSuccessful() {
        // Song songToBeSaved = SongCreator.createToBeSaved();

        // Song songSaved = songRepository.save(songToBeSaved);

        // Assertions.assertThat(songSaved)
        //         .isNotNull()
        //         .isEqualTo(songToBeSaved);
        // Assertions.assertThat(songSaved.getId()).isNotNull();
    }

    @Test
    @DisplayName("save update song when successful")
    public void save_updateSong_whenSucessful() {
        // Song songToBeSaved = SongCreator.createToBeSaved();

        // Song songSaved = songRepository.save(songToBeSaved);

        // songSaved.setName("Song_test_2");

        // Song songUpdated = songRepository.save(songSaved);

        // Assertions.assertThat(songUpdated)
        //         .isNotNull()
        //         .isEqualTo(songSaved);
    }

    @Test
    @DisplayName("delete remove song when successful")
    public void delete_removeSong_whenSucessful() {
        // Song songToBeSaved = SongCreator.createToBeSaved();
        // Song songSaved = songRepository.save(songToBeSaved);

        // songRepository.delete(songSaved);
        // Optional<Song> songOptional = songRepository.findById(songSaved.getId());

        // Assertions.assertThat(songOptional).isEmpty();
    }

    @Test
    @DisplayName("findByName return a list of song inside page object when successful")
    public void findByName_returnListOfSongInsidePageObject_WhenSuccessful() {
        // Song songToBeSaved = SongCreator.createToBeSaved();

        // Song songSaved = songRepository.save(songToBeSaved);

        // Page<Song> songPage = songRepository.findByName(songSaved.getName(), null);

        // Assertions.assertThat(songPage).isNotNull();
        // Assertions.assertThat(songPage.toList())
        //         .isNotEmpty()
        //         .hasSize(1);
        // Assertions.assertThat(songPage.toList().get(0)).isEqualTo(songSaved);
    }

    @Test
    @DisplayName("findDistinctSongsByTagsIn return a list of song inside page object when successful")
    public void findDistinctSongsByTagsIn_returnListOfSongInsidePageObject_whenSuccessful() {
        // Tag tagToBeSaved1 = TagCreator.createToBeSaved("1");
        // Tag tagSaved1 = tagRepository.save(tagToBeSaved1);

        // Tag tagToBeSaved2 = TagCreator.createToBeSaved("2");
        // Tag tagSaved2 = tagRepository.save(tagToBeSaved2);

        // List<Tag> tags = List.of(tagSaved1, tagSaved2);

        // Song songToBeSaved1 = SongCreator.createToBeSaved("1");
        // songToBeSaved1.setTags(tags);
        // Song songSaved1 = songRepository.save(songToBeSaved1);

        // Song songToBeSaved2 = SongCreator.createToBeSaved("2");
        // songToBeSaved2.setTags(tags);
        // Song songSaved2 = songRepository.save(songToBeSaved2);
        
        // Page<Song> songPage = songRepository.findDistinctSongsByTagsIn(tags, null);

        // Assertions.assertThat(songPage).isNotNull();
        // Assertions.assertThat(songPage.toList())
        //         .isNotEmpty()
        //         .hasSize(2);
        // Assertions.assertThat(songPage.toList().get(0)).isEqualTo(songSaved1);
        // Assertions.assertThat(songPage.toList().get(1)).isEqualTo(songSaved2);
    }

    @Test
    @DisplayName("findByNameAndArtist return song when successful")
    public void findByNameAndArtist_returnSong_whenSuccessful() {
        // Song songToBeSaved = SongCreator.createToBeSaved();

        // Song songSaved = songRepository.save(songToBeSaved);

        // Optional<Song> optionalSong = songRepository.findByNameAndArtist(songSaved.getName(), songSaved.getArtist());

        // Assertions.assertThat(optionalSong.get())
        //         .isNotNull()
        //         .isEqualTo(songSaved);
    }
    
    @Test
    @DisplayName("findByYoutubeLink return song when successful")
    public void findByYoutubeLink_returnSong_whenSuccessful() {
        // Song songToBeSaved = SongCreator.createToBeSaved();

        // Song songSaved = songRepository.save(songToBeSaved);

        // Optional<Song> optionalSong = songRepository.findByYoutubeLink(songSaved.getYoutubeLink());

        // Assertions.assertThat(optionalSong.get())
        //         .isNotNull()
        //         .isEqualTo(songSaved);
    }

    @Test
    @DisplayName("findBySpotifyLink return song when successful")
    public void findBySpotifyLink_returnSong_whenSuccessful() {
        // Song songToBeSaved = SongCreator.createToBeSaved();

        // Song songSaved = songRepository.save(songToBeSaved);

        // Optional<Song> optionalSong = songRepository.findBySpotifyLink(songSaved.getSpotifyLink());

        // Assertions.assertThat(optionalSong.get())
        //         .isNotNull()
        //         .isEqualTo(songSaved);
    }

    @Test
    @DisplayName("existsByNameAndArtist return true when sound exist")
    public void existsByNameAndArtist_returnTrue_whenSoundExists() {
        // Song songToBeSaved = SongCreator.createToBeSaved();
        // Song songSaved = songRepository.save(songToBeSaved);

        // Boolean exist = songRepository.existsByNameAndArtist(songSaved.getName(), songSaved.getArtist());

        // Assertions.assertThat(exist).isTrue();
    }

    @Test
    @DisplayName("existsByYoutubeLink return true when youtube link exist")
    public void existsByYoutubeLink_returnTrue_whenYoutubeLinkExists() {
        // Song songToBeSaved = SongCreator.createToBeSaved();
        // Song songSaved = songRepository.save(songToBeSaved);

        // Boolean exist = songRepository.existsByYoutubeLink(songSaved.getYoutubeLink());

        // Assertions.assertThat(exist).isTrue();
    }

    @Test
    @DisplayName("existsBySpotifyLink return true when spotify link exist")
    public void existsBySpotifyLink_returnTrue_whenSpotifyLinkExists() {
        // Song songToBeSaved = SongCreator.createToBeSaved();
        // Song songSaved = songRepository.save(songToBeSaved);

        // Boolean exist = songRepository.existsBySpotifyLink(songSaved.getSpotifyLink());

        // Assertions.assertThat(exist).isTrue();
    }
}
