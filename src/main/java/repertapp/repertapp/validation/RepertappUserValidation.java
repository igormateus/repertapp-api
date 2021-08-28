package repertapp.repertapp.validation;

import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.repository.RepertappUserRepository;

public class RepertappUserValidation {

    private static RepertappUserRepository userRepository;

    public static void valide(RepertappUser user, RepertappUserRepository repository) {
        userRepository = repository;

        if (!checkUsernameAvailability(user.getName()))
            throw new ResourceAlreadyExists("User", "name", user.getName());
        
        if (!checkEmailAvailability(user.getEmail()))
            throw new ResourceAlreadyExists("User", "email", user.getEmail());
    }

    public static boolean checkUsernameAvailability(String username) {
        boolean isAvailable = !userRepository.existsByUsername(username);
        
        return isAvailable;
    }

    public static boolean checkEmailAvailability(String email) {
        boolean isAvailable = !userRepository.existsByEmail(email);

        return isAvailable;
    }
}
