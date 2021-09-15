package repertapp.repertapp.util;

import java.time.LocalDate;
import java.util.ArrayList;

import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.setlist.Setlist;
import repertapp.repertapp.domain.version.Version;

public class SetlistCreator {

    private static ArrayList<Version> getVersions() {
        Version version = VersionCreator.createToBeSaved();
        ArrayList<Version> versions = new ArrayList<>();
        versions.add(version);
        
        return versions;
    }

    private static ArrayList<Version> getVersions(Object n) {
        Version version = VersionCreator.createToBeSaved(n);
        ArrayList<Version> versions = new ArrayList<>();
        versions.add(version);
        
        return versions;
    }

    private static Band getBand() {
        Band band = BandCreator.createToBeSaved();

        return band;
    }

    private static Band getBand(Object n) {
        Band band = BandCreator.createToBeSaved(n);

        return band;
    }

    public static Setlist createToBeSaved() {
        Setlist setlist = new Setlist();

        setlist.setBand(getBand());
        setlist.setEventDate(LocalDate.now());
        setlist.setIsDone(false);
        setlist.setName("test_name");
        setlist.setVersions(getVersions());
        
        return setlist;
    }

    public static Setlist createToBeSaved(Object n) {
        Setlist setlist = new Setlist();

        setlist.setBand(getBand(n));
        setlist.setEventDate(LocalDate.now());
        setlist.setIsDone(false);
        setlist.setName("test_name");
        setlist.setVersions(getVersions(n));
        
        return setlist;
    }

    public static Setlist createValid() {
        Setlist setlist = createToBeSaved();

        setlist.setId(1L);
        
        return setlist;
    }

    public static Setlist createValid(Object n) {
        Setlist setlist = createToBeSaved(n);

        setlist.setId((Long) n);
        
        return setlist;
    }
    
}
