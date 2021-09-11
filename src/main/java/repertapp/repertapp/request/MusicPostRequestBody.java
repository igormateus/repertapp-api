package repertapp.repertapp.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Song;

@Data
public class MusicPostRequestBody {
    private boolean isKnown;
    
    private Band band;

    @NotNull
    private Song song;
}
