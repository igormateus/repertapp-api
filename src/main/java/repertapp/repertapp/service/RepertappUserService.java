package repertapp.repertapp.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.RepertappUserMapper;
import repertapp.repertapp.repository.RepertappUserRepository;
import repertapp.repertapp.request.RepertappUserPostRequestBody;
import repertapp.repertapp.request.RepertappUserPutRequestBody;
import repertapp.repertapp.validation.RepertappUserRequestValidation;

@RequiredArgsConstructor
@Service
public class RepertappUserService implements UserDetailsService{

    private final RepertappUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private RepertappUser findByIdOrThrowResourceNotFoundException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RepertappUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return user;
    }
    
    // Ok
    @Transactional
    public RepertappUser addUser(@Valid RepertappUserPostRequestBody userRequest) {
        userRequest.setEmail(userRequest.getEmail().toLowerCase());
        userRequest.setName(userRequest.getName().toLowerCase());
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRequest.setUsername(userRequest.getUsername().toLowerCase());

        RepertappUser user = RepertappUserRequestValidation.valideAdd(userRequest, userRepository);

        RepertappUser userSaved = userRepository.save(user);

        return userSaved;
    }

    @Transactional
    public void updateUser(@Valid RepertappUserPutRequestBody userRequest) {
        RepertappUser user = findByIdOrThrowResourceNotFoundException(userRequest.getId());

        RepertappUserRequestValidation.valideUpdate(userRequest, user, userRepository);

        RepertappUser userToBeSaved = RepertappUserMapper.INSTANCE.toRepertappUser(userRequest);

        userRepository.save(userToBeSaved);
    }

    @Transactional
    public void deleteUser(Long id) {
        RepertappUser user = findByIdOrThrowResourceNotFoundException(id);
        
        userRepository.delete(user);
    }

    public Page<RepertappUser> getAllUsers(Pageable pageable) {
        Page<RepertappUser> users = userRepository.findAll(pageable);

        return users;
    }

    public RepertappUser getUser(Long id) {
        RepertappUser user = findByIdOrThrowResourceNotFoundException(id);
        
        return user;
    }

}
