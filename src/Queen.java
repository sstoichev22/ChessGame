import java.util.ArrayList;

public class Queen extends Piece{
    Queen(char name, int x, int y){
        super(name, x, y);
    }

    public ArrayList<Point> getMoves(){
        ArrayList<Point> moves = new ArrayList<>();

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
