package repertapp.repertapp.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.version.Version;
import repertapp.repertapp.domain.version.VersionPostRequestBody;
import repertapp.repertapp.domain.version.VersionPutRequestBody;
import repertapp.repertapp.domain.version.VersionResponseBody;

@Mapper(componentModel = "spring")
public abstract class VersionMapper {
    
    public static final VersionMapper INSTANCE = Mappers.getMapper(VersionMapper.class);

    public abstract Version toVersion(VersionPostRequestBody version);
    public abstract Version toVersion(VersionPutRequestBody version);

    public abstract VersionResponseBody toVersionResponseBody(Version version);
}
