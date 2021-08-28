package repertapp.repertapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "user", uniqueConstraints = {
    @UniqueConstraint(name = "user_username_unique", columnNames = "username"),
    @UniqueConstraint(name = "user_email_unique", columnNames = "email")})
public class RepertappUser {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank @Size(min = 3, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank @Email
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank @Size(min = 3)
    @Column(name = "username", nullable = false)
    private String username;

    // @Column(name = "password", nullable = false)
    // private String password;
    
}
