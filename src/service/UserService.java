package service;

import controller.GameController;
import dao.impl.UserDAOImpl;
import dto.SignupDto;
import dto.LoginDto;
import entity.UserEntity;
import session.UserSession;

public class UserService {
    UserDAOImpl userDao = UserDAOImpl.getInstance();
    public String signup(SignupDto signupDto) {
        String id = signupDto.getId();
        String password = signupDto.getPassword();
        String nickname = signupDto.getNickname();
        Integer bestScore = null;

        // ID 중복 검사
        if (userDao.isIdDuplicate(id)) {
            return "중복된 ID입니다.";
        } else {
            UserEntity userEntity = new UserEntity(id, password, nickname, bestScore);
            return userDao.signup(userEntity);
        }
    }

    public Boolean login(LoginDto loginDto, GameController gameController) {
        // Dto에서 id, password 받아옴
        String id = loginDto.getId();
        String password = loginDto.getPassword();
        UserEntity user = userDao.getUser(id);


        // store에서 id로 가져온 user가 null이 아니고,
        // 전달받은 user의 password와 Map에 저장되어있는 password가 같을 경우
        if (user != null && password.equals(user.getPassword())) {
            // 사용자 인증 성공
            UserSession.getInstance().setLoggedInUserId(id); // 로그인된 사용자의 ID를 UserSession에 저장
            UserSession.getInstance().setLoggedInUserNickname(user.getNickname());
            return true;
        } else {
            // 사용자 인증 실패
            return false;
        }
    }

    public UserEntity getUser(String id) {
        return userDao.getUser(id);
    }

    public void saveBestScore(String id, int score) {
        UserEntity user = userDao.getUser(id);
        if (user.getBestScore() != null) {
            user.setBestScore(Math.max(user.getBestScore(), score));
        } else {
            user.setBestScore(score);
        }
        userDao.saveUser(user);
    }
}
