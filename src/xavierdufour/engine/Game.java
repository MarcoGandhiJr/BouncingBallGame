package xavierdufour.engine;

public abstract class Game {

    private RenderingEngine renderingEngine;
    private GameTime gameTime;
    private boolean playing = true;

    public Game() {
        renderingEngine = new RenderingEngine();
    }

    public abstract void initialize();
    public abstract void update();
    public abstract void draw(Buffer buffer);
    public abstract void conclude();

    public void start() {
        initialize();
        run();
        conclude();
    }

    public void stop() {
        playing = false;
    }

    public void run() {
        renderingEngine.start();
        gameTime = new GameTime();
        while (playing) {
            update();
            draw(renderingEngine.getRenderingBuffer());
            renderingEngine.renderBufferOnScreen();
            gameTime.synchronize();
        }
        renderingEngine.stop();
    }
}
