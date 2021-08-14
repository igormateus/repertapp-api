package repertapp.repertapp.repository;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.util.SongCreator;
import repertapp.repertapp.util.TagCreator;

@DataJpaTest
@DisplayName("Tests For Tag Repository")
public class TagRepositoryTest {
    
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private SongRepository songRepository;

    @Test
    @DisplayName("save persists tags when Successful")
    public void save_PersistTag_WhenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = this.tagRepository.save(tagToBeSaved);

        Assertions.assertThat(tagSaved)
                .hasSameClassAs(tagToBeSaved)
                .isNotNull();

        Assertions.assertThat(tagSaved.getId()).isNotNull();

        Assertions.assertThat(tagSaved.getName()).isEqualTo(tagToBeSaved.getName());
    }

    @Test
    @DisplayName("save update tag when Sucessful")
    public void save_UpdatesTag_WhenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = this.tagRepository.save(tagToBeSaved);

        tagSaved.setName("Rock");

        Tag tagUpdated = this.tagRepository.save(tagSaved);

        Assertions.assertThat(tagUpdated).isNotNull();

        Assertions.assertThat(tagUpdated.getId()).isNotNull();

        Assertions.assertThat(tagUpdated.getName()).isEqualTo(tagSaved.getName());
    }

    @Test
    @DisplayName("delete removes tag when Sucessful")
    public void delete_RemovesAnime_WhenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = this.tagRepository.save(tagToBeSaved);

        this.tagRepository.delete(tagSaved);

        Optional<Tag> tagOptional = this.tagRepository.findById(tagSaved.getId());

        Assertions.assertThat(tagOptional).isEmpty();
    }

    @Test
    @DisplayName("findByName returns list of tag when Sucessful")
    public void findByName_ReturnsListOfTag_WhenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = this.tagRepository.save(tagToBeSaved);

        String name = tagSaved.getName();

        List<Tag> tags = this.tagRepository.findByName(name);

        Assertions.assertThat(tags)
                .isNotEmpty()
                .contains(tagSaved);
    }

    @Test
    @DisplayName("findByName returns empty list when no tag is found")
    public void findByName_ReturnsEmprtyList_WhenTagIsNotFound() {
        List<Tag> animes = this.tagRepository.findByName("Any");

        Assertions.assertThat(animes).isEmpty();
    }

    @Test
    @DisplayName("getAllTagsBySongsName returns Tags list when Song name is found")
    public void getAllTagsBySongsName_ReturnsTagsList_WhenSongNameIsFound() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = this.tagRepository.save(tagToBeSaved);

        Song songToBeSaved = SongCreator.createToBeSaved();
        
        songToBeSaved.getTags().add(tagSaved);

        Song songSaved = this.songRepository.save(songToBeSaved);

        List<Tag> tags = this.tagRepository.getAllTagsBySongsName(songSaved.getName());
        
        Assertions.assertThat(tags)
                .isNotEmpty()
                .contains(tagSaved);

    }

    // @Test
    // @DisplayName("Save throw DataIntegrityViolationException when name is empty")
    // public void save_ThrowsDataIntegrityViolationException_WhenNameIsEmpty() {
    //     Tag tagToBeSaved = new Tag();

    //     Assertions.assertThatThrownBy(() -> this.tagRepository.save(tagToBeSaved))
    //             .isInstanceOf(DataIntegrityViolationException.class);
    // }
}
