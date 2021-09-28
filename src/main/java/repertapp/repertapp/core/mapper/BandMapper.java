package repertapp.repertapp.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.band.Band;
import repertapp.repertapp.domain.band.BandPostRequestBody;
import repertapp.repertapp.domain.band.BandPutRequestBody;
import repertapp.repertapp.domain.band.BandResponseBody;

@Mapper(componentModel = "spring")
public abstract class BandMapper {
    
    public static final BandMapper INSTANCE = Mappers.getMapper(BandMapper.class);

    public abstract Band toBand(BandPostRequestBody band);
    public abstract Band toBand(BandPutRequestBody band);

    public abstract BandResponseBody toBandResponseBody(Band bandSaved);
}
