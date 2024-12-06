package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Tag;
import be.pxl.services.productcatalogus.domain.Utils.TagHelperMethods;
import be.pxl.services.productcatalogus.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    @Override
    public Tag addTag(String tagName) {
        if (!tagNameExists(tagName)) {
            Tag tag = Tag.builder()
                    .text(tagName)
                    .build();
            return tagRepository.save(tag);
        }
        return null;
    }



    @Override
    public List<Tag> getAllTags() {
        return List.of();
    }

    @Override
    public Tag findTagByTagName(String tagName) {
        return null;
    }

    @Override
    public Tag findTagById(Long id) {
        return null;
    }

    @Override
    public boolean tagNameExists(String tagName) {
        Tag tag = tagRepository.findAll().stream()
                .filter(t -> t.getText().equals(tagName))
                .findFirst().orElse(null);
        return tag != null;
    }



    @Override
    public void updateTag(Tag tag) {

    }
}
