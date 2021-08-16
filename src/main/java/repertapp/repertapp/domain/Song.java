package repertapp.repertapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "SONG",
    uniqueConstraints = {
        @UniqueConstraint(name = "UN_ARTIST_NAME", columnNames = { "ARTIST", "NAME" })
    }

)
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "ARTIST", nullable = false)
    private String artist;

    @Enumerated(EnumType.STRING)
    @Column(name = "TONE", nullable = false)
    private Tone tone;

    @Column(name = "YOUTUBE_LINK", unique = true)
    private String youtubeLink;

    @Column(name = "SPOTIFY_LINK", unique = true)
    private String spotifyLink;

    @Column(name = "COUNTER_PLAYS", columnDefinition = "INTEGER DEFAULT 0 NOT NULL")
    private int counterPlays;

    @Builder.Default
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "songs_tags")
    private List<Tag> tags = new ArrayList<>();

    // private Version version[];

}
