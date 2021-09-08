package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.request.SongPostRequestBody;
import repertapp.repertapp.request.SongPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class SongMapper {
    
    public static final SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    public abstract Song toSong(SongPostRequestBody song);
    public abstract Song toSong(SongPutRequestBody song);
}
