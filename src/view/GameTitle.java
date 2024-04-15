package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GameTitle extends JPanel implements ScreenSize, ActionListener {

    private ImageIcon backgroundIcon = new ImageIcon("images/GameTitle.png");
    private Image backgroundImage = backgroundIcon.getImage();
    private GameFrame gameFrame;

    public GameTitle(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        setLayout(null); // 레이아웃을 null로 설정하여 컴포넌트 위치를 절대값으로 지정

        // 시작 버튼 생성 및 설정
        JButton startBtn = new JButton("START");
        int btnWidth = 150; // 버튼의 너비
        int btnHeight = 40; // 버튼의 높이
        int x = (SCREEN_WIDTH - btnWidth) / 2; // 버튼을 화면 가운데 정렬
        int y = SCREEN_HEIGHT - btnHeight - 50; // 버튼을 화면 하단에서 적당히 위로 올림
        startBtn.setBounds(x, y, btnWidth, btnHeight); // 버튼의 위치와 크기 설정
        startBtn.setActionCommand("start");
        startBtn.addActionListener(this);
        add(startBtn); // 패널에 버튼 추가
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH - 15, SCREEN_HEIGHT, 0, 0, 338, 594, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 게임 시작 버튼을 눌렀을 경우
        if ("start".equals(e.getActionCommand())) {
            gameFrame.change("gameMap");
        }
    }
}
