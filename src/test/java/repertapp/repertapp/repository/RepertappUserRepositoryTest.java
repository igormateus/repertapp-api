package repertapp.repertapp.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.user.RepertappUserRepository;
import repertapp.repertapp.util.RepertappUserCreator;

@DataJpaTest
@DisplayName("Tests For RerpertappUser Repository")
public class RepertappUserRepositoryTest {

    @Autowired
    private RepertappUserRepository userRepository;

    @Test
    @DisplayName("save persists user when successful")
    void save_persistsUser_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();

        RepertappUser userSaved = userRepository.save(user);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getId()).isNotNull();
        Assertions.assertThat(userSaved).isEqualTo(user);
    }

    @Test
    @DisplayName("save update user when successful")
    void save_updateUser_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);

        userSaved.setUsername("new_user_name_test");
        RepertappUser userUpdated = userRepository.save(userSaved);

        Assertions.assertThat(userUpdated)
                .isNotNull()
                .isEqualTo(userSaved);
    }

    @Test
    @DisplayName("delete remove user when successful")
    void delete_removeUser_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);

        userRepository.delete(userSaved);
        Optional<RepertappUser> userOptional = userRepository.findById(userSaved.getId());

        Assertions.assertThat(userOptional).isEmpty();
    }

    @Test
    @DisplayName("existsByEmail return true if email is found when successful")
    void existsByEmail_returnTrueIfEmailIsFound_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);

        Boolean result = userRepository.existsByEmail(userSaved.getEmail());

        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("existsByName return true if name is found when successful")
    void existsByName_returnTrueIfNameIsFound_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);

        Boolean result = userRepository.existsByName(userSaved.getName());

        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("existsByUsername return true if username is found when successful")
    void existsByUsername_returnTrueIfUsernameIsFound_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);

        Boolean result = userRepository.existsByUsername(userSaved.getUsername());

        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("findByEmail returns user when successful")
    void findByEmail_resturnsUser_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);

        Optional<RepertappUser> userOptional = userRepository.findByEmail(userSaved.getEmail());

        Assertions.assertThat(userOptional.get())
                .isNotNull()
                .isEqualTo(userSaved);
    }

    @Test
    @DisplayName("findByUsername returns user when successful")
    void findByUsername_resturnsUser_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);

        Optional<RepertappUser> userOptional = userRepository.findByUsername(userSaved.getUsername());

        Assertions.assertThat(userOptional.get())
                .isNotNull()
                .isEqualTo(userSaved);
    }

    @Test
    @DisplayName("findByUsernameOrEmail returns user when successful")
    void findByUsernameOrEmail_resturnsUser_whenSuccessful() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        RepertappUser userSaved = userRepository.save(user);

        Optional<RepertappUser> userOptional = userRepository.findByUsernameOrEmail(
            userSaved.getUsername(), userSaved.getEmail());

        Assertions.assertThat(userOptional.get())
            .isNotNull()
            .isEqualTo(userSaved);
    }
}
