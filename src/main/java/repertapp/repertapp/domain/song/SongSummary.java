package repertapp.repertapp.domain.song;

import repertapp.repertapp.domain.enums.Tone;

public interface SongSummary {
    Long getId();

    String getName();

    String getArtist();

    String getYoutubeLink();

    String getSpotifyLink();

    Tone getTone();
}
