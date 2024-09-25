import java.util.ArrayList;

public class Bishop extends Piece{
    int width = 100, height = 100;
    Bishop(int x, int y, boolean isWhite){
        super(isWhite ? "wB" : "bB", x, y, isWhite);
    }

    public ArrayList<int[]> getMoves(){
        ArrayList<int[]> moves = new ArrayList<>();

        moves.addAll(getDir(this.x, this.y, 1, 1));
        moves.addAll(getDir(this.x, this.y, -1, 1));
        moves.addAll(getDir(this.x, this.y, 1, -1));
        moves.addAll(getDir(this.x, this.y, -1, -1));

        return moves;
    }
}
