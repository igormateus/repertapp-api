package repertapp.repertapp.requests;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SongPostRequestBody {

    @NotEmpty(message = "The song name cannot be empty")
    private String name;

    private String artist;

    private String key;

    private String link;
}
