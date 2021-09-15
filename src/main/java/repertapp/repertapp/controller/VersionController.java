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
import repertapp.repertapp.domain.Version;
import repertapp.repertapp.request.VersionPostRequestBody;
import repertapp.repertapp.request.VersionPutRequestBody;
import repertapp.repertapp.service.VersionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands/{bandId}/versions/")
public class VersionController {
    
    private final VersionService versionService;

    @PostMapping
    public ResponseEntity<Version> addVersion(@PathVariable(name = "bandId") Long bandId,
            @Valid @RequestBody VersionPostRequestBody version, @AuthenticationPrincipal RepertappUser user) {
        Version versionSaved = versionService.addVersion(version, bandId, user);

        return new ResponseEntity<>(versionSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateVersion(@PathVariable(name = "bandId") Long bandId,
            @Valid @RequestBody VersionPutRequestBody version, @AuthenticationPrincipal RepertappUser user) {
        versionService.updateVersion(version, bandId, user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVersion(@PathVariable(name = "bandId") Long bandId, @PathVariable Long id, 
            @AuthenticationPrincipal RepertappUser user) {
        versionService.deleteVersion(id, bandId, user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Version>> getAllVersions(@PathVariable(name = "bandId") Long bandId,
            @AuthenticationPrincipal RepertappUser user, Pageable pageable) {
        Page<Version> response = versionService.getAllVersions(bandId, user, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Version> getVersion(@PathVariable(name = "bandId") Long bandId, @PathVariable Long id, 
            @AuthenticationPrincipal RepertappUser user) {
        Version version = versionService.getVersion(id, bandId, user);

        return ResponseEntity.ok(version);
    }
}
