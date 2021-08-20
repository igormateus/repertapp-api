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
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.payload.SongRequest;
import repertapp.repertapp.payload.SongResponse;
import repertapp.repertapp.service.SongService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<Page<Song>> getAllSongs(Pageable pageable) {
        Page<Song> response = songService.getAllSongs(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Song> getSong(@PathVariable Long id) {
        Song song = songService.getSong(id);

        return ResponseEntity.ok(song);
    }

    @PostMapping
    public ResponseEntity<SongResponse> addSong(@Valid @RequestBody SongRequest songRequest) {
        SongResponse songResponse = songService.addSong(songRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(songResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSong(@PathVariable Long id, @Valid @RequestBody SongRequest newSongRequest) {
        songService.updateSong(id, newSongRequest);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletSong(@PathVariable Long id) {
        songService.deleteSong(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
