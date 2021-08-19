package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Song;
import repertapp.repertapp.payload.SongRequest;
import repertapp.repertapp.payload.SongResponse;

@Mapper(componentModel = "spring")
public abstract class SongMapper {
    
    public static final SongMapper INSTANCE = Mappers.getMapper(SongMapper.class);

    public abstract Song toSong(SongRequest songRequest);

    public abstract SongResponse toSongResponse(Song song);
}
