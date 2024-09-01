package vn.hoidanit.jobhunter.domain.DTO;

import vn.hoidanit.jobhunter.domain.Role;

public class UserLoginDto {
    private String email;
    private String username;
    private long id;
    private Role role;
private boolean isVerify;
private String type;
private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public UserLoginDto() {
    }

    public UserLoginDto(Role role, long id, String name, String email, boolean isVerify, String type, String address) {
        this.role = role;
        this.id = id;
        this.username = name;
        this.email = email;
        this.isVerify = isVerify;
        this.type = type;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}
