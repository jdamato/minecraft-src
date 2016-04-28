package com.damato.jack;

import java.util.Random;
import java.util.Scanner;

public class GameThread extends Thread {
	private boolean isPlaying = false;
	private Random rand = new Random(System.currentTimeMillis());
	private long lastNoteStart;
	private long totalTime;
	private long counter = 0;
	private boolean isRunning = true;
	private KeyboardWatcherThread kwt = null;

	public void setKwt(KeyboardWatcherThread kwt) {
		this.kwt = kwt;
	}

	@Override
	public void run() {
		for (counter = 1; counter < 11; counter++) {
			try {
				Tone.getLine();
				Thread.sleep(2000 + rand.nextInt(3000));
				kwt.flushKeyboard();
				lastNoteStart = System.currentTimeMillis();
				this.isPlaying = true;
				Tone.play(Note.D4, 5000);
				stopPlaying();
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		System.out.println("Average response time: " + (totalTime / counter) + " milliseconds");
		this.isRunning = false;
	}

	public void interrupt() {
		if (this.isPlaying) {
			long result = (System.currentTimeMillis() - lastNoteStart);
			System.out.format("Response time %d: %d milliseconds\n", counter, result);
			totalTime += result;
			Tone.interrupt();
			stopPlaying();
		}
	}
	
	private void stopPlaying() {
		this.isPlaying = false;
	}

	public boolean isPlayingSound() {
		return isPlaying;
	}

	public boolean isRunning() {
		return isRunning;
	}
}
