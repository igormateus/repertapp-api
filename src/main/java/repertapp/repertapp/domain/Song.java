package repertapp.repertapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    uniqueConstraints = {
        @UniqueConstraint(name = "UN_ARTIST_NAME", columnNames = { "ARTIST", "NAME" })
    }
)
public class Song {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String artist;

    @Column(unique = true)
    private String youtubeLink;

    @Column(unique = true)
    private String spotifyLink;

    @Column(columnDefinition = "INTEGER DEFAULT 0 NOT NULL")
    private int counterPlays;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(5)", nullable = false)
    private Tone tone;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "song_tag",
        joinColumns = { @JoinColumn(name = "song_id") },
        inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private List<Tag> tags = new ArrayList<>();

    // private Version version[];

}
