package vn.hoidanit.jobhunter.domain.DTO;

import vn.hoidanit.jobhunter.domain.Role;
import vn.hoidanit.jobhunter.utils.ENUM.Gender;

public class RestUser {
    private long id;
    private String name;
    private String email;
    private String address;
    private Gender gender;
    private  int age;
    private String role;
private  String type;
private  boolean isVerify;

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public RestUser() {
    }

    public RestUser(long id, String role, int age, Gender gender, String address, String email, String name, String type, boolean isVerify) {
        this.id = id;
        this.role = role;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.name = name;
        this.type = type;
        this.isVerify = isVerify;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
