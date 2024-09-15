package vn.hoidanit.jobhunter.domain.DTO;

public class TrackLikeRes {
    private long id;
    private String title;
    private String description;
    private  String url;
    private String imgUrl;
    private String category;
    private  int countLike;
    private  int countPlay;

    public TrackLikeRes(long id, int countPlay, int countLike, String category, String imgUrl, String url, String description, String title) {
        this.id = id;
        this.countPlay = countPlay;
        this.countLike = countLike;
        this.category = category;
        this.imgUrl = imgUrl;
        this.url = url;
        this.description = description;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
