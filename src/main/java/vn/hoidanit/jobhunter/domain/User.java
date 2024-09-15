package vn.hoidanit.jobhunter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import vn.hoidanit.jobhunter.utils.ENUM.Gender;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String password;
    private String email;
   private Gender gender;
   private  String address;
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private Boolean isVerify;
   private  String type;
   private  String avatar;

    public Boolean getVerify() {
        return isVerify;
    }

    public void setVerify(Boolean verify) {
        isVerify = verify;
    }
@JsonIgnoreProperties("user")
    @ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "role_id")
   private Role roles;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private  Like like;

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Role getRoles() {
        return roles;
    }

    public void setRoles(Role roles) {
        this.roles = roles;
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
