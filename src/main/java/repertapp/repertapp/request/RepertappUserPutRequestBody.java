package repertapp.repertapp.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RepertappUserPutRequestBody {
    private Long id;

    @NotBlank @Size(min = 3, max = 255)
    private String name;

    @NotBlank @Email
    private String email;

    @NotBlank @Size(min = 3)
    private String username;

    @NotBlank @Size(min = 3, max = 100)
    private String password;
}
