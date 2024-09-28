package vn.hoidanit.jobhunter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.jobhunter.domain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {
Page<Playlist>  findAll(Pageable pageable);
}
