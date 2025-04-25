package game_logic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clessidra {
	public void run() {
		// esecutore --> viene utilizzato per svolgere un compito dopo un tempo specifico
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		// operazione --> ciè che deve essere eseguito dall'esecutore in sequenza
		Runnable task = new Runnable() {
			int secondi = 60;
			
			@Override
			public void run() {
				if(secondi > 0) {
					secondi--;
				} else {
					System.out.println("Tempo scaduto!");
					executor.shutdown();
				}
			}
		};
		
		// Imposta l'esecuzione della task ogni secondo, senza delay iniziale
		executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
	}
}
