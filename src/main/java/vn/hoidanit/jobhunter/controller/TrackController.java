package vn.hoidanit.jobhunter.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.Tracks;
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
    @PostMapping("/tracks")
    public ResponseEntity<Tracks> CreateTrack(@RequestBody Tracks tracks) {
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
       return  ResponseEntity.ok(null);
  }
  @ApiMessage("Delete Track Success")
  @DeleteMapping("/tracks/{id}")
    public ResponseEntity<Tracks> deleteTrack(@PathVariable int id) {
        this.trackService.deleteTracks(id);
        return  ResponseEntity.ok(null);
  }
}
