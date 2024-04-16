import controller.GameController;
import controller.UserController;
import service.UserService;
import view.GameFrame;


public class Main {
    private final static UserService userService = new UserService();
    public static void main(String[] args) {
        startGame();
    }
    public static void startGame(){
        new UserController(userService, GameController.getInstance());
    }
}
