package repertapp.repertapp.util;

import java.util.ArrayList;
import java.util.List;

import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.user.RepertappUser;

public class BandCreator {

    private static List<RepertappUser> getUser() {
        RepertappUser user = RepertappUserCreator.createToBeSaved();
        ArrayList<RepertappUser> users = new ArrayList<>();
        users.add(user);

        return users;
    }

    private static List<RepertappUser> getUser(Object n) {
        RepertappUser user = RepertappUserCreator.createToBeSaved(n);
        ArrayList<RepertappUser> users = new ArrayList<>();
        users.add(user);

        return users;
    }

    public static Band createToBeSaved() {
        Band band = new Band();

        band.setName("bandName_test");
        // band.setSetlists(null);
        // band.setMusics(null);
        band.setMembers(getUser());
        // band.setVersions(null);
        
        return band;
    }

    public static Band createToBeSaved(Object n) {
        Band band = new Band();

        band.setName("bandName_test_" + n);
        // band.setSetlists(null);
        // band.setMusics(null);
        band.setMembers(getUser(n));
        // band.setVersions(null);
        
        return band;
    }

    public static Band createValid() {
        Band band = createToBeSaved(getUser());

        band.setId(1L);
        
        return band;
    }

    public static Band createValid(Object n) {
        Band band = createToBeSaved(n);

        band.setId((Long) n);
        
        return band;
    }
    
}
