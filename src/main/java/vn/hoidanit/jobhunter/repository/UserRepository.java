package vn.hoidanit.jobhunter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hoidanit.jobhunter.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsById(long id);

    User findByEmail(String email);

    boolean existsByTypeAndEmail(String type,String email);
    User findByEmailAndType(String email,String type);
}
