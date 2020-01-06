import javax.swing.*;
import java.awt.*;

public class Paddle {

    private int x, y;
    private  final int Width=20, Height = 100;
    Board board;
    Game game;
    private  int dy = 7;

    public Paddle(Board board, Game game){
        x= 0;
        y= 0;
        this.board = board;
        this.game = game;
    }

    public void setPosition(int x, int y){
        this.x = x - Width/2;
        this.y = y - Height/2;
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y , Width, Height);
    }

    public void move(){
        if(game.isUpPressed()){
            if(y > 0){
                y-=dy;
            }
        }
        if(game.isDownPressed()){
            if(y + Height < board.getHeight()){
                y+=dy;
            }
        }
    }

    public void move(Ball ball){
        if(ball.getX() + ball.getDiam() > board.getWidth()/2){
            if(ball.getY() > y + Height/2){
                y += 3;
            }
            if(ball.getY() < y + Height/2){
                y -= 3;
            }
        }
    }

    public void paint(Graphics g){
        g.fillRect(x, y, Width, Height);
    }
}
