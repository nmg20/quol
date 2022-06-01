package quol.parser;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.TargetDataLine;
//import javax.sound.sampled.*;

public class Parser {
	public static String mainMixerName = "Controlador primario de sonido";
	
	public static AudioFormat format = new AudioFormat(
			AudioFormat.Encoding.PCM_SIGNED,
			44100,
			16, 2, 4,
			44100, false);
	
	/*public static void n() throws LineUnavailableException {
		Mixer mainMixer = null;
		Port port = null;
		for(Mixer.Info i : AudioSystem.getMixerInfo()) {
			if (i.getName().equals(mainMixerName)) {
				mainMixer = AudioSystem.getMixer(i);
			}
			System.out.println(i.getName()+"\t"+i.hashCode());
		}
		if (AudioSystem.isLineSupported(Port.Info.MICROPHONE)) {
			try {
				port = (Port) AudioSystem.getLine(Port.Info.MICROPHONE);
				System.out.println("PORT: "+port+"\t"+port.getLineInfo());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (Mixer.Info info : AudioSystem.getMixerInfo()) {
			Mixer mixer = AudioSystem.getMixer(info);
			for (Line.Info lineInfo : mixer.getSourceLineInfo()) {
				System.out.println("LINE INFO: "+lineInfo);
				Line line = mixer.getLine(lineInfo);
				System.out.println("\t-----"+line.toString());
			}
		}
	}*/
	
	public static void level() {
		TargetDataLine line = null;
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
		if(!AudioSystem.isLineSupported(info)) {
			System.out.println("Line not supported:"+line);
		}
		try {
			line = (TargetDataLine) AudioSystem.getLine(info);
			line.open(format);
			System.out.println("Line opened: "+line);
			
			line.start();
			byte[] buffer = new byte[1024];
			int i = 0, numBytesRead = 0;
			while(i++<100) {
				numBytesRead = line.read(buffer,  0, buffer.length);
				System.out.println("\nnumBytesRead: "+numBytesRead);
				if (numBytesRead == 0) {
					continue;
				}
				for (int j=0; j<16; j++) {
					if (buffer[j] != 0) {
						System.out.print(". ");
					} else {
						System.out.print("0 ");
					}
				}
			}
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public static void printMixers() {
		for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			try {
				mixer.open();
				for (Line.Info lineInfo : mixer.getSourceLineInfo()) {
					System.out.println("LINE INFO: "+lineInfo);
					Line line = mixer.getLine(lineInfo);
					System.out.println("\tLINE: "+line);
				}
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void getVolumeOfAllLines() {
		for(Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			try {
				mixer.open();
				for(Line.Info lineInfo : mixer.getTargetLineInfo()) {
					if(AudioSystem.isLineSupported(lineInfo)) {
						DataLine line = (DataLine) AudioSystem.getLine(lineInfo);
						if (line != null) {
							System.out.println(line.getLevel());
						}
					}
				}
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main( String[] args) throws LineUnavailableException{
    	for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
			Mixer mixer = AudioSystem.getMixer(mixerInfo);
			try {
				mixer.open();
				for (Line.Info lineInfo : mixer.getSourceLineInfo()) {
					System.out.println("[SOURCE]");
					System.out.println("\tLINE INFO: "+lineInfo);
					Line line = mixer.getLine(lineInfo);
					System.out.println("\tLINE: "+line);
				}
				for (Line.Info lineInfo : mixer.getTargetLineInfo()) {
					System.out.println("[TARGET]");
					System.out.println("\tLINE INFO: "+lineInfo);
					Line line = mixer.getLine(lineInfo);
					System.out.println("\tLINE: "+line);
				}
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		}
//    	getVolumeOfAllLines();
    	level();
    }
	
	
}
