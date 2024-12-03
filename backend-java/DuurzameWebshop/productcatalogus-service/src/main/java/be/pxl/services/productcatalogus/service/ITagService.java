package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Tag;

import java.util.List;

public interface ITagService {
    public void addTag(String tag);
    public List<Tag> getAllTags();
    public Tag findTagByTagName(String tagName);
    public Tag findTagById(Long id);
    public boolean tagNameExists(String tagName);
    public void updateTag(Tag tag);
}
