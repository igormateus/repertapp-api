package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.RepertappUser;
import repertapp.repertapp.payload.RepertappUserProfile;

@Mapper(componentModel = "spring")
public abstract class RepertappUserMapper {
    
    public static final RepertappUserMapper INSTANCE = Mappers.getMapper(RepertappUserMapper.class);

    public abstract RepertappUserProfile toRepertappUserProfile(RepertappUser user);
    
    // public abstract RepertappUser toRepertappUser(SongRequest songRequest);
}
