package repertapp.repertapp.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonView;

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
import repertapp.repertapp.api.view.View;
import repertapp.repertapp.domain.song.SongPostRequestBody;
import repertapp.repertapp.domain.song.SongPutRequestBody;
import repertapp.repertapp.domain.song.SongResponseBody;
import repertapp.repertapp.domain.song.SongService;
import repertapp.repertapp.domain.tag.Tag;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;
    
    /**
     * Creates a new song and respond its body 
     * @param songRequest
     * @return
     */
    @JsonView(View.Summary.class)
    @PostMapping
    public ResponseEntity<SongResponseBody> addSong(@Valid @RequestBody SongPostRequestBody songRequest) {
        SongResponseBody songResponse = songService.addSong(songRequest);

        return new ResponseEntity<>(songResponse, HttpStatus.CREATED);
    }

    /**
     * Returns a song by ID
     * @param id
     * @return
     */
    @JsonView(View.Complete.class)
    @GetMapping("/{id}")
    public ResponseEntity<SongResponseBody> getSong(@PathVariable Long id) {
        SongResponseBody song = songService.getSong(id);

        return ResponseEntity.ok(song);
    }

    /**
     * Updates a song
     * @param songRequest
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateSong(@Valid @RequestBody SongPutRequestBody songRequest) {
        songService.updateSong(songRequest);

        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a song
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletSong(@PathVariable Long id) {
        songService.deleteSong(id);

        return ResponseEntity.noContent().build();
    }

    /**
     * Returns a page object with songs
     * @param pageable
     * @return
     */
    @JsonView(View.Summary.class)
    @GetMapping
    public ResponseEntity<Page<SongResponseBody>> getAllSongs(Pageable pageable) {
        Page<SongResponseBody> response = songService.getAllSongs(pageable);

        return ResponseEntity.ok(response);
    }

    /**
     * Returns a page object with songs by a list of tags
     * @param tags
     * @param pageable
     * @return
     */
    @JsonView(View.Summary.class)
    @GetMapping("/tags")
    public ResponseEntity<Page<SongResponseBody>> getSongsByTags(@Valid @RequestBody List<Tag> tags, Pageable pageable) {
        Page<SongResponseBody> response = songService.getSongsByTags(tags, pageable);

        return ResponseEntity.ok(response);
    }
    
}
