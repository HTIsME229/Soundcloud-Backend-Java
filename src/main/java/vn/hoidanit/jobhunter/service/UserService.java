package vn.hoidanit.jobhunter.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.DTO.RestLoginSocial;
import vn.hoidanit.jobhunter.domain.DTO.RestUser;
import vn.hoidanit.jobhunter.domain.DTO.SocialMediaAccountDTO;
import vn.hoidanit.jobhunter.domain.Role;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;
import vn.hoidanit.jobhunter.utils.SecurityUtil;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserService {
    UserRepository userRepository;
SecurityUtil securityUtil;
    PasswordEncoder passwordEncoder;
    RoleService roleService;

    public UserService(UserRepository userRepository, SecurityUtil securityUtil, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.securityUtil = securityUtil;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public RestUser handleSaveuUser(User datauser) {
        User user = new User();
        if(this.userRepository.existsByEmail(datauser.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setEmail(datauser.getEmail());
        user.setName(datauser.getName());
        user.setPassword(this.passwordEncoder.encode(datauser.getPassword()));
        user.setAddress(datauser.getAddress());
        user.setType("SYSTEM");
        user.setVerify(true);
    //        Role role = this.roleService.getRoleByName(datauser.getRoles().getName());
    //        if (role != null) {   user.setRoles(datauser.getRoles());}
        user.setGender(datauser.getGender());
        User SaveUser = this.userRepository.save(user);
        RestUser rest = new RestUser(SaveUser.getId(),"User", SaveUser.getAge(), SaveUser.getGender(), SaveUser.getAddress(), SaveUser.getEmail(), SaveUser.getName(), SaveUser.getType(),SaveUser.getVerify());
        return rest ;
    }
    public User handleGetUserById(long id) {

        Optional<User> userData = userRepository.findById(id);
        if (userData.isPresent()) {
            return userData.get();
        }
        return null;
    }

    public boolean checkIdExist(long id) {
        return this.userRepository.existsById(id);
    }

    public void handleDeleteUser(long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User handleUpdateUser(User dataUser) {
        Optional<User> currentUser = userRepository.findById(dataUser.getId());
        if (currentUser.isPresent()) {
            currentUser.get().setPassword(dataUser.getPassword());
            currentUser.get().setName(dataUser.getName());
            currentUser.get().setEmail(dataUser.getEmail());
            return userRepository.save(currentUser.get());

        } else return null;

    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public RestLoginSocial handleCreatUserBySocial(SocialMediaAccountDTO accountDTO) {
           String type =  accountDTO.getType().toLowerCase(Locale.ROOT);
           User user = new User();
           User saveUser = new User();
           switch (type) {
               case "github":
                   if(!this.userRepository.existsByTypeAndEmail(type,accountDTO.getUsername()))
                   {
                   user.setName(accountDTO.getUsername());
                   user.setEmail(accountDTO.getUsername());
                   user.setType(type);
                   saveUser=  this.userRepository.save(user);}
                   else{
                       saveUser = this.userRepository.findByEmailAndType(accountDTO.getUsername(),accountDTO.getType());
                   }
           }
           RestLoginSocial restLoginSocial = new RestLoginSocial();
        String AccessToken = this.securityUtil.CreateTokenWithSocial(accountDTO.getUsername());
        restLoginSocial.setAccess_token(AccessToken);
        String RefreshToken = this.securityUtil.CreateRefreshToken(accountDTO.getUsername());
        restLoginSocial.setRefresh_token(RefreshToken);
        RestLoginSocial.UserSocial userSocial = new RestLoginSocial.UserSocial();
        userSocial.setId(saveUser.getId());
        userSocial.setUsername(saveUser.getName());
        userSocial.setType(type);
userSocial.setRole("User");
//        userSocial.setRole(saveUser.getRoles().getName());
        userSocial.setEmail(saveUser.getEmail());
        userSocial.setVerify(true);
        restLoginSocial.setUser(userSocial);


        return restLoginSocial;

    }

}
