package repertapp.repertapp.domain.song;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import repertapp.repertapp.api.view.View;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.tag.TagResponseBody;

public class SongResponseBody {
    @JsonView(View.Summary.class)
    private Long id;

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

    @JsonView(View.Complete.class)
    private List<TagResponseBody> tags;
}
