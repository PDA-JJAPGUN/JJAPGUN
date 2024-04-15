package session;

public class UserSession {
    private static UserSession instance; // 싱글톤 인스턴스
    private String loggedInUserId; // 현재 로그인된 사용자의 ID

    private UserSession() {} // private 생성자

    // 싱글톤 인스턴스 반환 메소드
    public static synchronized UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    // 로그인된 사용자의 ID를 설정하는 메소드
    public void setLoggedInUserId(String loggedInUserId) {
        this.loggedInUserId = loggedInUserId;
    }

    // 로그아웃 처리
    public void logout() {
        this.loggedInUserId = null;
        System.out.println("로그아웃 되었습니다.");
    }

    // 현재 로그인된 사용자의 ID를 반환하는 메소드
    public String getLoggedInUserId() {
        return loggedInUserId;
    }
}
