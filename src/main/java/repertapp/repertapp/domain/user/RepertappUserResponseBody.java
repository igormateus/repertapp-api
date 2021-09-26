package repertapp.repertapp.domain.user;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Value;
import repertapp.repertapp.api.view.View;

@Value
public class RepertappUserResponseBody {
    @JsonView(View.Summary.class)
    Long id;

    @JsonView(View.Summary.class)
    String name;

    @JsonView(View.Summary.class)
    String email;

    @JsonView(View.Summary.class)
    String username;
}
