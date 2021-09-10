package repertapp.repertapp.service;

import javax.validation.Valid;

import org.apache.commons.text.WordUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.exception.NoPermissionException;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.BandMapper;
import repertapp.repertapp.repository.BandRepository;
import repertapp.repertapp.request.BandPostRequestBody;
import repertapp.repertapp.request.BandPutRequestBody;
import repertapp.repertapp.validation.BandRequestValidation;

@RequiredArgsConstructor
@Service
public class BandService {

    private final BandRepository bandRepository;

    private Band findByIdOrThrowResourceNotFoundException(Long id) {
        return bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band", "id", id));
    }

    private Band findByIdAndValidAccessOrThrowNoPermissionException(Long id, RepertappUser user) {
        Band band = findByIdOrThrowResourceNotFoundException(id);

        if (band.getMembers().stream().noneMatch(u -> u.getId().equals(user.getId())))
            throw new NoPermissionException(user.getUsername(), id);
        
        return band;
    }

    @Transactional
    public Band addBand(@Valid BandPostRequestBody bandRequest, RepertappUser user) {
        bandRequest.setName(WordUtils.capitalizeFully(bandRequest.getName()));
        
        BandRequestValidation.valideAdd(bandRequest, bandRepository);

        Band band = BandMapper.INSTANCE.toBand(bandRequest);

        band.getMembers().add(user);

        Band bandSaved = bandRepository.save(band);

        return bandSaved;
    }

    @Transactional
    public void updateBand(@Valid BandPutRequestBody bandRequest, RepertappUser user) {
        Band band = findByIdAndValidAccessOrThrowNoPermissionException(bandRequest.getId(), user);
        
        BandRequestValidation.valideUpdate(bandRequest, band, bandRepository);

        Band bandToBeSaved = BandMapper.INSTANCE.toBand(bandRequest);

        bandRepository.save(bandToBeSaved);
    }

    @Transactional
    public void deleteBand(Long id, RepertappUser user) {
        Band band = findByIdAndValidAccessOrThrowNoPermissionException(id, user);
        
        bandRepository.delete(band);
    }
    
    public Page<Band> getBandsByUser(RepertappUser user, Pageable pageable) {
        Page<Band> bands = bandRepository.findByMembers(user, pageable);
        
        return bands;
    }
    
    public Band getBandByUser(Long id, RepertappUser user) {
        Band band = findByIdAndValidAccessOrThrowNoPermissionException(id, user);
        
        return band;
    }
}
