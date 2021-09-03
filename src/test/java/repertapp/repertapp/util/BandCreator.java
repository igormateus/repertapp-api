package repertapp.repertapp.util;

import java.util.List;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.domain.RepertappUser;

public class BandCreator {

    public static Band createToBeSaved(List<RepertappUser> users) {
        Band band = new Band();

        band.setName("bandName_test");
        // band.setSetlists(null);
        // band.setMusics(null);
        band.setMembers(users);
        
        return band;
    }

    public static Band createToBeSaved(Object n, List<RepertappUser> users) {
        Band band = new Band();

        band.setName("bandName_test_" + n);
        // band.setSetlists(null);
        // band.setMusics(null);
        band.setMembers(users);
        
        return band;
    }

    public static Band createValid(List<RepertappUser> users) {
        Band band = createToBeSaved(users);

        band.setId(1L);
        
        return band;
    }

    public static Band createValid(Object n, List<RepertappUser> users) {
        Band band = createToBeSaved(n, users);

        band.setId((Long) n);
        
        return band;
    }
    
}
