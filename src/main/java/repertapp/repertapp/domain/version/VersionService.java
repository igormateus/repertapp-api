package repertapp.repertapp.domain.version;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.core.exception.NoPermissionException;
import repertapp.repertapp.core.exception.ResourceNotFoundException;
import repertapp.repertapp.core.mapper.VersionMapper;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.band.BandService;
import repertapp.repertapp.domain.music.Music;
import repertapp.repertapp.domain.music.MusicService;
import repertapp.repertapp.domain.user.RepertappUser;

@RequiredArgsConstructor
@Service
public class VersionService {

    private final VersionRepository versionRepository;

    private final BandService bandService;

    private final MusicService musicService;

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

    /**
     * Valid access user to band, valid band to users and musics and add version
     * @param versionRequest
     * @param bandId
     * @param user
     * @return
     */
    public VersionResponseBody addVersion(VersionPostRequestBody versionRequest, Long bandId, RepertappUser user) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        if (user.getId() != versionRequest.getRepertappUser().getId())
            bandService.findByIdAndValidAccess(band.getId(), versionRequest.getRepertappUser());

        Music music = musicService.findByIdAndValidAccess(versionRequest.getMusic().getId(), band);

        versionRequest.setMusic(music);
        versionRequest.setBand(band);
        if (versionRequest.getTone() == null) 
            versionRequest.setTone(music.getTone());
        
        VersionRequestValidation.valideAdd(versionRequest, versionRepository);

        Version version = VersionMapper.INSTANCE.toVersion(versionRequest);

        Version versionSaved = versionRepository.save(version);

        VersionResponseBody versionResponse = VersionMapper.INSTANCE.toVersionResponseBody(versionSaved);

        return versionResponse;
    }

    /**
     * Return a version by ID
     * @param id
     * @param bandId
     * @param user
     * @return
     */
    public VersionResponseBody getVersion(Long id, Long bandId, RepertappUser user) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        Version version = findByIdAndValidAccess(id, band);

        VersionResponseBody versionResponse = VersionMapper.INSTANCE.toVersionResponseBody(version);
        
        return versionResponse;
    }

    /**
     * Valid and updates a version
     * @param versionRequest
     * @param bandId
     * @param user
     */
    public void updateVersion(@Valid VersionPutRequestBody versionRequest, Long bandId, RepertappUser user) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        if (user.getId() != versionRequest.getRepertappUser().getId())
            bandService.findByIdAndValidAccess(band.getId(), versionRequest.getRepertappUser());

        Music music = musicService.findByIdAndValidAccess(versionRequest.getMusic().getId(), band);

        Version version = findByIdAndValidAccess(versionRequest.getId(), band);

        versionRequest.setMusic(music);
        versionRequest.setBand(band);
        if (versionRequest.getTone() == null) 
            versionRequest.setTone(music.getTone());

        VersionRequestValidation.valideUpdate(versionRequest, version, versionRepository);

        Version versionToBeSaved = VersionMapper.INSTANCE.toVersion(versionRequest);

        versionRepository.save(versionToBeSaved);
    }

    /**
     * Delete a version
     * @param id
     * @param bandId
     * @param user
     */
    @Transactional
    public void deleteVersion(Long id, Long bandId, RepertappUser user) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        Version version = findByIdAndValidAccess(id, band);
        
        versionRepository.delete(version);
    }

    /**
     * Return all versions by band
     * @param bandId
     * @param user
     * @param pageable
     * @return
     */
    public Page<VersionResponseBody> getAllVersions(Long bandId, RepertappUser user, Pageable pageable) {
        Band band = bandService.findByIdAndValidAccess(bandId, user);

        Page<Version> versions = versionRepository.findByBand(band, pageable);

        Page<VersionResponseBody> versionsResponse = versions.map(version -> 
            VersionMapper.INSTANCE.toVersionResponseBody(version));

        return versionsResponse;
    }
}
