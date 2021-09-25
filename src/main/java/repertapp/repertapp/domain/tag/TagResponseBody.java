package repertapp.repertapp.domain.tag;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Value;
import repertapp.repertapp.api.view.View;

@Value
public class TagResponseBody {
    @JsonView(View.Resume.class)
    private Long id;

    @JsonView(View.Resume.class)
    private String name;
}
