import java.awt.image.BufferedImage;

public abstract class Piece {
    String name;
    int x, y, width, height;
    BufferedImage bi;
    boolean isWhite;
    Piece(String name, int x, int y, int width, int height, BufferedImage bi, boolean isWhite){
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bi = bi;
        this.isWhite = isWhite;
    }
}
