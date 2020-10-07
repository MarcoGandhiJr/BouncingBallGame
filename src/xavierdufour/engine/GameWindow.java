package xavierdufour.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow {

    private static final int SLEEP = 25;
    private long before;
    private JFrame frame;
    private JPanel panel;
    private Ball ball;
    private int score = 0;
    private boolean playing = true;
    private BufferedImage bufferedImage;
    private Graphics2D buffer;

    public GameWindow() {
        initializeFrame();
        initializePanel();
        ball = new Ball(25);
    }

    public void start() {
        frame.setVisible(true);
        updateSyncTime();

        while (playing) {
            bufferedImage = new BufferedImage(800, 600,
                    BufferedImage.TYPE_INT_RGB);
            buffer = bufferedImage.createGraphics();
            buffer.setRenderingHints(getOptimalRenderingHints());
            update();
            drawOnBuffer();
            drawOnScreen();
            sleep();
        }

    }

    private void drawOnBuffer() {
        ball.draw(buffer);
        buffer.setPaint(Color.white);
        buffer.drawString("Score: " + score, 10, 20);
    }

    private void drawOnScreen() {
        Graphics2D graphics2D = (Graphics2D) panel.getGraphics();
        graphics2D.drawImage(bufferedImage, 0, 0, panel);
        Toolkit.getDefaultToolkit().sync();
        graphics2D.dispose();
    }

    private void update() {
        ball.update();
        if (ball.hasTouchBound()) {
            score += 10;
        }
    }

    private void initializePanel() {
        panel = new JPanel();
        panel.setBackground(Color.blue);
        panel.setFocusable(true);
        panel.setDoubleBuffered(true);
        frame.add(panel);
    }

    private void initializeFrame() {
        frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setTitle("Bouncing Ball Game");
        //setUndecorated(true); (Remove la barre du haut)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private RenderingHints getOptimalRenderingHints() {
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        return rh;
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
