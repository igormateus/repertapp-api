package repertapp.repertapp.repository;

import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests For Song Repository")
public class SongRepositoryTest {

    // @Autowired
    // SongRepository songRepository;
    
    // @Test
    // @DisplayName("save persists tags when Successful")
    // public void givenToneName_ThenNameChangeToEnum_WhenSaving() {

    //     Song song = SongCreator.createToBeSaved();

    //     Tone tone = Tone.Fsusm;

    //     song.setTone(tone);

    //     songRepository.save(song);

    //     System.out.println(song);
    // }

}
