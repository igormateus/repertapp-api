package repertapp.repertapp.controller;

import java.util.List;

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
import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.request.SongPostRequestBody;
import repertapp.repertapp.request.SongPutRequestBody;
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
    public ResponseEntity<Song> addSong(@Valid @RequestBody SongPostRequestBody songRequest) {
        Song songResponse = songService.addSong(songRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(songResponse);
    }

    @PutMapping
    public ResponseEntity<Void> updateSong(@Valid @RequestBody SongPutRequestBody songRequest) {
        songService.updateSong(songRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletSong(@PathVariable Long id) {
        songService.deleteSong(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tags")
    public ResponseEntity<Page<Song>> getSongsByTags(@Valid @RequestBody List<Tag> tags, Pageable pageable) {
        Page<Song> response = songService.getSongsByTags(tags, pageable);

        return ResponseEntity.ok(response);
    }
    
}
