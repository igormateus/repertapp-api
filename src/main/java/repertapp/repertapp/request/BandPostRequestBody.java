package repertapp.repertapp.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import repertapp.repertapp.domain.RepertappUser;

@Data
public class BandPostRequestBody {
    @NotBlank @Size(min = 3, max = 255)
    private String name;

    @NotNull
    private List<RepertappUser> members;
}
