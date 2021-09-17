package repertapp.repertapp.domain.user;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Value;
import repertapp.repertapp.api.view.View;

@Value
public class RepertappUserResponseBody {
    @JsonView(View.Resume.class)
    Long id;

    @JsonView(View.Resume.class)
    String name;

    @JsonView(View.Resume.class)
    String email;

    @JsonView(View.Resume.class)
    String username;
}
