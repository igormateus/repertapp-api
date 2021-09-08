package repertapp.repertapp.validation;

import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.repository.TagRepository;
import repertapp.repertapp.request.TagPostRequestBody;
import repertapp.repertapp.request.TagPutRequestBody;

public class TagRequestValidation {

    private static TagRepository repository;

    public static void valideAdd(TagPostRequestBody tagRequest, TagRepository tagRepository) {
        repository = tagRepository;

        checkTagNameUnique(tagRequest.getName());
    }

    public static void valideUpdate(TagPutRequestBody tagRequest, Tag tag, TagRepository tagRepository) {
        repository = tagRepository;

        if (!tag.getName().equals(tagRequest.getName()))
            checkTagNameUnique(tagRequest.getName());
    }

    private static void checkTagNameUnique(String name) {
        if (repository.existsByName(name))
            throw new ResourceAlreadyExists("Tag", "name", name);
    }    
}
