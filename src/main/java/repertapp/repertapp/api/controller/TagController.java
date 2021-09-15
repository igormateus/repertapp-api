package repertapp.repertapp.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.tag.Tag;
import repertapp.repertapp.domain.tag.TagPostRequestBody;
import repertapp.repertapp.domain.tag.TagPutRequestBody;
import repertapp.repertapp.domain.tag.TagService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tags")
public class TagController {
    
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<Page<Tag>> getAllTags(Pageable pageable) {
        Page<Tag> response = tagService.getAllTags(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTag(@PathVariable Long id) {
        Tag tag = tagService.getTag(id);

        return ResponseEntity.ok(tag);
    }

    @PostMapping
    public ResponseEntity<Tag> addTag(@Valid @RequestBody TagPostRequestBody tag) {
        Tag newTag = tagService.addTag(tag);

        return new ResponseEntity<>(newTag, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateTag(@Valid @RequestBody TagPutRequestBody tag) {
        tagService.updateTag(tag);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);

        return ResponseEntity.noContent().build();
    }
}
