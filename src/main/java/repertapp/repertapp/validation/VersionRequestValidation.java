package repertapp.repertapp.validation;

import javax.validation.Valid;

import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tone;
import repertapp.repertapp.domain.Version;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.payload.VersionPostRequestBody;
import repertapp.repertapp.payload.VersionPutRequestBody;
import repertapp.repertapp.repository.VersionRepository;

public class VersionRequestValidation {

    private static VersionRepository versionRepository;

    public static void valideAdd(@Valid VersionPostRequestBody versionRequest, VersionRepository repository) {
        versionRepository = repository;

        checkVersionToneAndRepertappUserAndSongUnique(versionRequest.getTone(), versionRequest.getRepertappUser(),
                versionRequest.getSong());
    }

    public static void valideUpdate(@Valid VersionPutRequestBody versionRequest, Version version,
            VersionRepository repository) {
        versionRepository = repository;

        if (!(version.getTone().equals(versionRequest.getTone())
                && version.getRepertappUser().equals(versionRequest.getRepertappUser())
                && version.getSong().equals(versionRequest.getSong())))
            checkVersionToneAndRepertappUserAndSongUnique(versionRequest.getTone(), versionRequest.getRepertappUser(),
                    versionRequest.getSong());
    }

    private static void checkVersionToneAndRepertappUserAndSongUnique(Tone tone, RepertappUser repertappUser,
            Song song) {
        if (versionRepository.existsByToneAndRepertappUserAndSong(tone, repertappUser, song))
            throw new ResourceAlreadyExists("Version", "tone", tone, "user_id", repertappUser.getId(), "song_id",
                    song.getId());
    }
}
