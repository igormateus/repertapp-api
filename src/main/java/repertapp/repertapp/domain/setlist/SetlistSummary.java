package repertapp.repertapp.domain.setlist;

import java.time.LocalDate;

public interface SetlistSummary {
    Long getId();

    String getName();

    LocalDate getEventDate();

    Boolean getIsDone();
}
