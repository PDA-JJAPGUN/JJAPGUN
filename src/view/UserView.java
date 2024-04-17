package view;

import controller.GameController;
import controller.UserController;
import dto.LoginDto;
import dto.SignupDto;
import service.UserService;
import session.UserSession;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame implements ActionListener {
    UserService userService;
    boolean isLogin = false;

    JTextField tf_id = new JTextField();
    JPasswordField tf_password = new JPasswordField();
    JTextField tf_nickname = new JTextField();

    JButton btn_signup = new JButton("회원가입");
    JButton btn_login = new JButton("로그인");
    JButton btn_logout = new JButton("로그아웃");

    JButton btn_start = new JButton("게임시작");
    ImageIcon bgIcon = new ImageIcon("Images/Login.png");
    JLabel la_bg = new JLabel();
    JLabel la_id = new JLabel("아이디");
    JLabel la_pw = new JLabel("비밀번호");
    JLabel la_nickName = new JLabel("닉네임");
    JLabel la_result = new JLabel("");
    UserController userController;
    GameController gameController = new GameController();

    public UserView(UserController userController) {
        this.userController = userController;

        btn_start.addActionListener(this);
        btn_signup.addActionListener(this);
        btn_login.addActionListener(this);
        btn_logout.addActionListener(this);

        setSize(500, 500); // 창 크기
        setLayout(null); // 레이아웃 매니저 초기화


        la_bg.setIcon(bgIcon);
        la_bg.setBounds(0, 0, 500, 500);

        la_id.setBounds(130, 100, 200, 40);
        tf_id.setBounds(200, 100, 150, 40);


        la_pw.setBounds(130, 150, 200, 40);
        tf_password.setBounds(200, 150, 150, 40);


        la_nickName.setBounds(130, 200, 200, 40);
        tf_nickname.setBounds(200, 200, 150, 40);


        btn_login.setBounds(250, 250, 150, 40);
        btn_logout.setBounds(250, 250, 150, 40);
        btn_signup.setBounds(100, 250, 150, 40);
        btn_start.setBounds(150, 300, 200, 40);

        la_result.setBounds(200, 350, 200, 40);

        add(tf_id);
        add(tf_password);
        add(tf_nickname);
        add(btn_signup);
        add(btn_login);
        add(btn_logout);
        add(btn_start);
        add(la_result);
        add(la_id);
        add(la_pw);
        add(la_nickName);
        add(la_bg);


        // 창을 끄면, 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn_logout.setVisible(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_signup) {
            String id = tf_id.getText();
            String password = tf_password.getText();
            String nickname = tf_nickname.getText();
            if(id.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
                la_result.setText("회원가입 실패.\n 공백없이 입력하세요");
                la_result.setForeground(Color.RED);
                return;
            }
            SignupDto signupDto = new SignupDto(id, password, nickname);

            la_result.setForeground(Color.BLUE);
            la_result.setText(
                    userController.signup(signupDto)+"님 회원가입 성공");
        }
        else if (e.getSource() == btn_login) {
            String id = tf_id.getText();
            String password = tf_password.getText();


            LoginDto loginDto = new LoginDto(id, password);

            if(userController.login(loginDto)) {

                la_result.setText( UserSession.getInstance().getLoggedInUserId() + "님 로그인 성공");
                this.isLogin = true;
                btn_logout.setVisible(true);
                btn_login.setVisible(false);
            } else {
                la_result.setText("로그인 실패");
                la_result.setForeground(Color.RED);
            }
        }
        else if (e.getSource() == btn_logout) {
           logout();
        }
        else if (e.getSource() == btn_start){
            if (isLogin)
                gameController.gameStart();
            else {
                la_result.setForeground(Color.RED);
                la_result.setText("유저정보 확인 실패");
            }
        }
    }

    void logout() {
        UserSession.getInstance().logout();

        btn_logout.setVisible(false);
        btn_login.setVisible(true);

        la_result.setText("로그아웃 성공");
        la_result.setForeground(Color.BLUE);
        tf_nickname.setText("");
        tf_id.setText("");
        tf_password.setText("");
        isLogin = false;
    }
}
