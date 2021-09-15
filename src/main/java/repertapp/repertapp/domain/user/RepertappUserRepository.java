package repertapp.repertapp.domain.user;

import java.util.Optional;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.band.Band;

@Repository
public interface RepertappUserRepository extends JpaRepository<RepertappUser, Long> {
    Optional<RepertappUser> findByUsername(@NotBlank String username);
    Optional<RepertappUser> findByEmail(@NotBlank @Email String email);

    Boolean existsByName(@NotBlank String name);
    Boolean existsByUsername(@NotBlank String username);
    Boolean existsByEmail(@NotBlank @Email String email);

    Optional<RepertappUser> findByUsernameOrEmail(String username, String email);

    Page<RepertappUser> findByBands(@NotNull Band band, Pageable pageable);
}
