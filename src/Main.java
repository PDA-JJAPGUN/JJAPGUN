import controller.GameController;
import controller.UserController;
import service.UserService;
import view.GameFrame;


public class Main {
    public static void main(String[] args) {
        new UserController(new UserService(), GameController.getInstance());
    }
}
