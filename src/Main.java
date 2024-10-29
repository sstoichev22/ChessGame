import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Chess Game");
        Input input = new Input();
        GamePanel gamePanel = new GamePanel(input, 800, 800);
        input.setGamePanel(gamePanel);
        frame.add(gamePanel);
        frame.addMouseListener(input);

        frame.pack();
        frame.setIconImage(ImageManager.cagnusMarlson);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        gamePanel.startGameThread();
    }
}
