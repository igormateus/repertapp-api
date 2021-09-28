package repertapp.repertapp.domain.music;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.Data;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.tag.Tag;

@Data
public class MusicPostRequestBody {
    @NotBlank @Size(min = 2, max = 255)
    private String name;
    
    @NotBlank @Size(min = 2, max = 255)
    private String artist;

    @URL
    private String youtubeLink;

    @URL
    private String spotifyLink;

    @NotNull
    private Tone tone;

    private boolean isKnown;

    private Band band;

    private List<Tag> tags;
}
