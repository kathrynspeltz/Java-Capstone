package com.devmountain.noteApp.entites;

import com.devmountain.bookclubApp.entities.Group;
import com.devmountain.bookclubApp.entities.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GroupUser")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupUser {

    @ManyToMany
    @JsonBackReference
    private User user;

    @ManyToMany
    @JsonBackReference
    private Group group;

    public User(UserDto userDto){
        if (userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
        if (userDto.getPassword() != null){
            this.password = userDto.getPassword();
        }
    }

}