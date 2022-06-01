package quol.parser;

import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class Noise {
	public static void main(String[] args) {
		try {
			AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
			SourceDataLine line = AudioSystem.getSourceDataLine(format);
			line.open();
			line.start();
			System.out.println("LEVEL: "+line.getLevel());
			int size = line.getBufferSize(); //Get size of line's internal buffer
			byte[] data = new byte[size]; //Create byte array of that size
			Random random = new Random(); 
//			for (int i=0;i<10;i++) { //Runs for 10 complete buffer lengths (5 secs)
//				random.nextBytes(data); //Refill del array con ruido
//				line.write(data,  0,  size); //byte array, starting location, size
//			}
			line.drain();
			line.close();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
