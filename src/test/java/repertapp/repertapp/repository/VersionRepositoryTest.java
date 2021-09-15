package repertapp.repertapp.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.band.BandRepository;
import repertapp.repertapp.domain.music.Music;
import repertapp.repertapp.domain.music.MusicRepository;
import repertapp.repertapp.domain.song.Song;
import repertapp.repertapp.domain.song.SongRepository;
import repertapp.repertapp.domain.user.RepertappUserRepository;
import repertapp.repertapp.domain.version.Version;
import repertapp.repertapp.domain.version.VersionRepository;
import repertapp.repertapp.util.BandCreator;
import repertapp.repertapp.util.MusicCreator;
import repertapp.repertapp.util.RepertappUserCreator;
import repertapp.repertapp.util.SongCreator;
import repertapp.repertapp.util.VersionCreator;

@DataJpaTest
@DisplayName("Tests for Version Repository")
public class VersionRepositoryTest {

    @Autowired
    private VersionRepository versionRepository;

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private RepertappUserRepository userRepository;

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private SongRepository songRepository;

    private Version setUp() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);
        ArrayList<RepertappUser> users = new ArrayList<>();
        users.add(userSaved);
        
        Song song = SongCreator.createToBeSaved();
        Song songSaved = songRepository.save(song);

        Band band = BandCreator.createToBeSaved();
        band.setMembers(users);
        Band bandSaved = bandRepository.save(band);

        Music music = MusicCreator.createToBeSaved();
        music.setBand(bandSaved);
        music.setSong(songSaved);
        Music musicSaved = musicRepository.save(music);

        Version version = VersionCreator.createToBeSaved();
        version.setMusic(musicSaved);
        version.setRepertappUser(userSaved);
        Version versionSaved = versionRepository.save(version);

        return versionSaved;
    }

    @Test
    @DisplayName("save persists version when successful")
    void save_persistVersion_whenSuccessful() {
        Version version = setUp();

        Version versionSaved = versionRepository.save(version);

        Assertions.assertThat(versionSaved).isNotNull();
        Assertions.assertThat(versionSaved.getId()).isNotNull();
        Assertions.assertThat(versionSaved.getRepertappUser()).isEqualTo(version.getRepertappUser());
        Assertions.assertThat(versionSaved.getMusic().getSong()).isEqualTo(version.getMusic().getSong());
        Assertions.assertThat(versionSaved.getMusic().getBand()).isEqualTo(version.getMusic().getBand());
        Assertions.assertThat(versionSaved.getMusic()).isEqualTo(version.getMusic());
    }

    @Test
    @DisplayName("save update version when successful")
    void save_updateVersion_whenSuccessful() {
        Version version = setUp();
        Version versionSaved = versionRepository.save(version);

        versionSaved.setTone(Tone.B);
        Version versionUpdated = versionRepository.save(versionSaved);

        Assertions.assertThat(versionUpdated)
                .isNotNull()
                .isEqualTo(versionSaved);
    }

    @Test
    @DisplayName("delete remove band and maintain user, song, band and music when successful")
    void delete_removeBandAndMaintainUserSongBandAndMusic_whenSuccessful() {
        Version version = setUp();
        Version versionSaved = versionRepository.save(version);
        RepertappUser user = version.getRepertappUser();
        Song song =  version.getMusic().getSong();
        Band band = version.getMusic().getBand();
        Music music =  version.getMusic();

        versionRepository.delete(versionSaved);
        Optional<Version> versionOptional = versionRepository.findById(versionSaved.getId());
        Optional<RepertappUser> userOptional = userRepository.findById(versionSaved.getRepertappUser().getId());
        Optional<Song> songOptional = songRepository.findById(versionSaved.getMusic().getSong().getId());
        Optional<Band> bandOptional = bandRepository.findById(versionSaved.getMusic().getBand().getId());
        Optional<Music> musicOptional = musicRepository.findById(versionSaved.getMusic().getId());

        Assertions.assertThat(versionOptional).isEmpty();
        Assertions.assertThat(userOptional.get()).isEqualTo(user);
        Assertions.assertThat(songOptional.get()).isEqualTo(song);
        Assertions.assertThat(bandOptional.get()).isEqualTo(band);
        Assertions.assertThat(musicOptional.get()).isEqualTo(music);
    }

    @Test
    @DisplayName("existsByToneAndRepertappUserAndMusic return true when successful")
    void existsByToneAndRepertappUserAndMusic_returnTrue_whenSuccessful() {
        Version version = setUp();

        Boolean result = versionRepository.existsByToneAndRepertappUserAndMusic(
            version.getTone(), version.getRepertappUser(), version.getMusic());

        Assertions.assertThat(result).isTrue();
    }
}
