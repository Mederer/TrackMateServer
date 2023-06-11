package com.mitchelldederer.trackmateserver.categories;

public class CategoryMapper {

    public static CategoryDTO modelToDto(Category category) {
        return new CategoryDTO(
                category.getCategoryId(),
                category.getCategoryName(),
                category.getCategoryDescription()
        );
    }

    public static Category dtoToModel(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setCategoryName(categoryDTO.categoryName());
        category.setCategoryDescription(categoryDTO.categoryDescription());
        return category;
    }
}
