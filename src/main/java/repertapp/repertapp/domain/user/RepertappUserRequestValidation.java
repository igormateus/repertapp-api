package repertapp.repertapp.domain.user;

import javax.validation.Valid;

import repertapp.repertapp.core.exception.ResourceAlreadyExists;

public class RepertappUserRequestValidation {

    private static RepertappUserRepository userRepository;

    public static void valideAdd(@Valid RepertappUserPostRequestBody userRequest, RepertappUserRepository repository) {
        userRepository = repository;

        checkRepertappUserNameUnique(userRequest.getName());

        checkRepertappUserEmailUnique(userRequest.getEmail());

        checkRepertappUserUsernameUnique(userRequest.getUsername());
    }

    public static void valideUpdate(@Valid RepertappUserPutRequestBody userRequest, RepertappUser user,
            RepertappUserRepository repository) {
        userRepository = repository;

        if (!(user.getName().equals(userRequest.getName())))
            checkRepertappUserNameUnique(userRequest.getName());

        if (!(user.getEmail().equals(userRequest.getEmail())))
            checkRepertappUserEmailUnique(userRequest.getEmail());

        if (!(user.getUsername().equals(userRequest.getUsername())))
            checkRepertappUserUsernameUnique(userRequest.getUsername());
    }

    private static void checkRepertappUserUsernameUnique(String username) {
        if (userRepository.existsByUsername(username))
            throw new ResourceAlreadyExists("User", "username", username);
    }

    private static void checkRepertappUserEmailUnique(String email) {
        if (userRepository.existsByEmail(email))
            throw new ResourceAlreadyExists("User", "email", email);

    }

    private static void checkRepertappUserNameUnique(String name) {
        if (userRepository.existsByName(name))
            throw new ResourceAlreadyExists("User", "name", name);
    }
    
}
