package repertapp.repertapp.request;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Version;

@Data
public class SetlistPostRequestBody {
    
    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    private LocalDate eventDate;

    private Boolean isDone;

    private Band band;
    
    private List<Version> versions;
}
