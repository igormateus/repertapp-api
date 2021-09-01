package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Music;
import repertapp.repertapp.payload.MusicPostRequestBody;
import repertapp.repertapp.payload.MusicPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class MusicMapper {
    
    public static final MusicMapper INSTANCE = Mappers.getMapper(MusicMapper.class);

    public abstract Music toMusic(MusicPostRequestBody music);
    public abstract Music toMusic(MusicPutRequestBody music);
}
