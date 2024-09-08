package vn.hoidanit.jobhunter.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.jobhunter.domain.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findByName(String name);
    boolean existsByName(String name);
}
