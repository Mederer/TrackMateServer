package com.mitchelldederer.trackmateserver.categories;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("categories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @PostMapping("categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO newCategory) {
        return new ResponseEntity<>(categoryService.createCategory(newCategory), HttpStatus.CREATED);
    }
}
