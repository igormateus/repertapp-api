package repertapp.repertapp.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.util.TagCreator;

@DataJpaTest
@DisplayName("Tests For Tag Repository")
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    @DisplayName("save persists tag when successful")
    public void save_persistTag_whenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = tagRepository.save(tagToBeSaved);

        Assertions.assertThat(tagSaved).isNotNull();
        Assertions.assertThat(tagSaved.getId()).isNotNull();
        Assertions.assertThat(tagSaved.getName()).isEqualTo(tagToBeSaved.getName());
    }

    @Test
    @DisplayName("save update tag when successful")
    public void save_updateTag_whenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = tagRepository.save(tagToBeSaved);

        tagSaved.setName("Test_name_2");

        Tag tagUpdated = tagRepository.save(tagSaved);

        Assertions.assertThat(tagUpdated).isNotNull();
        Assertions.assertThat(tagUpdated).isEqualTo(tagSaved);
    }

    @Test
    @DisplayName("delete remove tag when successful")
    public void delete_removeTag_whenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = tagRepository.save(tagToBeSaved);

        tagRepository.delete(tagSaved);

        Optional<Tag> tagOptional = tagRepository.findById(tagSaved.getId());

        Assertions.assertThat(tagOptional).isEmpty();
    }

    // @Test
    // @DisplayName("findByName return a tag when successful")
    // public void findByName_returnTag_whenSuccessful() {
    //     Tag tagToBeSaved = TagCreator.createToBeSaved();

    //     Tag tagSaved = tagRepository.save(tagToBeSaved);

    //     Tag tagFound = tagRepository.findByName(tagSaved.getName());

    //     Assertions.assertThat(tagFound).isNotNull();
    //     Assertions.assertThat(tagFound.getId()).isEqualTo(tagSaved.getId());
    //     Assertions.assertThat(tagFound.getName()).isEqualTo(tagToBeSaved.getName());
    // }
}
