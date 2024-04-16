package controller;

import dto.LoginDto;
import dto.SignupDto;
import entity.UserEntity;
import service.UserService;
import session.UserSession;
import view.UserView;

public class UserController {
    UserService userService;
    UserSession userSession;
    GameController gameController;

    public UserController(UserService userService, GameController gameController) {
        this.userService = userService;
        this.gameController = gameController;
        new UserView(this);
    }

    public String signup(SignupDto signUpDto) {
        return userService.signup(signUpDto);
    }

    public Boolean login(LoginDto loginDto) {
        return userService.login(loginDto, gameController);
    }

    public UserEntity getLogginedUser() {
        String userId = userSession.getLoggedInUserId();
        return userService.getUser(userId);
    }

}