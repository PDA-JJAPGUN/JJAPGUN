package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameTitle extends JPanel implements ScreenSize {

    private ImageIcon backgroundIcon = new ImageIcon("images/GameTitle.png");
    private Image backgroundImage = backgroundIcon.getImage();


    private GameView gameView;

    public GameTitle(GameView gameView) {
        this.gameView = gameView;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH - 15, SCREEN_HEIGHT, 0, 0, 338, 594, this);
    }



}
