package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    protected JLabel createLabel(String text, Color backgroundColor, Color textColor) {
        JLabel jLabel =  new JLabel(text);

        if (backgroundColor != null) {
            jLabel.setOpaque(true); // 불투명도를 참으로 설정하여 배경색을 보이게 한다
            jLabel.setBackground(backgroundColor);
        }

        jLabel.setForeground(textColor);

        return jLabel;
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
                gameFrame.change(Panel.GAME_SELECT_PLAYER.name());
                break;
            case RANK:
                gameFrame.change(Panel.GAME_RANK.name());
                break;
            case X: // 랭킹 화면에서 닫기 버튼 눌렀을 때
                gameFrame.change(Panel.GAME_END.name());
                break;
            case EXIT:
                gameFrame.dispose();
                break;

            default:
                break;
        }

    }

    int setX(int width) {
        return (SCREEN_WIDTH - width) / 2;
    }

    int setY(int height) {
        return SCREEN_HEIGHT - height - 50;
    }
}
