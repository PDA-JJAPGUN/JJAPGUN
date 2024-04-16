package controller;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entity.UserEntity;
import view.GameFrame;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GameController {
    private static final GameController instance = new GameController();

    // private 생성자를 통해 외부에서 인스턴스 생성을 방지
    private GameController() {}

    // 싱글톤 인스턴스를 반환하는 메소드
    public static GameController getInstance() {
        return instance;
    }


    UserEntity user;
    GameFrame gameFrame;
    public void gameStart(UserEntity user) {
        this.user = user;
        System.out.println(user.getNickname());
        gameFrame = new GameFrame();
    }

    public UserEntity getUser() {
        return user;
    }

    public List<UserEntity> getRanks() {
        List<UserEntity> userEntities = UserDAOImpl.getInstance().getUsers();
        return userEntities.stream()
                .filter(user -> user.getBestScore() != null)
                .sorted(Comparator.comparing(UserEntity::getBestScore).reversed())
                .collect(Collectors.toList());
    }
}
