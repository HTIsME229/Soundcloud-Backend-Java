package vn.hoidanit.jobhunter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.jobhunter.domain.Like;
import vn.hoidanit.jobhunter.domain.Tracks;
import vn.hoidanit.jobhunter.domain.User;

import java.util.List;


@Repository
public interface TrackRepository extends CrudRepository<Tracks, Integer>, JpaSpecificationExecutor<Tracks> {
    Page<Tracks> findAll(Specification specification ,Pageable pageable);
    Page<Tracks> findByCreatedBy(String createdBy, Pageable pageable);
    Page<Tracks> findByLikes(Like like, Pageable pageable);
    List<Tracks> findByIdIn(List<Long> ids);
}
