package be.pxl.services.productcatalogus.service;

import be.pxl.services.productcatalogus.builders.CategoryBuilder;
import be.pxl.services.productcatalogus.controller.dto.CategoryRecord;
import be.pxl.services.productcatalogus.controller.dto.CategoryRequest;
import be.pxl.services.productcatalogus.domain.Category;
import be.pxl.services.productcatalogus.exception.ConflictException;
import be.pxl.services.productcatalogus.exception.ResourceNotFoundException;
import be.pxl.services.productcatalogus.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTests {
    @Mock
    private CategoryRepository categoryRepositoryMock;
    private CategoryBuilder categoryBuilder = new CategoryBuilder();

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void findAll_ShouldReturnAllCategories() throws Exception {
        //ARRANGE
        List<Category> categories = getRandomCategoryList(5);
        Mockito.when(categoryRepositoryMock.findAll()).thenReturn(categories);
        //ACT
        var result = categoryService.findAll();

        //ASSERT
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findAll();
        Assertions.assertEquals(categories.size(), result.size());
        Assertions.assertInstanceOf(CategoryRecord.class,result.getFirst());
    }

    @Test
    public void findById_WithValidId_ShouldReturnCatergoryRecord() throws Exception {
        //ARRANGE
        Long validId = 1L;
        Category category = categoryBuilder.withId(validId).build();
        Mockito.when(categoryRepositoryMock.findById(validId)).thenReturn(Optional.of(category));

        //ACT
        var result = categoryService.findCategoryById(validId);

        //ASSERT
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findById(validId);
        Assertions.assertNotNull(result);
        Assertions.assertInstanceOf(CategoryRecord.class,result);
        Assertions.assertEquals(category.getName(), result.name());
    }

    @Test
    public void findCategoryById_WithInvalidId_ShouldThrowResourceNotFoundException() throws Exception {
        //ARRANGE
        Long invalidId = 1L;
        Category category = categoryBuilder.withId(invalidId).build();
        Mockito.when(categoryRepositoryMock.findById(invalidId)).thenThrow(new ResourceNotFoundException(String.format("Category with id %d not found", invalidId)));

        //ACT & ASSERT
        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class,() -> categoryService.findCategoryById(invalidId));
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findById(invalidId);
        Assertions.assertTrue(ex.getMessage().contains(String.format("%d not found", invalidId)));
    }

    @Test
    public void findCategoryByName_WithValidName_ShouldCategoryRecord() throws Exception {
        //ARRANGE
        String validName = "valid category";
        Category category = categoryBuilder.withName(validName).build();
        CategoryRecord expectedResult = new CategoryRecord(category.getId(), category.getName());
        Mockito.when(categoryRepositoryMock.findByName(validName)).thenReturn(Optional.of(category));

        //ACT
        var result = categoryService.findCategoryByName(validName);

        // ASSERT
        Assertions.assertNotNull(result);
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findByName(Mockito.anyString());
        Assertions.assertEquals(category.getName(), result.name());
        Assertions.assertInstanceOf(CategoryRecord.class,result);
    }

    @Test
    public void findCategoryByName_WithInvalidName_ShouldThrowResourceNotFoundException() throws Exception {
        //ARRANGE
        String invalidName = "invalid category";
        Category category = categoryBuilder.withName(invalidName).build();
        Mockito.when(categoryRepositoryMock.findByName(invalidName)).thenThrow(new ResourceNotFoundException(String.format("Category with name %s not found", invalidName)));

        //ACT & ASSERT
        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class,() -> categoryService.findCategoryByName(invalidName));
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findByName(invalidName);
        Assertions.assertTrue(ex.getMessage().contains(String.format("%s not found", invalidName)));
    }

    @Test
    public void addCategory_WithValidInput_ShouldSaveCategoryViaRepository() throws Exception {
        //ARRANGE
        Category category = categoryBuilder.withId(null).build();

        Category returnCategory = new Category();
        returnCategory.setName(category.getName());
        returnCategory.setId(1L);

        CategoryRequest validInput = new CategoryRequest();
        validInput.setCategoryName(category.getName());

        Mockito.when(categoryRepositoryMock.save(Mockito.any(Category.class))).thenReturn(returnCategory);
        Mockito.when(categoryRepositoryMock.findByName(Mockito.anyString())).thenReturn(Optional.empty());

        //ACT
        categoryService.addCategory(validInput);

        //ASSERT
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findByName(validInput.getCategoryName());
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    public void addCategory_WithExistingName_ShouldThrowConflictException() throws Exception {
        //ARRANGE
        String invalidName = "invalid category name";
        Category category = categoryBuilder.withName(invalidName).build();
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName(category.getName());
        Mockito.when(categoryRepositoryMock.findByName(invalidName)).thenReturn(Optional.of(category));

        //ACT & ASSERT
        ConflictException ex = Assertions.assertThrows(ConflictException.class,() -> categoryService.addCategory(categoryRequest));
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findByName(invalidName);
        Assertions.assertTrue(ex.getMessage().contains(invalidName), "The error message must contain the category name.");
        Assertions.assertTrue(ex.getMessage().contains("already exists"),"The error message must contain the words :'alreay exists");
        Mockito.verify(categoryRepositoryMock, Mockito.never()).save(Mockito.any(Category.class));
    }

    @Test
    public void updateCategory_WithValidIdAndUniqueName_ShouldUpdateCategory() throws Exception {
        //ARRANGE
        Long validId = 1L;
        String validName = "valid category";
        Category category = categoryBuilder.withId(validId).build();

        Mockito.when(categoryRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(category));
        Mockito.when(categoryRepositoryMock.findByName(validName)).thenReturn(Optional.empty());
        Mockito.when(categoryRepositoryMock.save(Mockito.any(Category.class))).thenReturn(category);

        //ACT
        categoryService.updateCategoryName(validId, validName);

        //ASSERT
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findById(validId);
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findByName(Mockito.anyString());
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).save(Mockito.any(Category.class));
    }

    @Test
    public void updateCategory_WithInvalidId_ShouldThrowResourceNotFoundException() throws Exception {
        //ARRANGE
        Long invalidId = 1L;
        Category category = categoryBuilder.withId(invalidId).build();
        Mockito.when(categoryRepositoryMock.findById(invalidId)).thenThrow(new ResourceNotFoundException(String.format("Category with id %d not found", invalidId)));

        //ACT & ASSERT
        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class,() -> categoryService.updateCategoryName(invalidId, Mockito.anyString()));
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findById(invalidId);
        Assertions.assertTrue(ex.getMessage().contains(String.format("%d not found", invalidId)));
        Mockito.verify(categoryRepositoryMock, Mockito.never()).save(Mockito.any(Category.class));
    }

    @Test
    public void updateCategory_WithValidId_ButInvalidName_ShouldThrowConflictException() throws Exception {
        //ARRANGE
        Long validId = 1L;
        String invalidName = "invalid category name";
        Category category = categoryBuilder.withId(validId).build();

        Mockito.when(categoryRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(category));
        Mockito.when(categoryRepositoryMock.findByName(invalidName)).thenReturn(Optional.of(categoryBuilder.withId(validId).withName(invalidName).build()));

        //ACT & ASSERT
        ConflictException ex = Assertions.assertThrows(ConflictException.class,() -> categoryService.updateCategoryName(validId, invalidName));
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findByName(invalidName);
        Assertions.assertTrue(ex.getMessage().contains(invalidName), "The error message must contain the category name.");
        Assertions.assertTrue(ex.getMessage().contains("already exists"), "The error message must contain the words 'already exists'");
        Mockito.verify(categoryRepositoryMock, Mockito.never()).save(Mockito.any(Category.class));
    }

    @Test
    public void deleteCategoryById_invalidId_ShouldThrowResourceNotFoundException() throws Exception {
        //ARRANGE
        Long invalidId = 1L;

        Mockito.when(categoryRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        //ACT & ASSERT
        ResourceNotFoundException ex = Assertions.assertThrows(ResourceNotFoundException.class,() -> categoryService.deleteCategoryById(invalidId));
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findById(invalidId);
        Assertions.assertTrue(ex.getMessage().contains(invalidId.toString()));
        Assertions.assertTrue(ex.getMessage().contains("not found"));
        Mockito.verify(categoryRepositoryMock, Mockito.never()).deleteById(Mockito.anyLong());
    }

    @Test
    public void deleteCategoryById_ValidId_ShouldDeleteCategory() throws Exception {
        //ARRANGE
        Long validId = 1L;
        Category category = categoryBuilder.withId(validId).build();

        Mockito.when(categoryRepositoryMock.findById(validId)).thenReturn(Optional.of(category));
        Mockito.doNothing().when(categoryRepositoryMock).deleteById(Mockito.anyLong());

        //ACT
        categoryService.deleteCategoryById(validId);

        //ASSERT
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).findById(validId);
        Mockito.verify(categoryRepositoryMock, Mockito.times(1)).deleteById(validId);
    }


    //@Test
    public void testName() throws Exception {
        //ARRANGE

        //ACT

        //ASSERT
    }


    private List<Category> getRandomCategoryList(int listSize){
        List<Category> categories = new ArrayList<>();
        while(categories.size() < listSize){
            Category category = categoryBuilder.build();
            categories.add(category);
        }
        return categories;
    }

}
