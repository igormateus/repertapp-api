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
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.payload.BandPostRequestBody;
import repertapp.repertapp.payload.BandPutRequestBody;
import repertapp.repertapp.service.BandService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands")
public class BandController {
    
    private final BandService bandService;

    @PostMapping
    public ResponseEntity<Band> addBand(@Valid @RequestBody BandPostRequestBody band) {
        Band bandSaved = bandService.addBand(band);

        return new ResponseEntity<>(bandSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateBand(@Valid @RequestBody BandPutRequestBody band) {
        bandService.updateBand(band);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBand(@PathVariable Long id) {
        bandService.deleteBand(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Band>> getAllBands(Pageable pageable) {
        Page<Band> response = bandService.getAllBands(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Band> getBand(@PathVariable Long id) {
        Band band = bandService.getBand(id);

        return ResponseEntity.ok(band);
    }
    

}
