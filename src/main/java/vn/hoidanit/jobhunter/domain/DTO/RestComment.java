package vn.hoidanit.jobhunter.domain.DTO;

import vn.hoidanit.jobhunter.domain.Comment;

import java.time.Instant;

public class RestComment  {
    private long id;
    private String content;
    private  int moment ;
    private  boolean isDeleted;
    private Instant create_at;

    public RestComment() {
    }

    public RestComment(long id, String content, int moment, boolean isDeleted, Instant create_at, Instant update_at, userComment user, trackComment track) {
        this.id = id;
        this.content = content;
        this.moment = moment;
        this.isDeleted = isDeleted;
        this.create_at = create_at;
        this.update_at = update_at;
        this.user = user;
        this.track = track;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMoment() {
        return moment;
    }

    public void setMoment(int moment) {
        this.moment = moment;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Instant getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Instant create_at) {
        this.create_at = create_at;
    }

    public userComment getUser() {
        return user;
    }

    public void setUser(userComment user) {
        this.user = user;
    }

    public trackComment getTrack() {
        return track;
    }

    public void setTrack(trackComment track) {
        this.track = track;
    }

    public Instant getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Instant update_at) {
        this.update_at = update_at;
    }

    private Instant update_at;
    public static  class  userComment {
        private int id;
        private String email;
        private String avatar;
        private String name ;
        private String role;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public userComment(int id, String email, String name, String role, String type,String avatar) {
            this.id = id;
            this.email = email;
            this.name = name;
            this.role = role;
            this.type = type;
            this.avatar = avatar;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        private String type;
    }
    private userComment user;
    public static class  trackComment{
        private int id;

        public trackComment(int id, String description, String url, String title) {
            this.id = id;
            this.description = description;
            this.url = url;
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        private String title;
        private String description;
        private String url;
    }
    private trackComment track;
}
