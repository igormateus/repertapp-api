package repertapp.repertapp.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.song.Song;
import repertapp.repertapp.domain.song.SongPostRequestBody;
import repertapp.repertapp.domain.song.SongPutRequestBody;
import repertapp.repertapp.domain.song.SongResponseBody;

@Mapper(componentModel = "spring")
public abstract class SongMapper {
    
    public static final SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    public abstract Song toSong(SongPostRequestBody song);
    public abstract Song toSong(SongPutRequestBody song);

    public abstract SongResponseBody toSongResponseBody(Song song);
}
