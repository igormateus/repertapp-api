package repertapp.repertapp.domain.band;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Value;
import repertapp.repertapp.api.view.View;
import repertapp.repertapp.domain.user.RepertappUserResponseBody;

@Value
public class BandResponseBody {
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String name;

    @JsonView(View.Summary.class)
    private String description;

    @JsonView(View.Complete.class)
    private List<RepertappUserResponseBody> members;
}
