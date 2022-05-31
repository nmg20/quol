package quol.parser;

//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Mixer;
import javax.sound.sampled.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        for(Mixer.Info i : AudioSystem.getMixerInfo()) {
        	System.out.println(i.getName()+"\t"+i.hashCode());
        }
    }
}
