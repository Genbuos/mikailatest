package com.mikailatest.mikailatest.controller;

import com.mikailatest.mikailatest.model.Book;
import com.mikailatest.mikailatest.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/books/{cid}")
    public ResponseEntity<?> createBook(@RequestBody Book book, @PathVariable Long cid){
        var newBook = bookService.createBook(book, cid);

        return new ResponseEntity<>( newBook,HttpStatus.CREATED  );
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId), HttpStatus.OK);
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/books/{name}")
    public ResponseEntity<?> getBookByNameOrSku(@PathVariable String name){
        return new ResponseEntity<>(bookService.getBookByName(name), HttpStatus.OK);
    }
    @GetMapping("/books/{catId}")
    public ResponseEntity<?> getBooksByCategory(@PathVariable Long catId){
        return new ResponseEntity<>(bookService.getBooksByCategory(catId), HttpStatus.OK);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long bookId){
        bookService.updateBook(book, bookId);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
