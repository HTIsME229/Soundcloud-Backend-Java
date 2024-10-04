package vn.hoidanit.jobhunter.domain.DTO;

import java.time.Instant;
import java.util.List;

public class RestPlaylist {
    private long id;
    private String title;
    private boolean isPublic;
    private Instant createdAt;

    private Instant updatedAt;
    private String createdBy;
    private String updatedBy;
 private RestUser user;
 private List<TrackPlaylist> tracks;

    public List<TrackPlaylist> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackPlaylist> tracks) {
        this.tracks = tracks;
    }

    public static  class  TrackPlaylist{
     private  long id;
     private  String title;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
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

        private  String url;
    }

    public RestUser getUser() {
        return user;
    }

    public void setUser(RestUser user) {
        this.user = user;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
