package game_logic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clessidra {
	private boolean timeEnded = false;
	private int secondi;
	private ScheduledExecutorService executor;
	
	public void start(int secondi) {
		this.secondi = secondi;
		this.timeEnded = false;
		
		this.executor = Executors.newSingleThreadScheduledExecutor();
		
		this.executor.scheduleAtFixedRate(() -> {
			if(this.secondi > 0) {
				this.secondi--;
			} else {
				this.timeEnded = true;
				executor.shutdown();
			}
		}, 0, 1, TimeUnit.SECONDS);
	}

	public boolean isTimeEnded() {
		return timeEnded;
	}
	
	public int getSecondi() {
		return secondi;
	}
	
	public void stop() {
        if (executor != null && !executor.isShutdown()) {
            executor.shutdown();
            timeEnded = true;
        }
    }
}
