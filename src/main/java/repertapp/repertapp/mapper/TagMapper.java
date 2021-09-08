package repertapp.repertapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.request.TagPostRequestBody;
import repertapp.repertapp.request.TagPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class TagMapper {
    
    public static final TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    public abstract Tag toTag(TagPostRequestBody tag);
    public abstract Tag toTag(TagPutRequestBody tag);
}
