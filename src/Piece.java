import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    String name;
    int x, y, width, height;
    BufferedImage bi;
    boolean isWhite;
    Piece(String name, int x, int y, boolean isWhite){
        this.name = name;
        this.x = x;
        this.y = y;
        this.isWhite = isWhite;
    }

    protected ArrayList<int[]> getDir(int sx, int sy, int stepx, int stepy){
        ArrayList<int[]> moves = new ArrayList<>();
        for(int x = sx + 1, y = sy + 1;x < 8 && y < 8;x += stepx, y += stepy){
            moves.add(new int[]{x, y});
            if(Board.board[x][y] != null){
                break;
            }
        }
        return moves;
    }
}
