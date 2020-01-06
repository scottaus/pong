import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JPanel implements ActionListener {

    final int Width = 800;
    final int Height = 600;

    private final int EdgeSpace = 50;
    private final int DecorSize = 25;

    private int pScore = 0, cScore = 0;

    Paddle pPaddle;
    Paddle cPaddle;
    Ball ball;
    Timer timer;
    Game game;
    

    public Board(Game game){

        setPreferredSize(new Dimension(Width, Height));
        setBackground(Color.BLACK);

        pPaddle = new Paddle(this, game);
        cPaddle = new Paddle(this, game);
        ball = new Ball(this);
    }

    public void init(){
        ball.setPosition(Width/2, Height/2);
        pPaddle.setPosition(EdgeSpace, Height/2);
        cPaddle.setPosition(Width-EdgeSpace, Height/2);
        timer = new Timer(1000/50, this);
        timer.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 72));
        printSimpleString(Integer.toString(pScore), getWidth()/2, 0, DecorSize*2, g);
        printSimpleString(Integer.toString(cScore), getWidth()/2, getWidth()/2, DecorSize*2, g);

        pPaddle.paint(g);
        cPaddle.paint(g);
        ball.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ball.checkCollisions(cPaddle);
        ball.checkCollisions(pPaddle);
        ball.move();
        cPaddle.move(ball);
        pPaddle.move();
        repaint();
    }

    private void printSimpleString(String s, int width, int XPos, int YPos, Graphics g){
        int stringLen = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();
        int start = width/2 - stringLen/2;
        g.drawString(s, start+XPos, YPos);
    }

    public int getpScore() {
        return pScore;
    }

    public void setpScore(int pScore) {
        this.pScore = pScore;
    }

    public int getcScore() {
        return cScore;
    }

    public void setcScore(int cScore) {
        this.cScore = cScore;
    }
}
