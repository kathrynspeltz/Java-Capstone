package com.devmountain.bookclubApp.services;

import com.devmountain.bookclubApp.dtos.CommentDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<CommentDto> getAllCommentsByBookId(Long bookId);

    @Transactional
    void addComment(CommentDto commentDto, Long bookId);

    @Transactional
    void deleteCommentById(Long commentId);

    @Transactional
    void updateCommentById(CommentDto commentDto);

    Optional<CommentDto> getCommentById(Long commentId);
}
