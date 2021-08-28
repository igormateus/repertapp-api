package repertapp.repertapp.validation;

import repertapp.repertapp.domain.Tag;
import repertapp.repertapp.exception.ResourceAlreadyExists;
import repertapp.repertapp.repository.TagRepository;

public class TagRequestValidation {

    private static TagRepository repository;

    public static void validePost(Tag tag, TagRepository tagRepository) {
        repository = tagRepository;

        checkTagNameUnique(tag.getName());
    }

    public static void valideUpdate(Tag tag, Tag tagRequest, TagRepository tagRepository) {
        repository = tagRepository;

        if (!isNullOrEmpty(tagRequest.getName()) && !tag.getName().equals(tagRequest.getName()))
            checkTagNameUnique(tagRequest.getName());
    }

    private static void checkTagNameUnique(String name) {
        if (repository.existsByName(name))
            throw new ResourceAlreadyExists("Tag", "name", name);
    }

    /**
     * Check if the string is null or empty
     * @param value
     * @return
     */
    private static boolean isNullOrEmpty(String value) {
        if (value != null && !value.isEmpty())
            return false;

        return true;
    }

    
}
