package repertapp.repertapp.domain.version;

import javax.validation.constraints.NotNull;

import lombok.Data;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.music.Music;

@Data
public class VersionPutRequestBody {
    @NotNull
    private Long id;

    @NotNull
    private Tone tone;

    @NotNull
    private RepertappUser repertappUser;

    @NotNull
    private Music music;

    private Band band;
}
