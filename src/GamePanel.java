import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    Thread gameThread;
    Input input;
    int screenWidth, screenHeight, tileSize;
    Color tileColor1, tileColor2;
    GamePanel(Input input, int screenWidth, int screenHeight){
        this.input = input;
        setGameSize(this.screenWidth = screenWidth, this.screenHeight = screenHeight);
        tileSize = 100;
        tileColor1 = new Color(196, 164, 132);
        tileColor2 = new Color(92, 64, 51);
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

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintTiles(g);
        paintCoords(g);
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
}
