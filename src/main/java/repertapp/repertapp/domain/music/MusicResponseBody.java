package repertapp.repertapp.domain.music;

import repertapp.repertapp.domain.band.BandSummary;

public interface MusicResponseBody extends MusicSummary {
    BandSummary getBand();
}
