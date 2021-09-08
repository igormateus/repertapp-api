package repertapp.repertapp.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Setlist;

@Data
public class BandPutRequestBody {
    @NotNull
    private Long id;

    @NotBlank @Size(min = 3, max = 255)
    private String name;

    private List<Music> musics;
    
    private List<RepertappUser> members;

    private List<Setlist> setlists;
}
