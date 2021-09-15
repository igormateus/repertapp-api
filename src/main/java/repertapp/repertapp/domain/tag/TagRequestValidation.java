package repertapp.repertapp.domain.tag;

import repertapp.repertapp.domain.exception.ResourceAlreadyExists;

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
