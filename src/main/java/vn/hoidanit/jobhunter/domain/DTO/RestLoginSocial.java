package vn.hoidanit.jobhunter.domain.DTO;

public class RestLoginSocial {
    private String access_token;
    private String refresh_token;
    private UserSocial user;

    public UserSocial getUser() {
        return user;
    }

    public void setUser(UserSocial user) {
        this.user = user;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public static  class  UserSocial {
        private long id;
        private String avatar;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        private String type;
        private String username;
        private Boolean isVerify;
        private String role;
private  String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public Boolean getVerify() {
            return isVerify;
        }

        public void setVerify(Boolean verify) {
            isVerify = verify;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
