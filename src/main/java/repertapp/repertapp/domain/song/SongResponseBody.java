package repertapp.repertapp.domain.song;

import java.util.List;

import repertapp.repertapp.domain.tag.Tag;

public interface SongResponseBody extends SongSummary {
    List<Tag> getTags();
}
