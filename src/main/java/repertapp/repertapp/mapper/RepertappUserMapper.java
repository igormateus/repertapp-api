package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.payload.RepertappUserPostRequestBody;
import repertapp.repertapp.payload.RepertappUserPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class RepertappUserMapper {
    
    public static final RepertappUserMapper INSTANCE = Mappers.getMapper(RepertappUserMapper.class);

    public abstract RepertappUser toRepertappUser(RepertappUserPostRequestBody user);
    public abstract RepertappUser toRepertappUser(RepertappUserPutRequestBody user);
}
