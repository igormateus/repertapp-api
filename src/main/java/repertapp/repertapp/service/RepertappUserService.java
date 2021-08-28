package repertapp.repertapp.service;

import org.springframework.stereotype.Service;
// import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.RepertappUserMapper;
import repertapp.repertapp.payload.RepertappUserProfile;
import repertapp.repertapp.repository.RepertappUserRepository;
import repertapp.repertapp.validation.RepertappUserValidation;

@RequiredArgsConstructor
@Service
public class RepertappUserService {

    private final RepertappUserRepository userRepository;

    // private PasswordEncoder passwordEncoder
    
    public RepertappUserProfile getUserProfile(String username) {
        RepertappUser user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        return RepertappUserMapper.INSTANCE.toRepertappUserProfile(user);
    }

    public RepertappUser addUser(RepertappUser user) {
        RepertappUserValidation.valide(user, userRepository);

        // user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    // public RepertappUser updateUser(RepertappUser newUser, String name) {
        
    // }
}
