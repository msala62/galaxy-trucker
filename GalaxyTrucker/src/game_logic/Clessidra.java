package game_logic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clessidra {
    private volatile boolean timeEnded = false;
    private volatile int remainingSeconds;
    private ScheduledExecutorService executor;

    public void start(int seconds) {
        this.remainingSeconds = seconds;
        this.timeEnded = false;
        
        executor = Executors.newSingleThreadScheduledExecutor();
        
        executor.scheduleAtFixedRate(() -> {
            if (remainingSeconds > 0) {
                remainingSeconds--;
            } else {
                System.out.println("======================================== Tempo scaduto! ========================================");
                timeEnded = true;
                executor.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public boolean isTimeEnded() {
        return timeEnded;
    }
    
    public int getRemainingTime() {
        return remainingSeconds;
    }
    
    public void stop() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            timeEnded = true;
        }
    }
}
