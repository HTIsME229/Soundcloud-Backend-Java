package vn.hoidanit.jobhunter.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.Comment;
import vn.hoidanit.jobhunter.domain.DTO.ReqComment;
import vn.hoidanit.jobhunter.domain.DTO.RestComment;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.RestResponse;
import vn.hoidanit.jobhunter.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/comments")
    public ResponseEntity<RestComment> CreateComment(@RequestBody ReqComment comment){
        return ResponseEntity.ok(this.commentService.handleCreateComment(comment));

    }
    @GetMapping("tracks/comments")
    public ResponseEntity<RestPaginateDto> GetCommentsByTrack(Pageable pageable, @RequestParam String trackId){
        return ResponseEntity.ok(this.commentService.handleGetCommentsByTrack(pageable,trackId));

    }
}
