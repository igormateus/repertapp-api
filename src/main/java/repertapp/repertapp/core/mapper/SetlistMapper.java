package repertapp.repertapp.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.setlist.Setlist;
import repertapp.repertapp.domain.setlist.SetlistPostRequestBody;
import repertapp.repertapp.domain.setlist.SetlistPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class SetlistMapper {
    
    public static final SetlistMapper INSTANCE = Mappers.getMapper(SetlistMapper.class);

    public abstract Setlist toSetlist(SetlistPostRequestBody setlist);
    public abstract Setlist toSetlist(SetlistPutRequestBody setlist);
}
