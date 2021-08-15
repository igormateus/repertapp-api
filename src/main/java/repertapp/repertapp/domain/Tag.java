package repertapp.repertapp.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Tag")
@Table(
    name = "tags",
    uniqueConstraints = {
        @UniqueConstraint(name = "tags_name_unique", columnNames = "name")
    }
)
public class Tag {

    @Id
    @SequenceGenerator(
        name = "tags_sequence",
        sequenceName = "tags_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "tags_sequence"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Builder.Default
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();
}
