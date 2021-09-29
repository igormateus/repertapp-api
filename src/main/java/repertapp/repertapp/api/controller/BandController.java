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
import repertapp.repertapp.domain.band.BandPostRequestBody;
import repertapp.repertapp.domain.band.BandPutRequestBody;
import repertapp.repertapp.domain.band.BandResponseBody;
import repertapp.repertapp.domain.band.BandService;
import repertapp.repertapp.domain.user.RepertappUser;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands")
public class BandController {
    
    private final BandService bandService;

    /**
     * Creates a new Band with auth user
     * @param band
     * @param userAuth
     * @return
     */
    @JsonView(View.Summary.class)
    @PostMapping
    public ResponseEntity<BandResponseBody> addBand(
        @Valid @RequestBody BandPostRequestBody band,
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        BandResponseBody bandSaved = bandService.addBand(band, userAuth);

        return new ResponseEntity<>(bandSaved, HttpStatus.CREATED);
    }
    
    /**
     * Return a band by ID
     * @param id
     * @param userAuth
     * @return
     */
    @JsonView(View.Complete.class)
    @GetMapping("/{id}")
    public ResponseEntity<BandResponseBody> getBandByUser(
        @PathVariable Long id,
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        BandResponseBody band = bandService.getBandByUser(id, userAuth);

        return ResponseEntity.ok(band);
    }

    /**
     * Update a band
     * @param bandRequest
     * @param userAuth
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateBand(
        @Valid @RequestBody BandPutRequestBody bandRequest,
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        bandService.updateBand(bandRequest, userAuth);

        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a band by ID
     * @param id
     * @param user
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBand(
        @PathVariable Long id,
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        bandService.deleteBand(id, userAuth);

        return ResponseEntity.noContent().build();
    }
    
    /**
     * Return a page object with list of bands by user
     * @param user
     * @param pageable
     * @return
     */
    @JsonView(View.Summary.class)
    @GetMapping
    public ResponseEntity<Page<BandResponseBody>> getBandsByUser(
        @AuthenticationPrincipal RepertappUser user,
        Pageable pageable
    ) {
        Page<BandResponseBody> response = bandService.getBandsByUser(user, pageable);
        
        return ResponseEntity.ok(response);
    }
}
