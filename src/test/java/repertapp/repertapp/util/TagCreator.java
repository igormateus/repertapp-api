package repertapp.repertapp.util;

import java.util.ArrayList;

import repertapp.repertapp.domain.Tag;

public class TagCreator {
    
    public static Tag createToBeSaved() {
        return Tag.builder()
                .name("Worship")
                .songs(new ArrayList<>())
                .build();
    }

    public static Tag createValid() {
        return Tag.builder()
                .name("Worship")
                .songs(new ArrayList<>())
                .id(1L)
                .build();
    }

    public static Tag createValidUpdated() {
        return Tag.builder()
                .name("Worship")
                .id(1L)
                .build();
    }
}
