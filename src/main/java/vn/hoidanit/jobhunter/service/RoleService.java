package vn.hoidanit.jobhunter.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.Role;
import vn.hoidanit.jobhunter.repository.RoleRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    public Role handleCreateRole(Role role) {
        if (this.roleRepository.existsByName(role.getName())) {
            throw new RuntimeException("Role already exists");
        }


        Role newRole = new Role();
        newRole.setName(role.getName());
        newRole.setDescription(role.getDescription());
        newRole.setActive(role.isActive());
        return this.roleRepository.save(newRole);
    }

    public Role handleUpdateRole(Role role) {
        Optional<Role> currentRole = this.roleRepository.findById((int) role.getId());
        if (!currentRole.isPresent()) {
            throw new RuntimeException("Role does not exist");
        }

            currentRole.get().setName(role.getName());
            currentRole.get().setDescription(role.getDescription());
            currentRole.get().setActive(role.isActive());
            return this.roleRepository.save(currentRole.get());


    }
}

//    public RestPaginateDTO handleGetRoleWithPaginate(Pageable pageable) {
//        Page<Role> Roles = this.roleRepository.findAll(pageable);
//        RestPaginateDTO paginateDTO = new RestPaginateDTO();
//        paginateDTO.setResult(Roles.getContent());
//        Meta meta = new Meta();
//        meta.setCurrent(pageable.getPageNumber() + 1);
//        meta.setPageSize(pageable.getPageSize());
//        meta.setTotalsItems((int) Roles.getTotalElements());
//        meta.setTotalsPage(Roles.getTotalPages());
//        paginateDTO.setMeta(meta);
//        return paginateDTO;
//    }
//
//    public void handleDeleteRole(long id) {
//        Optional<Role> currentRole = this.roleRepository.findById(id);
//        if (!currentRole.isPresent()) {
//            throw new RuntimeException("Role does not exist");
//        }
//        this.roleRepository.deleteById(id);
//    }
//
//    public Role handleGetRoleById(long id) {
//        Optional<Role> currentRole = this.roleRepository.findById(id);
//        if (!currentRole.isPresent()) {
//            throw new RuntimeException("Role does not exist");
//        }
//        return currentRole.get();
//    }
