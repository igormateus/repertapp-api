package repertapp.repertapp.domain.band;

import javax.validation.Valid;

import org.apache.commons.text.WordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.core.exception.NoPermissionException;
import repertapp.repertapp.core.exception.ResourceNotFoundException;
import repertapp.repertapp.core.mapper.BandMapper;
import repertapp.repertapp.domain.user.RepertappUser;

@RequiredArgsConstructor
@Service
public class BandService {

    private final BandRepository bandRepository;

    public Band findByIdAndValidAccess(Long id, RepertappUser user) {
        Band band = findByIdOrThrowResourceNotFoundException(id);
        
        if (band.getMembers().stream().noneMatch(u -> u.getId().equals(user.getId())))
        throw new NoPermissionException("User", user.getUsername(), "Band", id);
        
        return band;
    }
    
    private Band findByIdOrThrowResourceNotFoundException(Long id) {
        return bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band", "id", id));
    }
    
    /**
     * Creates band and add user auth in the members array
     * @param bandRequest
     * @param user
     * @return
     */
    public BandResponseBody addBand(
        @Valid BandPostRequestBody bandRequest,
        RepertappUser user
    ) {
        bandRequest.setName(WordUtils.capitalizeFully(bandRequest.getName()));
        
        BandRequestValidation.valideAdd(bandRequest, bandRepository);

        Band band = BandMapper.INSTANCE.toBand(bandRequest);
        band.getMembers().add(user);

        Band bandSaved = bandRepository.save(band);

        BandResponseBody bandResponse = BandMapper.INSTANCE.toBandResponseBody(bandSaved);

        return bandResponse;
    }
    
    /**
     * Valid user has band and return a Band by ID
     * @param id
     * @param user
     * @return
     */
    public BandResponseBody getBandByUser(Long id, RepertappUser user) {
        Band band = findByIdAndValidAccess(id, user);

        BandResponseBody bandResponse = BandMapper.INSTANCE.toBandResponseBody(band);
        
        return bandResponse;
    }
    
    /**
     * Update a Band
     * @param bandRequest
     * @param user
     */
    public void updateBand(@Valid BandPutRequestBody bandRequest, RepertappUser user) {
        bandRequest.setName(WordUtils.capitalizeFully(bandRequest.getName()));

        Band band = findByIdAndValidAccess(bandRequest.getId(), user);
        
        BandRequestValidation.valideUpdate(bandRequest, band, bandRepository);

        Band bandToBeSaved = BandMapper.INSTANCE.toBand(bandRequest);

        bandRepository.save(bandToBeSaved);
    }

    /**
     * Delete a band by id
     * @param id
     * @param user
     */
    public void deleteBand(Long id, RepertappUser user) {
        Band band = findByIdAndValidAccess(id, user);
        
        bandRepository.delete(band);
    }
    
    /**
     * Return a page object with list of bands by user
     * @param user
     * @param pageable
     * @return
     */
    public Page<BandResponseBody> getBandsByUser(RepertappUser user, Pageable pageable) {
        Page<Band> bands = bandRepository.findByMembers(user, pageable);

        Page<BandResponseBody> bandsResponse = bands.map(band ->
            BandMapper.INSTANCE.toBandResponseBody(band));
        
        return bandsResponse;
    }
}
