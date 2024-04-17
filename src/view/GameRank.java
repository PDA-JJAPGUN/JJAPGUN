package view;

import controller.GameController;
import controller.UserController;
import entity.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameRank extends GameTitle {

    private ImageIcon LeaderBoardIcon = new ImageIcon("images/LeaderBoard.png");
    private Image LeaderBoardImg = LeaderBoardIcon.getImage();


    public GameRank(GameFrame gameFrame) {
        super(gameFrame);

        setLayout(null);
        // 배경 패널을 추가합니다.
        BackgroundPanel backgroundPanel = new BackgroundPanel(LeaderBoardImg);
        backgroundPanel.setBounds(40, 25, SCREEN_WIDTH - 100, SCREEN_HEIGHT - 100);
        add(backgroundPanel);

        backgroundPanel.setLayout(null);
        JButton closeBtn = createButton("X", 50, 50, SCREEN_WIDTH - 150, 0);
        closeBtn.setFont(new Font("Arial", Font.BOLD, 20));
        backgroundPanel.add(closeBtn);

//        List<UserEntity> users = GameController.getInstance().getRanks();
        List<UserEntity> users = UserController.getInstance().getRanks();

        // 사용자 순위 정보 추가
        int rankNumber = 0;
        for (UserEntity user : users) {
//            RankUnit rankUnit = new RankUnit(rankNumber++, user.getNickname(), user.getBestScore());
            if (rankNumber < 12) {
                int y = 150 + rankNumber * 45;
                JLabel rank = new JLabel(Integer.toString(++rankNumber));
                JLabel nickname = new JLabel(user.getNickname());
                JLabel score = new JLabel(Integer.toString(user.getBestScore()));

                rank.setFont(new Font("Arial", Font.BOLD, 20));
                nickname.setFont(new Font("Arial", Font.BOLD, 20));
                score.setFont(new Font("Arial", Font.BOLD, 20));

                rank.setForeground(Color.WHITE);
                nickname.setForeground(Color.WHITE);
                score.setForeground(Color.WHITE);

                rank.setHorizontalAlignment(JLabel.CENTER);
                nickname.setHorizontalAlignment(JLabel.CENTER);
                score.setHorizontalAlignment(JLabel.CENTER);

                rank.setBounds(80, y, 50, 50);
                nickname.setBounds(200, y, 100, 50);
                score.setBounds(380, y, 50, 50);

                backgroundPanel.add(rank);
                backgroundPanel.add(nickname);
                backgroundPanel.add(score);

            }

        }
    }

}

class RankUnit {
    JLabel rank;
    JLabel nickname;
    JLabel score;

    RankUnit(int rank, String nickname, int score) {
        this.rank = new JLabel(Integer.toString(rank));
        this.nickname = new JLabel(nickname);
        this.score = new JLabel(Integer.toString(score));

        this.rank.setFont(new Font("Arial", Font.BOLD, 20));
        this.nickname.setFont(new Font("Arial", Font.PLAIN, 20));
        this.score.setFont(new Font("Arial", Font.PLAIN, 20));

        this.rank.setForeground(Color.WHITE);
        this.nickname.setForeground(Color.WHITE);
        this.score.setForeground(Color.WHITE);

    }
}
