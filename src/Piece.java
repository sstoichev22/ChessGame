import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Piece {
    public char name;
    int x, y;
    Piece(char name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public abstract ArrayList<int[]> getMoves();

    protected ArrayList<int[]> getDir(int sx, int sy, int stepx, int stepy){
        ArrayList<int[]> moves = new ArrayList<>();
        for(int x = sx + stepx, y = sy + stepy;x >= 0 && y >= 0 && x < 8 && y < 8;x += stepx, y += stepy){
            if(Board.board[x][y] != null){
                if(Board.board[x][y].getColor() != this.getColor()) moves.add(new int[]{x, y});
                break;
            }
            moves.add(new int[]{x, y});
        }
        return moves;
    }
    public boolean getColor(){
        return Character.isUpperCase(name);
    }
}
