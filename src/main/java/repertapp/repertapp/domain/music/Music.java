package repertapp.repertapp.domain.music;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.tag.Tag;
import repertapp.repertapp.domain.version.Version;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "music", uniqueConstraints = {
        @UniqueConstraint(name = "music_artist_name_unique", columnNames = { "ARTIST", "NAME" }),
        @UniqueConstraint(name = "music_youtube_link_unique", columnNames = "youtube_link"),
        @UniqueConstraint(name = "music_spotify_link_unique", columnNames = "spotify_link") })
public class Music {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank @Size(min = 2, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank @Size(min = 2, max = 255)
    @Column(name = "artist", nullable = false)
    private String artist;

    @URL
    @Column(name = "youtube_link")
    private String youtubeLink;

    @URL
    @Column(name = "spotify_link")
    private String spotifyLink;

    @Enumerated(EnumType.STRING) @NotNull
    @Column(name = "tone", columnDefinition = "VARCHAR(5)", nullable = false)
    private Tone tone;

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
    @JoinColumn(name = "band_id", nullable = false)
    private Band band;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "music_tag",
    joinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
    uniqueConstraints = @UniqueConstraint(name = "music_tag_unique", columnNames = {"music_id", "tag_id"}))
    private List<Tag> tags = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "music", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Version> versions = new ArrayList<>();
}
