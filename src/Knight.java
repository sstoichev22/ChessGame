import java.util.ArrayList;
public class Knight extends Piece{
    Knight(char name, int x, int y) {
        super(name, x, y);
    }

    @Override
    public ArrayList<Point> getMoves() {
        ArrayList<Point> list = new ArrayList<>();
        int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
        int[] dy = {1, -1, 2, -2, 2, -2, 1, -1};

        for (int i = 0; i < 8; i++) {
            int newX = this.x + dx[i];
            int newY = this.y + dy[i];

            if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                if (Board.board[newY][newX] == null || Board.board[newY][newX].getColor() != this.getColor()) {
                    list.add(new Point(newX, newY));
                }
            }
        }
        return list;
    }

}
