package repertapp.repertapp.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TagPostRequestBody {
    @NotNull @Size(min = 2, max = 255)
    private String name;
}
