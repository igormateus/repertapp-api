package repertapp.repertapp.domain.tag;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import repertapp.repertapp.domain.music.Music;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tag", uniqueConstraints = {
        @UniqueConstraint(name = "tag_name_unique", columnNames = "name") })
public class Tag {
    
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank @Size(min = 2, max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "music_tag",
        joinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "music_id", referencedColumnName = "id"), 
        uniqueConstraints = @UniqueConstraint(name = "music_tag_unique", columnNames = {"music_id", "tag_id"}))
    private List<Music> songs = new ArrayList<>();
}