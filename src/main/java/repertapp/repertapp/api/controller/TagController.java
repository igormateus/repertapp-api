package repertapp.repertapp.api.controller;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;

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
import repertapp.repertapp.api.view.View;
import repertapp.repertapp.domain.tag.TagPostRequestBody;
import repertapp.repertapp.domain.tag.TagPutRequestBody;
import repertapp.repertapp.domain.tag.TagResponseBody;
import repertapp.repertapp.domain.tag.TagService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tags")
public class TagController {
    
    private final TagService tagService;

    /**
     * Creates a new Tag and respond his body
     * @param tag
     * @return
     */
    @JsonView(View.Summary.class)
    @PostMapping
    public ResponseEntity<TagResponseBody> addTag(@Valid @RequestBody TagPostRequestBody tagRequest) {
        TagResponseBody tag = tagService.addTag(tagRequest);

        return new ResponseEntity<>(tag, HttpStatus.CREATED);
    }

    /**
     * Returns a tag by ID
     * @param id
     * @return
     */
    @JsonView(View.Complete.class)
    @GetMapping("/{id}")
    public ResponseEntity<TagResponseBody> getTag(@PathVariable Long id) {
        TagResponseBody tag = tagService.getTag(id);

        return ResponseEntity.ok(tag);
    }

    /**
     * Updates a tag
     * @param tag
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateTag(@Valid @RequestBody TagPutRequestBody tag) {
        tagService.updateTag(tag);

        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a tag
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Returns a page object with tags
     * @param pageable
     * @return
     */
    @JsonView(View.Summary.class)
    @GetMapping
    public ResponseEntity<Page<TagResponseBody>> getAllTags(Pageable pageable) {
        Page<TagResponseBody> response = tagService.getAllTags(pageable);

        return ResponseEntity.ok(response);
    }
}
