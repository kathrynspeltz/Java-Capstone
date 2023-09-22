package com.devmountain.bookclubApp.services;

import com.devmountain.bookclubApp.dtos.BookDto;
import com.devmountain.bookclubApp.entities.Book;
import com.devmountain.bookclubApp.entities.User;
import com.devmountain.bookclubApp.repositories.BookRepository;
import com.devmountain.bookclubApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void addBook(BookDto bookDto, Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Book book = new Book(bookDto);
        userOptional.ifPresent(book::setUser);
        bookRepository.saveAndFlush(book);
    }

    @Override
    @Transactional
    public void deleteBookById(Long bookId) {
        Optional <Book> bookOptional = bookRepository.findById(bookId);
        bookOptional.ifPresent(book -> bookRepository.delete(book));
    }

    @Override
    @Transactional
    public void updateBookById(BookDto bookDto) {
        Optional<Book> bookOptional = bookRepository.findById(bookDto.getId());
        bookOptional.ifPresent( book -> {
            book.setGenre(bookDto.getGenre());
            book.setBookName(bookDto.getBookName());
            book.setBookAuthor(bookDto.getBookAuthor());
            book.setReadStatus(bookDto.getReadStatus());
            bookRepository.saveAndFlush(book);
        });
    }

    @Override
    public List<BookDto> getAllBooksByUserId(Long userId){
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            List<Book> bookList = bookRepository.findAllByUserEquals(userOptional.get());
            return bookList.stream().map(book -> new BookDto(book)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<BookDto> getBookById(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()){
            return Optional.of(new BookDto(bookOptional.get()));
        }
        return Optional.empty();

}
}