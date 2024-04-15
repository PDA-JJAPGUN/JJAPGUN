package dto;

// DTO(Data Transfer Object)
// DTO는 계층간 데이터 교환을 위한 객체
// 데이터 베이스의 컬럼과는 독립적
// 어떤 데이터를 넘기고 받을 것인지

public class SignupDto {
    private String id;
    private String password;
    private String nickname;
    public SignupDto(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
