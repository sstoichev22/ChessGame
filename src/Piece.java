import java.util.ArrayList;

public abstract class Piece {
    public char name;
    int x, y;
    int[] dx = {1, 0, 0, -1, 1, 1, -1, -1};
    int[] dy = {0, 1, -1, 0, 1, -1, 1, -1};
    Piece(char name, int x, int y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public abstract ArrayList<Point> getMoves();

    public boolean isOppositeColor(Piece p){
        return this.getColor() != p.getColor();
    }

    protected ArrayList<Point> getDir(int sx, int sy, int stepx, int stepy){
        ArrayList<Point> moves = new ArrayList<>();
        for(int x = sx + stepx, y = sy + stepy;x >= 0 && y >= 0 && x < 8 && y < 8;x += stepx, y += stepy){
            if(Board.board[x][y] != null){
                if(Board.board[x][y].getColor() != this.getColor()) moves.add(new Point(x, y));
                break;
            }
            moves.add(new Point(x, y));
        }
        return moves;
    }
    public boolean getColor(){
        return Character.isUpperCase(name);
    }
}
