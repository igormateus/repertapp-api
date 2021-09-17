package repertapp.repertapp.domain.music;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.song.Song;
import repertapp.repertapp.domain.version.Version;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "music", uniqueConstraints = {
        @UniqueConstraint(name = "music_band_song_unique", columnNames = { "band_id", "song_id" }) })
public class Music {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "is_known", columnDefinition = "BOOLEAN DEFAULT FALSE NOT NULL")
    private boolean isKnown;

    @NotNull
    @Column(name = "score", columnDefinition = "INTEGER DEFAULT 1000 NOT NULL")
    private int score;

    @Column(name = "counter_play", columnDefinition = "INTEGER DEFAULT 0 NOT NULL")
    private int counterPlay;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @JsonIgnore
    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Version> versions = new ArrayList<>();

}
