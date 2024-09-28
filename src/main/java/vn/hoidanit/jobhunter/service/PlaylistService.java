package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.DTO.ReqPlayList;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.DTO.RestPlaylist;
import vn.hoidanit.jobhunter.domain.DTO.RestUser;
import vn.hoidanit.jobhunter.domain.Playlist;
import vn.hoidanit.jobhunter.domain.Tracks;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.PlaylistRepository;
import vn.hoidanit.jobhunter.repository.TrackRepository;
import vn.hoidanit.jobhunter.repository.UserRepository;
import vn.hoidanit.jobhunter.utils.SecurityUtil;

import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    public PlaylistService(PlaylistRepository playlistRepository, TrackRepository trackRepository, UserRepository userRepository) {
        this.playlistRepository = playlistRepository;
        this.trackRepository = trackRepository;
        this.userRepository = userRepository;
    }

    public RestPlaylist handleCreateEmptyPlaylist(Playlist playlist) {
        Playlist newPlaylist = new Playlist();
        newPlaylist.setPublic(playlist.isPublic());
        newPlaylist.setTitle(playlist.getTitle());
        String Email = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get():null;
        User user = userRepository.findByEmail(Email);
        RestUser restUser = new RestUser();
        if (user != null) {
            newPlaylist.setUser(user);
            restUser.setEmail(Email);
            restUser.setAddress(user.getAddress());
            restUser.setName(user.getName());
            restUser.setAge(user.getAge());
            restUser.setGender(user.getGender());
            restUser.setId(user.getId());
            restUser.setType(user.getType());
            restUser.setRole(user.getRoles() != null  ? user.getRoles().getName() :"");
        }
       Playlist savePlaylist= this.playlistRepository.save(newPlaylist);
       RestPlaylist restPlaylist = new RestPlaylist();
       restPlaylist.setPublic(savePlaylist.isPublic());
       restPlaylist.setTitle(savePlaylist.getTitle());
       restPlaylist.setCreatedAt(savePlaylist.getCreatedAt());
    restPlaylist.setCreatedBy(savePlaylist.getCreatedBy());
       restPlaylist.setId(savePlaylist.getId());
       restPlaylist.setUser(restUser);



       return restPlaylist;

    }
    public Playlist handleUpdatePlaylist (ReqPlayList reqPlayList) {
        Optional<Playlist> currentPlaylist = this.playlistRepository.findById( (int)reqPlayList.getId());
        if(!currentPlaylist.isPresent()) {
            throw  new  RuntimeException("Playlist not found");

        }
        currentPlaylist.get().setPublic(reqPlayList.isPublic());
        currentPlaylist.get().setTitle(reqPlayList.getTitle());
        List<Integer> listId = Arrays.stream(reqPlayList.getTracks()).map( e->e.getId()).toList();

        List<Tracks> listTrackInPlaylist= this.trackRepository.findByIdIn(listId);
        currentPlaylist.get().setTracks(listTrackInPlaylist);
        return this.playlistRepository.save(currentPlaylist.get());
    }
    public void handleDeletePlaylist (int id) {
        this.playlistRepository.deleteById(id);
    }
    public  RestPlaylist handleGetPlaylistById(int id) {
        Optional<Playlist> playlist = this.playlistRepository.findById(id);
        RestPlaylist restPlaylist = new RestPlaylist();

        if(!playlist.isPresent()) {
            throw  new  RuntimeException("Playlist not found");
        }
        User user = playlist.get().getUser();
        RestUser restUser = new RestUser();
        if(user != null) {
            restUser.setEmail(user.getEmail());
            restUser.setAddress(user.getAddress());
            restUser.setName(user.getName());
            restUser.setAge(user.getAge());
            restUser.setGender(user.getGender());
            restUser.setId(user.getId());
            restUser.setType(user.getType());
            restUser.setRole(user.getRoles() != null  ? user.getRoles().getName() :"");
        }
        restPlaylist.setUser(restUser);
        restPlaylist.setPublic(playlist.get().isPublic());
        restPlaylist.setTitle(playlist.get().getTitle());
        restPlaylist.setCreatedAt(playlist.get().getCreatedAt());
        restPlaylist.setCreatedBy(playlist.get().getCreatedBy());
        restPlaylist.setId(playlist.get().getId());
        return restPlaylist;
    }
    public RestPaginateDto handleGetPlaylistWithPaginate(Pageable pageable) {
        RestPaginateDto res = new RestPaginateDto();
Page<Playlist> pagePlaylist = this.playlistRepository.findAll(pageable);
List<Playlist> playlists = pagePlaylist.getContent();
List<RestPlaylist> restPlaylists = new ArrayList<>();
            playlists.forEach(playlist -> {
                RestUser restUser = new RestUser();
                User user = playlist.getUser();
                if(user != null) {
                    restUser.setEmail(user.getEmail());
                    restUser.setAddress(user.getAddress());
                    restUser.setName(user.getName());
                    restUser.setAge(user.getAge());
                    restUser.setGender(user.getGender());
                    restUser.setId(user.getId());
                    restUser.setType(user.getType());
                    restUser.setRole(user.getRoles() != null  ? user.getRoles().getName() :"");

                }
                RestPlaylist restPlaylist = new RestPlaylist();
                restPlaylist.setPublic(playlist.isPublic());
                restPlaylist.setTitle(playlist.getTitle());
                restPlaylist.setCreatedAt(playlist.getCreatedAt());
                restPlaylist.setCreatedBy(playlist.getCreatedBy());
                restPlaylist.setId(playlist.getId());
                restPlaylist.setUser(restUser);
                restPlaylists.add(restPlaylist);
            });

res.setResult(restPlaylists);
        RestPaginateDto.Meta meta = new RestPaginateDto.Meta();
        meta.setCurrent(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setTotalsItems((int) pagePlaylist.getTotalElements());
        meta.setTotalsPage(pagePlaylist.getTotalPages());
        res.setMeta(meta);
        return res;



    }
}
