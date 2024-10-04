package vn.hoidanit.jobhunter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.hoidanit.jobhunter.domain.Playlist;
import vn.hoidanit.jobhunter.domain.User;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer>, JpaSpecificationExecutor<Playlist> {

Page<Playlist>  findAll(Specification<Playlist> specification,Pageable pageable);
Page<Playlist> findByUser(Specification<Playlist> specification , Pageable pageable, User user);
}
