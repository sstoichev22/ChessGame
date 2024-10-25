import java.util.ArrayList;

public class King extends Piece{
    Point p;
    King(char name, int x, int y) {
        super(name, x, y);
        p = new Point(x, y);
    }

    @Override
    public ArrayList<Point> getMoves() {
        ArrayList<Point> list = new ArrayList<>();
        int[] dx = {1, 0, -1, 1, -1, 1, 0, -1};
        int[] dy = {1, 1, 1, 0, 0, -1, -1, -1};
        for(int i = 0 ; i < 8; i++){
            if(this.x + dx[i] >= 0 && this.x + dx[i] < 8 && this.y + dy[i] >= 0 && this.y + dy[i] < 8)
                if(Board.board[this.x+dx[i]][this.y+dy[i]] != null && Board.board[this.x+dx[i]][this.y+dy[i]].getColor() != this.getColor())
                    list.add(new Point(this.x + dx[i], this.y + dy[i]));
        }
        return list;
    }

    public boolean isChecked(){
        ArrayList<Point> list = new ArrayList<>();
        boolean checked = false;
        //rook and queen dirs
        for(int i = 0 ; i < 4; i++) list.addAll(getDir(p.x, p.y, dx[i], dy[i]));
        for(Point p : list)
            if((Board.board[p.x][p.y] instanceof Rook && Board.board[p.x][p.y].isOppositeColor(this)) || (Board.board[p.x][p.y] instanceof Queen && Board.board[p.x][p.y].isOppositeColor(this)))
                checked = true;
        list.clear();
        //bishop and queen dirs
        for(int i = 4 ; i < 8; i++) list.addAll(getDir(p.x, p.y, dx[i], dy[i]));
        for(Point p : list)
            if((Board.board[p.x][p.y] instanceof Bishop && Board.board[p.x][p.y].isOppositeColor(this)) || (Board.board[p.x][p.y] instanceof Queen && Board.board[p.x][p.y].isOppositeColor(this)))
                checked = true;
        list.clear();
        //knight dirs
        int[] ndx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] ndy = {1, -1, 2, -2, 2, -2, 1, -1};
        for(int i = 0 ; i < 8; i++) {
            if(p.x+ndx[i] >= 0 && p.x+ndx[i] < 8 && p.y+ndy[i] >= 0 && p.y+ndy[i] < 8 && Board.board[p.x + ndx[i]][p.y + ndy[i]] != null)
                if(Board.board[p.x + ndx[i]][p.y + ndy[i]] instanceof Knight && Board.board[p.x + ndx[i]][p.y + ndy[i]].isOppositeColor(this))
                    checked = true;
        }
        //pawn dirs
        if (p.x + 1 < 8 && p.y + (!getColor() ? 1 : -1) >= 0 && p.y + (!getColor() ? 1 : -1) < 8 && Board.board[p.x + 1][p.y + (!getColor() ? 1 : -1)] instanceof Pawn && Board.board[p.x + 1][p.y + (!getColor() ? 1 : -1)].isOppositeColor(this))
            checked = true;
        if (p.x - 1 >= 0 && p.y + (!getColor() ? 1 : -1) >= 0 && p.y + (!getColor() ? 1 : -1) < 8 && Board.board[p.x - 1][p.y + (!getColor() ? 1 : -1)] instanceof Pawn && Board.board[p.x - 1][p.y + (!getColor() ? 1 : -1)].isOppositeColor(this))
            checked = true;
        return checked;
    }

    public boolean checked() {
        return false;
    }
}
