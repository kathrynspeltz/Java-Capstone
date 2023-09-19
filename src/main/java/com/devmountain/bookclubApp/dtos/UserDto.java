package com.devmountain.bookclubApp.dtos;


import com.devmountain.bookclubApp.entities.Comment;
import com.devmountain.bookclubApp.entities.Group;
import com.devmountain.bookclubApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String username;
    private String password;
    private Set<GroupDto> groupDtoSet = new HashSet<>();
    private Set<CommentDto> commentDtoSet = new HashSet<>();

    public UserDto(User user){
        if (user.getId() != null) {
            this.id = user.getId();
        }
        if (user.getUsername() != null){
            this.username = user.getUsername();
        }
        if (user.getPassword() != null) {
            this.password = user.getPassword();
        }

    }
}
