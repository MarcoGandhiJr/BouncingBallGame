package xavierdufour.engine;

import java.awt.*;

public class Game {

    private static final int SLEEP = 25;
    private long before;
    private RenderingEngine renderingEngine;
    private Ball ball;
    private int score = 0;
    private boolean playing = true;

    public Game() {
        renderingEngine = new RenderingEngine();
        ball = new Ball(25);
    }

    public void start() {
        renderingEngine.start();
        updateSyncTime();

        while (playing) {

            update();
            drawOnBuffer(renderingEngine.getRenderingBuffer());
            renderingEngine.renderBufferOnScreen();
            sleep();
            updateSyncTime();
        }
        renderingEngine.stop();
    }

    private void drawOnBuffer(Graphics2D buffer) {
        ball.draw(buffer);
        buffer.setPaint(Color.white);
        buffer.drawString("Score: " + score, 10, 20);
    }



    private void update() {
        ball.update();
        if (ball.hasTouchBound()) {
            score += 10;
        }
    }

    private void sleep() {
        try {
            Thread.sleep(getSleepTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateSyncTime();
    }

    private void updateSyncTime() {
        before = System.currentTimeMillis();
    }

    private long getSleepTime() {
        long sleep = SLEEP - (System.currentTimeMillis() - before);
        if (sleep < 0) {
            sleep = 4;
        }
        return sleep;
    }

}
