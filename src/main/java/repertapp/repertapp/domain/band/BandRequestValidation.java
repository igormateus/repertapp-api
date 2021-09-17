package repertapp.repertapp.domain.band;

import java.util.List;

import javax.validation.Valid;

import repertapp.repertapp.core.exception.ResourceAlreadyExists;
import repertapp.repertapp.core.exception.ResourceEmptyException;
import repertapp.repertapp.domain.user.RepertappUser;

public class BandRequestValidation {

    private static BandRepository repository;

    public static void valideAdd(@Valid BandPostRequestBody bandRequest, BandRepository bandRepository) {
        repository = bandRepository;

        checkBandNameUnique(bandRequest.getName());
    }

    public static void valideUpdate(@Valid BandPutRequestBody bandRequest, Band band, BandRepository bandRepository) {
        repository = bandRepository;

        checkMembersIsEmpty(bandRequest.getMembers());

        if (!(band.getName().equals(bandRequest.getName())))
            checkBandNameUnique(bandRequest.getName());
    }

    private static void checkBandNameUnique(String name) {
        if (repository.existsByName(name))
            throw new ResourceAlreadyExists("Band", "name", name);
    }

    private static void checkMembersIsEmpty(List<RepertappUser> members) {
        if (members == null || members.isEmpty())
            throw new ResourceEmptyException();
    }
}
