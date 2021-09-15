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
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.music.Music;
import repertapp.repertapp.domain.music.MusicPostRequestBody;
import repertapp.repertapp.domain.music.MusicPutRequestBody;
import repertapp.repertapp.domain.music.MusicService;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands/{bandId}/musics")
public class MusicController {
    
    private final MusicService musicService;

    @PostMapping
    public ResponseEntity<Music> addMusic(@PathVariable(name = "bandId") Long bandId,
            @Valid @RequestBody MusicPostRequestBody music, @AuthenticationPrincipal RepertappUser user) {
        Music musicSaved = musicService.addMusic(music, bandId, user);

        return new ResponseEntity<>(musicSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateMusic(@PathVariable(name = "bandId") Long bandId,
            @Valid @RequestBody MusicPutRequestBody music, @AuthenticationPrincipal RepertappUser user) {
        musicService.updateMusic(music, bandId, user);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(@PathVariable(name = "bandId") Long bandId, @PathVariable Long id,
            @AuthenticationPrincipal RepertappUser user) {
        musicService.deleteMusic(id, bandId, user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Music>> getAllMusicsByBand(@PathVariable(name = "bandId") Long bandId,
            @AuthenticationPrincipal RepertappUser user, Pageable pageable) {
        Page<Music> response = musicService.getAllMusicsByBand(bandId, user, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Music> getMusicByBand(@PathVariable(name = "bandId") Long bandId, @PathVariable Long id, 
            @AuthenticationPrincipal RepertappUser user) {
        Music music = musicService.getMusicByBand(id, bandId, user);

        return ResponseEntity.ok(music);
    }
}
