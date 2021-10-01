package repertapp.repertapp.api.controller;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;

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
import repertapp.repertapp.api.view.View;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.version.VersionPostRequestBody;
import repertapp.repertapp.domain.version.VersionPutRequestBody;
import repertapp.repertapp.domain.version.VersionResponseBody;
import repertapp.repertapp.domain.version.VersionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands/{bandId}/versions")
public class VersionController {
    
    private final VersionService versionService;

    /**
     * Creates a new Version and respond its body
     * @param bandId
     * @param version
     * @param userAuth
     * @return
     */
    @JsonView(View.Summary.class)
    @PostMapping
    public ResponseEntity<VersionResponseBody> addVersion(
        @PathVariable(name = "bandId") Long bandId,
        @Valid @RequestBody VersionPostRequestBody version,
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        VersionResponseBody response = versionService.addVersion(version, bandId, userAuth);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Returns a Version by ID
     * @param bandId
     * @param id
     * @param userAuth
     * @return
     */
    @JsonView(View.Complete.class)
    @GetMapping("/{id}")
    public ResponseEntity<VersionResponseBody> getVersion(
        @PathVariable(name = "bandId") Long bandId,
        @PathVariable Long id, 
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        VersionResponseBody version = versionService.getVersion(id, bandId, userAuth);

        return ResponseEntity.ok(version);
    }

    /**
     * update a version
     * @param bandId
     * @param version
     * @param userAuth
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateVersion(
        @PathVariable(name = "bandId") Long bandId,
        @Valid @RequestBody VersionPutRequestBody version,
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        versionService.updateVersion(version, bandId, userAuth);

        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a version
     * @param bandId
     * @param id
     * @param userAuth
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVersion(
        @PathVariable(name = "bandId") Long bandId,
        @PathVariable Long id, 
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        versionService.deleteVersion(id, bandId, userAuth);

        return ResponseEntity.noContent().build();
    }

    /**
     * Return all versions by band
     * @param bandId
     * @param user
     * @param pageable
     * @return
     */
    @JsonView(View.Summary.class)
    @GetMapping
    public ResponseEntity<Page<VersionResponseBody>> getAllVersions(
        @PathVariable(name = "bandId") Long bandId,
        @AuthenticationPrincipal RepertappUser user, 
        Pageable pageable
    ) {
        Page<VersionResponseBody> versions = versionService.getAllVersions(bandId, user, pageable);

        return ResponseEntity.ok(versions);
    }
}
