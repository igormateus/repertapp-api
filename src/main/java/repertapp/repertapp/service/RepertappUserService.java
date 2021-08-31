package repertapp.repertapp.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.RepertappUserMapper;
import repertapp.repertapp.payload.RepertappUserPostRequestBody;
import repertapp.repertapp.payload.RepertappUserPutRequestBody;
import repertapp.repertapp.repository.RepertappUserRepository;
import repertapp.repertapp.validation.RepertappUserRequestValidation;

@RequiredArgsConstructor
@Service
public class RepertappUserService {

    private final RepertappUserRepository userRepository;

    private RepertappUser findByIdOrThrowResourceNotFoundException(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    @Transactional
    public RepertappUser addUser(@Valid RepertappUserPostRequestBody userRequest) {
        RepertappUserRequestValidation.valideAdd(userRequest, userRepository);

        RepertappUser user = RepertappUserMapper.INSTANCE.toRepertappUser(userRequest);

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
