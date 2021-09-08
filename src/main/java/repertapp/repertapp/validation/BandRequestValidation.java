package repertapp.repertapp.validation;

import javax.validation.Valid;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.repository.BandRepository;
import repertapp.repertapp.request.BandPostRequestBody;
import repertapp.repertapp.request.BandPutRequestBody;

public class BandRequestValidation {

    private static BandRepository repository;

    public static void valideAdd(@Valid BandPostRequestBody bandRequest, BandRepository bandRepository) {
        repository = bandRepository;

        checkBandNameUnique(bandRequest.getName());
    }

    public static void valideUpdate(@Valid BandPutRequestBody bandRequest, Band band, BandRepository bandRepository) {
        repository = bandRepository;

        if (!(band.getName().equals(bandRequest.getName())))
            checkBandNameUnique(bandRequest.getName());
    }

    private static void checkBandNameUnique(String name) {
        if (repository.existsByName(name))
            throw new ResourceAlreadyExists("Band", "name", name);
    }
}
