package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.Tracks;
import vn.hoidanit.jobhunter.repository.TrackRepository;
import vn.hoidanit.jobhunter.service.Specfication.SpecificationsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class TrackService {
    private TrackRepository trackRepository;
    private SpecificationsBuilder specificationsBuilder;
    public TrackService(TrackRepository trackRepository, SpecificationsBuilder specificationsBuilder) {
        this.trackRepository = trackRepository;
        this.specificationsBuilder = specificationsBuilder;

    }
    public Tracks handleCreateTracks(Tracks tracks) {
        Tracks newTracks = new Tracks();
        newTracks.setCategory(tracks.getCategory());
        newTracks.setDescription(tracks.getDescription());
        newTracks.setUrl(tracks.getUrl());
        newTracks.setTitle(tracks.getTitle());
        newTracks.setImgUrl(tracks.getImgUrl());
        return this.trackRepository.save(newTracks);
    }
    public RestPaginateDto handleGetTracks(Pageable pageable,String category) {
        RestPaginateDto res = new RestPaginateDto();
        Specification<Tracks> tracksSpecification = Specification.where(null);
                     tracksSpecification = tracksSpecification.and(specificationsBuilder.whereAttributeContains("category", category));
                Page<Tracks> tracks = this.trackRepository.findAll(tracksSpecification,pageable);
        List<Tracks> tracksList = tracks.getContent();
        res.setResult(tracksList);
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
}
