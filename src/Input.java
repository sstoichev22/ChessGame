import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

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
        switch(gamePanel.gameState){
            case 0:
                if(gamePanel.playButton.contains(mouseX, mouseY)){
                    gamePanel.gameState = 1;
                }
                break;
            case 1:
                if(gamePanel.selectedPiece == null && Character.isUpperCase(Board.board[mouseY/100][mouseX/100].name) == whiteTurn)
                    gamePanel.selectedPiece = Board.board[mouseY/100][mouseX/100];
                else{
                    boolean f = false;
                    for(Point a : gamePanel.selectedPiece.getMoves()){
                        if(Arrays.equals(new int[]{a.x, a.y}, new int[]{mouseY / 100, mouseX / 100}) && ( Board.board[mouseY/100][mouseX/100] == null || Character.isUpperCase(Board.board[mouseY/100][mouseX/100].name) != Character.isUpperCase(Board.board[gamePanel.selectedPiece.x][gamePanel.selectedPiece.y].name)) ) {
                            Board.board[mouseY/100][mouseX/100] = gamePanel.selectedPiece;
                            Board.board[gamePanel.selectedPiece.x][gamePanel.selectedPiece.y] = null;
                            gamePanel.selectedPiece.x = mouseY/100;
                            gamePanel.selectedPiece.y = mouseX/100;
                            if(gamePanel.selectedPiece instanceof Pawn) ((Pawn) gamePanel.selectedPiece).firstMove = false;

                            gamePanel.selectedPiece = null;
                            f = true;
                            whiteTurn = !whiteTurn;
                            if(gamePanel.wCLM && gamePanel.wK.checked()){
                                gamePanel.gameState = 3;
                            }
                            if(gamePanel.bCLM && gamePanel.bK.checked()){
                                gamePanel.gameState = 4;
                            }
                            gamePanel.wCLM =gamePanel.wK.checked();
                            gamePanel.bCLM =gamePanel.bK.checked();


                        }

                    }
                    if(!f) gamePanel.selectedPiece = null;
                }
                break;
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
