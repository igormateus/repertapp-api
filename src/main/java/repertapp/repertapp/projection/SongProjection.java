package repertapp.repertapp.projection;

import java.util.List;

import repertapp.repertapp.domain.Tone;

public interface SongProjection {
    
    Long getId();
    
    String getName();
    
    String getArtist();
    
    String getYoutubeLink();
    
    String getSpotifyLink();
    
    Integer getCounterPlays();
    
    Tone getTone();
    
    List<TagResumeProjection> getTags();
}
