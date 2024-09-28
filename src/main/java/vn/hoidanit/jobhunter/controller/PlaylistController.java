package vn.hoidanit.jobhunter.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.hoidanit.jobhunter.domain.DTO.ReqPlayList;
import vn.hoidanit.jobhunter.domain.Playlist;
import vn.hoidanit.jobhunter.service.PlaylistService;
@RequestMapping("/api/v1/")
@Controller
public class PlaylistController{
    private PlaylistService playlistService;
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    @PostMapping("/playlist")
    public ResponseEntity<Playlist> CreatePlaylist(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(this.playlistService.handleCreateEmptyPlaylist(playlist));
    }
    @PatchMapping("/playlist")
    public ResponseEntity<Playlist> UpdatePlaylist(@RequestBody ReqPlayList playlist) {
        return  ResponseEntity.ok(this.playlistService.handleUpdatePlaylist(playlist));

    }


}
