package com.devmountain.bookclubApp.services;

import com.devmountain.bookclubApp.dtos.BookDto;
import com.devmountain.bookclubApp.entities.Book;
import com.devmountain.bookclubApp.entities.Group;
import com.devmountain.bookclubApp.repositories.BookRepository;
import com.devmountain.bookclubApp.repositories.GroupRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    @Transactional
    public void addBook(BookDto bookDto, Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        Book book = new Book(bookDto);
        groupOptional.ifPresent(book::setGroup);
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
            book.setReadByDate(bookDto.getReadByDate());
            bookRepository.saveAndFlush(book);
        });
    }

    @Override
    public List<BookDto> getAllBooksByGroupId(Long groupId){
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isPresent()) {
            List<Book> bookList = bookRepository.findAllByGroupEquals(groupOptional.get());
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