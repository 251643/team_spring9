package com.sparta.hh99_w3.service;

import com.sparta.hh99_w3.dto.CommentSaveDto;
import com.sparta.hh99_w3.dto.CommentUpdateDto;
import com.sparta.hh99_w3.exception.comment.CommentException;
import com.sparta.hh99_w3.exception.comment.CommentExceptionType;
import com.sparta.hh99_w3.exception.post.PostException;
import com.sparta.hh99_w3.exception.post.PostExceptionType;
import com.sparta.hh99_w3.exception.user.UserException;
import com.sparta.hh99_w3.exception.user.UserExceptionType;
import com.sparta.hh99_w3.models.Comment;
import com.sparta.hh99_w3.models.Users;
import com.sparta.hh99_w3.repository.CommentRepository;
import com.sparta.hh99_w3.repository.PostRepository;
import com.sparta.hh99_w3.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 예외처리는 회의 후에 적용예정 우선은 각각 적용

    /* CREATE */
    public void commentSave(Long postId , CommentSaveDto commentSaveDto, Users users) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmUser(userRepository.findByUsername(users.getUsername()).orElseThrow(() ->
                new UserException(UserExceptionType.NOT_FOUND_MEMBER)));

        comment.confirmPost(postRepository.findById(postId).orElseThrow(() ->
                new PostException(PostExceptionType.POST_NOT_POUND)));

        commentRepository.save(comment);
    }

    public void saveReComment(Long postId, Long parentId, CommentSaveDto commentSaveDto, Users users) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmUser(userRepository.findByUsername(users.getUsername()).orElseThrow(() ->
                new UserException(UserExceptionType.NOT_FOUND_MEMBER)));

//        comment.confirmPostReComment(postRepository.findById(postId).orElseThrow(() ->
//                new PostException(PostExceptionType.POST_NOT_POUND)));

        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(() ->
                new CommentException(CommentExceptionType.NOT_POUND_COMMENT)));

        commentRepository.save(comment);

    }

    public void update(Long id, CommentUpdateDto commentUpdateDto, Users users) {

        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new CommentException(CommentExceptionType.NOT_POUND_COMMENT));

        if(!comment.getUsers().getUsername().equals(users.getUsername())){
            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_UPDATE_COMMENT);
        }

        commentUpdateDto.getContent().ifPresent(comment::updateContent);
    }

    public Long delete(Long id, Users users) {
        commentRepository.deleteById(id);
        return id;
    }
    public void remove(Long id) throws CommentException {
        Comment comment = commentRepository.findById(id).orElseThrow(() ->
                new CommentException(CommentExceptionType.NOT_POUND_COMMENT));

//        if(!comment.getUser().getUsername().equals(SecurityUtil.getLoginUsername())){
//            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_DELETE_COMMENT);
//        }

        comment.remove();
        List<Comment> removableCommentList = comment.findRemovableList();
        commentRepository.deleteAll(removableCommentList);
    }


    /* READ */
//    @Transactional(readOnly = true)
//    public ResponseDto<?> getAllCommentsByPost(Long postId) {
//
//        Post post = postRepository.findById(postId).orElseThrow(() ->
//                new IllegalArgumentException("댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다." + postId));
//
//        List<Comment> commentList = commentRepository.findAllByPost(post);
//        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
//
//        for (Comment comment : commentList) {
//            commentResponseDtoList.add(
//                    CommentResponseDto.builder()
//                            .id(comment.getId())
//                            .content(comment.getContent())
//                            .createdAt(comment.getCreatedAt())
//                            .modifiedAt(comment.getModifiedAt())
//                            .build()
//            );
//        }
//        return ResponseDto.success(commentResponseDtoList);
//    }


    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("찾는 포스팅이 존재하지 않습니다")
        );
    }

//    /* UPDATE */
//    @Transactional
//    public void update(Long id, CommentRequestDto dto) {
//        Comment comment = commentRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. " + id));
//        comment.update(dto.getContent());
//    }        /* DELETE */
//
//    @Transactional
//    public void delete(Long id) {
//        Comment comment = commentRepository.findById(id).orElseThrow(() ->
//                new IllegalArgumentException("해당 댓글이 존재하지 않습니다. id=" + id));
//        commentRepository.delete(comment);
//    }

}
