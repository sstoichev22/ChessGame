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

    public boolean checked() {
        ArrayList<int[]> d1 = getDir(this.x, this.y, 1, 1);
        ArrayList<int[]> d2 = getDir(this.x, this.y, -1, 1);
        ArrayList<int[]> d3 = getDir(this.x, this.y, -1, -1);
        ArrayList<int[]> d4 = getDir(this.x, this.y, 1, -1);
        ArrayList<ArrayList<int[]>> d = new ArrayList<>();
        d.add(d1);
        d.add(d2);
        d.add(d3);
        d.add(d4);

        for(ArrayList<int[]> arr : d){
            if(!arr.isEmpty() && Board.board[arr.get(arr.size()-1)[1]][arr.get(arr.size()-1)[0]] != null && (Character.toLowerCase(Board.board[arr.get(arr.size()-1)[1]][arr.get(arr.size()-1)[0]].name) == 'b' || Character.toLowerCase(Board.board[arr.get(arr.size()-1)[1]][arr.get(arr.size()-1)[0]].name) == 'q')){
                return true;
            }
        }

        ArrayList<int[]> v1 = getDir(this.x, this.y, 0, 1);
        ArrayList<int[]> v2 = getDir(this.x, this.y, 0, -1);
        ArrayList<int[]> h1 = getDir(this.x, this.y, 1, 0);
        ArrayList<int[]> h2 = getDir(this.x, this.y, -1, 0);
        ArrayList<ArrayList<int[]>> v = new ArrayList<>();
        v.add(v1);
        v.add(v2);
        v.add(h1);
        v.add(h2);

        for(ArrayList<int[]> arr : v){
            if(!arr.isEmpty() && Board.board[arr.get(arr.size()-1)[1]][arr.get(arr.size()-1)[0]] != null && (Character.toLowerCase(Board.board[arr.get(arr.size()-1)[1]][arr.get(arr.size()-1)[0]].name) == 'r' || Character.toLowerCase(Board.board[arr.get(arr.size()-1)[0]][arr.get(arr.size()-1)[1]].name) == 'q')){
                return true;
            }
        }



        return false;
    }
}
