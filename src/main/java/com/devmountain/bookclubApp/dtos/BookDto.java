package com.devmountain.bookclubApp.dtos;


import com.devmountain.bookclubApp.entities.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements Serializable{

    private Long id;
    private String bookName;
    private String bookAuthor;
    private String genre;
    private String readStatus;

    public BookDto(Book book){
        if (book.getId() != null){
            this.id = book.getId();
        }
        if (book.getBookName() != null){
            this.bookName = book.getBookName();
        }
        if (book.getBookAuthor() != null){
            this.bookAuthor = book.getBookAuthor();
        }
        if (book.getGenre() != null){
            this.genre = book.getGenre();
        }
        if (book.getReadStatus() != null){
            this.readStatus = book.getReadStatus();
        }
    }
}
