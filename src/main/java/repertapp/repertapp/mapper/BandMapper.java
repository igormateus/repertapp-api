package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Band;
import repertapp.repertapp.payload.BandPostRequestBody;
import repertapp.repertapp.payload.BandPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class BandMapper {
    
    public static final BandMapper INSTANCE = Mappers.getMapper(BandMapper.class);

    public abstract Band toBand(BandPostRequestBody band);
    public abstract Band toBand(BandPutRequestBody band);
}
