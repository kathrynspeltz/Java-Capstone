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

    @GetMapping("/group/{groupId}")
    public List<BookDto> getBooksByGroup(@PathVariable Long groupId){
        return bookService.getAllBooksByGroupId(groupId);
    }

    @GetMapping("/{groupId}")
    public Optional<BookDto> getBookById(@PathVariable Long bookId){
        return bookService.getBookById(bookId);
    }

    @PostMapping("/group/{groupId}")
    public void addBook(@RequestBody BookDto bookDto,@PathVariable Long groupId){
        bookService.addBook(bookDto, groupId);
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

