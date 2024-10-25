import java.util.ArrayList;

public class Pawn extends Piece{
    boolean firstMove = true;
    Pawn(char name, int x, int y) {
        super(name, x, y);
    }

    public ArrayList<Point> getMoves(){
        ArrayList<Point> list = new ArrayList<>();
        int isWhite = -1;
        if(this.name >= 'a') isWhite = 1;
        if(this.x+isWhite >= 8 || this.x+isWhite <= 0) {
            Board.board[this.x][this.y] = null;
            Board.board[this.x][this.y] = new Queen(isWhite == -1?'Q':'q', this.x, this.y);
        }
        else {
            if (Board.board[this.x + isWhite][this.y] == null) list.add(new Point(this.x + isWhite, this.y));
            if (firstMove && Board.board[this.x + isWhite * 2][this.y] == null)
                list.add(new Point(this.x + isWhite * 2, this.y));
            if (Board.board[this.x + isWhite][this.y + 1] != null) list.add(new Point(this.x + isWhite, this.y + 1));
            if (Board.board[this.x + isWhite][this.y - 1] != null) list.add(new Point(this.x + isWhite, this.y - 1));
        }
        return list;
    }
}
