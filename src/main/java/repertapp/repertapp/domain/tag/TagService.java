package repertapp.repertapp.domain.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.core.exception.ResourceNotFoundException;
import repertapp.repertapp.core.mapper.TagMapper;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;

    private Tag findByIdOrThrowResourceNotFoundException(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
    }
    
    /**
     * Creates a new tag and respond his body
     * @param tagRequest
     * @return
     */
    @Transactional
    public TagResponseBody addTag(TagPostRequestBody tagRequest) {
        TagRequestValidation.valideAdd(tagRequest, tagRepository);
        
        Tag tag = TagMapper.INSTANCE.toTag(tagRequest);
        
        Tag tagSaved = tagRepository.save(tag);
        
        TagResponseBody tagResponse = TagMapper.INSTANCE.toTagResponseBody(tagSaved);
        
        return tagResponse;
    }
    
    /**
     * Return the tag by ID
     * @param id
     * @return
     */
    public TagResponseBody getTag(Long id) {
        Tag tag = findByIdOrThrowResourceNotFoundException(id);

        TagResponseBody tagResponse = TagMapper.INSTANCE.toTagResponseBody(tag);

        return tagResponse;
    }

    /**
     * Updates the tag attributes
     * @param tagRequest
     */
    @Transactional
    public void updateTag(TagPutRequestBody tagRequest) {
        Tag tag = findByIdOrThrowResourceNotFoundException(tagRequest.getId());

        TagRequestValidation.valideUpdate(tagRequest, tag, tagRepository);

        Tag tagToBeSaved = TagMapper.INSTANCE.toTag(tagRequest);

        tagRepository.save(tagToBeSaved);
    }

    /**
     * Delete a tag
     * @param id
     */
    @Transactional
    public void deleteTag(Long id) {
        Tag tag = findByIdOrThrowResourceNotFoundException(id);

        tagRepository.delete(tag);
    }
    
    /**
     * Return a page object with all tags
     * @param pageable
     * @return
     */
    public Page<TagResponseBody> getAllTags(Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);

        Page<TagResponseBody> tagsResponse = tags.map(
            tag -> TagMapper.INSTANCE.toTagResponseBody(tag)
        );

        return tagsResponse;
    }
}
