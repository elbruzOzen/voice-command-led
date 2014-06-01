package ioio.examples.command_led;

/*
 * CommandLed.java 
 * 
 * Command three LEDs that are connected to IOIO board by using voice commands.
 * 
 * Pin Connections:
 * 4->Green
 * 5->White
 * 6->Yellow
 * IOIO Board is connected to PC (A Mode)
 * 
 * Author: Elbruz Özen
 * 
 * IOIO-OTG Board Library (Firmware App-IOIO0330)
 * https://github.com/ytai/ioio/wiki/Downloads
 * 
 * CMU Sphinx - Speech Recognition Toolkit Library (sphinx4-0.1alpha)
 * http://cmusphinx.sourceforge.net/wiki/download/
 * 
 */

import java.io.IOException;
import java.net.URL;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOConnectionManager.Thread;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.pc.IOIOConsoleApp;

public class App extends IOIOConsoleApp {
	
	URL url;
	ConfigurationManager cm;
	Microphone microphone;
	Recognizer recognizer;
	
	//We will change these variables by using voice commands
	private boolean ledOn_4;
	private boolean ledOn_5;
	private boolean ledOn_6;

	public App() throws IOException, PropertyException, InstantiationException{
            
		url = App.class.getResource("app.config.xml");
		cm = new ConfigurationManager(url);
		recognizer = (Recognizer) cm.lookup("recognizer");
		microphone = (Microphone) cm.lookup("microphone");
		
		recognizer.allocate();
		
		ledOn_4 = false;
		ledOn_5 = false;
		ledOn_6 = false;
	
	}
	
	// Boilerplate main(). Copy-paste this code into any IOIOapplication.
	public static void main(String[] args) throws Exception {
		new App().go(args);
	}

	@Override
	protected void run(String[] args) throws IOException {
		
		if (microphone.startRecording()) {

			while (true) {
			    
				System.out.println("Start speaking. Press Ctrl-C to quit.\n");
			    Result result = recognizer.recognize();
			    
			    if (result != null) {
			    	
			    	//Get the best combination in .gram file and print
			    	String resultText = result.getBestFinalResultNoFiller();
			    	System.out.println("You said: " + resultText + "\n");
					
			    	//These commands are fixed in .gram file under bin folder, you need also edit them
			    	//If you make changes in project, make sure .gram file is still under this folder 
			    	//and app.config.xml indicates it correctly
			    	
					if(resultText.equals("toggle led green")){
						ledOn_4 = !ledOn_4;
					}
					else if(resultText.equals("toggle led white")){
						ledOn_5 = !ledOn_5;
					}
					else if(resultText.equals("toggle led yellow")){
						ledOn_6 = !ledOn_6;
					}
				
			    }else{
			    	
			    	System.out.println("I can't hear what you said.\n");
			    
			    }
			
			}
	    
		}else{
	    	
			System.out.println("Cannot start microphone.");
			recognizer.deallocate();
			System.exit(1);
	    
		}
	}

	//IOIO code controls three digital ports by using three boolean variables above
	@Override
	public IOIOLooper createIOIOLooper(String connectionType, Object extra) {
		
		return new BaseIOIOLooper() {
			
			private DigitalOutput led_4;
			private DigitalOutput led_5;
			private DigitalOutput led_6;

			@Override
			protected void setup() throws ConnectionLostException,
					InterruptedException {
				
				//Port numbers are on the right side
				led_4 = ioio_.openDigitalOutput(4, true);
				led_5 = ioio_.openDigitalOutput(5, true);
				led_6 = ioio_.openDigitalOutput(6, true);
			
			}
			
			@Override
			public void loop() throws ConnectionLostException,InterruptedException {
				
				//In loop, refresh led states for each 10ms
				led_4.write(ledOn_4);
				led_5.write(ledOn_5);
				led_6.write(ledOn_6);
				Thread.sleep(10);
			
			}
		};
	}
}
