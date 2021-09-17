package repertapp.repertapp.domain.version;

import repertapp.repertapp.domain.enums.Tone;

public interface VersionSummary {
    Long getId();

    Tone getTone();
}
