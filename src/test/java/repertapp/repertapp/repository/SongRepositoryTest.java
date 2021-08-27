package repertapp.repertapp.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.util.SongCreator;
import repertapp.repertapp.util.TagCreator;

@DataJpaTest
@DisplayName("Tests For Song Repository")
public class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("save persists song when successful")
    public void save_persistSong_whenSuccessful() {
        Song songToBeSaved = SongCreator.createToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        Assertions.assertThat(songSaved).isNotNull();
        Assertions.assertThat(songSaved.getId()).isNotNull();
        Assertions.assertThat(songSaved.getArtist()).isEqualTo(songToBeSaved.getArtist());
        Assertions.assertThat(songSaved.getName()).isEqualTo(songToBeSaved.getName());
        Assertions.assertThat(songSaved.getSpotifyLink()).isEqualTo(songToBeSaved.getSpotifyLink());
        Assertions.assertThat(songSaved.getYoutubeLink()).isEqualTo(songToBeSaved.getYoutubeLink());
        Assertions.assertThat(songSaved.getTone()).isEqualTo(songToBeSaved.getTone());
    }

    @Test
    @DisplayName("save update song when successful")
    public void save_updateSong_whenSucessful() {
        Song songToBeSaved = SongCreator.createToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        songSaved.setName("Song_test_2");

        Song songUpdated = songRepository.save(songSaved);

        Assertions.assertThat(songUpdated).isNotNull();
        Assertions.assertThat(songUpdated.getId()).isEqualTo(songSaved.getId());
        Assertions.assertThat(songUpdated.getArtist()).isEqualTo(songSaved.getArtist());
        Assertions.assertThat(songUpdated.getName()).isEqualTo(songSaved.getName());
        Assertions.assertThat(songUpdated.getSpotifyLink()).isEqualTo(songSaved.getSpotifyLink());
        Assertions.assertThat(songUpdated.getYoutubeLink()).isEqualTo(songSaved.getYoutubeLink());
        Assertions.assertThat(songUpdated.getTone()).isEqualTo(songSaved.getTone());
    }

    @Test
    @DisplayName("delete remove song when successful")
    public void delete_removeSong_whenSucessful() {
        Song songToBeSaved = SongCreator.createToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        songRepository.delete(songSaved);

        Optional<Song> songOptional = songRepository.findById(songSaved.getId());

        Assertions.assertThat(songOptional).isEmpty();
    }

    @Test
    @DisplayName("findByName return a list of song inside page object when successful")
    public void findByName_returnListOfSongInsidePageObject_WhenSuccessful() {
        Song songToBeSaved = SongCreator.createToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        Page<Song> songPage = songRepository.findByName(songSaved.getName(), null);

        Assertions.assertThat(songPage).isNotNull();
        Assertions.assertThat(songPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(songPage.toList().get(0).getId()).isEqualTo(songSaved.getId());
        Assertions.assertThat(songPage.toList().get(0).getArtist()).isEqualTo(songSaved.getArtist());
        Assertions.assertThat(songPage.toList().get(0).getName()).isEqualTo(songSaved.getName());
        Assertions.assertThat(songPage.toList().get(0).getSpotifyLink()).isEqualTo(songSaved.getSpotifyLink());
        Assertions.assertThat(songPage.toList().get(0).getYoutubeLink()).isEqualTo(songSaved.getYoutubeLink());
        Assertions.assertThat(songPage.toList().get(0).getTone()).isEqualTo(songSaved.getTone());
    }

    @Test
    @DisplayName("findDistinctSongsByTagsIn return a list of song inside page object when successful")
    public void findDistinctSongsByTagsIn_returnListOfSongInsidePageObject_whenSuccessful() {
        Tag tagToBeSaved1 = TagCreator.createToBeSaved("1");
        Tag tagSaved1 = tagRepository.save(tagToBeSaved1);

        Tag tagToBeSaved2 = TagCreator.createToBeSaved("2");
        Tag tagSaved2 = tagRepository.save(tagToBeSaved2);

        List<Tag> tags = List.of(tagSaved1, tagSaved2);

        Song songToBeSaved1 = SongCreator.createToBeSaved("1");
        songToBeSaved1.setTags(tags);
        Song songSaved1 = songRepository.save(songToBeSaved1);

        Song songToBeSaved2 = SongCreator.createToBeSaved("2");
        songToBeSaved2.setTags(tags);
        Song songSaved2 = songRepository.save(songToBeSaved2);
        
        Page<Song> songPage = songRepository.findDistinctSongsByTagsIn(tags, null);

        Assertions.assertThat(songPage).isNotNull();
        Assertions.assertThat(songPage.toList())
                .isNotEmpty()
                .hasSize(2);
        Assertions.assertThat(songPage.toList().get(0).getId()).isEqualTo(songSaved1.getId());
        Assertions.assertThat(songPage.toList().get(0).getArtist()).isEqualTo(songSaved1.getArtist());
        Assertions.assertThat(songPage.toList().get(0).getName()).isEqualTo(songSaved1.getName());
        Assertions.assertThat(songPage.toList().get(0).getSpotifyLink()).isEqualTo(songSaved1.getSpotifyLink());
        Assertions.assertThat(songPage.toList().get(0).getYoutubeLink()).isEqualTo(songSaved1.getYoutubeLink());
        Assertions.assertThat(songPage.toList().get(0).getTone()).isEqualTo(songSaved2.getTone());
        Assertions.assertThat(songPage.toList().get(1).getId()).isEqualTo(songSaved2.getId());
        Assertions.assertThat(songPage.toList().get(1).getArtist()).isEqualTo(songSaved2.getArtist());
        Assertions.assertThat(songPage.toList().get(1).getName()).isEqualTo(songSaved2.getName());
        Assertions.assertThat(songPage.toList().get(1).getSpotifyLink()).isEqualTo(songSaved2.getSpotifyLink());
        Assertions.assertThat(songPage.toList().get(1).getYoutubeLink()).isEqualTo(songSaved2.getYoutubeLink());
        Assertions.assertThat(songPage.toList().get(1).getTone()).isEqualTo(songSaved2.getTone());
    }

    @Test
    @DisplayName("findByNameAndArtist return song when successful")
    public void findByNameAndArtist_returnSong_whenSuccessful() {
        Song songToBeSaved = SongCreator.createToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        Song songFound = songRepository.findByNameAndArtist(songSaved.getName(), songSaved.getArtist());

        Assertions.assertThat(songFound).isNotNull();
        Assertions.assertThat(songFound.getId()).isEqualTo(songSaved.getId());
        Assertions.assertThat(songFound.getArtist()).isEqualTo(songSaved.getArtist());
        Assertions.assertThat(songFound.getName()).isEqualTo(songSaved.getName());
        Assertions.assertThat(songFound.getSpotifyLink()).isEqualTo(songSaved.getSpotifyLink());
        Assertions.assertThat(songFound.getYoutubeLink()).isEqualTo(songSaved.getYoutubeLink());
        Assertions.assertThat(songFound.getTone()).isEqualTo(songSaved.getTone());
    }
    
    @Test
    @DisplayName("findByYoutubeLink return song when successful")
    public void findByYoutubeLink_returnSong_whenSuccessful() {
        Song songToBeSaved = SongCreator.createToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        Song songFound = songRepository.findByYoutubeLink(songSaved.getYoutubeLink());

        Assertions.assertThat(songFound).isNotNull();
        Assertions.assertThat(songFound.getId()).isEqualTo(songSaved.getId());
        Assertions.assertThat(songFound.getArtist()).isEqualTo(songSaved.getArtist());
        Assertions.assertThat(songFound.getName()).isEqualTo(songSaved.getName());
        Assertions.assertThat(songFound.getSpotifyLink()).isEqualTo(songSaved.getSpotifyLink());
        Assertions.assertThat(songFound.getYoutubeLink()).isEqualTo(songSaved.getYoutubeLink());
        Assertions.assertThat(songFound.getTone()).isEqualTo(songSaved.getTone());
    }

    @Test
    @DisplayName("findBySpotifyLink return song when successful")
    public void findBySpotifyLink_returnSong_whenSuccessful() {
        Song songToBeSaved = SongCreator.createToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        Song songFound = songRepository.findBySpotifyLink(songSaved.getSpotifyLink());

        Assertions.assertThat(songFound).isNotNull();
        Assertions.assertThat(songFound.getId()).isEqualTo(songSaved.getId());
        Assertions.assertThat(songFound.getArtist()).isEqualTo(songSaved.getArtist());
        Assertions.assertThat(songFound.getName()).isEqualTo(songSaved.getName());
        Assertions.assertThat(songFound.getSpotifyLink()).isEqualTo(songSaved.getSpotifyLink());
        Assertions.assertThat(songFound.getYoutubeLink()).isEqualTo(songSaved.getYoutubeLink());
        Assertions.assertThat(songFound.getTone()).isEqualTo(songSaved.getTone());
    }
}
