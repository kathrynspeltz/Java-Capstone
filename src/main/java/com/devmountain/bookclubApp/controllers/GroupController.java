package com.devmountain.bookclubApp.controllers;

import com.devmountain.bookclubApp.dtos.CommentDto;
import com.devmountain.bookclubApp.dtos.GroupDto;
import com.devmountain.bookclubApp.dtos.UserDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @PostMapping("/groupAdd")
    public void addGroup(@RequestBody GroupDto groupDto, @PathVariable Long groupId){
        groupService.addComment(commentDto, groupId);
    }
}
