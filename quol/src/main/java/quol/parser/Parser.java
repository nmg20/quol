package quol.parser;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.Port;
//import javax.sound.sampled.*;

public class Parser {
	public static String mainMixerName = "Controlador primario de sonido";
    public static void main( String[] args){
    	Mixer mainMixer = null;
    	Port line = null;
        for(Mixer.Info i : AudioSystem.getMixerInfo()) {
        	if (i.getName().equals(mainMixerName)) {
        		mainMixer = AudioSystem.getMixer(i);
        	}
        	System.out.println(i.getName()+"\t"+i.hashCode());
        }
        if (AudioSystem.isLineSupported(Port.Info.LINE_OUT)) {
            try {
                line = (Port) AudioSystem.getLine(
                    Port.Info.LINE_OUT);
            } catch (Exception e) {
            	e.printStackTrace();
            }
        }
    }
}
