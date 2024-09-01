package vn.hoidanit.jobhunter.domain.DTO;

public class RestLoginDto {
    private String access_token;
    private String refresh_token;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    private UserLoginDto user;

    public UserLoginDto getUser() {
        return user;
    }

    public void setUser(UserLoginDto user) {
        this.user = user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
