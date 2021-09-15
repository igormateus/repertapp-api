package repertapp.repertapp.domain.music;

import javax.validation.constraints.NotNull;

import lombok.Data;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.song.Song;

@Data
public class MusicPostRequestBody {
    private boolean isKnown;
    
    private Band band;

    @NotNull
    private Song song;
}
