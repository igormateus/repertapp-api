package repertapp.repertapp.domain.version;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.music.Music;
import repertapp.repertapp.domain.setlist.Setlist;
import repertapp.repertapp.domain.user.RepertappUser;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "version", uniqueConstraints = {
    @UniqueConstraint(name = "version_tone_user_music_unique", 
            columnNames = { "tone", "repertapp_user_id", "music_id" }) })
public class Version {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tone", columnDefinition = "VARCHAR(5)", nullable = false)
    private Tone tone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "repertapp_user_id")
    private RepertappUser repertappUser;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "music_id")
    private Music music;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "band_id")
    private Band band;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "setlist_version",
        joinColumns = @JoinColumn(name = "version_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "setlist_id", referencedColumnName = "id"),
        uniqueConstraints = @UniqueConstraint(name = "setlist_version_unique", columnNames = {"version_id", "setlist_id"}))
    private List<Setlist> setlists = new ArrayList<>();

}
