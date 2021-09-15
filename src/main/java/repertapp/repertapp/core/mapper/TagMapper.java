package repertapp.repertapp.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import repertapp.repertapp.domain.tag.Tag;
import repertapp.repertapp.domain.tag.TagPostRequestBody;
import repertapp.repertapp.domain.tag.TagPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class TagMapper {
    
    public static final TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    public abstract Tag toTag(TagPostRequestBody tag);
    public abstract Tag toTag(TagPutRequestBody tag);
}
