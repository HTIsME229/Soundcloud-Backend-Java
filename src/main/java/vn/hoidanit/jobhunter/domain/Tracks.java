package vn.hoidanit.jobhunter.domain;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "tracks")
    public class Tracks {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
private String title;
private String description;
private  String url;
private String imgUrl;
private String category;

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
