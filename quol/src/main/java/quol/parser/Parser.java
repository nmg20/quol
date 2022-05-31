package quol.parser;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
//import javax.sound.sampled.*;

public class Parser {
	public static String mainMixerName = "Controlador primario de sonido";
    public static void main( String[] args){
    	Mixer mainMixer = null;
        for(Mixer.Info i : AudioSystem.getMixerInfo()) {
        	if (i.getName().equals(mainMixerName)) {
        		mainMixer = AudioSystem.getMixer(i);
        	}
        	System.out.println(i.getName()+"\t"+i.hashCode());
        }
    }
}
