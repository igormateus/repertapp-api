package repertapp.repertapp.repository;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

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

        Assertions.assertThat(tagSaved)
                .isNotNull()
                .isEqualTo(tagToBeSaved);
    }

    @Test
    @DisplayName("save update tag when successful")
    public void save_updateTag_whenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();
        Tag tagSaved = tagRepository.save(tagToBeSaved);

        tagSaved.setName("Test_name_2");
        Tag tagUpdated = tagRepository.save(tagSaved);

        Assertions.assertThat(tagUpdated)
                .isNotNull()
                .isEqualTo(tagSaved);
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

    @Test
    @DisplayName("findByName return a tag when successful")
    public void findByName_returnTag_whenSuccessful() {
        Tag tagToBeSaved = TagCreator.createToBeSaved();

        Tag tagSaved = tagRepository.save(tagToBeSaved);

        Page<Tag> tagPage = tagRepository.findByNameLike(tagSaved.getName(), null);

        Assertions.assertThat(tagPage).isNotNull();
        Assertions.assertThat(tagPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(tagPage.toList().get(0)).isEqualTo(tagSaved);
    }
}
