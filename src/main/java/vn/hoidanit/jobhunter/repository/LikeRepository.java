package vn.hoidanit.jobhunter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.jobhunter.domain.Like;
import vn.hoidanit.jobhunter.domain.User;

import javax.sound.midi.Track;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByUser(User user);
//    Like findByTrack(Track track);
}
