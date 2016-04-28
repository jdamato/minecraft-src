package com.damato.jack;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Tone {
	private static final AudioFormat af = new AudioFormat(Note.SAMPLE_RATE, 8, 1, true, true);
	private static SourceDataLine line_DONOTUSEDIRECTLY = null;

	public static void play(Note note, int ms) throws LineUnavailableException, InterruptedException {
		ms = Math.min(ms, Note.SECONDS * 1000);
		int length = Note.SAMPLE_RATE * ms / 1000;
		int count = getLine().write(note.data(), 0, length);
	}

	public static SourceDataLine getLine() throws LineUnavailableException, InterruptedException {
		if (line_DONOTUSEDIRECTLY == null) {
			line_DONOTUSEDIRECTLY = AudioSystem.getSourceDataLine(af);
			line_DONOTUSEDIRECTLY.open(af, Note.SAMPLE_RATE);
			line_DONOTUSEDIRECTLY.start();
		}
		return line_DONOTUSEDIRECTLY;
	}

	public static void interrupt() {
		if (line_DONOTUSEDIRECTLY != null) {
			line_DONOTUSEDIRECTLY.stop();
			line_DONOTUSEDIRECTLY.close();
			line_DONOTUSEDIRECTLY = null;
		}
	}
}