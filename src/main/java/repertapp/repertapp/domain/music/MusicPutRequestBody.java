package repertapp.repertapp.domain.music;

import javax.validation.constraints.NotNull;

import lombok.Data;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.song.Song;

@Data
public class MusicPutRequestBody {
    @NotNull
    private long id;

    @NotNull
    private boolean isKnown;

    @NotNull
    private int score;

    @NotNull
    private int counterPlay;

    @NotNull
    private Band band;
    
    @NotNull
    private Song song;
}
