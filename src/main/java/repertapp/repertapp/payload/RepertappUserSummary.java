package repertapp.repertapp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RepertappUserSummary {
    private Long id;
    private String name;
    private String email;
    private String username;
}
