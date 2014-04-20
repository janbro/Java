package org.jnect.tutorial.testing;
import java.util.HashSet;
import java.util.Set;

import javax.speech.synthesis.SynthesizerModeDesc;
import javax.speech.synthesis.Voice;

import org.jnect.core.KinectManager;
import org.jnect.core.SpeechListener;
 
public class HelloWorldSpeechListener extends SpeechListener {

       private final Set<String> words = new HashSet<String>();

       public HelloWorldSpeechListener(){
                words.add("Jarvis");
        }
 
       @Override
        public Set<String> getWords() {
                return words;
        }

       @Override
        public void notifySpeech(String speech) {
            //System.out.println("Speech recongized: " + speech);
    	    FreeTTSVoice me = new FreeTTSVoice(FreeTTSVoice.VOICE_KEVIN16);
    	    me.open();
    	    me.speak("Hello admin.");
    	    me.close();
        }
 }
 