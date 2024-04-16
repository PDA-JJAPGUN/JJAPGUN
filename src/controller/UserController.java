package controller;

import dto.LoginDto;
import dto.SignupDto;
import entity.UserEntity;
import service.UserService;
import session.UserSession;
import view.UserView;

public class UserController {
    public static UserController instance;

    UserService userService;
    UserSession userSession;
    GameController gameController;

    private UserController(UserService userService, GameController gameController) {
        this.userService = userService;
        this.gameController = gameController;
        new UserView(this);
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController(new UserService(), GameController.getInstance());
        }
        return instance;
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