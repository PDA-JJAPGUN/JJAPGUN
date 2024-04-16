package service;

import controller.GameController;
import dao.impl.UserDAOImpl;
import dto.SignupDto;
import dto.LoginDto;
import entity.UserEntity;

public class UserService {
    UserDAOImpl userDao = UserDAOImpl.getInstance();
    public String signup(SignupDto signupDto) {
        String id = signupDto.getId();
        String password = signupDto.getPassword();
        String nickname = signupDto.getNickname();
        Integer bestScore = null;

        UserEntity userEntity = new UserEntity(id, password, nickname, bestScore);
        return userDao.signup(userEntity);
    }

    public Boolean login(LoginDto loginDto, GameController gameController) {
        String id = loginDto.getId();
        String password = loginDto.getPassword();

        UserEntity userEntity = new UserEntity(id, password);
        boolean isLogin = userDao.login(userEntity);

        if (isLogin) gameController.setUser(userDao.getUser(id));
        return isLogin;
    }

    public void saveBestScore(String id, int score) {
        UserEntity user = userDao.getUser(id);
        int currentScore = user.getBestScore();
        user.setBestScore(Math.max(currentScore, score));
        userDao.saveUser(user);
    }
}
