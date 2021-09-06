package repertapp.repertapp.util;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.Song;

public class MusicCreator {
    
    private static Band getBand() {
        Band band = BandCreator.createToBeSaved();
        
        return band;
    }

    private static Band getBand(Object n) {
        Band band = BandCreator.createToBeSaved(n);

        return band;
    }

    private static Song getSong() {
        Song song = SongCreator.createToBeSaved();
        
        return song;
    }

    private static Song getSong(Object n) {
        Song song = SongCreator.createToBeSaved(n);

        return song;
    }

    public static Music createToBeSaved() {
        Music music = new Music();

        music.setKnown(true);
        music.setScore(1000);
        // music.setCounterPlays(counterPlays);
        music.setSong(getSong());
        music.setBand(getBand());
        // music.setVersions(versions);

        return music;
    }

    public static Music createToBeSaved(Object n) {
        Music music = new Music();

        music.setKnown(true);
        music.setScore(1000);
        // music.setCounterPlays(counterPlays);
        music.setSong(getSong(n));
        music.setBand(getBand(n));
        // music.setVersions(versions);

        return music;
    }

    public static Music createValid() {
        Music music = createToBeSaved();

        music.setId(1L);

        return music;
    }

    public static Music createValid(Object n) {
        Music music = createToBeSaved(n);

        music.setId((Long) n);

        return music;
    }
}
