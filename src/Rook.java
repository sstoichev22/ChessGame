import java.util.ArrayList;

public class Rook extends Piece{
    Rook(char name, int x, int y){
        super(name, x, y);
    }

    public ArrayList<int[]> getMoves(){
        ArrayList<int[]> moves = new ArrayList<>();

        moves.addAll(getDir(this.x, this.y, 0, 1));
        moves.addAll(getDir(this.x, this.y, 0, -1));
        moves.addAll(getDir(this.x, this.y, 1, 0));
        moves.addAll(getDir(this.x, this.y, -1, 0));

        return moves;
    }

}