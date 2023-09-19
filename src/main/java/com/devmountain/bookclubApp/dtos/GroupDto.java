package com.devmountain.bookclubApp.dtos;


import com.devmountain.bookclubApp.entities.Group;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto implements Serializable {
    private Long id;
    private String groupName;
    private Set<CommentDto> commentDtoSet = new HashSet<>();
    private Set<UserDto> userDtoSet = new HashSet<>();
    private Set<BookDto> bookDtoSet = new HashSet<>();


    public GroupDto(Group group){
        if (group.getId() != null) {
            this.id = group.getId();
        }
        if (group.getGroupName() != null){
            this.groupName = group.getGroupName();
        }

    }
}
