package vn.hoidanit.jobhunter.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.jobhunter.domain.DTO.*;
import vn.hoidanit.jobhunter.domain.Role;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.utils.SecurityUtil;
import vn.hoidanit.jobhunter.utils.annotation.ApiMessage;
import vn.hoidanit.jobhunter.utils.error.InvalidLogin;
@RequestMapping("/api/v1")
@RestController

public class AuthController {
    @Value("${hoidanit.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityUtil securityUtil;
    private  final UserService userService;

        public AuthController(AuthenticationManagerBuilder authenticationManagerBuilder, SecurityUtil securityUtil, UserService userService) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.securityUtil = securityUtil;
        this.userService = userService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<RestLoginDto> login(@Valid @RequestBody loginDTO loginDTO) throws InvalidLogin {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // create token

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String Email = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser=this.userService.getUserByEmail(Email);
        UserLoginDto userLoginDto = new UserLoginDto(currentUser.getRoles(), currentUser.getId(), currentUser.getName(), Email, currentUser.getVerify(), currentUser.getType(), currentUser.getAddress());
    String RefreshToken = this.securityUtil.CreateRefreshToken(Email);
        String AccessToken = this.securityUtil.CreateToken(Email);
        RestLoginDto restLoginDto = new RestLoginDto();



        if (restLoginDto != null){
            restLoginDto.setAccess_token(AccessToken);
            restLoginDto.setRefresh_token(RefreshToken);
        restLoginDto.setUser(userLoginDto);}

        ResponseCookie responseCookie = ResponseCookie.from("refresh_token", RefreshToken).httpOnly(true).path("/").maxAge(refreshTokenExpiration).build();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, responseCookie.toString()).body(restLoginDto);


    }
    @PostMapping("/auth/social-media")
    public ResponseEntity<RestLoginSocial> socialMedia(@RequestBody SocialMediaAccountDTO account ) throws InvalidLogin {

            return ResponseEntity.ok(this.userService.handleCreatUserBySocial(account));
    }
    @PostMapping("/auth/register")
    public ResponseEntity<RestUser> register(@RequestBody ReqUser user) throws RuntimeException {
return ResponseEntity.ok(this.userService.handleSaveuUser(user));

    }
    @ApiMessage("Get new refresh token success")
    @PostMapping("/auth/refresh")
    public ResponseEntity<RestLoginDto> getRefreshToken(@RequestBody ReqRefreshToken refreshToken) {
        //check valid token
        String refresh_token= refreshToken.getRefresh_token();
        Jwt tokenDecode = this.securityUtil.checkValidRefreshToken(refresh_token);
        String email = tokenDecode.getSubject();
        User currentUser = this.userService.handleFindByEmailAndRefreshToken(email, refresh_token);

        if (currentUser != null) {
            UserLoginDto userLoginDto = new UserLoginDto(currentUser.getRoles(), currentUser.getId(), currentUser.getName(), currentUser.getEmail(), currentUser.getVerify(), currentUser.getType(), currentUser.getAddress());
            String AccessToken = this.securityUtil.CreateToken(currentUser.getEmail());
            String newRefreshToken = this.securityUtil.CreateRefreshToken(currentUser.getEmail());


            this.userService.UpdateUserToken(newRefreshToken, currentUser.getEmail());
            RestLoginDto restLoginDto = new RestLoginDto();
            restLoginDto.setAccess_token(AccessToken);
            restLoginDto.setRefresh_token(newRefreshToken);
            restLoginDto.setUser(userLoginDto);
            return ResponseEntity.ok(restLoginDto);
        }
        throw new RuntimeException("Token is not valid");


    }
}
