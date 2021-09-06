package repertapp.repertapp.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.util.MusicCreator;

@DataJpaTest
@DisplayName("Tests For Music Repository")
public class MusicRepositoryTest {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private BandRepository bandRepository;
    
    @Autowired
    private RepertappUserRepository userRepository;

    private Music setUp() {
        Music music = MusicCreator.createToBeSaved();
        userRepository.save(music.getBand().getMembers().get(0));
        songRepository.save(music.getSong());
        bandRepository.save(music.getBand());
        
        return music;
    }
    
    @Test
    @DisplayName("save persists music when successful")
    void save_persistsMusic_whenSuccessful() {
        Music music = setUp();

        Music musicSaved = musicRepository.save(music);

        Assertions.assertThat(musicSaved).isNotNull();
        Assertions.assertThat(musicSaved.getId()).isNotNull();
        Assertions.assertThat(musicSaved).isEqualTo(music);
    }

    @Test
    @DisplayName("save update music when successful")
    void save_updateMusic_whenSuccessful() {
        Music music = setUp();
        Music musicSaved = musicRepository.save(music);

        musicSaved.setKnown(false);
        Music musicUpdated = musicRepository.save(musicSaved);

        Assertions.assertThat(musicUpdated)
                .isNotNull()
                .isEqualTo(musicSaved);
    }

    @Test
    @DisplayName("delete remove music and maintain song and band when successful")
    void delete_removeMusicAndMaintainSongAndBand_whenSuccessful() {
        Music music = setUp();
        Music musicSaved = musicRepository.save(music);
        Song song = musicSaved.getSong();
        Band band = musicSaved.getBand();

        musicRepository.delete(musicSaved);
        Optional<Music> musicOptional = musicRepository.findById(musicSaved.getId());
        Optional<Song> songOptional = songRepository.findById(song.getId());
        Optional<Band> bandOptional = bandRepository.findById(band.getId());

        Assertions.assertThat(musicOptional).isEmpty();
        Assertions.assertThat(songOptional.get()).isEqualTo(song);
        Assertions.assertThat(bandOptional.get()).isEqualTo(band);
    }


    @Test
    void existsByBandAndSong_returnTrueIfBandAndSongIsFound_whenSuccessful() {
        Music music = setUp();
        Music musicSaved = musicRepository.save(music);
        Song song = musicSaved.getSong();
        Band band = musicSaved.getBand();

        Boolean result = musicRepository.existsByBandAndSong(band, song);

        Assertions.assertThat(result).isTrue();
    }
}
