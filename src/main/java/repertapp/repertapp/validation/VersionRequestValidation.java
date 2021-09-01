package repertapp.repertapp.validation;

import javax.validation.Valid;

import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.RepertappUser;
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

        checkVersionToneAndRepertappUserAndMusicUnique(versionRequest.getTone(), versionRequest.getRepertappUser(),
                versionRequest.getMusic());
    }

    public static void valideUpdate(@Valid VersionPutRequestBody versionRequest, Version version,
            VersionRepository repository) {
        versionRepository = repository;

        if (!(version.getTone().equals(versionRequest.getTone())
                && version.getRepertappUser().equals(versionRequest.getRepertappUser())
                && version.getMusic().equals(versionRequest.getMusic())))
            checkVersionToneAndRepertappUserAndMusicUnique(versionRequest.getTone(), versionRequest.getRepertappUser(),
                    versionRequest.getMusic());
    }

    private static void checkVersionToneAndRepertappUserAndMusicUnique(Tone tone, RepertappUser repertappUser,
            Music music) {
        if (versionRepository.existsByToneAndRepertappUserAndMusic(tone, repertappUser, music))
            throw new ResourceAlreadyExists("Version", "tone", tone, "user_id", repertappUser.getId(), "music_id",
                    music.getId());
    }
}
