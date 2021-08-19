package repertapp.repertapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "song", uniqueConstraints = {
        @UniqueConstraint(name = "UN_ARTIST_NAME", columnNames = { "ARTIST", "NAME" }) })
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "youtube_link", unique = true)
    private String youtubeLink;

    @Column(name = "spotify_link", unique = true)
    private String spotifyLink;

    @Column(name = "counter_plays", columnDefinition = "INTEGER DEFAULT 0 NOT NULL")
    private int counterPlays;

    @Enumerated(EnumType.STRING)
    @Column(name = "tone", columnDefinition = "VARCHAR(5)", nullable = false)
    private Tone tone;

    // @Builder.Default
    // @ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(
    // name = "song_tag",
    // joinColumns = { @JoinColumn(name = "song_id") },
    // inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    // private List<Tag> tags = new ArrayList<>();

    // private Version version[];

}
