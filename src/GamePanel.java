import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable{
    Thread gameThread;
    Input input;
    int screenWidth, screenHeight, tileSize;
    Color tileColor1, tileColor2;
    String startPos = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    ArrayList<int[]> kings = new ArrayList<>();
    //test pos:"3r3K/b5Bp/4brKp/ppp3pp/1p1p1p1p/rRrR3R/bNnnNb2/bKqQk3", "rnbqk3/1B6/7P/8/5K2/2n5/5pb1/8"
    String FEIN = startPos;
    Piece selectedPiece = null;
    GamePanel(Input input, int screenWidth, int screenHeight){
        initImages();
        this.input = input;
        setGameSize(this.screenWidth = screenWidth, this.screenHeight = screenHeight);
        tileSize = 100;
        tileColor1 = new Color(196, 164, 132);
        tileColor2 = new Color(92, 64, 51);
        setBoard();
    }
    @Override
    public void run() {
        double FPS = 1_000_000_000.0/120;
        double UPS = 1_000_000_000.0/200;
        long previousTime = System.nanoTime();
        double deltaF = 0, deltaU = 0;
        int frames = 0, updates = 0;
        long lastCheck = System.currentTimeMillis();
        while(gameThread != null){
            long currentTime = System.nanoTime();
            deltaF += (currentTime-previousTime)/FPS;
            deltaU += (currentTime-previousTime)/UPS;
            previousTime = currentTime;
            if(deltaF >= 1){
                repaint();
                deltaF--;
                frames++;
            }
            if(deltaU >= 1){
                update();
                deltaU--;
                updates++;
            }
            if(System.currentTimeMillis()-lastCheck >= 1_000){
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                lastCheck = System.currentTimeMillis();
                frames = 0;
                updates = 0;
            }
        }
    }
    public void update(){
        FEIN = getFENString();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintTiles(g);
        paintCoords(g);
        drawMoves(g);
        drawPieces(g);

    }


    public void drawMoves(Graphics g){
        if(selectedPiece != null){
            ArrayList<Point> moves = selectedPiece.getMoves();

            for(Point coord : moves){

                g.drawImage(ImageManager.hawktuahcaptureonthatthang, (coord.y)*100, (coord.x)*100, tileSize, tileSize, null);

            }
        }
    }

    public void setBoard(){
        for (int i = 0, r = 0, c = 0; i < FEIN.length(); i++, c++) {
            if (Character.isDigit(FEIN.charAt(i))) {
                c += FEIN.charAt(i) - '0' - 1;
                continue;
            }
            if (FEIN.charAt(i) == '/') {
                r++;
                c = -1;
                continue;
            }
            switch (FEIN.charAt(i)) {
                case 'b':
                case 'B':
                    Board.board[r][c] = new Bishop(FEIN.charAt(i), r, c);
                    break;
                case 'k':
                case 'K':
                    Board.board[r][c] = new King(FEIN.charAt(i), r, c);
                    kings.add(new int[]{r, c});
                    break;
                case 'n':
                case 'N':
                    Board.board[r][c] = new Knight(FEIN.charAt(i), r, c);
                    break;
                case 'p':
                case 'P':
                    Board.board[r][c] = new Pawn(FEIN.charAt(i), r, c);
                    break;
                case 'q':
                case 'Q':
                    Board.board[r][c] = new Queen(FEIN.charAt(i), r, c);
                    break;
                case 'r':
                case 'R':
                    Board.board[r][c] = new Rook(FEIN.charAt(i), r, c);
                    break;
                default:
                    Board.board[r][c] = null;
            };
        }
    }
    public String getFENString() {
        StringBuilder fen = new StringBuilder();

        for (int i = 0; i < Board.board.length; i++) {
            int emptyCount = 0;

            for (int j = 0; j < Board.board[i].length; j++) {
                if (Board.board[i][j] == null) {
                    emptyCount++;
                } else {
                    if (emptyCount > 0) {
                        fen.append(emptyCount);
                        emptyCount = 0;
                    }
                    fen.append(Board.board[i][j].name);
                }
            }

            if (emptyCount > 0) {
                fen.append(emptyCount);
            }

            if (i < Board.board.length - 1) {
                fen.append("/");
            }
        }
        FEIN = fen.toString();
        return fen.toString();
    }

    public void drawPieces(Graphics g) {

        for (int i = 0, r = 0, c = 0; i < FEIN.length(); i++, c++) {
            BufferedImage dp;
            if (Character.isDigit(FEIN.charAt(i))) {
                c += FEIN.charAt(i) - '0' - 1;
                continue;
            }
            if (FEIN.charAt(i) == '/') {
                r++;
                c = -1;
                continue;
            }
            dp = switch (FEIN.charAt(i)) {
                case 'b' -> ImageManager.bB;
                case 'k' -> ImageManager.bK;
                case 'n' -> ImageManager.bN;
                case 'p' -> ImageManager.bP;
                case 'q' -> ImageManager.bQ;
                case 'r' -> ImageManager.bR;
                case 'B' -> ImageManager.wB;
                case 'K' -> ImageManager.wK;
                case 'N' -> ImageManager.wN;
                case 'P' -> ImageManager.wP;
                case 'Q' -> ImageManager.wQ;
                case 'R' -> ImageManager.wR;
                default -> null;
            };
            if (dp != null) {
                g.drawImage(dp, tileSize * c, tileSize * r, tileSize, tileSize, null);
            }
        }
        for(Piece[] arr : Board.board){
            for(Piece p : arr){
                if(p != null && Character.toLowerCase(p.name) == 'k'){
                    System.out.println(((King) p).checked());
                }
            }
        }
    }

    private void paintTiles(Graphics g){
        for(int i = 0; i < 8; i++){
            for(int j = 0 ; j < 8; j++){
                if((j+i)%2 == 0) g.setColor(tileColor1);
                else g.setColor(tileColor2);
                g.fillRect(i*tileSize, j*tileSize, tileSize, tileSize);
            }
        }
    }
    private void paintCoords(Graphics g){
        g.setFont(new Font("Georgia", Font.BOLD, 17));
        g.setColor(Color.black);
        int offset = 5;
        for(int i = 0 ; i < 8; i++){
            g.drawString(""+(char)('A'+i), i*tileSize+offset+(i==0?offset*2:0), screenHeight-offset);
        }
        g.setFont(new Font("Georgia", Font.BOLD, 20));
        for(int i = 0 ; i < 8; i++){
            g.drawString(""+(i+1), offset, screenHeight -i*tileSize-offset+(i==0?-offset*2:0));
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(offset, screenHeight-offset, 5*offset, screenHeight-5*offset);
    }
    private void setGameSize(int width, int height){
        Dimension d = new Dimension(width, height);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    private void initImages(){
        try{
            ImageManager.bB = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/BlackBishop.png")));
            ImageManager.bK = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/BlackKing.png")));
            ImageManager.bN = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/BlackKnight.png")));
            ImageManager.bP = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/BlackPawn.png")));
            ImageManager.bQ = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/BlackQueen.png")));
            ImageManager.bR = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/BlackRook.png")));
            ImageManager.wB = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/WhiteBishop.png")));
            ImageManager.wK = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/WhiteKing.png")));
            ImageManager.wN = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/WhiteKnight.png")));
            ImageManager.wP = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/WhitePawn.png")));
            ImageManager.wQ = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/WhiteQueen.png")));
            ImageManager.wR = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/WhiteRook.png")));
            ImageManager.hawktuahcaptureonthatthang = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/GreyCircle.png")));

        }catch(IOException ignored){}
    }
}
