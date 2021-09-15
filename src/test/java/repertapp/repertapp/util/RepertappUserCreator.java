package repertapp.repertapp.util;

import repertapp.repertapp.domain.user.RepertappUser;

public class RepertappUserCreator {

    public static RepertappUser createToBeSaved() {
        RepertappUser user = new RepertappUser();

        user.setUsername("userUsername_test");
        user.setName("userName_test");
        user.setEmail("userEmail_test@test.com.br");
        user.setPassword("password"); // TODO: how to encode?
        // user.setBands(null);
        // user.setVersions(null);
        
        return user;
    }

    public static RepertappUser createToBeSaved(Object n) {
        RepertappUser user = new RepertappUser();

        user.setUsername("userUsername_test_" + n);
        user.setName("userName_test_" + n);
        user.setEmail("userEmail_test_" + n + "@test.com.br");
        user.setPassword("password"); // TODO: how to encode?
        
        return user;
    }

    public static RepertappUser createValid() {
        RepertappUser user = createToBeSaved();

        user.setId(1L);
        
        return user;
    }

    public static RepertappUser createValid(Object n) {
        RepertappUser user = createToBeSaved(n);

        user.setId((Long) n);
        
        return user;
    }
    
}
