package repertapp.repertapp.util;

import repertapp.repertapp.domain.tag.Tag;

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

    public static Tag createValid() {
        Tag tag = createToBeSaved();

        tag.setId(1L);
        
        return tag;
    }
    
}
