package com.devmountain.bookclubApp.repositories;

import com.devmountain.bookclubApp.entities.Book;
import com.devmountain.bookclubApp.entities.Group;
import com.devmountain.bookclubApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByGroupEquals(Group group);

}
