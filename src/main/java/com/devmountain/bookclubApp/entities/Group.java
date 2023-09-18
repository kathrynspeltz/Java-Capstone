package com.devmountain.bookclubApp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String groupName;

    @ManyToMany
    @JsonBackReference
    private GroupUser user;

    public Group(GroupDto groupDto) {
        if(groupDto.getBody() != null){
            this.body = groupDto.getBody();
        }
    }

}