import java.util.ArrayList;

public class Knight extends Piece{
    Knight(char name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public ArrayList<int[]> getMoves() {
        ArrayList<int[]> list = new ArrayList<>();
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {1, -1, 2, -2, 2, -2, 1, -1};
        for(int i = 0 ; i < 8; i++){
            list.add(new int[]{this.x + dx[i], this.y + dy[i]});
        }
        return list;
    }
}
