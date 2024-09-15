package vn.hoidanit.jobhunter.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne()
    @JoinColumn(name ="user_id")
    private User user;
    @ManyToMany
    @JoinTable(
            name = "track_like",
            joinColumns = @JoinColumn(name = "like_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    private  List<Tracks> tracks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }
}
