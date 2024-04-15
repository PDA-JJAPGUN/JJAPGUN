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
    }

    protected JButton createButton(String text, int width, int height, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setActionCommand(text); // 버튼 이름과 동일하게 처리, 버튼 추가 시 ActionCommand 에 추가
        button.addActionListener(this);
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH - 15, SCREEN_HEIGHT, 0, 0, 338, 594, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 버튼을 눌렀을 때
        switch (ActionCommand.valueOf(e.getActionCommand())) {
            case START:
                gameFrame.change(Panel.GAME_MAP.name());
                break;
            case RANK:
                //TODO: 랭크 패널 변경
                System.out.println("rank");
                break;
            case EXIT:
                // 게임 종료
                System.out.println("exit");
                break;
            default:
                break;
        }

    }
}
