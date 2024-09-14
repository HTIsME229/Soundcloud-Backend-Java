package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.Comment;
import vn.hoidanit.jobhunter.domain.DTO.ReqComment;
import vn.hoidanit.jobhunter.domain.DTO.RestComment;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.Tracks;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.CommentRepository;
import vn.hoidanit.jobhunter.repository.TrackRepository;
import vn.hoidanit.jobhunter.repository.UserRepository;
import vn.hoidanit.jobhunter.utils.SecurityUtil;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private final TrackRepository trackRepository;
    private final UserRepository userRepository;
    public CommentService(CommentRepository commentRepository, TrackRepository trackRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;


    }
    public RestComment handleCreateComment(ReqComment comment) {
        Optional<Tracks> currentTrack= trackRepository.findById(comment.getTrack_id());
        if(!currentTrack.isPresent()) {
            throw  new RuntimeException("Track not found");

        }
        String email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : null;
        User currentUser = this.userRepository.findByEmail(email);
        if(currentUser == null) {
            throw  new RuntimeException("User not found");
        }


        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setMoment(comment.getMoment());
        newComment.setUser(currentUser);
        newComment.setTrack(currentTrack.get());
      Comment saveComment=  commentRepository.save(newComment);

        RestComment.trackComment trackComment = new RestComment.trackComment( (int)currentTrack.get().getId(), currentTrack.get().getDescription(), currentTrack.get().getUrl(), currentTrack.get().getTitle());
RestComment.userComment userComment = new RestComment.userComment((int)currentUser.getId(), currentUser.getEmail(), currentUser.getName(), currentUser.getRoles() != null? currentUser.getRoles().getName(): null, currentUser.getType(),currentUser.getAvatar());
        RestComment restComment = new RestComment(saveComment.getId(), saveComment.getContent(), saveComment.getMoment(), saveComment.isDeleted(), saveComment.getCreateAt(),
        saveComment.getUpdate_at(), userComment, trackComment);
        return restComment;


    }
    public RestPaginateDto handleGetCommentsByTrack (Pageable pageable, String trackId)
    {
        Optional<Tracks> currentTrack = trackRepository.findById(Integer.parseInt(trackId));
        if(!currentTrack.isPresent()) {
            throw  new RuntimeException("Track not found");
        }
        RestPaginateDto restPaginateDto = new RestPaginateDto();
        Page<Comment> comments= commentRepository.findByTrack(currentTrack.get(),pageable);
        List<Comment> commentList = comments.getContent();

        RestPaginateDto.Meta meta =new RestPaginateDto.Meta();
        meta.setCurrent(pageable.getPageNumber() +1 );
        meta.setTotalsPage(comments.getTotalPages());
        meta.setPageSize(pageable.getPageSize());
        meta.setTotalsItems((int)comments.getTotalElements());
        restPaginateDto.setMeta(meta);
        List<RestComment> restCommentList = new ArrayList<>();
        for (Comment comment : commentList) {

            RestComment.trackComment trackComment = new RestComment.trackComment((int) comment.getTrack().getId(), comment.getTrack().getDescription(), comment.getTrack().getUrl(), comment.getTrack().getTitle());
            RestComment.userComment userComment = new RestComment.userComment((int)comment.getUser().getId(), comment.getUser().getEmail(), comment.getUser().getName(),  comment.getUser().getRoles() != null? comment.getUser().getRoles().getName(): null, comment.getUser().getType(),comment.getUser().getAvatar());
            RestComment restComment = new RestComment(comment.getId(), comment.getContent(), comment.getMoment(), comment.isDeleted(), comment.getCreateAt(),
                    comment.getUpdate_at(), userComment, trackComment);
            restCommentList.add(restComment);
        };
        restPaginateDto.setResult(restCommentList);
        return restPaginateDto;
    }
}
