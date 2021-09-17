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
    
    public Page<Tag> getAllTags(Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);

        return tags;
    }

    public Tag getTag(Long id) {
        Tag tag = findByIdOrThrowResourceNotFoundException(id);

        return tag;
    }

    @Transactional
    public Tag addTag(TagPostRequestBody tagRequest) {
        TagRequestValidation.valideAdd(tagRequest, tagRepository);

        Tag tag = TagMapper.INSTANCE.toTag(tagRequest);

        Tag tagSaved = tagRepository.save(tag);

        return tagSaved;
    }

    @Transactional
    public void updateTag(TagPutRequestBody tagRequest) {
        Tag tag = findByIdOrThrowResourceNotFoundException(tagRequest.getId());

        TagRequestValidation.valideUpdate(tagRequest, tag, tagRepository);

        Tag tagToBeSaved = TagMapper.INSTANCE.toTag(tagRequest);

        tagRepository.save(tagToBeSaved);
    }

    @Transactional
    public void deleteTag(Long id) {
        Tag tag = findByIdOrThrowResourceNotFoundException(id);

        tagRepository.delete(tag);
    }
}
