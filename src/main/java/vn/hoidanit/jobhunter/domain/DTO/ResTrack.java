package vn.hoidanit.jobhunter.domain.DTO;

import java.time.Instant;

public class ResTrack {
    private long id;
    private String title;
    private String description;
    private  String url;
    private String imgUrl;
    private String category;
    private  int countLike;
    private  int countPlay;
    private Instant createdAt;
    private Instant updatedAt;
    private boolean isDeleted;
    private Uploader uploader;

    public Uploader getUploader() {
        return uploader;
    }

    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }

    public ResTrack(long id, String title, String description, String url, String imgUrl, String category, int countLike, int countPlay, Instant updatedAt, Instant createdAt, boolean isDeleted, Uploader uploader) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.imgUrl = imgUrl;
        this.category = category;
        this.countLike = countLike;
        this.countPlay = countPlay;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.uploader = uploader;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public int getCountPlay() {
        return countPlay;
    }

    public void setCountPlay(int countPlay) {
        this.countPlay = countPlay;
    }

    public int getCountLike() {
        return countLike;
    }

    public void setCountLike(int countLike) {
        this.countLike = countLike;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public  static  class Uploader {
        private  long id;
        private  String email;
        private  String name;
        private String role;

        public Uploader() {
        }

        public Uploader(String role, String type, String name, String email, long id) {
            this.role = role;
            this.type = type;
            this.name = name;
            this.email = email;
            this.id = id;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        private  String type;
    }


}

