import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    char name;
    int x, y;
    Piece(char name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
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
