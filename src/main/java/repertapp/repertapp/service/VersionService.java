package repertapp.repertapp.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Version;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.VersionMapper;
import repertapp.repertapp.payload.VersionPostRequestBody;
import repertapp.repertapp.payload.VersionPutRequestBody;
import repertapp.repertapp.repository.VersionRepository;
import repertapp.repertapp.validation.VersionRequestValidation;

@RequiredArgsConstructor
@Service
public class VersionService {

    private final VersionRepository versionRepository;

    private Version findByIdOrThrowResourceNotFoundException(Long id) {
        return versionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Version", "id", id));
    }

    @Transactional
    public Version addVersion(@Valid VersionPostRequestBody versionRequest) {
        VersionRequestValidation.valideAdd(versionRequest, versionRepository);

        Version version = VersionMapper.INSTANCE.toVersion(versionRequest);

        Version versionSaved = versionRepository.save(version);

        return versionSaved;
    }

    @Transactional
    public void updateVersion(@Valid VersionPutRequestBody versionRequest) {
        Version version = findByIdOrThrowResourceNotFoundException(versionRequest.getId());

        VersionRequestValidation.valideUpdate(versionRequest, version, versionRepository);

        Version versionToBeSaved = VersionMapper.INSTANCE.toVersion(versionRequest);

        versionRepository.save(versionToBeSaved);
    }

    @Transactional
    public void deleteVersion(Long id) {
        Version version = findByIdOrThrowResourceNotFoundException(id);
        
        versionRepository.delete(version);
    }

    public Page<Version> getAllVersions(Pageable pageable) {
        Page<Version> versions = versionRepository.findAll(pageable);

        return versions;
    }

    public Version getVersion(Long id) {
        Version version = findByIdOrThrowResourceNotFoundException(id);
        
        return version;
    }
}
