package repertapp.repertapp.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.util.SongCreator;

@DataJpaTest
@DisplayName("Testes for Song Repository")
public class SongRepositoryTest {

    @Autowired
    private SongRepository songRepository;

    @Test
    @DisplayName("save persists song when successful")
    public void save_persistSong_whenSuccessful() {
        Song songToBeSaved = SongCreator.createSongToBeSaved();

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
        Song songToBeSaved = SongCreator.createSongToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        songSaved.setName("Song_test_2");

        Song songUpdated = songRepository.save(songSaved);

        Assertions.assertThat(songUpdated).isNotNull();
        Assertions.assertThat(songUpdated.getId()).isNotNull();
        Assertions.assertThat(songUpdated.getArtist()).isEqualTo(songSaved.getArtist());
        Assertions.assertThat(songUpdated.getName()).isEqualTo(songSaved.getName());
        Assertions.assertThat(songUpdated.getSpotifyLink()).isEqualTo(songSaved.getSpotifyLink());
        Assertions.assertThat(songUpdated.getYoutubeLink()).isEqualTo(songSaved.getYoutubeLink());
        Assertions.assertThat(songUpdated.getTone()).isEqualTo(songSaved.getTone());
    }

    @Test
    @DisplayName("delete removes song when sucessful")
    public void delete_removeSong_whenSucessful() {
        Song songToBeSaved = SongCreator.createSongToBeSaved();

        Song songSaved = songRepository.save(songToBeSaved);

        songRepository.delete(songSaved);

        Optional<Song> songOptional = songRepository.findById(songSaved.getId());

        Assertions.assertThat(songOptional).isEmpty();
    }

}
