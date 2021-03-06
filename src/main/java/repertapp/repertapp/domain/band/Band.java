package repertapp.repertapp.domain.band;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.music.Music;
import repertapp.repertapp.domain.setlist.Setlist;
import repertapp.repertapp.domain.version.Version;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "band", uniqueConstraints = {
        @UniqueConstraint(name = "band_name_unique", columnNames = "name") })
public class Band {
    
    @EqualsAndHashCode.Include
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Size(min = 3, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank @Size(min = 3, max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "band_repertapp_user",
            joinColumns = @JoinColumn(name = "band_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "repertapp_user_id", referencedColumnName = "id"),
            uniqueConstraints = @UniqueConstraint(name = "band_repertapp_user_unique", columnNames = {"band_id", "repertapp_user_id"}))
    private List<RepertappUser> members = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Music> musics = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Setlist> setlists = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "band", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Version> versions = new ArrayList<>();
}
