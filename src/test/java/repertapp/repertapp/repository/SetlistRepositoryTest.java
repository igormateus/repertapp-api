package repertapp.repertapp.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Setlist;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Version;
import repertapp.repertapp.util.BandCreator;
import repertapp.repertapp.util.MusicCreator;
import repertapp.repertapp.util.RepertappUserCreator;
import repertapp.repertapp.util.SetlistCreator;
import repertapp.repertapp.util.SongCreator;
import repertapp.repertapp.util.VersionCreator;

@DataJpaTest
@DisplayName("Tests for Version Repository")
public class SetlistRepositoryTest {
    
    @Autowired
    private SetlistRepository setlistRepository;
    
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

    private Setlist setUp() {
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
        ArrayList<Version> versions = new ArrayList<>();
        versions.add(versionSaved);

        Setlist setlist = SetlistCreator.createToBeSaved();
        setlist.setBand(bandSaved);
        setlist.setVersions(versions);
        
        return setlist;
    }

    @Test
    @DisplayName("save persists setlist when successful")
    void save_persistSetlist_whenSuccessful() {
        Setlist setlist = setUp();

        Setlist setlistSaved = setlistRepository.save(setlist);

        Assertions.assertThat(setlistSaved).isNotNull();
        Assertions.assertThat(setlistSaved.getId()).isNotNull();
        Assertions.assertThat(setlistSaved.getBand()).isEqualTo(setlist.getBand());
        Assertions.assertThat(setlistSaved.getVersions()).isEqualTo(setlist.getVersions());
    }

    @Test
    @DisplayName("save update setlist when successful")
    void save_updateSetlist_whenSuccessful() {
        Setlist setlist = setUp();
        Setlist setlistSaved = setlistRepository.save(setlist);

        setlistSaved.setName("Other Name");
        Setlist setlistUpdated = setlistRepository.save(setlistSaved);

        Assertions.assertThat(setlistUpdated)
                .isNotNull()
                .isEqualTo(setlistSaved);
    }

    @Test
    @DisplayName("delete remove setlist maintaining band and version when successful")
    void delete_removeBandAndMaintainUserSongBandAndMusic_whenSuccessful() {
        Setlist setlist = setUp();
        Setlist setlistSaved = setlistRepository.save(setlist);
        Band band = setlistSaved.getBand();
        Version version =  setlistSaved.getVersions().get(0);

        setlistRepository.delete(setlistSaved);
        List<Setlist> setlistOptional = setlistRepository.findAll();
        Optional<Version> versionOptional = versionRepository.findById(version.getId());
        Optional<Band> bandOptional = bandRepository.findById(band.getId());

        Assertions.assertThat(setlistOptional).isEmpty();
        Assertions.assertThat(bandOptional.get()).isEqualTo(band);
        Assertions.assertThat(versionOptional.get()).isEqualTo(version);
    }
}
