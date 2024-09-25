import java.util.ArrayList;

public class Queen extends Piece{
    int width = 100, height = 100;
    Queen(int x, int y, boolean isWhite){
        super(isWhite ? "wQ" : "bQ", x, y, isWhite);
    }

    public ArrayList<int[]> getMoves(){
        ArrayList<int[]> moves = new ArrayList<>();

        moves.addAll(getDir(this.x, this.y, 0, 1));
        moves.addAll(getDir(this.x, this.y, 0, -1));
        moves.addAll(getDir(this.x, this.y, 1, 0));
        moves.addAll(getDir(this.x, this.y, -1, 0));

        moves.addAll(getDir(this.x, this.y, 1, 1));
        moves.addAll(getDir(this.x, this.y, -1, 1));
        moves.addAll(getDir(this.x, this.y, 1, -1));
        moves.addAll(getDir(this.x, this.y, -1, -1));

        return moves;
    }
}
