package repertapp.repertapp.domain.setlist;

import java.util.List;

import repertapp.repertapp.domain.band.BandSummary;
import repertapp.repertapp.domain.version.VersionSummary;

public interface SetlistResponseBody extends SetlistSummary {
    List<VersionSummary> getVersions();

    BandSummary getBand();
}
