import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener{

    static final int SCREEN_WIDTH = 480;
    static final int SCREEN_HEIGHT = 320;

    private Image tetris;
    private Image pacman;
    private Image space_invaders;
    private Image right;
    private Image left;

    int Game = 2;
    Timer timer;
    boolean inGame;

    Font pixelfont;

    GamePanel() {
        timer = new Timer(100,this);
        timer.start();
        try {
            pixelfont = Font.createFont(Font.TRUETYPE_FONT, new File("src/resources/pixelfont.ttf")).deriveFont(20f);
            GraphicsEnvironment ge =GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT,new File("src/resources/pixelfont.ttf")));
        }
        catch (IOException | FontFormatException e) {

        }
        setBackground(new Color(28, 30, 38));
        setFocusable(true);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        loadImages();
        addKeyListener(new TAdapter());
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void loadImages() {
        ImageIcon add = new ImageIcon("src/resources/right.png");
        right = add.getImage();
        ImageIcon bdd = new ImageIcon("src/resources/left.png");
        left = bdd.getImage();
        ImageIcon cdd = new ImageIcon("src/resources/tetris.png");
        tetris = cdd.getImage();
        ImageIcon ddd = new ImageIcon("src/resources/pacman.png");
        pacman = ddd.getImage();
        ImageIcon edd = new ImageIcon("src/resources/space_invaders.png");
        space_invaders = edd.getImage();
    }

    public void draw(Graphics g) {
        if(Game == 1) {
            g.setColor(new Color(28, 30, 38));
            g.fillRect(0,0,480,320);
            g.drawImage(pacman, 122, 131,this);
            g.drawImage(right, 375, 126, this);
            g.setColor(new Color(254,130,4));
            g.setFont(pixelfont);
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("Highscore 5",(SCREEN_WIDTH - metrics2.stringWidth("Highscore 5"))/2,250);
        }
        else if(Game == 2) {
            g.setColor(new Color(28, 30, 38));
            g.fillRect(0,0,480,320);
            g.drawImage(space_invaders, 118, 85,this);
            g.drawImage(right, 375, 126, this);
            g.drawImage(left, 40, 126, this);
            g.setColor(new Color(254,251,0));
            g.setFont(pixelfont);
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("Highscore 19",(SCREEN_WIDTH - metrics2.stringWidth("Highscore 19"))/2,250);
        }
        else if(Game == 3) {
            g.setColor(new Color(28, 30, 38));
            g.fillRect(100,80,282,150);
            g.drawImage(tetris, 155, 99, this);
            g.drawImage(left, 40, 126, this);
            g.setColor(new Color(255,0,0));
            g.setFont(pixelfont);
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("Highscore 69",(SCREEN_WIDTH - metrics2.stringWidth("Highscore 69"))/2,250);
        }
    }

    public void start_game(int Gamenummer) {
        if(Game == 1) {
            Snake_GameFrame snake = new Snake_GameFrame();
            //inGame = true;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            //if(inGame=false){
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (Game != 1) {
                            Game--;
                            //System.out.println("links");
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (Game != 3) {
                            Game++;
                            //System.out.println("rechts");
                        }
                        break;
                    case KeyEvent.VK_UP:
                        start_game(Game);
                        break;
                }
            //}
        }
    }
}