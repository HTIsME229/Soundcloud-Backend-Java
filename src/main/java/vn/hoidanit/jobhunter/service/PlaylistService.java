package vn.hoidanit.jobhunter.service;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.DTO.ReqPlayList;
import vn.hoidanit.jobhunter.domain.Playlist;
import vn.hoidanit.jobhunter.domain.Tracks;
import vn.hoidanit.jobhunter.repository.PlaylistRepository;
import vn.hoidanit.jobhunter.repository.TrackRepository;

import javax.sound.midi.Track;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    private PlaylistRepository playlistRepository;
    private TrackRepository trackRepository;
    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist handleCreateEmptyPlaylist(Playlist playlist) {
        Playlist newPlaylist = new Playlist();
        newPlaylist.setPublic(playlist.isPublic());
        newPlaylist.setTitle(playlist.getTitle());
       return this.playlistRepository.save(newPlaylist);

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
}
