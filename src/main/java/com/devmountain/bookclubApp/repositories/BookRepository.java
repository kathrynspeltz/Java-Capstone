package com.devmountain.bookclubApp.repositories;

import com.devmountain.bookclubApp.entities.Book;
import com.devmountain.bookclubApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByUserEquals(User user);

}
