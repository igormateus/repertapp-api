package repertapp.repertapp.controller;

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
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.request.BandPostRequestBody;
import repertapp.repertapp.request.BandPutRequestBody;
import repertapp.repertapp.response.View;
import repertapp.repertapp.service.BandService;
import repertapp.repertapp.service.RepertappUserService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands")
public class BandController {
    
    private final BandService bandService;

    private final RepertappUserService userService;

    @JsonView(View.Resume.class)
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
    
    @JsonView(View.Resume.class)
    @GetMapping
    public ResponseEntity<Page<Band>> getBandsByUser(@AuthenticationPrincipal RepertappUser user, Pageable pageable) {
        Page<Band> response = bandService.getBandsByUser(user, pageable);
        
        return ResponseEntity.ok(response);
    }
    
    @JsonView(View.Complete.class)
    @GetMapping("/{id}")
    public ResponseEntity<Band> getBandByUser(@PathVariable Long id, @AuthenticationPrincipal RepertappUser user) {
        Band band = bandService.getBandByUser(id, user);

        return ResponseEntity.ok(band);
    }

    @JsonView(View.Complete.class)
    @GetMapping("/{id}/users")
    public ResponseEntity<Page<RepertappUser>> getUsersByBand(@PathVariable Long id, @AuthenticationPrincipal RepertappUser user, Pageable pageable) {
        Band band = bandService.getBandByUser(id, user);

        Page<RepertappUser> users = userService.getUsersByBand(band, pageable);

        return ResponseEntity.ok(users);
    }
}
