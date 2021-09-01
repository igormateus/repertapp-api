package repertapp.repertapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @Column(name = "known", columnDefinition = "BOOLEAN DEFAULT FALSE NOT NULL")
    private boolean known;

    @NotNull
    @Column(name = "score", columnDefinition = "INTEGER DEFAULT 1000 NOT NULL")
    private int score;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

}
