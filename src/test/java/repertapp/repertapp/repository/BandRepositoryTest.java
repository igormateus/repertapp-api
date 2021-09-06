package repertapp.repertapp.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.util.BandCreator;

@DataJpaTest
@DisplayName("Tests For Band Repository")
public class BandRepositoryTest {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private RepertappUserRepository userRepository;

    private Band setUp() {
        Band band = BandCreator.createToBeSaved();
        userRepository.save(band.getMembers().get(0));

        return band;
    }

    @Test
    @DisplayName("save persists band when successful")
    void save_persistBand_whenSuccessful() {
        Band band = setUp();

        Band bandSaved = bandRepository.save(band);

        Assertions.assertThat(bandSaved).isNotNull();
        Assertions.assertThat(bandSaved.getId()).isNotNull();
        Assertions.assertThat(bandSaved.getName()).isEqualTo(band.getName());
        Assertions.assertThat(bandSaved.getMembers()).isEqualTo(band.getMembers());
    }

    @Test
    @DisplayName("save update band when successful")
    void save_updateBand_whenSuccessful() {
        Band band = setUp();
        Band bandSaved = bandRepository.save(band);

        bandSaved.setName("Test_name_2");
        Band bandUpdated = bandRepository.save(bandSaved);

        Assertions.assertThat(bandUpdated)
                .isNotNull()
                .isEqualTo(bandSaved);
    }

    @Test
    @DisplayName("delete remove band and maintain user when successful")
    void delete_removeBandAndMaintainUser_whenSuccessful() {
        Band band = setUp();
        Band bandSaved = bandRepository.save(band);
        RepertappUser user = bandSaved.getMembers().get(0);

        bandRepository.delete(bandSaved);
        Optional<Band> bandOptional = bandRepository.findById(bandSaved.getId());
        Optional<RepertappUser> userOptional = userRepository.findById(user.getId());

        Assertions.assertThat(bandOptional).isEmpty();
        Assertions.assertThat(userOptional.get()).isEqualTo(user);
    }

    @Test
    @DisplayName("findByNameLike return a list of band inside page object when successful")
    void findByNameLike_listOfBandInsidePageObject_whenSuccessful() {
        Band band = setUp();
        Band bandSaved = bandRepository.save(band);

        Page<Band> bandPage = bandRepository.findByNameLike(bandSaved.getName(), null);


        Assertions.assertThat(bandPage).isNotNull();
        Assertions.assertThat(bandPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(bandPage.toList().get(0)).isEqualTo(bandSaved);
    }

    @Test
    @DisplayName("existsByName return true if band is found when successful")
    void existsByName_returnTrueIfBandIsFound_whenSuccessful() {
        Band band = setUp();
        Band bandSaved = bandRepository.save(band);

        Boolean found = bandRepository.existsByName(bandSaved.getName());

        Assertions.assertThat(found).isTrue();
    }
}
