package com.ingressaca.bookstoretask.repository;


import com.ingressaca.bookstoretask.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    @Query("select  bb from  Book bb where (bb.name = :name or :name is null) " +
            " and (bb.price = :price or :price is null) " +
            " and (bb.author.id = :authorId or :authorId is null)" +
            " and (bb.publisher.id = : publisherId or :publisherId is null)")
    Page <Book> findSpecificBook(String name, String price, Long authorId, Long publisherId, Pageable pageable);
}
