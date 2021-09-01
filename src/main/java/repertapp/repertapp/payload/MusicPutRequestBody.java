package repertapp.repertapp.payload;

import javax.validation.constraints.NotNull;

import lombok.Data;
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Song;

@Data
public class MusicPutRequestBody {
    @NotNull
    private long id;

    @NotNull
    private boolean isKnown;

    @NotNull
    private int score;

    @NotNull
    private Band band;
    
    @NotNull
    private Song song;
}
