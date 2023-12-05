package com.mikailatest.mikailatest.controller;

import com.mikailatest.mikailatest.model.Category;
import com.mikailatest.mikailatest.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/cat")
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }
    @GetMapping("/cat")
    public ResponseEntity<?> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);

    }
    @PutMapping("/cat/{catId}")
    public ResponseEntity<?> editCategory(@RequestBody Category category, @PathVariable Long catId){
        categoryService.editCategory(category, catId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
