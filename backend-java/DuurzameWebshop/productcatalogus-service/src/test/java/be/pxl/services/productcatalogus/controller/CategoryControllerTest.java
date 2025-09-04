package be.pxl.services.productcatalogus.controller;

import be.pxl.services.productcatalogus.builders.CategoryBuilder;
import be.pxl.services.productcatalogus.controller.dto.CategoryRecord;
import be.pxl.services.productcatalogus.controller.dto.CategoryRequest;
import be.pxl.services.productcatalogus.exception.ConflictException;
import be.pxl.services.productcatalogus.exception.ResourceNotFoundException;
import be.pxl.services.productcatalogus.service.ICategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
public class CategoryControllerTest {
    private CategoryBuilder categoryBuilder = new CategoryBuilder();
    private String CATEGORY_URL = "/api/category";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ICategoryService categoryServiceMock;

    @Test
    public void getAllCategories_ShouldReturnOkAndAllCategories() throws Exception {
        //ARRANGE
        List<CategoryRecord> categoryRecords = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            categoryRecords.add(new CategoryRecord((long)i, UUID.randomUUID().toString()));
        }

        Mockito.when(categoryServiceMock.findAll()).thenReturn(categoryRecords);

        //ACT & ASSERT
        var respopnse = mockMvc.perform(get(CATEGORY_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(5));

        Mockito.verify(categoryServiceMock, Mockito.times(1)).findAll();
    }


    @Test
    public void getCategoryById_ShouldReturnOkAndCategoryById() throws Exception {
        //Arrange
        Long id = 1L;
        String requestURL = CATEGORY_URL + "/" + id;
        String name = "category name";
        CategoryRecord categoryRecord = new CategoryRecord(id, name);
        Mockito.when(categoryServiceMock.findCategoryById(Mockito.anyLong())).thenReturn(categoryRecord);

        //ACT & ASSERT
        var response = mockMvc.perform(get(requestURL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name));

        Mockito.verify(categoryServiceMock, Mockito.times(1)).findCategoryById(Mockito.anyLong());
    }

    @Test
    public void getCategoryById_WithInvalidId_ShouldReturnNotFound() throws Exception {
        //Arrange
        Long invalidId = 1L;
        String requestURL = CATEGORY_URL + "/" + invalidId;
        String name = "category name";
        CategoryRecord categoryRecord = new CategoryRecord(invalidId, name);
        Mockito.when(categoryServiceMock.findCategoryById(Mockito.anyLong())).thenThrow(new ResourceNotFoundException("Id " + invalidId + " not found"));

        //ACT & ASSERT
        var response = mockMvc.perform(get(requestURL))
                .andExpect(status().isNotFound());

        Mockito.verify(categoryServiceMock, Mockito.times(1)).findCategoryById(Mockito.anyLong());
    }

    @Test
    public void createCategory_WithValidRequestBody_ShouldReturnCreated() throws Exception {
        //Arrange
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName("category name");

        Mockito.doNothing().when(categoryServiceMock).addCategory(Mockito.any(CategoryRequest.class));

        //ACT & ASSERT
        var response = mockMvc.perform(post(CATEGORY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isCreated());

        Mockito.verify(categoryServiceMock, Mockito.times(1)).addCategory(Mockito.any(CategoryRequest.class));
    }

    @Test
    public void createCategory_WithInvalidRequestBody_ShouldReturnBadRequest() throws Exception {
        //Arrange
        CategoryRequest categoryRequest = new CategoryRequest();

        //ACT & ASSERT
        var response = mockMvc.perform(post(CATEGORY_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isBadRequest());

        Mockito.verify(categoryServiceMock, Mockito.never()).addCategory(Mockito.any(CategoryRequest.class));
    }

    @Test
    public void updateCategoryName_WithValidIdAndValidRequestBody_ShouldReturnAccepted() throws Exception {
        //Arrange
        Long validId = 1L;
        String validName = "valid name";
        String requestURL = CATEGORY_URL + "/" + validId;
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName(validName);

        Mockito.doNothing().when(categoryServiceMock).updateCategoryName(Mockito.anyLong(), Mockito.anyString());

        //ACT & ASSERT
        var response = mockMvc.perform(put(requestURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isAccepted());

        Mockito.verify(categoryServiceMock, Mockito.times(1)).updateCategoryName(validId, validName);
    }

    @Test
    public void updateCategoryName_WithinValidIdAndValidRequestBody_ShouldReturnNotFound() throws Exception {
        //Arrange
        Long invalidId = 1L;
        String validName = "valid name";
        String requestURL = CATEGORY_URL + "/" + invalidId;
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName(validName);

        Mockito.doThrow(new ResourceNotFoundException("Id " + invalidId + "not found.") ).when(categoryServiceMock).updateCategoryName(Mockito.anyLong(), Mockito.anyString());

        //ACT & ASSERT
        var response = mockMvc.perform(put(requestURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isNotFound());

        Mockito.verify(categoryServiceMock, Mockito.times(1)).updateCategoryName(invalidId, validName);
    }

    @Test
    public void updateCategoryName_WithValidIdAndInValidName_ShouldReturnConflict() throws Exception {
        //Arrange
        Long validId = 1L;
        String invalidName = "invalid name";
        String requestURL = CATEGORY_URL + "/" + validId;
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setCategoryName(invalidName);

        Mockito.doThrow(new ConflictException("Category already exists.")).when(categoryServiceMock).updateCategoryName(validId, invalidName);

        //ACT & ASSERT
        var response = mockMvc.perform(put(requestURL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryRequest)))
                .andExpect(status().isConflict());

        Mockito.verify(categoryServiceMock, Mockito.times(1)).updateCategoryName(validId, invalidName);
    }

    @Test
    public void deleteById_WithValidId_ShouldReturnNoContent() throws Exception {
        //Arrange
        Long validId = 1L;
        String requestURL = CATEGORY_URL + "/" + validId;

        Mockito.doNothing().when(categoryServiceMock).deleteCategoryById(validId);

        //ACT & ASSERT
        var response = mockMvc.perform(delete(requestURL))
                .andExpect(status().isNoContent());

        Mockito.verify(categoryServiceMock, Mockito.times(1)).deleteCategoryById(validId);
    }

    @Test
    public void deleteById_WithInvalidId_ShouldReturnConfict() throws Exception {
        //Arrange
        Long invalidId = 1L;
        String requestURL = CATEGORY_URL + "/" + invalidId;

        Mockito.doThrow(new ResourceNotFoundException("Category with id " + invalidId + " not found.")).when(categoryServiceMock).deleteCategoryById(invalidId);

        //ACT & ASSERT
        var response = mockMvc.perform(delete(requestURL))
                .andExpect(status().isNotFound());

        Mockito.verify(categoryServiceMock, Mockito.times(1)).deleteCategoryById(invalidId);
    }
}
