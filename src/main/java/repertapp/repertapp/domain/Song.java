package repertapp.repertapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "song", uniqueConstraints = {
        @UniqueConstraint(name = "song_artist_name_unique", columnNames = { "ARTIST", "NAME" }),
        @UniqueConstraint(name = "song_youtube_link_unique", columnNames = "youtube_link"),
        @UniqueConstraint(name = "song_spotify_link_unique", columnNames = "spotify_link") })
public class Song {

    @Id
    @GeneratedValue
    private Long id;

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

    @Column(name = "counter_plays", columnDefinition = "INTEGER DEFAULT 0 NOT NULL")
    private int counterPlays;

    @Column(name = "score", columnDefinition = "INTEGER DEFAULT 0 NOT NULL")
    private int score;

    @Enumerated(EnumType.STRING) @NotNull
    @Column(name = "tone", columnDefinition = "VARCHAR(5)", nullable = false)
    private Tone tone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "song_tag",
        joinColumns = @JoinColumn(name = "song_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<Tag> tags = new ArrayList<>();

}
