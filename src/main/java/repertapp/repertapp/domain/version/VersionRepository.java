package repertapp.repertapp.domain.version;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.music.Music;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long>{
    Boolean existsByToneAndRepertappUserAndMusic(@NotNull Tone tone, @NotNull RepertappUser repertappUser, @NotNull Music music);

    Page<Version> findByBand(Band band, Pageable pageable);
}
