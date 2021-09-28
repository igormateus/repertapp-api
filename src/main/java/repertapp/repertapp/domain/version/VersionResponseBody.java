package repertapp.repertapp.domain.version;


import com.fasterxml.jackson.annotation.JsonView;

import lombok.Value;
import repertapp.repertapp.api.view.View;
import repertapp.repertapp.domain.band.BandResponseBody;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.music.MusicResponseBody;
import repertapp.repertapp.domain.user.RepertappUserResponseBody;

@Value
public class VersionResponseBody {
    @JsonView(View.Summary.class)
    private long id;

    @JsonView(View.Summary.class)
    private Tone tone;

    @JsonView(View.Summary.class)
    private RepertappUserResponseBody repertappUser;

    @JsonView(View.Summary.class)
    private MusicResponseBody music;

    @JsonView(View.Complete.class)
    private BandResponseBody band;
}
