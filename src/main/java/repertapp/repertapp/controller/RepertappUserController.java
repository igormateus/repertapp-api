package repertapp.repertapp.controller;

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
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.request.RepertappUserPostRequestBody;
import repertapp.repertapp.request.RepertappUserPutRequestBody;
import repertapp.repertapp.service.RepertappUserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class RepertappUserController {

    private final RepertappUserService userService;

    @PostMapping
    public ResponseEntity<RepertappUser> registerUser(@Valid @RequestBody RepertappUserPostRequestBody userRequest) {
        RepertappUser userSaved = userService.addUser(userRequest);

        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateUser(@Valid @RequestBody RepertappUserPutRequestBody userRequest,
            @AuthenticationPrincipal RepertappUser user) {
        userRequest.setId(user.getId());

        userService.updateUser(userRequest);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal RepertappUser user) {
        userService.deleteUser(user.getId());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<RepertappUser>> getAllUsers(Pageable pageable) {
        Page<RepertappUser> response = userService.getAllUsers(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepertappUser> getUser(@PathVariable Long id) {
        RepertappUser user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }
}
