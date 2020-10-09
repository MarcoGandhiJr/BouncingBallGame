package xavierdufour.engine;

import java.awt.*;

public class BouncingBallGame extends Game{

    private Ball ball;
    private int score = 0;

    public BouncingBallGame() {
        ball = new Ball(25);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void update() {
        ball.update();
        if (ball.hasTouchBound()) {
            score += 10;
        }
    }

    @Override
    public void draw(Buffer buffer) {
        ball.draw(buffer);
        buffer.drawText("Score: " + score, 10, 20, Color.white);
    }

    @Override
    public void conclude() {

    }

}
