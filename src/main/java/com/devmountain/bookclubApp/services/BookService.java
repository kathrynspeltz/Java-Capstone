package com.devmountain.bookclubApp.services;

import com.devmountain.bookclubApp.dtos.BookDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BookService {
    @Transactional
    void addBook(BookDto bookDto, Long groupId);

    @Transactional
    void deleteBookById(Long bookId);

    @Transactional
    void updateBookById(BookDto bookDto);

    List<BookDto> getAllBooksByGroupId(Long groupId);

    Optional<BookDto> getBookById(Long bookId);
}
