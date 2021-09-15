package repertapp.repertapp.domain.band;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BandPostRequestBody {
    @NotBlank @Size(min = 3, max = 255)
    private String name;
}
