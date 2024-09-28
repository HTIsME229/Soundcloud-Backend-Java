package vn.hoidanit.jobhunter.domain;

    import com.fasterxml.jackson.annotation.JsonFormat;
    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.NotEmpty;
    import vn.hoidanit.jobhunter.utils.SecurityUtil;

    import java.time.Instant;
    import java.util.List;

@Entity
    @Table(name = "tracks")
    public class Tracks {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @NotEmpty(message = "Vui long dien day du thong tin")
private String title;
    @NotEmpty(message = "Vui long dien day du thong tin")

    private String description;
    @NotEmpty(message = "Vui long dien day du thong tin")

    private  String url;
    @NotEmpty(message = "Vui long dien day du thong tin")

    private String imgUrl;
    @NotEmpty(message = "Vui long dien day du thong tin")

    private String category;
    private  int countLike;
    private  int countPlay;

    private Instant createdAt;

    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;

    @OneToMany(mappedBy = "track")
    @JsonIgnoreProperties(value = {"user","track"})
   private List<Comment> comments;
    @JsonIgnore
    @ManyToMany(mappedBy = "tracks")


    private  List<Like> likes;
    @ManyToOne()
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public int getCountPlay() {
        return countPlay;
    }

    public void setCountPlay(int countPlay) {
        this.countPlay = countPlay;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @PrePersist
    public void handleCreate() {
        this.createdAt = Instant.now();
        this.createdBy = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
    }

    @PreUpdate
    public void handleUpdate() {
        this.updatedAt = Instant.now();
        this.updatedBy = SecurityUtil.getCurrentUserLogin().isPresent() ? SecurityUtil.getCurrentUserLogin().get() : "";
    }
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
