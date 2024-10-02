import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Objects;

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
        if(gamePanel.selectedPiece == null)
            gamePanel.selectedPiece = Board.board[e.getY()/100][e.getX()/100];
        else{
            for(int[] a : gamePanel.selectedPiece.getMoves()){
                if(Arrays.equals(a, new int[]{e.getY() / 100, e.getX() / 100})) {
                    Board.board[e.getY()/100][e.getX()/100] = gamePanel.selectedPiece;
                    Board.board[gamePanel.selectedPiece.x][gamePanel.selectedPiece.y] = null;
                    gamePanel.selectedPiece = null;
                }

            }
        }
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
