package repertapp.repertapp.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Setlist;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.SetlistMapper;
import repertapp.repertapp.payload.SetlistPostRequestBody;
import repertapp.repertapp.payload.SetlistPutRequestBody;
import repertapp.repertapp.repository.SetlistRepository;

@RequiredArgsConstructor
@Service
public class SetlistService {

    private final SetlistRepository setlistRepository;

    private Setlist findByIdOrThrowResourceNotFoundException(Long id) {
        return setlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setlist", "id", id));
    }

    @Transactional
    public Setlist addSetlist(@Valid SetlistPostRequestBody setlistRequest) {
        Setlist setlist = SetlistMapper.INSTANCE.toSetlist(setlistRequest);

        Setlist setlistSaved = setlistRepository.save(setlist);

        return setlistSaved;
    }

    @Transactional
    public void updateSetlist(@Valid SetlistPutRequestBody setlistRequest) {
        findByIdOrThrowResourceNotFoundException(setlistRequest.getId());

        Setlist setlistToBeSaved = SetlistMapper.INSTANCE.toSetlist(setlistRequest);

        setlistRepository.save(setlistToBeSaved);
    }

    @Transactional
    public void deleteSetlist(Long id) {
        Setlist setlist = findByIdOrThrowResourceNotFoundException(id);
        
        setlistRepository.delete(setlist);
    }

    public Page<Setlist> getAllSetlists(Pageable pageable) {
        Page<Setlist> setlist = setlistRepository.findAll(pageable);

        return setlist;
    }

    public Setlist getSetlist(Long id) {
        Setlist setlist = findByIdOrThrowResourceNotFoundException(id);
        
        return setlist;
    }
}
