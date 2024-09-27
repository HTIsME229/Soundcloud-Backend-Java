package vn.hoidanit.jobhunter.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.DTO.ReqLikeTrack;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.Tracks;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.TrackService;
import vn.hoidanit.jobhunter.utils.annotation.ApiMessage;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class TrackController {
    private final TrackService trackService;
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }
    @ApiMessage("Create Track Success")
    @PostMapping("/tracks")
    public ResponseEntity<Tracks> CreateTrack(@RequestBody @Valid Tracks tracks) {
           return  ResponseEntity.ok(this.trackService.handleCreateTracks(tracks));
    }
    @ApiMessage("Fetch Tracks Success")
    @GetMapping("/tracks")
    public ResponseEntity<RestPaginateDto> getAllTracks(Pageable pageable,@RequestParam("category") Optional<String> OCategory) {
        String category = OCategory.isPresent() ? OCategory.get() : "";


return ResponseEntity.ok(this.trackService.handleGetTracks(pageable,category));

    }
    @ApiMessage("Update Track Success ")
    @PatchMapping("tracks")
    public ResponseEntity<Tracks> updateTrack(@RequestBody Tracks tracks) {
        return  ResponseEntity.ok(this.trackService.handleUpdateTracks(tracks));
    }
    @ApiMessage("Fetch Tracks Success ")
  @GetMapping("/tracks/{id}")
    public ResponseEntity<Tracks> getTrackById(@PathVariable int id) {
       return  ResponseEntity.ok(this.trackService.handleGetTrackById(id));
  }
  @ApiMessage("Delete Track Success")
  @DeleteMapping("/tracks/{id}")
    public ResponseEntity<Tracks> deleteTrack(@PathVariable int id) {
        this.trackService.deleteTracks(id);
        return  ResponseEntity.ok(null);
  }
  @PostMapping ("/tracks/users")
    public ResponseEntity<RestPaginateDto> getTracksByUser( Pageable pageable ,@RequestBody User user) {
                       return ResponseEntity.ok(this.trackService.handleGetTracksByUser(pageable,user.getId()));
  }
  @PostMapping("/likes")
    public ResponseEntity<Void> likeTrack(@RequestBody ReqLikeTrack likeTracks) {
        this.trackService.handleLikeDislikeTrack(likeTracks);
return  ResponseEntity.ok(null);
  }
  @GetMapping("/likes")
    public  ResponseEntity<RestPaginateDto> getLikedTracksByUser(Pageable pageable) {
        return ResponseEntity.ok(this.trackService.handleGetLikedTrackByUser(pageable));
  }
  @PostMapping("/tracks/increase-view")
    public ResponseEntity<Tracks> increaseViewTrack(@RequestBody  ReqLikeTrack likeTracks) {
      this.trackService.handleIncreaseCountView(likeTracks);
        return  ResponseEntity.ok(null);

  }
}
