package entity;


public class UserEntity {
    String id;
    String password;
    String nickname;
    Integer bestScore;

    public UserEntity(String id, String password, String nickname, Integer bestScore) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.bestScore = bestScore;
    }

    public UserEntity(String id, String password) {
        this.id = id;
        this.password = password;
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

    public Integer getBestScore() {
        return bestScore;
    }

    public void setBestScore(Integer bestScore) {
        this.bestScore = bestScore;
    }
}
