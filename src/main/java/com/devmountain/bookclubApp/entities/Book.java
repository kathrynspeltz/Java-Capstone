package com.devmountain.bookclubApp.entities;

import com.devmountain.bookclubApp.dtos.BookDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    private String readStatus;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value = "user_book")
    private User user;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference(value = "book_comment")
    private Set<Comment> comments = new HashSet<>();


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
        if (bookDto.getReadStatus() != null){
            this.readStatus = bookDto.getReadStatus();
        }
    }

}
