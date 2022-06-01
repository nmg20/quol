package quol.parser;

import java.util.Random;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class InputReader {
	
	public static String mainMixerName = "Controlador primario de sonido";
	public static String targetMixerName = "Controlador primario de captura de sonido";
	
	public static AudioFormat format = new AudioFormat(
			AudioFormat.Encoding.PCM_SIGNED,
			44100.0F,
			16, 2, 4,
			44100.0F, false);
	
	private static final DataLine.Info targetDataLineInfo = new DataLine.Info(TargetDataLine.class, format);
	private static final Port.Info myInputType = new Port.Info((Port.class), "LINE_OUT", true);
	private static TargetDataLine targetDataLine = null;
//	public static void main(String[] args) {
//		Mixer mixer = getMixer();
//		try {
//			AudioFormat format = new AudioFormat(44100, 16, 2, true, false);
//			TargetDataLine line = AudioSystem.getTargetDataLine(format);
//			line.open();
//			int size = line.getBufferSize();
//			byte[] data = new byte[size];
//			line.start();
////			for (int i=0; i<5; i++) { //Loop through 5 buffers
////				line.read(data, 0, size);
////				System.out.println("LINE: "+line.toString());
////				System.out.println("\tLevel: "+line.getLevel());
////			}
//			line.drain();
//			line.close();
//		} catch (LineUnavailableException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static Mixer getMixer() {
		Mixer mixer = null;
		for (Mixer.Info mixerInfo : AudioSystem.getMixerInfo()) {
			mixer = AudioSystem.getMixer(mixerInfo);
			if (mixer.getMixerInfo().getName().equals("Controlador primario de captura de sonido")) {
				System.out.println("Encontrado!!"+mixer.getMixerInfo().getDescription());
			}
		}
		return mixer;
	}
	
	private static Mixer getPortMixerInfo(Mixer.Info mixerInfo) {
		try {
			Mixer targetMixer = AudioSystem.getMixer(mixerInfo);
			targetMixer.open();
			if (targetMixer.isLineSupported(targetDataLineInfo)) {
				System.out.println(mixerInfo.getName()+" supports DataLine");
			}
		}
	}
	
	public static void main(String[] args) {
		Mixer portMixer = null;
		Mixer targetMixer = null;
		try {
			for (Mixer.Info mi : AudioSystem.getMixerInfo()) {
				if (mi.getName().equals(targetMixerName)) {
					System.out.println("Trying to get portmixer for: "+mi.getName());
					portMixer = getPort
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
