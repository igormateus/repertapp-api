package repertapp.repertapp.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.Data;
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.domain.Tone;

@Data
public class SongPutRequestBody {
    @NotNull
    private Long id;

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

    private List<Tag> tags;
}
