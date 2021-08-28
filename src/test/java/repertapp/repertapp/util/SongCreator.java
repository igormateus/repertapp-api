package repertapp.repertapp.util;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tone;

public class SongCreator {
    
    public static Song createToBeSaved() {
        Song song = new Song();

        song.setArtist("artist_test");
        song.setName("name_test");
        song.setSpotifyLink("https://www.spotify.com/br/test");
        song.setTone(Tone.A);
        song.setYoutubeLink("https://www.youtube.com/test");
        
        return song;
    }

    public static Song createToBeSaved(String number) {
        Song song = new Song();

        song.setArtist("artist_test_" + number);
        song.setName("name_test_" + number);
        song.setSpotifyLink("https://www.spotify.com/br/test_" + number);
        song.setTone(Tone.A);
        song.setYoutubeLink("https://www.youtube.com/test_" + number);
        
        return song;
    }

    public static Song createValid() {
        Song song = createToBeSaved();
        
        song.setId(1L);
        
        return song;
    }
}
