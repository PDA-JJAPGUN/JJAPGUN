package view;

import controller.GameController;
import controller.UserController;
import dto.LoginDto;
import dto.SignupDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame implements ActionListener {
    boolean isLogin = false;

    JTextField tf_id = new JTextField();
    JTextField tf_password = new JTextField();
    JTextField tf_nickname = new JTextField();

    JButton btn_signup = new JButton("회원가입");
    JButton btn_login = new JButton("로그인");
    JButton btn_start = new JButton("게임시작");

    JLabel la_intro = new JLabel(".");
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

        setSize(500, 500); // 창 크기
        setLayout(null); // 레이아웃 매니저 초기화

        la_intro.setBounds(150,10,200,40);
        la_id.setBounds(50, 50, 200, 40);
        tf_id.setBounds(150, 50, 150, 40);

        la_pw.setBounds(50, 100, 200, 40);
        tf_password.setBounds(150, 100, 150, 40);

        la_nickName.setBounds(50, 150, 200, 40);
        tf_nickname.setBounds(150, 150, 150, 40);


        btn_login.setBounds(50, 200, 150, 40);
        btn_signup.setBounds(200, 200, 150, 40);
        btn_start.setBounds(100, 250, 200, 40);

        la_result.setBounds(50, 300, 200, 40);


        add(tf_id);
        add(tf_password);
        add(tf_nickname);
        add(btn_signup);
        add(btn_login);
        add(btn_start);
        add(la_result);
        add(la_id);
        add(la_pw);
        add(la_nickName);

        add(la_intro);
        // 창을 끄면, 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("isLogin : "+ isLogin);

        if (e.getSource() == btn_start && isLogin){
            System.out.println("hihi");
//            this.dispose();
            this.setVisible(false);
            gameController.gameStart();
        }else
            la_result.setText("유저정보 확인 실패");
        
        if (e.getSource() == btn_signup) {
            String id = tf_id.getText();
            String password = tf_password.getText();
            String nickname = tf_nickname.getText();
            if(id.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
                la_result.setText("회원가입 실패.\n 공백없이 입력하세요");
                return;
            }
            SignupDto signupDto = new SignupDto(id, password, nickname);

            la_result.setText(
                    userController.signup(signupDto)+"님 회원가입 성공");
        }
        if (e.getSource() == btn_login) {
            String id = tf_id.getText();
            String password = tf_password.getText();


            LoginDto loginDto = new LoginDto(id, password);

            if(userController.login(loginDto)) {
                la_result.setText("로그인 성공");
                la_intro.setText(id+"님 안녕하세요");
                this.isLogin = true;
            } else {
                la_result.setText("로그인 실패");
            }
        }
    }
}
