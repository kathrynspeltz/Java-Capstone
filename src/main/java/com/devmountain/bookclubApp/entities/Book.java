package com.devmountain.bookclubApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Books")
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

    @ManyToMany
    @JsonBackReference
    private Group group;

    public Book(BookDto bookDto) {
        if(noteDto.getBody() != null){
            this.body = noteDto.getBody();
        }
    }

}
