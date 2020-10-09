package xavierdufour.engine;

public class GameTime {

    private static final int SLEEP = 25;
    private long before;

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public GameTime() {
        updateSyncTime();
    }

    public void sleep() {
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
