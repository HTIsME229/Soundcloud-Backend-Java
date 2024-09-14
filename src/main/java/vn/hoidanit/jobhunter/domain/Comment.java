package vn.hoidanit.jobhunter.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


import java.time.Instant;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private  int moment ;
    private  boolean isDeleted;
    private Instant createAt;
    private Instant update_at;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = "comments")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    @JsonIgnoreProperties(value = "comments")

    private Tracks track;
    @PrePersist
    public void handleCreate() {
        this.createAt = Instant.now();
    }

    @PreUpdate
    public void handleUpdate() {
        this.update_at = Instant.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Tracks getTrack() {
        return track;
    }

    public void setTrack(Tracks track) {
        this.track = track;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreateAt() {
        return createAt;
    }


    public Instant getUpdate_at() {
        return update_at;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getMoment() {
        return moment;
    }

    public void setMoment(int moment) {
        this.moment = moment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
