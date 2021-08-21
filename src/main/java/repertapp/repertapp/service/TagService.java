package repertapp.repertapp.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.repository.TagRepository;

@RequiredArgsConstructor
@Service
public class TagService {

    private final TagRepository tagRepository;
    
    public Page<Tag> getAllTags(Pageable pageable) {
        Page<Tag> tags = tagRepository.findAll(pageable);

        return tags;
    }

    public Tag getTag(Long id) {
        return tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));
    }

    @Transactional
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Transactional
    public void updateTag(Long id, Tag newTag) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));

        tag.setName(newTag.getName());

        tagRepository.save(tag);
    }

    @Transactional
    public void deleteTag(Long id) {
        Tag tag = tagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Tag", "id", id));

        tagRepository.delete(tag);
    }
}
