package repertapp.repertapp.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Setlist;
import repertapp.repertapp.request.SetlistPostRequestBody;
import repertapp.repertapp.request.SetlistPutRequestBody;
import repertapp.repertapp.service.SetlistService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands/{bandId}/setlists")
public class SetlistController {
    
    private final SetlistService setlistService;

    @PostMapping
    public ResponseEntity<Setlist> addSetlist(@PathVariable(name = "bandId") Long bandId,
        @Valid @RequestBody SetlistPostRequestBody setlist, @AuthenticationPrincipal RepertappUser user) {
        Setlist setlistSaved = setlistService.addSetlist(setlist, bandId, user);

        return new ResponseEntity<>(setlistSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateSetlist(@PathVariable(name = "bandId") Long bandId,
        @Valid @RequestBody SetlistPutRequestBody setlist, @AuthenticationPrincipal RepertappUser user) {
        setlistService.updateSetlist(setlist, bandId, user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetlist(@PathVariable(name = "bandId") Long bandId,
        @PathVariable Long id, @AuthenticationPrincipal RepertappUser user) {
        setlistService.deleteSetlist(id, bandId, user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Setlist>> getAllSetlistsByBand(@PathVariable(name = "bandId") Long bandId,
        @AuthenticationPrincipal RepertappUser user, Pageable pageable) {
        Page<Setlist> response = setlistService.getAllSetlistsByBand(bandId, user, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Setlist> getSetlistByBand(@PathVariable(name = "bandId") Long bandId,
        @PathVariable Long id, @AuthenticationPrincipal RepertappUser user) {
        Setlist setlist = setlistService.getSetlistByBand(id, bandId, user);

        return ResponseEntity.ok(setlist);
    }
}
