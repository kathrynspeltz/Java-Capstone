package com.devmountain.bookclubApp.services;

import com.devmountain.bookclubApp.dtos.CommentDto;
import com.devmountain.bookclubApp.entities.Comment;
import com.devmountain.bookclubApp.entities.Group;
import com.devmountain.bookclubApp.repositories.CommentRepository;
import com.devmountain.bookclubApp.repositories.GroupRepository;
import com.devmountain.bookclubApp.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public List<CommentDto> getAllCommentsByGroupId(Long groupId){
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if (groupOptional.isPresent()){
            List<Comment> commentList = commentRepository.findAllByGroupEquals(groupOptional.get());
            return commentList.stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addComment(CommentDto commentDto, Long groupId) {
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        Comment comment = new Comment(commentDto);
        groupOptional.ifPresent(comment::setGroup);
        commentRepository.saveAndFlush(comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        commentOptional.ifPresent(comment -> commentRepository.delete(comment));
    }

    @Override
    @Transactional
    public void updateCommentById(CommentDto commentDto) {
        Optional<Comment> commentOptional = commentRepository.findById(commentDto.getId());
        commentOptional.ifPresent(comment -> {
            comment.setCommentText(commentDto.getCommentText());
            commentRepository.saveAndFlush(comment);
        });
    }

    @Override
    public Optional<CommentDto> getCommentById(Long commentId) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()){
            return Optional.of(new CommentDto(commentOptional.get()));
        }
        return Optional.empty();
    }



}
