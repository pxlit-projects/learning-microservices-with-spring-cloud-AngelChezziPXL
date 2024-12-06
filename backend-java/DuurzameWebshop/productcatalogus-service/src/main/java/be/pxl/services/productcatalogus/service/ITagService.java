package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Tag;

import java.util.List;
import java.util.Set;

public interface ITagService {
    public Tag addTag(String tag);
    public List<Tag> getAllTags();
    public Tag findTagByTagName(String tagName);
    public Tag findTagById(Long id);
    public boolean tagNameExists(String tagName);
    public void updateTag(Tag tag);

}
