package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Version;
import repertapp.repertapp.payload.VersionPostRequestBody;
import repertapp.repertapp.payload.VersionPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class VersionMapper {
    
    public static final VersionMapper INSTANCE = Mappers.getMapper(VersionMapper.class);

    public abstract Version toVersion(VersionPostRequestBody version);
    public abstract Version toVersion(VersionPutRequestBody version);
}
