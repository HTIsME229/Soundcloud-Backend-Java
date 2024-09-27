package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.DTO.ReqLikeTrack;
import vn.hoidanit.jobhunter.domain.DTO.ResTrack;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.DTO.TrackLikeRes;
import vn.hoidanit.jobhunter.domain.Like;
import vn.hoidanit.jobhunter.domain.Tracks;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.LikeRepository;
import vn.hoidanit.jobhunter.repository.TrackRepository;
import vn.hoidanit.jobhunter.repository.UserRepository;
import vn.hoidanit.jobhunter.service.Specfication.SpecificationsBuilder;
import vn.hoidanit.jobhunter.utils.SecurityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrackService {
    private TrackRepository trackRepository;
    private UserRepository userRepository;
    private SpecificationsBuilder specificationsBuilder;
    private LikeRepository likeRepository;
    public TrackService(TrackRepository trackRepository, SpecificationsBuilder specificationsBuilder, UserRepository userRepository,LikeRepository likeRepository) {
        this.trackRepository = trackRepository;
        this.specificationsBuilder = specificationsBuilder;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;

    }
    public Tracks handleCreateTracks(Tracks tracks) {
        Tracks newTracks = new Tracks();
        newTracks.setCategory(tracks.getCategory());
        newTracks.setDescription(tracks.getDescription());
        newTracks.setUrl(tracks.getUrl());
        newTracks.setTitle(tracks.getTitle());
        newTracks.setImgUrl(tracks.getImgUrl(   ));

        return this.trackRepository.save(newTracks);
    }
    public RestPaginateDto handleGetTracks(Pageable pageable,String category) {
        RestPaginateDto res = new RestPaginateDto();
        Specification<Tracks> tracksSpecification = Specification.where(null);
                     tracksSpecification = tracksSpecification.and(specificationsBuilder.whereAttributeContains("category", category));
                Page<Tracks> tracks = this.trackRepository.findAll(tracksSpecification,pageable);
        List<Tracks> tracksList = tracks.getContent();

        List<ResTrack> resTracks = new ArrayList<>();

        for (Tracks track : tracksList) {
            User tracksUser = this.userRepository.findByEmail(track.getCreatedBy());
            System.out.println(tracksUser);
            ResTrack.Uploader uploader = new ResTrack.Uploader();

            if (tracksUser != null) {
               uploader.setEmail(tracksUser.getEmail());
               uploader.setName(tracksUser.getName());
               uploader.setId(tracksUser.getId());
               uploader.setRole(tracksUser.getRoles() != null ? tracksUser.getRoles().getName():null);
               uploader.setType(tracksUser.getType());

            }

            ResTrack item  = new ResTrack(track.getId(), track.getTitle(), track.getDescription(), track.getUrl(),
                    track.getImgUrl(), track.getCategory(), track.getCountLike(), track.getCountPlay(),
                    track.getUpdatedAt(), track.getCreatedAt(), false, uploader);
            resTracks.add(item);
        }


        res.setResult(resTracks);
        RestPaginateDto.Meta meta = new RestPaginateDto.Meta();
        meta.setCurrent(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());
        meta.setTotalsItems((int) tracks.getTotalElements());
        meta.setTotalsPage(tracks.getTotalPages());
        res.setMeta(meta);
        return res;
    }
    public  Tracks handleUpdateTracks(Tracks tracks) {
        Optional<Tracks> currentTrack = this.trackRepository.findById((int)tracks.getId());
        if(!currentTrack.isPresent()) {
         throw   new  RuntimeException("Track not Found");
        }
        currentTrack.get().setCategory(tracks.getCategory());
        currentTrack.get().setDescription(tracks.getDescription());
        currentTrack.get().setUrl(tracks.getUrl());
        currentTrack.get().setTitle(tracks.getTitle());
        currentTrack.get().setImgUrl(tracks.getImgUrl());
        return this.trackRepository.save(currentTrack.get());
    }
    public void  deleteTracks(int id) {
        Optional<Tracks> tracks = this.trackRepository.findById(id);
        if(!tracks.isPresent()) {
            throw   new  RuntimeException("Track not Found");
        }
        this.trackRepository.deleteById(id);
    }
    public RestPaginateDto handleGetTracksByUser( Pageable pageable, long id) {
        RestPaginateDto res = new RestPaginateDto();
        Optional<User> currentUser  = this.userRepository.findById(id);
        if(!currentUser.isPresent()) {
            throw   new  RuntimeException("User not Found");
        }
        Page<Tracks> Tracks = this.trackRepository.findByCreatedBy(currentUser.get().getEmail(),pageable);
        List<Tracks> tracksList = Tracks.getContent();
        res.setResult(tracksList);
        RestPaginateDto.Meta meta = new RestPaginateDto.Meta();
        meta.setCurrent(pageable.getPageNumber() +1);
        meta.setPageSize(pageable.getPageSize());
        meta.setTotalsItems((int) Tracks.getTotalElements());
        meta.setTotalsPage(Tracks.getTotalPages());
        res.setMeta(meta);
        return res;
    }
    public  Tracks handleGetTrackById(int id)
    {
        Optional<Tracks> tracks = this.trackRepository.findById(id);
        if(!tracks.isPresent()) {
            throw   new  RuntimeException("Track not Found");
        }
        return tracks.get();
    }
    public  void  handleLikeDislikeTrack(ReqLikeTrack likeTrack)
    {
        Optional<Tracks> currentTrack = this.trackRepository.findById((int)likeTrack.getTrack());
        String email= SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : null;

        User currentUser= this.userRepository.findByEmail(email);
        if(currentUser == null){
            throw   new  RuntimeException("User not Found");
        }

        Like like = this.likeRepository.findByUser(currentUser);
        if(like == null)
        {
           Like newLike = new Like();
            newLike.setUser(currentUser);
            like= this.likeRepository.save(newLike);

        }
        boolean check = false;
        List<Tracks>  tracksList = like.getTracks();
        if(tracksList.size() > 0)
        {
        for(Tracks track : tracksList)
        {
                if(currentTrack.get().getId() == track.getId()){
                    tracksList.remove(track);
                    check = true;
                    break;
                }
        }
        if(!check){
            tracksList.add(currentTrack.get());
        }
        like.setTracks(tracksList);
        }
        else {
            tracksList.add(currentTrack.get());
            like.setTracks(tracksList);
        }
            this.likeRepository.save(like);
        if(!currentTrack.isPresent()) {
            throw   new  RuntimeException("Track not Found");
        }
                currentTrack.get().setCountLike(currentTrack.get().getCountLike() + likeTrack.getQuantity());
        this.trackRepository.save(currentTrack.get());
    }
    public  RestPaginateDto handleGetLikedTrackByUser(Pageable pageable){
        String email= SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get(): "";
        User currentUser= this.userRepository.findByEmail(email);
        if(currentUser == null){
            throw   new  RuntimeException("User not Found");
        }
        Like like = currentUser.getLike();
        Page<Tracks> tracks = this.trackRepository.findByLikes(like,pageable);
        List<Tracks> tracksList = tracks.getContent();
        List<TrackLikeRes> listLikeTrack = new ArrayList<>();
        for (Tracks track : tracksList) {
                TrackLikeRes item = new TrackLikeRes(track.getId(),track.getCountPlay(), track.getCountLike(), track.getCategory(), track.getImgUrl(), track.getUrl(), track.getDescription(), track.getTitle());
            listLikeTrack.add(item);
        }
        RestPaginateDto res = new RestPaginateDto();
        RestPaginateDto.Meta meta = new RestPaginateDto.Meta();
        meta.setCurrent(pageable.getPageNumber() +1);
        meta.setPageSize(pageable.getPageSize());
        meta.setTotalsItems((int) tracks.getTotalElements());
        meta.setTotalsPage(tracks.getTotalPages());
        res.setMeta(meta);
        res.setResult(listLikeTrack);
        return res;

    }
public void  handleIncreaseCountView(ReqLikeTrack reqLikeTrack)
{
    Optional<Tracks> currentTrack = this.trackRepository.findById((int)reqLikeTrack.getTrack());
    if(!currentTrack.isPresent()) {
        throw   new  RuntimeException("Track not Found");
    }
    currentTrack.get().setCountPlay(currentTrack.get().getCountPlay() + 1);
    this.trackRepository.save(currentTrack.get());  
}
}
