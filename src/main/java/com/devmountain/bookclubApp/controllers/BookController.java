package com.devmountain.bookclubApp.controllers;

import com.devmountain.bookclubApp.dtos.BookDto;
import com.devmountain.bookclubApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/user/{userId}")
    public List<BookDto> getAllBooksByUserId(@PathVariable Long userId){
        return bookService.getAllBooksByUserId(userId);
    }

    @GetMapping("/{bookId}")
    public Optional<BookDto> getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }

    @PostMapping("/user/{userId}")
    public void addBook(@RequestBody BookDto bookDto,@PathVariable Long userId){
        bookService.addBook(bookDto, userId);
    }

    @DeleteMapping("/{bookId}")
    public void deleteBookById(@PathVariable Long bookId){
        bookService.deleteBookById(bookId);
    }

    @PutMapping
    public void updateBook(@RequestBody BookDto bookDto){
        bookService.updateBookById(bookDto);
    }
}

