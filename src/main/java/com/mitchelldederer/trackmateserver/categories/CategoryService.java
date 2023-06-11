package com.mitchelldederer.trackmateserver.categories;

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

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category newCategory =  categoryRepository.save(CategoryMapper.dtoToModel(categoryDTO));
        return CategoryMapper.modelToDto(newCategory);
    }
}
