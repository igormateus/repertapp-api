package repertapp.repertapp.domain.band;

import java.util.List;

import repertapp.repertapp.domain.user.RepertappUserSummary;

public interface BandResponseBody extends BandSummary {
    List<RepertappUserSummary> getMembers();
}
