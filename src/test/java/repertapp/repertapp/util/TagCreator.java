package repertapp.repertapp.util;

import repertapp.repertapp.domain.Tag;

public class TagCreator {

    public static Tag createToBeSaved() {
        Tag tag = new Tag();

        tag.setName("name_test");
        
        return tag;
    }

    public static Tag createToBeSaved(String number) {
        Tag tag = new Tag();

        tag.setName("name_test_" + number);
        
        return tag;
    }
    
}
