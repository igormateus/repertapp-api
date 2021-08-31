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
import repertapp.repertapp.domain.Version;
import repertapp.repertapp.payload.VersionPostRequestBody;
import repertapp.repertapp.payload.VersionPutRequestBody;
import repertapp.repertapp.service.VersionService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/versions")
public class VersionController {
    
    private final VersionService versionService;

    @PostMapping
    public ResponseEntity<Version> addUser(@Valid @RequestBody VersionPostRequestBody version) {
        Version versionSaved = versionService.addVersion(version);

        return new ResponseEntity<>(versionSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@Valid @RequestBody VersionPutRequestBody version) {
        versionService.updateVersion(version);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        versionService.deleteVersion(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Version>> getAllUsers(Pageable pageable) {
        Page<Version> response = versionService.getAllVersions(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Version> getVersion(@PathVariable Long id) {
        Version version = versionService.getVersion(id);

        return ResponseEntity.ok(version);
    }
}
