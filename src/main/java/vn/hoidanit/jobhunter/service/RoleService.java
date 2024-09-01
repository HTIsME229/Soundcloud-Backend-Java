package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.Role;
import vn.hoidanit.jobhunter.repository.RoleRepository;

@Service
public class RoleService {
    private RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
