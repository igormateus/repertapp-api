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
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.user.RepertappUserPostRequestBody;
import repertapp.repertapp.domain.user.RepertappUserPutRequestBody;
import repertapp.repertapp.domain.user.RepertappUserResponseBody;
import repertapp.repertapp.domain.user.RepertappUserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class RepertappUserController {

    private final RepertappUserService userService;

    /**
     * Creates a new user and respond his body
     * @param userRequest
     * @return
     */
    @JsonView(View.Summary.class)
    @PostMapping
    public ResponseEntity<RepertappUserResponseBody> registerUser(
        @Valid @RequestBody RepertappUserPostRequestBody userRequest
    ) {
        RepertappUserResponseBody userResponse = userService.addUser(userRequest);

        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    /**
     * Returns a user by ID
     * @param id
     * @param user
     * @return
     */
    @JsonView(View.Complete.class)
    @GetMapping("/{id}")
    public ResponseEntity<RepertappUserResponseBody> getUser(
        @PathVariable Long id, @AuthenticationPrincipal RepertappUser user
    ) {
        RepertappUserResponseBody userResponse = userService.getUser(id, user);

        return ResponseEntity.ok(userResponse);
    }

    /**
     * Updates a user
     * @param userRequest
     * @param user
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateUser(
        @Valid @RequestBody RepertappUserPutRequestBody userRequest,
        @AuthenticationPrincipal RepertappUser user
    ) {
        userService.updateUser(userRequest, user);

        return ResponseEntity.noContent().build();
    }

    /**
     * Delete a user
     * @param user
     * @return
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(
        @AuthenticationPrincipal RepertappUser user
    ) {
        userService.deleteUser(user.getId());

        return ResponseEntity.noContent().build();
    }

    /**
     * Returns all users
     * @param pageable
     * @return
     */
    @JsonView(View.Summary.class)
    @GetMapping
    public ResponseEntity<Page<RepertappUserResponseBody>> getAllUsers(Pageable pageable) {
        Page<RepertappUserResponseBody> response = userService.getAllUsers(pageable);

        return ResponseEntity.ok(response);
    }
}
