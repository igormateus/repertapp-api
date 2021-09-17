package repertapp.repertapp.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.user.RepertappUser;
import repertapp.repertapp.domain.user.RepertappUserPostRequestBody;
import repertapp.repertapp.domain.user.RepertappUserPutRequestBody;
import repertapp.repertapp.domain.user.RepertappUserResponseBody;

@Mapper(componentModel = "spring")
public abstract class RepertappUserMapper {
    
    public static final RepertappUserMapper INSTANCE = Mappers.getMapper(RepertappUserMapper.class);

    public abstract RepertappUser toRepertappUser(RepertappUserPostRequestBody user);
    public abstract RepertappUser toRepertappUser(RepertappUserPutRequestBody user);

    public abstract RepertappUserResponseBody toRepertappUserResponseBody(RepertappUser user);
}
