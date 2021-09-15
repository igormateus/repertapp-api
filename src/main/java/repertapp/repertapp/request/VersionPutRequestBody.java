package repertapp.repertapp.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Tone;

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
