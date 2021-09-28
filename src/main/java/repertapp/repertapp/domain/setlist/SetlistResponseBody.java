package repertapp.repertapp.domain.setlist;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Value;
import repertapp.repertapp.api.view.View;
import repertapp.repertapp.domain.band.BandResponseBody;
import repertapp.repertapp.domain.version.VersionResponseBody;

@Value
public class SetlistResponseBody {
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private LocalDate eventDate;

    @JsonView(View.Summary.class)
    private Boolean isDone;

    @JsonView(View.Complete.class)
    private List<VersionResponseBody> versions;

    @JsonView(View.Complete.class)
    private BandResponseBody band;
}
