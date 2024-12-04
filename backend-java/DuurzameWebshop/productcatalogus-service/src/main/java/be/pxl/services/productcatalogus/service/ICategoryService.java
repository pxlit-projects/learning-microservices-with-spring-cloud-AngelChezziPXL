package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.domain.Category;

import java.util.List;

public interface ICategoryService {
    public Category findCategoryById(Long id);
    public List<Category> findAll();
    public Category findCategoryByName(String name);
    public void addCategory(Category category);
    public boolean categoryExists(String name);
}
