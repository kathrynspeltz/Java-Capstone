package com.devmountain.bookclubApp.entities;

import com.devmountain.bookclubApp.dtos.BookDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String bookName;

    @Column(columnDefinition = "text")
    private String bookAuthor;

    @Column(columnDefinition = "text")
    private String genre;

    @Column(columnDefinition = "text")
    private LocalDate readByDate;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @JsonBackReference(value = "groups_book")
    private Group group;


    public Book(BookDto bookDto) {
        if (bookDto.getBookName() != null){
            this.bookName = bookDto.getBookName();
        }
        if (bookDto.getBookAuthor() != null){
            this.bookAuthor = bookDto.getBookAuthor();
        }
        if (bookDto.getGenre() != null){
            this.genre = bookDto.getGenre();
        }
    }

}
