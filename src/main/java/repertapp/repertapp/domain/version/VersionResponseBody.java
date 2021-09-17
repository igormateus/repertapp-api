package repertapp.repertapp.domain.version;

import repertapp.repertapp.domain.band.BandSummary;
import repertapp.repertapp.domain.music.MusicSummary;
import repertapp.repertapp.domain.user.RepertappUserSummary;

public interface VersionResponseBody extends VersionSummary {
    RepertappUserSummary getRepertappUser();

    MusicSummary getMusic();

    BandSummary getBand();
}
