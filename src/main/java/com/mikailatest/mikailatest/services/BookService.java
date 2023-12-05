package com.mikailatest.mikailatest.services;

import com.mikailatest.mikailatest.exceptions.BookIdNotFoundException;
import com.mikailatest.mikailatest.exceptions.CategoryIdNotFoundException;
import com.mikailatest.mikailatest.exceptions.NoContentException;
import com.mikailatest.mikailatest.model.Book;
import com.mikailatest.mikailatest.repositories.BookRepo;
import com.mikailatest.mikailatest.repositories.CategoryRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @PersistenceContext
    private EntityManager entityManager;

    private final Logger logger = LoggerFactory.getLogger(BookService.class);


    public Book createBook(Book book, Long cid){
        var catOp = categoryRepo.findById(cid);

        if(catOp.isEmpty()){
            logger.error("category id does not exist");
            throw new CategoryIdNotFoundException("Category with id: " + cid + " not found!");
        }

        book.setCategory(catOp.get());
        logger.info("category has been set to book");

       return bookRepo.save(book);

    }

    public Book getBookById(Long bookId){
        var bookOp = bookRepo.findById(bookId);

        if(bookOp.isEmpty()){
            throw new BookIdNotFoundException("Book with id " + bookId + " does not exist!");
        }

        return bookOp.get();
    }

    public Iterable<Book> getAllBooks(){
        if(bookRepo.findAll().size() == 0){
            throw new NoContentException("there are no books created");
        }


        return bookRepo.findAll();
    }

    public Book getBookByName(String name){


        if( bookRepo.getBookByNameOrSku(name) == null){
            throw new NoContentException("this book does not exist");
        }

       return bookRepo.getBookByNameOrSku(name);
    }

    public void updateBook(Book book, Long bookId){
        var bookOp = Optional.ofNullable(entityManager.find(Book.class, bookId));

        if(bookOp.isPresent()){
            var newBook = bookOp.get();

            if(book.getName() != null){
                newBook.setName(book.getName());
            }
            if(book.getSku() != null){
                newBook.setSku(book.getSku());
            }
            if(book.getDescription() != null){
                newBook.setDescription(book.getDescription());
            }
            if (book.getPrice() != null){
                newBook.setPrice(book.getPrice());
            }
            if(book.getImage() != null){
                newBook.setImage(book.getImage());
            }
            if(book.getStock() != null){
                newBook.setStock(book.getStock());
            }
            if(book.getCategory() != null){
                newBook.setCategory(book.getCategory());
            }

        }
        else {
            throw new NoContentException("This book does not exist ");
        }
    }

    public Iterable<Book> getBooksByCategory(Long catId){
        if(categoryRepo.findById(catId).isEmpty()){
            throw new CategoryIdNotFoundException("Category does not exist!");
        }
       return bookRepo.getBooksByCategory(catId);
    }
    public void deleteBook(Long bookId){
        if(bookRepo.findById(bookId).isPresent()){
            bookRepo.deleteById(bookId);
        }
      else{
          throw new BookIdNotFoundException("This book does not exist !");
        }
    }
}
