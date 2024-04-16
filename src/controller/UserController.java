package controller;

import dto.LoginDto;
import dto.SignupDto;
import service.UserService;
import view.UserView;

public class UserController {
    UserService userService;
    GameController gameController;

    // 모델 객체의 메소드 호출
    public UserController(UserService userService, GameController gameController) {
        this.userService = userService;
        this.gameController = gameController;
        new UserView(this);
    }

    // 메소드 -> 모델.plus();
    // 자바에서 값을 전달하는 방법은 '매개변수' 밖에 없다.
    public String signup(SignupDto signUpDto) {
        return userService.signup(signUpDto);
    }

    // 로그인
    public Boolean login(LoginDto loginDto) {
        return userService.login(loginDto, gameController);
    }
}