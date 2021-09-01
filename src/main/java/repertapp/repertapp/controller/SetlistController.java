package repertapp.repertapp.controller;

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
import repertapp.repertapp.domain.Setlist;
import repertapp.repertapp.payload.SetlistPostRequestBody;
import repertapp.repertapp.payload.SetlistPutRequestBody;
import repertapp.repertapp.service.SetlistService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/setlist")
public class SetlistController {
    
    private final SetlistService setlistService;

    @PostMapping
    public ResponseEntity<Setlist> addSetlist(@Valid @RequestBody SetlistPostRequestBody setlist) {
        Setlist setlistSaved = setlistService.addSetlist(setlist);

        return new ResponseEntity<>(setlistSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateSetlist(@Valid @RequestBody SetlistPutRequestBody setlist) {
        setlistService.updateSetlist(setlist);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetlist(@PathVariable Long id) {
        setlistService.deleteSetlist(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Setlist>> getAllSetlists(Pageable pageable) {
        Page<Setlist> response = setlistService.getAllSetlists(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setlist> getSetlist(@PathVariable Long id) {
        Setlist setlist = setlistService.getSetlist(id);

        return ResponseEntity.ok(setlist);
    }
}
