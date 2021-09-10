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
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.request.MusicPostRequestBody;
import repertapp.repertapp.request.MusicPutRequestBody;
import repertapp.repertapp.service.MusicService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/musics")
public class MusicController {
    
    private final MusicService musicService;

    @PostMapping
    public ResponseEntity<Music> addMusic(@Valid @RequestBody MusicPostRequestBody music) {
        Music musicSaved = musicService.addMusic(music);

        return new ResponseEntity<>(musicSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateMusic(@Valid @RequestBody MusicPutRequestBody music) {
        musicService.updateMusic(music);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable Long id) {
        musicService.deleteMusic(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Music>> getAllMusicsByBand(Pageable pageable) {
        Page<Music> response = musicService.getAllMusics(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusic(@PathVariable Long id) {
        Music music = musicService.getMusic(id);

        return ResponseEntity.ok(music);
    }
}
