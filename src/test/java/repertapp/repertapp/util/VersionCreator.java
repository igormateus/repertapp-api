package repertapp.repertapp.util;

import repertapp.repertapp.domain.Music;
import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.domain.Tone;
import repertapp.repertapp.domain.Version;

public class VersionCreator {

    private static RepertappUser getUser() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        
        return user;
    }

    private static RepertappUser getUser(Object n) {
        RepertappUser user = RepertappUserCreator.createToBeSaved(n);

        return user;
    }

    private static Music getMusic() {
        Music music = MusicCreator.createToBeSaved();

        return music;
    }

    private static Music getMusic(Object n) {
        Music music = MusicCreator.createToBeSaved(n);

        return music;
    }

    public static Version createToBeSaved() {
        Version version = new Version();

        version.setMusic(getMusic());
        version.setRepertappUser(getUser());
        // version.setSetlists(setlists);
        version.setTone(Tone.A);
        
        return version;
    }

    public static Version createToBeSaved(Object n) {
        Version version = new Version();

        version.setMusic(getMusic(n));
        version.setRepertappUser(getUser(n));
        // version.setSetlists(setlists);
        version.setTone(Tone.A);
        
        return version;
    }

    public static Version createValid() {
        Version version = createToBeSaved(getUser());

        version.setId(1L);
        
        return version;
    }

    public static Version createValid(Object n) {
        Version version = createToBeSaved(n);

        version.setId((Long) n);
        
        return version;
    }
    
}
