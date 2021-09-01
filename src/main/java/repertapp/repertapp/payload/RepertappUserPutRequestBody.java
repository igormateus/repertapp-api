package repertapp.repertapp.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RepertappUserPutRequestBody {
    @NotNull
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
