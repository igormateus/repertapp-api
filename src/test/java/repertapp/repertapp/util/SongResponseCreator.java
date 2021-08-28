package repertapp.repertapp.util;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.payload.SongResponse;

public class SongResponseCreator {
    
    public static SongResponse createValid() {
        SongResponse songResponse = new SongResponse();

        Song song = SongCreator.createValid();

        songResponse.setId(song.getId());
        songResponse.setArtist(song.getArtist());
        songResponse.setName(song.getName());
        songResponse.setSpotifyLink(song.getSpotifyLink());
        songResponse.setTone(song.getTone());
        songResponse.setYoutubeLink(song.getYoutubeLink());
        
        return songResponse;
    }
}
