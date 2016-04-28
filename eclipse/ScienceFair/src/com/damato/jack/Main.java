package com.damato.jack;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


public class Main {

	public static void main(String[] args) throws LineUnavailableException, InterruptedException {
		Tone.play(Note.A4, 1);
    	GameThread game = new GameThread();
    	KeyboardWatcherThread watcher = new KeyboardWatcherThread(game);
    	game.setKwt(watcher);
    	game.start();
		watcher.start();
    }
}
