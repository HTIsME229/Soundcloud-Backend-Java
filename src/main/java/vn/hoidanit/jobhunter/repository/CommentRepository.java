package vn.hoidanit.jobhunter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.hoidanit.jobhunter.domain.Comment;
import vn.hoidanit.jobhunter.domain.Tracks;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> , JpaSpecificationExecutor<Comment> {
 Page<Comment> findByTrack(Tracks track, Pageable pageable);
}
