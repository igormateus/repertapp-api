package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Setlist;
import repertapp.repertapp.request.SetlistPostRequestBody;
import repertapp.repertapp.request.SetlistPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class SetlistMapper {
    
    public static final SetlistMapper INSTANCE = Mappers.getMapper(SetlistMapper.class);

    public abstract Setlist toSetlist(SetlistPostRequestBody setlist);
    public abstract Setlist toSetlist(SetlistPutRequestBody setlist);
}
