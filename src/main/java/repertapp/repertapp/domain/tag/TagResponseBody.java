package repertapp.repertapp.domain.tag;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Value;
import repertapp.repertapp.api.view.View;

@Value
public class TagResponseBody {
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    private String name;
}
