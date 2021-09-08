package repertapp.repertapp.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.BandMapper;
import repertapp.repertapp.repository.BandRepository;
import repertapp.repertapp.repository.RepertappUserRepository;
import repertapp.repertapp.request.BandPostRequestBody;
import repertapp.repertapp.request.BandPutRequestBody;
import repertapp.repertapp.validation.BandRequestValidation;

@RequiredArgsConstructor
@Service
public class BandService {

    private final BandRepository bandRepository;
    private final RepertappUserRepository userRepository;

    private Band findByIdOrThrowResourceNotFoundException(Long id) {
        return bandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Band", "id", id));
    }

    @Transactional
    public Band addBand(@Valid BandPostRequestBody bandRequest) {
        bandRequest.getMembers().stream().forEach(user -> userRepository.findById(user.getId()).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", user.getId())));

        BandRequestValidation.valideAdd(bandRequest, bandRepository);

        Band band = BandMapper.INSTANCE.toBand(bandRequest);

        Band bandSaved = bandRepository.save(band);

        return bandSaved;
    }

    @Transactional
    public void updateBand(@Valid BandPutRequestBody bandRequest) {
        bandRequest.getMembers().stream().forEach(user -> userRepository.findById(user.getId()).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", user.getId())));
            
        Band band = findByIdOrThrowResourceNotFoundException(bandRequest.getId());

        BandRequestValidation.valideUpdate(bandRequest, band, bandRepository);

        Band bandToBeSaved = BandMapper.INSTANCE.toBand(bandRequest);

        bandRepository.save(bandToBeSaved);
    }

    @Transactional
    public void deleteBand(Long id) {
        Band band = findByIdOrThrowResourceNotFoundException(id);
        
        bandRepository.delete(band);
    }

    public Page<Band> getAllBands(Pageable pageable) {
        Page<Band> bands = bandRepository.findAll(pageable);

        return bands;
    }

    public Band getBand(Long id) {
        Band band = findByIdOrThrowResourceNotFoundException(id);
        
        return band;
    }
}
