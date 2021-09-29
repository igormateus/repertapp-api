package repertapp.repertapp.domain.music;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Value;
import repertapp.repertapp.api.view.View;
import repertapp.repertapp.domain.band.BandResponseBody;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.tag.TagResponseBody;

@Value
public class MusicResponseBody {
    @JsonView(View.Summary.class)
    private long id;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private String artist;

    @JsonView(View.Summary.class)
    private String youtubeLink;

    @JsonView(View.Summary.class)
    private String spotifyLink;

    @JsonView(View.Summary.class)
    private Tone tone;

    @JsonView(View.Summary.class)
    private boolean known;

    @JsonView(View.Summary.class)
    private int score;

    @JsonView(View.Summary.class)
    private int counterPlay;

    @JsonView(View.Complete.class)
    private BandResponseBody band;

    @JsonView(View.Complete.class)
    private List<TagResponseBody> tags;
}
