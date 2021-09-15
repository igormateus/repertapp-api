package repertapp.repertapp.domain.setlist;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.version.Version;

@Data
public class SetlistPutRequestBody {
    @NotNull
    private Long id;

    @Size(min = 3, max = 255)
    private String name;

    @NotNull
    private LocalDate eventDate;

    @NotNull
    private Boolean isDone;

    private List<Version> versions;

    @NotNull
    private Band band;
}
