import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Objects;

public class Input implements MouseListener {
    GamePanel gamePanel;
    int mouseX, mouseY;
    int Xoff = -10, Yoff = -30;
    boolean whiteTurn = true;
    public void setGamePanel(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseX = e.getX() + Xoff;
        mouseY = e.getY() + Yoff;
        if(gamePanel.selectedPiece == null && Character.isUpperCase(Board.board[mouseY/100][mouseX/100].name) == whiteTurn)
            gamePanel.selectedPiece = Board.board[mouseY/100][mouseX/100];
        else{
            boolean f = false;
            for(int[] a : gamePanel.selectedPiece.getMoves()){
                if(Arrays.equals(a, new int[]{mouseY / 100, mouseX / 100}) && ( Board.board[mouseY/100][mouseX/100] == null || Character.isUpperCase(Board.board[mouseY/100][mouseX/100].name) != Character.isUpperCase(Board.board[gamePanel.selectedPiece.y][gamePanel.selectedPiece.x].name))) {
                    Board.board[mouseY/100][mouseX/100] = gamePanel.selectedPiece;
                    Board.board[gamePanel.selectedPiece.x][gamePanel.selectedPiece.y] = null;
                    gamePanel.selectedPiece.x = mouseY/100;
                    gamePanel.selectedPiece.y = mouseX/100;
                    if(gamePanel.selectedPiece instanceof Pawn) ((Pawn) gamePanel.selectedPiece).firstMove = false;

                    gamePanel.selectedPiece = null;
                    f = true;
                    whiteTurn = !whiteTurn;
                }

            }
            if(!f) gamePanel.selectedPiece = null;
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
