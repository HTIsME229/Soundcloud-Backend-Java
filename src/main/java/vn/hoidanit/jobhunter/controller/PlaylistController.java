package vn.hoidanit.jobhunter.controller;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.DTO.ReqPlayList;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.DTO.RestPlaylist;
import vn.hoidanit.jobhunter.domain.Playlist;
import vn.hoidanit.jobhunter.service.PlaylistService;
import vn.hoidanit.jobhunter.utils.annotation.ApiMessage;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/")
@Controller
public class PlaylistController{
    private PlaylistService playlistService;
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    @ApiMessage(("Create Playlist Success"))
    @PostMapping("/playlists/empty")
    public ResponseEntity<RestPlaylist> CreatePlaylist(@RequestBody Playlist playlist) {
        return ResponseEntity.ok(this.playlistService.handleCreateEmptyPlaylist(playlist));
    }
    @PutMapping("/playlists")
    public ResponseEntity<Playlist> UpdatePlaylist(@RequestBody ReqPlayList playlist) {
        return  ResponseEntity.ok(this.playlistService.handleUpdatePlaylist(playlist));

    }
    @DeleteMapping("playlists/{id}")
    public ResponseEntity<Void> DeletePlaylist(@PathVariable int id) {
        this.playlistService.handleDeletePlaylist(id);
        return  ResponseEntity.ok(null);
    }
@GetMapping("playlists/{id}")
    public ResponseEntity<RestPlaylist> GetPlaylistByid(@PathVariable int id) {
        return ResponseEntity.ok(this.playlistService.handleGetPlaylistById(id));
}
@GetMapping("playlists")
    public ResponseEntity<RestPaginateDto> GetPlaylistsByPaginate(Pageable pageable,@RequestParam("title") Optional<String> Otitle ) {
        String title= Otitle.isPresent() ? Otitle.get() : "";
        return ResponseEntity.ok(this.playlistService.handleGetPlaylistWithPaginate(pageable,title));
}
@GetMapping("playlists/by-user")
    public ResponseEntity<RestPaginateDto> GetPlaylistByUser(Pageable pageable,@RequestParam("title") Optional<String> Otitle) {
    String title= Otitle.isPresent() ? Otitle.get() : "";
        return ResponseEntity.ok(this.playlistService.handleGetPlaylistByUser(pageable,title));
}

}
