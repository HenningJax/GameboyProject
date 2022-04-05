import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Snake_Game extends JPanel implements ActionListener {

        static final int SCREEN_WIDTH = 480;
        static final int SCREEN_HEIGHT = 320;
        static final int UNIT_SIZE = 20;
        static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
        private final int DELAY = 85;

        private final int x[] = new int[GAME_UNITS];
        private final int y[] = new int[GAME_UNITS];
        int bodyParts = 6;
        int applesEaten = 0;
        int appleX;
        int appleY;
        char direction = 'R';
        boolean running = false;
        Timer timer;
        Random random;

        Snake_Game(){
            random = new Random();
            this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
            setBackground(new Color(28, 30, 38));
            this.setFocusable(true);
            this.addKeyListener(new MyKeyAdapter());
            startGame();
        }
        public void startGame(){
            newApple();
            running = true;
            timer = new Timer(DELAY,this);
            timer.start();
        }
        public void checkCollisions(){
            //Head mit Body Colliding
            for(int i=bodyParts;i>0;i--){
                if((x[0])==x[i]&& (y[0]==y[i])){
                    running = false;
                }
            }
            //Head mit Wall rechts Colliding
            if(x[0]< 0) {
                running = false;}
            //Head mit Wall links Colliding
            if(x[0]> SCREEN_WIDTH) {
                running = false;}
            //Head mit Wall oben Colliding
            if(y[0]< 0) {
                running = false;}
            //Head mit Wall unten Colliding
            if(y[0]> SCREEN_HEIGHT) {
                running = false;}

            if(!running){
                timer.stop();
            }
        }
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            draw(g);
        }
        public void draw(Graphics g){
            if(running) {
                for (int i = 0; i < SCREEN_WIDTH / UNIT_SIZE; i=i+2) {
                    for (int t = 0; t < SCREEN_HEIGHT / UNIT_SIZE; t=t+2) {
                        g.fillRect(i * UNIT_SIZE, t*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    }
                }
                for (int i = 1; i < SCREEN_WIDTH / UNIT_SIZE; i=i+2) {
                    for (int t = 1; t < SCREEN_HEIGHT / UNIT_SIZE; t=t+2) {
                        g.fillRect(i * UNIT_SIZE, t*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
                    }
                }

                g.setColor(Color.red);
                g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

                for (int i = 0; i < bodyParts; i++) {
                    if (i == 0) {
                        g.setColor(Color.GREEN);
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    } else {
                        g.setColor(new Color(45, 180, 0));
                        g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                    }
                }
                g.setColor(Color.red);
                g.setFont(new Font("Calibri",Font.BOLD, 20));
                FontMetrics metrics = getFontMetrics(g.getFont());
                g.drawString("Score: "+applesEaten, 6/(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten)),g.getFont().getSize());
            }
            else {
                gameOver(g);
            }
        }
        public void newApple(){
            appleX = random.nextInt((int)SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE;
            appleY = random.nextInt((int)SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE;
        }

        public void move(){
            for(int i = bodyParts;i>0;i--){
                x[i] = x[i-1];
                y[i] = y[i-1];
            }

            switch (direction){
                case 'U':
                    y[0] = y[0] - UNIT_SIZE;
                    break;
                case 'D':
                    y[0] = y[0] + UNIT_SIZE;
                    break;
                case 'L':
                    x[0] = x[0] - UNIT_SIZE;
                    break;
                case 'R':
                    x[0] = x[0] + UNIT_SIZE;
                    break;
            }
        }
        public void checkApple(){
            if((x[0] == appleX)&& (y[0] == appleY)){
                bodyParts++;
                applesEaten++;
                newApple();
            }
        }
        public void gameOver(Graphics g){
            //Game Over SCreen
            g.setColor(Color.red);
            g.setFont(new Font("Comic Sans",Font.BOLD, 45));
            FontMetrics metrics1 = getFontMetrics(g.getFont());
            g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
            g.setColor(Color.red);
            g.setFont(new Font("Comic Sans",Font.BOLD, 30));
            FontMetrics metrics2 = getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(running) {
                move();
                checkApple();
                checkCollisions();
            }
            repaint();
        }
        public class MyKeyAdapter extends KeyAdapter {

            @Override
            public void keyPressed(KeyEvent e) {
                    if (running=true) {
                        switch(e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction != 'R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction != 'L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction != 'D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction != 'U') {
                                direction = 'D';
                            }
                            break;
                        }
                    }
                    else{
                        switch(e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                Snake_GameFrame.stop();
                                break;
                        }
                    }
                }
            }
}

