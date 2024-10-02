import java.util.ArrayList;

public class Pawn extends Piece{
    boolean firstMove = true;
    Pawn(char name, int x, int y) {
        super(name, x, y);
    }

    public ArrayList<int[]> getMoves(){
        return new ArrayList<>();
    }
}
