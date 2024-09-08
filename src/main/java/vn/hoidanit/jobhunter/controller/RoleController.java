package vn.hoidanit.jobhunter.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.DTO.RestPaginateDto;
import vn.hoidanit.jobhunter.domain.Role;
import vn.hoidanit.jobhunter.service.RoleService;

@Controller
@RequestMapping("/api/v1")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role res = this.roleService.handleCreateRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PutMapping("/roles")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        Role res = this.roleService.handleUpdateRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

//    @GetMapping("/roles")
//    public ResponseEntity<RestPaginateDto> getAllRoles(Pageable pageable) {
//        return ResponseEntity.ok(this.roleService.handleGetRoleWithPaginate(pageable));
//    }
//
//    @DeleteMapping("/roles/{id}")
//    public ResponseEntity<Void> deleteRole(@PathVariable long id) {
//        this.roleService.handleDeleteRole(id);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping("/roles/{id}")
//    public ResponseEntity<Role> getRole(@PathVariable long id) {
//        return ResponseEntity.ok(this.roleService.handleGetRoleById(id)
//        );
//    }

}
