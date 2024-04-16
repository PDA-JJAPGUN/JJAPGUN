package view;

import javax.swing.*;
import java.awt.*;

// BackgroundPanel 클래스는 배경 이미지를 그리는데 사용됩니다.
class BackgroundPanel extends JPanel {
    private Image image;

    public BackgroundPanel(Image image) {
        this.image = image;
        setLayout(null); // 자식 컴포넌트들의 위치를 수동으로 설정하기 위해 레이아웃 매니저를 null로 설정합니다.
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
