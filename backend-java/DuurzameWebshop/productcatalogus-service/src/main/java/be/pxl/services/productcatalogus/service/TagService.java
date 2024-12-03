package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Tag;
import be.pxl.services.productcatalogus.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class TagService implements ITagService {

    private final TagRepository tagRepository;

    @Override
    public void addTag(String tagName) {
        if (!tagNameExists(tagName)) {
            Tag tag = Tag.builder()
                    .name(tagName)
                    .build();
            tagRepository.save(tag);
        }
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
                .filter(t -> t.getName().equals(tagName))
                .findFirst().orElse(null);
        return tag != null;
    }
}
