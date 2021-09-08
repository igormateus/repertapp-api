package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Music;
import repertapp.repertapp.request.MusicPostRequestBody;
import repertapp.repertapp.request.MusicPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class MusicMapper {
    
    public static final MusicMapper INSTANCE = Mappers.getMapper(MusicMapper.class);

    public abstract Music toMusic(MusicPostRequestBody music);
    public abstract Music toMusic(MusicPutRequestBody music);
}
