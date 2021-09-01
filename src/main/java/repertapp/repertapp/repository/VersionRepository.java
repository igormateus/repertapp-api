package repertapp.repertapp.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Tone;
import repertapp.repertapp.domain.Version;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long>{
    Boolean existsByToneAndRepertappUserAndMusic(@NotNull Tone tone, @NotNull RepertappUser repertappUser, @NotNull Music music);
}
