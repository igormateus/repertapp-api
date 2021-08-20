package repertapp.repertapp.payload;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Data;
import repertapp.repertapp.domain.Tone;

@Data
public class SongRequest {
    
    @NotBlank(message = "Name not must be blank")
    private String name;

    @NotBlank(message = "Artist not must be blank")
    private String artist;

    @URL
    private String youtubeLink;

    @URL
    private String spotifyLink;
    
    @Enumerated(EnumType.STRING)
    private Tone tone;

}
