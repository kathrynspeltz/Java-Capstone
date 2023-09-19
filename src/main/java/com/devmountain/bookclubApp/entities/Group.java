package com.devmountain.bookclubApp.entities;

import com.devmountain.bookclubApp.dtos.GroupDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String groupName;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "group",  fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference(value = "groups_book")
    private Set<Book> books = new HashSet<>();

    @OneToMany(mappedBy = "group",  fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference(value = "groups_comment")
    private Set<Comment> comments = new HashSet<>();

    public Group(GroupDto groupDto) {
        if(groupDto.getGroupName() != null){
            this.groupName = groupDto.getGroupName();
        }
    }

}