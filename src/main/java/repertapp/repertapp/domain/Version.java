package repertapp.repertapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "version", uniqueConstraints = {
    @UniqueConstraint(name = "version_tone_user_song_unique", 
            columnNames = { "tone", "repertapp_user_id", "song_id" }) })
public class Version {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tone", columnDefinition = "VARCHAR(5)", nullable = false)
    private Tone tone;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "repertapp_user_id")
    private RepertappUser repertappUser;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

}
