package view;

import controller.UserController;
import dto.LoginDto;
import dto.SignupDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView extends JFrame implements ActionListener {
    JTextField tf_id = new JTextField();
    JTextField tf_password = new JTextField();
    JTextField tf_nickname = new JTextField();

    JButton btn_signup = new JButton("회원가입");
    JButton btn_login = new JButton("로그인");
    JButton btn_logout = new JButton("로그아웃");

    JLabel la_result = new JLabel("결과 출력");
    UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;

        btn_signup.addActionListener(this);
        btn_login.addActionListener(this);

        setSize(500, 500); // 창 크기
        setLayout(null); // 레이아웃 매니저 초기화

        tf_id.setBounds(50, 50, 50, 40);
        tf_password.setBounds(100, 50, 50, 40);
        tf_nickname.setBounds(150, 50, 50, 40);
        btn_signup.setBounds(200, 50, 200, 40);
        btn_login.setBounds(200, 100, 200, 40);
        btn_logout.setBounds(200, 150, 200, 40);
        la_result.setBounds(50, 100, 200, 40);

        add(tf_id);
        add(tf_password);
        add(tf_nickname);
        add(btn_signup);
        add(btn_login);
        add(btn_logout);
        add(la_result);

        // 창을 끄면, 프로그램 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_signup) {
            String id = tf_id.getText();
            String password = tf_password.getText();
            String nickname = tf_password.getText();

            SignupDto signupDto = new SignupDto(id, password, nickname);

            la_result.setText(
                    userController.signup(signupDto));
        }
        if (e.getSource() == btn_login) {
            String id = tf_id.getText();
            String password = tf_password.getText();


            LoginDto loginDto = new LoginDto(id, password);

            if(userController.login(loginDto)) {
                la_result.setText("로그인 성공");
            } else {
                la_result.setText("로그인 실패");
            }
        }
        if (e.getSource() == btn_logout) {
            String id = tf_id.getText();
            String password = tf_password.getText();


            LoginDto loginDto = new LoginDto(id, password);

            if(userController.login(loginDto)) {
                la_result.setText("로그인 성공");
            } else {
                la_result.setText("로그인 실패");
            }
        }
    }
}
