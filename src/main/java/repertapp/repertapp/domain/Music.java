package repertapp.repertapp.domain;

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
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import repertapp.repertapp.response.View;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "music", uniqueConstraints = {
        @UniqueConstraint(name = "music_band_song_unique", columnNames = { "band_id", "song_id" }) })
public class Music {

    @JsonView(View.Resume.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonView(View.Resume.class)
    @NotNull
    @Column(name = "is_known", columnDefinition = "BOOLEAN DEFAULT FALSE NOT NULL")
    private boolean isKnown;

    @JsonView(View.Resume.class)
    @NotNull
    @Column(name = "score", columnDefinition = "INTEGER DEFAULT 1000 NOT NULL")
    private int score;

    @JsonView(View.Resume.class)
    @Column(name = "counter_plays", columnDefinition = "INTEGER DEFAULT 0 NOT NULL")
    private int counterPlays;
    
    @JsonView(View.Resume.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "song_id", nullable = false)
    private Song song;

    @JsonView(View.Resume.class)
    @NotNull
    @ManyToOne
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @JsonIgnore
    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Version> versions = new ArrayList<>();

}
