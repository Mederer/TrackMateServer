package com.mitchelldederer.trackmateserver.categories;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("categories/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategory(@PathVariable int categoryId) {
        return new ResponseEntity<>(categoryService.getCategory(categoryId), HttpStatus.OK);
    }

    @PostMapping("categories")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO newCategory) {
        return new ResponseEntity<>(categoryService.createCategory(newCategory), HttpStatus.CREATED);
    }

    @DeleteMapping("categories/{categoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int categoryId) {
        categoryService.deleteCategory(categoryId);
    }

    @PutMapping("/categories")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO updatedCategoryDto) {
        return new ResponseEntity<>(categoryService.updateCategory(updatedCategoryDto), HttpStatus.OK);
    }
}
