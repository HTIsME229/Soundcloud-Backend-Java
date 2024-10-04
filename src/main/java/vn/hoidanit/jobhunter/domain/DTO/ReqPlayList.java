package vn.hoidanit.jobhunter.domain.DTO;

import java.util.List;

public class ReqPlayList {
    private long id;
    private String title;
    private boolean isPublic;
    private List<Long> tracks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getTracks() {
        return tracks;
    }

    public void setTracks(List<Long> tracks) {
        this.tracks = tracks;
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



}
