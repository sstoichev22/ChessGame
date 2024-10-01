import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements MouseListener {
    GamePanel gamePanel;
    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        gamePanel.selectedPiece = Board.board[e.getY()][e.getX()];
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
