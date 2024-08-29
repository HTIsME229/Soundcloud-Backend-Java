package vn.hoidanit.jobhunter.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.hoidanit.jobhunter.service.TrackService;

@RestController
public class UserTrackController {
    private TrackService trackService;
    public UserTrackController(TrackService trackService) {
        this.trackService = trackService;

    }


}
