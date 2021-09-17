package repertapp.repertapp.domain.user;

import javax.validation.Valid;

import org.apache.commons.text.WordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.core.exception.NoPermissionException;
import repertapp.repertapp.core.exception.ResourceNotFoundException;
import repertapp.repertapp.core.mapper.RepertappUserMapper;
import repertapp.repertapp.core.util.Util;
import repertapp.repertapp.domain.band.Band;

@RequiredArgsConstructor
@Service
public class RepertappUserService implements UserDetailsService{

    private final RepertappUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private RepertappUser findByIdOrThrowResourceNotFoundException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    private RepertappUser findByIdAndValideUser(Long id, RepertappUser user) {
        if (user.getId() != id) throw new NoPermissionException("User", user.getUsername(), "User", id);

        return findByIdOrThrowResourceNotFoundException(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RepertappUser user = userRepository.findByUsername(username, RepertappUser.class)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return user;
    }
    
    /**
     * Creates a new user and respond his body
     * @param userRequest
     * @return
     */
    @Transactional
    public RepertappUserResponseBody addUser(@Valid RepertappUserPostRequestBody userRequest) {
        userRequest.setEmail(userRequest.getEmail().toLowerCase());
        userRequest.setName(WordUtils.capitalizeFully(userRequest.getName()));
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        userRequest.setUsername(userRequest.getUsername().toLowerCase());

        RepertappUserRequestValidation.valideAdd(userRequest, userRepository);

        RepertappUser user = RepertappUserMapper.INSTANCE.toRepertappUser(userRequest);

        RepertappUser userSaved = userRepository.save(user);

        RepertappUserResponseBody userResponse = RepertappUserMapper.INSTANCE.toRepertappUserResponseBody(userSaved);

        return userResponse;
    }

    /**
     * Return the user by ID
     * @param id
     * @param userAuth
     * @return
     */
    public RepertappUserResponseBody getUser(Long id, RepertappUser userAuth) {
        RepertappUser user = findByIdAndValideUser(id, userAuth);

        RepertappUserResponseBody userResponse = RepertappUserMapper.INSTANCE.toRepertappUserResponseBody(user);
        
        return userResponse;
    }

    /**
     * Updates the user attributes
     * @param userRequest
     * @param userAuth
     */
    @Transactional
    public void updateUser(@Valid RepertappUserPutRequestBody userRequest, RepertappUser userAuth) {
        userRequest.setId(userAuth.getId());
        userRequest.setEmail(userRequest.getEmail().toLowerCase());
        userRequest.setName(WordUtils.capitalizeFully(userRequest.getName()));
        userRequest.setUsername(userRequest.getUsername().toLowerCase());

        RepertappUser user = findByIdOrThrowResourceNotFoundException(userRequest.getId());

        if (Util.isNullOrEmpty(userRequest.getPassword())) {
            userRequest.setPassword(user.getPassword());
        } else {
            userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
            
        RepertappUserRequestValidation.valideUpdate(userRequest, user, userRepository);

        RepertappUser userToBeSaved = RepertappUserMapper.INSTANCE.toRepertappUser(userRequest);

        userRepository.save(userToBeSaved);
    }

    /**
     * Verify id belongs to the user authenticated and delete user
     * @param id
     * @param userAuth
     */
    @Transactional
    public void deleteUser(Long id) {
        RepertappUser user = findByIdOrThrowResourceNotFoundException(id);
        
        userRepository.delete(user);
    }

    public Page<RepertappUser> getUsersByBand(Band band, Pageable pageable) {
        Page<RepertappUser> users = userRepository.findByBands(band, pageable);

        return users;
    }

}
