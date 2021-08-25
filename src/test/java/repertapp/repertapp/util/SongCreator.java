package repertapp.repertapp.util;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tone;

public class SongCreator {
    
    public static Song createSongToBeSaved() {
        Song song = new Song();

        song.setArtist("artist_test");
        song.setName("name_test");
        song.setSpotifyLink("https://www.spotify.com/br/test");
        song.setTone(Tone.A);
        song.setYoutubeLink("https://www.youtube.com/test");
        
        return song;
    }
}
