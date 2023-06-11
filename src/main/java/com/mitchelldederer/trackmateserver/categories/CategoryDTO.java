package com.mitchelldederer.trackmateserver.categories;

public record CategoryDTO(
        int categoryId,
        String categoryName,
        String categoryDescription
) { }
