package repertapp.repertapp.payload;

import javax.validation.constraints.NotNull;

import lombok.Data;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tone;

@Data
public class VersionPostRequestBody {
    @NotNull
    private Tone tone;

    @NotNull
    private RepertappUser repertappUser;

    @NotNull
    private Song song;
}