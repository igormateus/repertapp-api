package repertapp.repertapp.domain.band;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.music.Music;
import repertapp.repertapp.domain.setlist.Setlist;

@Data
public class BandPutRequestBody {
    @NotNull
    private Long id;

    @NotBlank @Size(min = 3, max = 255)
    private String name;

    @NotBlank @Size(min = 3, max = 255)
    private String description;

    private List<Music> musics;
    
    private List<RepertappUser> members;

    private List<Setlist> setlists;
}
