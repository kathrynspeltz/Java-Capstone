package com.devmountain.bookclubApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String bookName;

    @Column(columnDefinition = "text")
    private String commentText;

    @ManyToOne
    @JsonBackReference
    private Group group;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Comment(Comment commentDto) {
        if(commentDto.getBody() != null){
            this.body = commentDto.getBody();
        }
    }

}

