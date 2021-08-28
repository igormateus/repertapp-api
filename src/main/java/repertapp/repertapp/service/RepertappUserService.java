package repertapp.repertapp.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.repository.RepertappUserRepository;

@RequiredArgsConstructor
@Service
public class RepertappUserService {

    private final RepertappUserRepository userRepository;

    public boolean checkUsernameAvailability(String username) {
        boolean isAvailable = !userRepository.existsByUsername(username);
        
        return isAvailable;
    }

    public boolean checkEmailAvailability(String email) {
        boolean isAvailable = !userRepository.existsByEmail(email);

        return isAvailable;
    }  
}
