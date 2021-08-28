package repertapp.repertapp.service;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.repository.TagRepository;
import repertapp.repertapp.util.TagCreator;

@ExtendWith(SpringExtension.class)
public class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepositoryMock;

    @BeforeEach
    public void setUp() {
        PageImpl<Tag> tagPage = new PageImpl<>(List.of(TagCreator.createValid()));

        BDDMockito.when(tagRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(tagPage);

        BDDMockito.when(tagRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(TagCreator.createValid()));

        BDDMockito.when(tagRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(TagCreator.createValid());

        BDDMockito.when(tagRepositoryMock.save(ArgumentMatchers.any(Tag.class)))
                .thenReturn(TagCreator.createValid());

        BDDMockito.doNothing().when(tagRepositoryMock).delete(ArgumentMatchers.any(Tag.class));

        BDDMockito.when(tagRepositoryMock.existsByName(ArgumentMatchers.anyString()))
                .thenReturn(Boolean.TRUE);
    }

    @Test
    @DisplayName("getAllTags returns a list of tags inside page object when successful")
    public void getAllTags_returnsListOfTagsInsidePageObject_whenSuccessful() {
        Page<Tag> tagPage = tagService.getAllTags(PageRequest.of(1, 1));
        
        Assertions.assertThat(tagPage).isNotNull();
        Assertions.assertThat(tagPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(tagPage.toList().get(0).getName())
                .isEqualTo(TagCreator.createValid().getName());
    }
    
    @Test
    @DisplayName("getTag returns a tag when successful")
    public void getTag_returnsTag_whenSuccessful() {
        Tag tag = tagService.getTag(TagCreator.createValid().getId());
        
        Assertions.assertThat(tag).isNotNull();
        Assertions.assertThat(tag.getId())
                .isNotNull()
                .isEqualTo(TagCreator.createValid().getId());
    }
    
    @Test
    @DisplayName("addTag returns a tag when successful")
    public void addTag_returnsTag_whenSuccessful() {
        Tag tag = tagService.addTag(TagCreator.createToBeSaved());
        
        Assertions.assertThat(tag)
                .isNotNull()
                .isEqualTo(TagCreator.createValid());
    }
    
    @Test
    @DisplayName("updateTag updates tag when successful")
    void updateTag_updatesTag_whenSuccessful() {
        Assertions.assertThatCode(() -> tagService.updateTag(
            TagCreator.createValid().getId(),
            TagCreator.createToBeSaved())
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("deleteTag removes tag when successful")
    void deleteTag_removesTag_whenSuccessful() {
        Assertions.assertThatCode(() -> tagService.deleteTag(TagCreator.createValid().getId()))
                .doesNotThrowAnyException();
    }
}
