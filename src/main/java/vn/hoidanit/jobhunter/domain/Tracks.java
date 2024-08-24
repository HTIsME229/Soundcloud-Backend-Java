package vn.hoidanit.jobhunter.domain;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "tracks")
    public class Tracks {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

    }
