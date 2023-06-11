package com.mitchelldederer.trackmateserver.categories;

import com.mitchelldederer.trackmateserver.exceptions.AppEntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();

        categoryRepository.findAll().forEach(category -> categoryDTOList.add(CategoryMapper.modelToDto(category)));
        return categoryDTOList;
    }

    public CategoryDTO getCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(AppEntityNotFoundException::new);
        return CategoryMapper.modelToDto(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category newCategory =  categoryRepository.save(CategoryMapper.dtoToModel(categoryDTO));
        return CategoryMapper.modelToDto(newCategory);
    }

    public void deleteCategory(int categoryId) {
         categoryRepository.deleteById(categoryId);
    }

    public CategoryDTO updateCategory(CategoryDTO updatedCategoryDto) {
        Category category = categoryRepository.findById(updatedCategoryDto.categoryId()).orElseThrow(AppEntityNotFoundException::new);

        category.setCategoryName(updatedCategoryDto.categoryName());
        category.setCategoryDescription(updatedCategoryDto.categoryDescription());

        categoryRepository.save(category);
        return CategoryMapper.modelToDto(category);
    }
}
