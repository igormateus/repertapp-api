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
import repertapp.repertapp.domain.music.MusicPostRequestBody;
import repertapp.repertapp.domain.music.MusicPutRequestBody;
import repertapp.repertapp.domain.music.MusicResponseBody;
import repertapp.repertapp.domain.music.MusicService;
import repertapp.repertapp.domain.user.RepertappUser;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bands/{bandId}/musics")
public class MusicController {
    
    private final MusicService musicService;

    /**
     * Creates a new Music and respond its body
     * @param bandId
     * @param music
     * @param userAuth
     * @return
     */
    @JsonView(View.Summary.class)
    @PostMapping
    public ResponseEntity<MusicResponseBody> addMusic(
        @PathVariable(name = "bandId") Long bandId,
        @Valid @RequestBody MusicPostRequestBody music, 
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        MusicResponseBody response = musicService.addMusic(music, bandId, userAuth);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    
    /**
     * Return a music by ID
     * @param bandId
     * @param id
     * @param user
     * @return
     */
    @JsonView(View.Complete.class)
    @GetMapping("/{id}")
    public ResponseEntity<MusicResponseBody> getMusicByBand(
        @PathVariable(name = "bandId") Long bandId,
        @PathVariable Long id,
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        MusicResponseBody response = musicService.getMusicByBand(id, bandId, userAuth);

        return ResponseEntity.ok(response);
    }

    /**
     * Updates a music
     * @param bandId
     * @param music
     * @param user
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateMusic(
        @PathVariable(name = "bandId") Long bandId,
        @Valid @RequestBody MusicPutRequestBody music,
        @AuthenticationPrincipal RepertappUser userAuth
    ) {
        musicService.updateMusic(music, bandId, userAuth);

        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a music
     * @param bandId
     * @param id
     * @param user
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMusic(
        @PathVariable(name = "bandId") Long bandId, 
        @PathVariable Long id,
        @AuthenticationPrincipal RepertappUser user
    ) {
        musicService.deleteMusic(id, bandId, user);

        return ResponseEntity.noContent().build();
    }

    /**
     * Return a page object with musics by band
     * @param bandId
     * @param user
     * @param pageable
     * @return
     */
    @JsonView(View.Summary.class)
    @GetMapping
    public ResponseEntity<Page<MusicResponseBody>> getAllMusicsByBand(
        @PathVariable(name = "bandId") Long bandId,
        @AuthenticationPrincipal RepertappUser user,
        Pageable pageable
    ) {
        Page<MusicResponseBody> response = musicService.getAllMusicsByBand(bandId, user, pageable);

        return ResponseEntity.ok(response);
    }
}
