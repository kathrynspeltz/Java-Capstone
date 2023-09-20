package com.devmountain.bookclubApp.controllers;

import com.devmountain.bookclubApp.dtos.CommentDto;
import com.devmountain.bookclubApp.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/group/{groupId}")
    public List<CommentDto> getCommentsByGroup(@PathVariable Long groupId) {
        return commentService.getAllCommentsByGroupId(groupId);
    }

    @GetMapping("/{commentId}")
    public Optional<CommentDto> getCommentById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }

    @PostMapping("/group/{groupId}")
    public void addComment(@RequestBody CommentDto commentDto,@PathVariable Long groupId){
        commentService.addComment(commentDto, groupId);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);
    }

    @PutMapping
    public void updateComment(@RequestBody CommentDto commentDto){
        commentService.updateCommentById(commentDto);
    }
}