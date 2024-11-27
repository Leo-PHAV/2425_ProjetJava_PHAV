import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOver extends JPanel {
    public GameOver(ActionListener restartAction) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        gameOverLabel.setForeground(Color.RED);
        gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton restartButton = new JButton("Recommencer");
        restartButton.setFont(new Font("Verdana", Font.BOLD, 20));
        restartButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        restartButton.addActionListener(restartAction);

        add(Box.createVerticalGlue());
        add(gameOverLabel);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(restartButton);
        add(Box.createVerticalGlue());
    }
}
