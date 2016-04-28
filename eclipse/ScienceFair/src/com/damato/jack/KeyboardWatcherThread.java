package com.damato.jack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class KeyboardWatcherThread extends Thread {
	private GameThread game;
	private InputStreamReader isr = new InputStreamReader(System.in);
	private Scanner keyboard = new Scanner(System.in);

	public KeyboardWatcherThread(GameThread game) {
		this.game = game;
	}

	@Override
	public void run() {
		int counter = 0;
		while (game.isRunning()) {
			System.out.print("");
			try {
				if (game.isPlayingSound()) {
					waitForKeyboardInput();
					game.interrupt();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void waitForKeyboardInput() throws Exception {
		keyboard.nextLine();
	}
	
	public void flushKeyboard() throws Exception {
		while (System.in.available() > 0) {
			System.in.read();
		}

	}

}
