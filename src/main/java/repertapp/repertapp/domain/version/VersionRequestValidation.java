package repertapp.repertapp.domain.version;

import javax.validation.Valid;

import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.enums.Tone;
import repertapp.repertapp.domain.exception.ResourceAlreadyExists;
import repertapp.repertapp.domain.music.Music;

public class VersionRequestValidation {

    private static VersionRepository versionRepository;

    public static void valideAdd(@Valid VersionPostRequestBody versionRequest, VersionRepository repository) {
        versionRepository = repository;

        checkVersionToneAndRepertappUserAndMusicUnique(
            versionRequest.getTone(), 
            versionRequest.getRepertappUser(),
            versionRequest.getMusic()
        );
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
