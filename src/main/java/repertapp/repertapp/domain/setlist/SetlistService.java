package repertapp.repertapp.domain.setlist;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import repertapp.repertapp.core.mapper.SetlistMapper;
import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.band.BandService;
import repertapp.repertapp.domain.exception.NoPermissionException;
import repertapp.repertapp.domain.exception.ResourceNotFoundException;
import repertapp.repertapp.domain.music.MusicService;

@RequiredArgsConstructor
@Service
public class SetlistService {

    private final SetlistRepository setlistRepository;

    private final BandService bandService;

    private final MusicService musicService;

    private Setlist findByIdOrThrowResourceNotFoundException(Long id) {
        return setlistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Setlist", "id", id));
    }

    private Setlist findByIdAndValidAccess(Long id, Band band) {
        Setlist setlist = findByIdOrThrowResourceNotFoundException(id);

        if (band.getSetlists().stream().noneMatch(s -> s.getId() == setlist.getId()))
            throw new NoPermissionException("Band", band.getName(), "Setlist", setlist.getId());
        
        return setlist;
    }

    @Transactional
    public Setlist addSetlist(@Valid SetlistPostRequestBody setlistRequest, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        setlistRequest.setBand(band);

        Setlist setlist = SetlistMapper.INSTANCE.toSetlist(setlistRequest);

        Setlist setlistSaved = setlistRepository.save(setlist);

        return setlistSaved;
    }

    @Transactional
    public void updateSetlist(@Valid SetlistPutRequestBody setlistRequest, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        Setlist setlist = findByIdAndValidAccess(setlistRequest.getId(), band);

        setlist.getVersions().stream().forEach(v -> musicService.getMusicByBand(v.getMusic().getId(), band.getId(), user));
        
        Setlist setlistToBeSaved = SetlistMapper.INSTANCE.toSetlist(setlistRequest);

        setlistRepository.save(setlistToBeSaved);
    }

    @Transactional
    public void deleteSetlist(Long id, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        Setlist setlist = findByIdAndValidAccess(id, band);
        
        setlistRepository.delete(setlist);
    }

    public Page<Setlist> getAllSetlistsByBand(Long bandId, RepertappUser user, Pageable pageable) {
        Band band = bandService.getBandByUser(bandId, user);

        Page<Setlist> setlists = setlistRepository.findByBand(band, pageable);

        return setlists;
    }

    public Setlist getSetlistByBand(Long id, Long bandId, RepertappUser user) {
        Band band = bandService.getBandByUser(bandId, user);

        Setlist setlist = findByIdAndValidAccess(id, band);
        
        return setlist;
    }
}
