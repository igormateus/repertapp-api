package repertapp.repertapp.payload;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TagPutRequestBody {
    @NotNull
    private Long id;
    
    @NotNull @Size(min = 2, max = 255)
    private String name;
}
