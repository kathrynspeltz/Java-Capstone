package com.devmountain.bookclubApp.services;

import com.devmountain.bookclubApp.dtos.CommentDto;
import com.devmountain.bookclubApp.entities.Book;
import com.devmountain.bookclubApp.entities.Comment;
import com.devmountain.bookclubApp.repositories.BookRepository;
import com.devmountain.bookclubApp.repositories.CommentRepository;
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
    private BookRepository bookRepository;

    @Override
    public List<CommentDto> getAllCommentsByBookId(Long bookId){
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()){
            List<Comment> commentList = commentRepository.findAllByBookEquals(bookOptional.get());
            return commentList.stream().map(comment -> new CommentDto(comment)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addComment(CommentDto commentDto, Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Comment comment = new Comment(commentDto);
        bookOptional.ifPresent(comment::setBook);
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
