package repertapp.repertapp.payload;

import lombok.Data;
import repertapp.repertapp.domain.Tone;

@Data
public class SongResponse {
    private Long id;
    private String name;
    private String artist;
    private String youtubeLink;
    private String spotifyLink;
    private int counterPlays;
    private int score;
    private Tone tone;
}
