package com.mikailatest.mikailatest.services;

import com.mikailatest.mikailatest.exceptions.NoContentException;
import com.mikailatest.mikailatest.model.Book;
import com.mikailatest.mikailatest.model.Category;
import com.mikailatest.mikailatest.repositories.CategoryRepo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(BookService.class);

    public Category createCategory(Category category){
        return categoryRepo.save(category);
    }

    public Iterable<Category> getAllCategories(){
        if(categoryRepo.findAll() == null){
            throw new NoContentException("There is no catgories");
        }
        return categoryRepo.findAll();
    }
    public void editCategory(Category category, Long catId){
        var catOp = Optional.ofNullable(entityManager.find(Category.class, catId));
        if(catOp.isPresent()){
            var newCat = catOp.get();

            if(category.getCategory_name() != null){
                newCat.setCategory_name(category.getCategory_name());
            }
        }
        else {
            throw new NoContentException("This book does not exist ");
        }
    }
}
