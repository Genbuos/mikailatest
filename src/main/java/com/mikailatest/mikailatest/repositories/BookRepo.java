package com.mikailatest.mikailatest.repositories;

import com.mikailatest.mikailatest.model.Book;
import com.mikailatest.mikailatest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books WHERE name = :name OR sku = :name ", nativeQuery = true)
    Book getBookByNameOrSku(String name);

    @Query(value = "SELECT b.* FROM books b JOIN categories c on b.cat_id = c.cat_id WHERE c.cat_id = ?1 ", nativeQuery = true)
    Iterable<Book> getBooksByCategory(Long catId);
}
