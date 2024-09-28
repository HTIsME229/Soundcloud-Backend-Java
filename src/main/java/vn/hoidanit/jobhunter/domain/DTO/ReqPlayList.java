package vn.hoidanit.jobhunter.domain.DTO;

public class ReqPlayList {
    private long id;
    private String title;
    private boolean isPublic;
    private  listTrackId[] tracks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public listTrackId[] getTracks() {
        return tracks;
    }

    public void setTracks(listTrackId[] tracks) {
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

    public  static  class listTrackId{
    private  int id ;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
