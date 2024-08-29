package vn.hoidanit.jobhunter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.jobhunter.domain.Tracks;



@Repository
public interface TrackRepository extends CrudRepository<Tracks, Integer>, JpaSpecificationExecutor<Tracks> {
    Page<Tracks> findAll(Specification specification ,Pageable pageable);
}
