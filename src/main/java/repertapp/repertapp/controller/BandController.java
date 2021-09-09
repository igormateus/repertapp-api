package repertapp.repertapp.controller;

import java.util.List;

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
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.request.BandPostRequestBody;
import repertapp.repertapp.request.BandPutRequestBody;
import repertapp.repertapp.service.BandService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands")
public class BandController {
    
    private final BandService bandService;

    private Boolean hasBand(List<RepertappUser> members, RepertappUser user) { //
        return members.stream().anyMatch(u -> u.getId().equals(user.getId()));
    }

    @PostMapping //
    public ResponseEntity<Band> addBand(@Valid @RequestBody BandPostRequestBody band, @AuthenticationPrincipal RepertappUser user) {
        Band bandSaved = bandService.addBand(band, user);

        return new ResponseEntity<>(bandSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateBand(@Valid @RequestBody BandPutRequestBody band, @AuthenticationPrincipal RepertappUser user) {
        
        if (!hasBand(band.getMembers(), user)) 
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            
        bandService.updateBand(band);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBand(@PathVariable Long id) {
        bandService.deleteBand(id);

        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping //
    public ResponseEntity<Page<Band>> getBandsByUser(@AuthenticationPrincipal RepertappUser user, Pageable pageable) {
        Page<Band> response = bandService.getBandsByUser(user, pageable);
        
        return ResponseEntity.ok(response);
    }
    
    //
    @GetMapping("/{id}")
    public ResponseEntity<Band> getBandByUser(@PathVariable Long id, @AuthenticationPrincipal RepertappUser user) {
        Band band = bandService.getBand(id);

        if (!hasBand(band.getMembers(), user)) 
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        
        return ResponseEntity.ok(band);
    }
}