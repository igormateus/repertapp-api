package repertapp.repertapp.api.controller;

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
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.band.BandPostRequestBody;
import repertapp.repertapp.domain.band.BandPutRequestBody;
import repertapp.repertapp.domain.band.BandService;
import repertapp.repertapp.domain.user.RepertappUserService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands")
public class BandController {
    
    private final BandService bandService;

    private final RepertappUserService userService;

    @PostMapping
    public ResponseEntity<Band> addBand(@Valid @RequestBody BandPostRequestBody band, @AuthenticationPrincipal RepertappUser user) {
        Band bandSaved = bandService.addBand(band, user);

        return new ResponseEntity<>(bandSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateBand(@Valid @RequestBody BandPutRequestBody bandRequest, @AuthenticationPrincipal RepertappUser user) {
        bandService.updateBand(bandRequest, user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBand(@PathVariable Long id, @AuthenticationPrincipal RepertappUser user) {
        bandService.deleteBand(id, user);

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping
    public ResponseEntity<Page<Band>> getBandsByUser(@AuthenticationPrincipal RepertappUser user, Pageable pageable) {
        Page<Band> response = bandService.getBandsByUser(user, pageable);
        
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Band> getBandByUser(@PathVariable Long id, @AuthenticationPrincipal RepertappUser user) {
        Band band = bandService.getBandByUser(id, user);

        return ResponseEntity.ok(band);
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<Page<RepertappUser>> getUsersByBand(@PathVariable Long id, @AuthenticationPrincipal RepertappUser user, Pageable pageable) {
        Band band = bandService.getBandByUser(id, user);

        Page<RepertappUser> users = userService.getUsersByBand(band, pageable);

        return ResponseEntity.ok(users);
    }
}
