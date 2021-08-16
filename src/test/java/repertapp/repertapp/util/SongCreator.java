package repertapp.repertapp.util;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tone;

public class SongCreator {
    
    public static Song createToBeSaved() {
        return Song.builder()
                .name("A casa é sua")
                .artist("Casa Worship")
                .tone(Tone.Gsus)
                .youtubeLink("https://www.youtube.com/watch?v=5QHF5OQeFOs")
                .build();
    }

    public static Song createValid() {
        return Song.builder()
                .name("A casa é sua")
                .artist("Casa Worship")
                .tone(Tone.Gsus)
                .youtubeLink("https://www.youtube.com/watch?v=5QHF5OQeFOs")
                .id(1L)
                .build();
    }

    public static Song createValidUpdated() {
        return Song.builder()
                .name("A casa é sua")
                .artist("Casa Worship")
                .tone(Tone.Gsus)
                .youtubeLink("https://www.youtube.com/watch?v=5QHF5OQeFOs")
                .id(1L)
                .build();
    }
}
