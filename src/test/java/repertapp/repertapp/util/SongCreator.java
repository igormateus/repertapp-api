package repertapp.repertapp.util;

import java.util.ArrayList;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.domain.Tone;

public class SongCreator {
    
    public static Song createToBeSaved() {
        return Song.builder()
                .name("A casa é sua")
                .artist("Casa Worship")
                .tone(Tone.Gsus)
                .youtube_link("https://www.youtube.com/watch?v=5QHF5OQeFOs")
                .tags(new ArrayList<>())
                .build();
    }

    public static Song createValid() {
        return Song.builder()
                .name("A casa é sua")
                .artist("Casa Worship")
                .tone(Tone.Gsus)
                .youtube_link("https://www.youtube.com/watch?v=5QHF5OQeFOs")
                .tags(new ArrayList<>())
                .id(1L)
                .build();
    }

    public static Song createValidUpdated() {
        return Song.builder()
                .name("A casa é sua")
                .artist("Casa Worship")
                .tone(Tone.Gsus)
                .youtube_link("https://www.youtube.com/watch?v=5QHF5OQeFOs")
                .tags(new ArrayList<>())
                .id(1L)
                .build();
    }
}
