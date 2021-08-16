package repertapp.repertapp.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tone;
import repertapp.repertapp.util.SongCreator;

@DataJpaTest
@DisplayName("Tests For Song Repository")
public class SongRepositoryTest {

    @Autowired
    SongRepository songRepository;
    
    @Test
    @DisplayName("save persists tags when Successful")
    public void givenToneName_ThenNameChangeToEnum_WhenSaving() {

        Song song = SongCreator.createToBeSaved();

        Tone tone = Tone.Fsusm;

        song.setTone(tone);

        songRepository.save(song);

        System.out.println(song);
    }

}
