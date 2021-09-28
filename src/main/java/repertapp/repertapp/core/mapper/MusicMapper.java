package repertapp.repertapp.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.music.Music;
import repertapp.repertapp.domain.music.MusicPostRequestBody;
import repertapp.repertapp.domain.music.MusicPutRequestBody;
import repertapp.repertapp.domain.music.MusicResponseBody;

@Mapper(componentModel = "spring")
public abstract class MusicMapper {
    
    public static final MusicMapper INSTANCE = Mappers.getMapper(MusicMapper.class);

    public abstract Music toMusic(MusicPostRequestBody music);
    public abstract Music toMusic(MusicPutRequestBody music);
    
    public abstract MusicResponseBody toMusicResponseBody(Music musicSaved);
}
