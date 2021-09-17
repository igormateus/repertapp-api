package repertapp.repertapp.domain.music;

import repertapp.repertapp.domain.song.SongSummary;

public interface MusicSummary {
    Long getId();

    Boolean getIsKnown();

    Integer getScore();

    Integer getCounterPlay();

    SongSummary getSong();
}
