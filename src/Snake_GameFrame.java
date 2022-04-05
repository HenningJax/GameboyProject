import javax.swing.*;

public class Snake_GameFrame extends JFrame {

    Snake_GameFrame(){
        this.add(new Snake_Game());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    static void stop(){
    }
}