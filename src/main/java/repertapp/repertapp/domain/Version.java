package repertapp.repertapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "version", uniqueConstraints = {
    @UniqueConstraint(name = "version_tone_user_song_unique", 
            columnNames = { "tone", "repertapp_user_id", "song_id" }) })
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
    @JoinColumn(name = "song_id")
    private Song song;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "setlist_version",
        joinColumns = @JoinColumn(name = "version_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "setlist_id", referencedColumnName = "id")
    )
    private List<Setlist> setlists = new ArrayList<>();

}
