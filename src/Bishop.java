import java.util.ArrayList;

public class Bishop extends Piece{
    Bishop(char name, int x, int y){
        super(name, x, y);
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
