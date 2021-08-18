package repertapp.repertapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.projection.SongProjection;
import repertapp.repertapp.service.SongService;

@RestController
@RequestMapping("songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;

    @GetMapping
    public ResponseEntity<Page<SongProjection>> list(Pageable pageable) {
        return ResponseEntity.ok(songService.findAllSongs(pageable));
    }

    @GetMapping("/all")
    public ResponseEntity<List<SongProjection>> list() {
        return ResponseEntity.ok(songService.findAllSongs());
    }

    @PostMapping
    public ResponseEntity<Song> save(@RequestBody @Valid Song song) {
        return new ResponseEntity<>(songService.save(song), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Song song) {
        songService.replace(song); 
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
