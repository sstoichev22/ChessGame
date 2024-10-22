import java.util.ArrayList;

public class King extends Piece{
    King(char name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public ArrayList<int[]> getMoves() {
        ArrayList<int[]> list = new ArrayList<>();
        int[] dx = {1, 0, -1, 1, -1, 1, 0, -1};
        int[] dy = {1, 1, 1, 0, 0, -1, -1, -1};
        for(int i = 0 ; i < 8; i++){
            if(this.x + dx[i] >= 0 && this.x + dx[i] < 8 && this.y + dy[i] >= 0 && this.y + dy[i] < 8)
                if(Board.board[this.x+dx[i]][this.y+dy[i]] != null && Board.board[this.x+dx[i]][this.y+dy[i]].getColor() != this.getColor())
                    list.add(new int[]{this.x + dx[i], this.y + dy[i]});
        }
        return list;
    }
}
