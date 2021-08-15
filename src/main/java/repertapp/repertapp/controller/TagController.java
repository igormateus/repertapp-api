package repertapp.repertapp.controller;

import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.service.TagService;

@RestController
@RequestMapping("tags")
@RequiredArgsConstructor
public class TagController {
    
    private final TagService tagService;

    @GetMapping
    public ResponseEntity<Page<Tag>> list(Pageable pageable) {
        return new ResponseEntity<>(tagService.listAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Tag> save(@RequestBody @Valid Tag tag) {
        return new ResponseEntity<>(tagService.save(tag), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Tag tag) {
        tagService.replace(tag); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
