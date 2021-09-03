package repertapp.repertapp.repository;

import java.util.ArrayList;
import java.util.List;
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
import repertapp.repertapp.util.RepertappUserCreator;

@DataJpaTest
@DisplayName("Tests For Band Repository")
public class BandRepositoryTest {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private RepertappUserRepository userRepository;

    @Test
    @DisplayName("save persists band when successful")
    public void save_persistBand_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);
        ArrayList<RepertappUser> users = new ArrayList<>();
        users.add(userSaved);

        Band band = BandCreator.createToBeSaved(List.of(userSaved));
        Band bandSaved = bandRepository.save(band);

        Assertions.assertThat(bandSaved).isNotNull();
        Assertions.assertThat(bandSaved.getId()).isNotNull();
        Assertions.assertThat(bandSaved.getName()).isEqualTo(band.getName());
        Assertions.assertThat(bandSaved.getMembers()).isEqualTo(band.getMembers());
    }

    @Test
    @DisplayName("save update band when successful")
    public void save_updateBand_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);
        ArrayList<RepertappUser> users = new ArrayList<>();
        users.add(userSaved);

        Band band = BandCreator.createToBeSaved(users);
        Band bandSaved = bandRepository.save(band);

        bandSaved.setName("Test_name_2");

        Band bandUpdated = bandRepository.save(bandSaved);

        Assertions.assertThat(bandUpdated).isNotNull();
        Assertions.assertThat(bandUpdated).isEqualTo(bandSaved);
    }

    @Test
    @DisplayName("delete remove band when successful")
    public void delete_removeBand_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);
        ArrayList<RepertappUser> users = new ArrayList<>();
        users.add(userSaved);

        Band band = BandCreator.createToBeSaved(users);
        Band bandSaved = bandRepository.save(band);

        bandRepository.delete(bandSaved);

        Optional<Band> bandOptional = bandRepository.findById(bandSaved.getId());

        Assertions.assertThat(bandOptional).isEmpty();
    }

    @Test
    @DisplayName("findByNameLike return a list of band inside page object when successful")
    public void findByNameLike_listOfBandInsidePageObject_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);
        ArrayList<RepertappUser> users = new ArrayList<>();
        users.add(userSaved);

        Band band = BandCreator.createToBeSaved(users);
        Band bandSaved = bandRepository.save(band);

        Page<Band> bandPage = bandRepository.findByNameLike(bandSaved.getName(), null);

        Assertions.assertThat(bandPage.get()).isNotNull().hasSize(1);
        Assertions.assertThat(bandPage.toList().get(0)).isEqualTo(bandSaved);
    }

    @Test
    void existsByName_returnTrueForBandFound_whenSuccessful() {
        
    }
}
