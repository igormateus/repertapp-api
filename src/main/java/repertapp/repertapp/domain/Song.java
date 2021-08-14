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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "songs")
public class Song {

    @Id
    @GeneratedValue
    @Column(name = "song_id")
    private Long id;

    private String name;

    private String artist;

    @Enumerated(EnumType.STRING)
    private Tone tone;

    @Column(unique = true)
    private String youtube_link;

    @Column(unique = true)
    private String spotify_link;

    private int counter_plays;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "songs_tags")
    private List<Tag> tags = new ArrayList<>();

    // private Version version[];

}
