package vn.hoidanit.jobhunter.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "name not empty")
    private String name;
    private String description;

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public @NotBlank(message = "name not empty") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "name not empty") String name) {
        this.name = name;
    }

    private boolean active;
@OneToMany(mappedBy = "roles")
    private List<User> user;
}
