package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.payload.SongPostRequestBody;
import repertapp.repertapp.payload.SongPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class SongMapper {
    
    public static final SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    public abstract Song toSong(SongPostRequestBody song);
    public abstract Song toSong(SongPutRequestBody song);
}
