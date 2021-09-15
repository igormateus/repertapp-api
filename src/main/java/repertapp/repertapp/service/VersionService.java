package repertapp.repertapp.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Version;
import repertapp.repertapp.exception.NoPermissionException;
import repertapp.repertapp.exception.ResourceNotFoundException;
import repertapp.repertapp.mapper.VersionMapper;
import repertapp.repertapp.repository.VersionRepository;
import repertapp.repertapp.request.VersionPostRequestBody;
import repertapp.repertapp.request.VersionPutRequestBody;
import repertapp.repertapp.validation.VersionRequestValidation;

@RequiredArgsConstructor
@Service
public class VersionService {

    private final VersionRepository versionRepository;

    private final BandService bandService;

    private Version findByIdOrThrowResourceNotFoundException(Long id) {
        return versionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Version", "id", id));
    }

    private Version findByIdAndValidAccess(Long id, Band band) {
        Version version = findByIdOrThrowResourceNotFoundException(id);

        if (band.getVersions().stream().noneMatch(v -> v.getId() == version.getId()))
            throw new NoPermissionException("Band", band.getName(), "Version", version.getId());

        return version;        
    }

    @Transactional
    public Version addVersion(@Valid VersionPostRequestBody versionRequest, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        versionRequest.setBand(band);
        
        VersionRequestValidation.valideAdd(versionRequest, versionRepository);

        Version version = VersionMapper.INSTANCE.toVersion(versionRequest);

        Version versionSaved = versionRepository.save(version);

        return versionSaved;
    }

    @Transactional
    public void updateVersion(@Valid VersionPutRequestBody versionRequest, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        Version version = findByIdAndValidAccess(versionRequest.getId(), band);

        VersionRequestValidation.valideUpdate(versionRequest, version, versionRepository);

        Version versionToBeSaved = VersionMapper.INSTANCE.toVersion(versionRequest);

        versionRepository.save(versionToBeSaved);
    }

    @Transactional
    public void deleteVersion(Long id, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        Version version = findByIdAndValidAccess(id, band);
        
        versionRepository.delete(version);
    }

    public Page<Version> getAllVersions(Long bandId, RepertappUser user, Pageable pageable) {
        Band band = bandService.getBandByUser(bandId, user);

        Page<Version> versions = versionRepository.findByBand(band, pageable);

        return versions;
    }

    public Version getVersion(Long id, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        Version version = findByIdAndValidAccess(id, band);
        
        return version;
    }
}
