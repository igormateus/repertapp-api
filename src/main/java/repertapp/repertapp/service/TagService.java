package repertapp.repertapp.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.repository.TagRepository;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Page<Tag> listAll(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }

    @Transactional
    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }
    
}
