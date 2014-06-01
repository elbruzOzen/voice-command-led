Control multiple LEDs with voice command.

*************** REQUIREMENTS *********************************************

-Project is written in Java.You need installed Java SDK and Eclipse 4.3 Kepler
-You need IOIO-OTG Board, different colours of LEDs, 470 Ohm resistors , cables or jumpers and preferably a breadboard.


*********** SETTING UP SOFTWARE ***************************************

-Import IOIOLib project into Eclipse, make sure "copy into workspace" box is not selected and only check IOIOLibPC library

-Import VoiceCommandLED, again not copy into your workspace 
(If you want to store them in your workspace, firstly copy paste them into workspace, then follow two previous steps)

-Make sure you have not got any errors, For IOIOLib you shouldn't got otherwise you can look at here(Only PC lib is necessary!)
https://github.com/ytai/ioio/wiki/Using-IOIO-With-a-PC

-VoiceCommandLED must have 4 jar files indicated in buildPath(In the folder of VoiceCommandLED) and IOIOLib project
If you have a problem, try to edit resources which gives an error in build path.

-Finally you will find a file ends with ".gram" with project, just copy into the directory which contains class files of 
VoiceCommandLED (VoiceCommandLED\bin\ioio\examples\command_led)

-You can edit them later for different commands later, make sure a copy of it stored somewhere else. Sometimes this directory can be formatted.

-Now you need to have two projects without error. No, wait a little for running them!

************* HARDWARE *************************************************

-Circuit is given as a Fritzing sketch and an image, just establish it.

-Make sure A-Mode selected on IOIO board. (H is used for Android)

-Connect it PC with a Android USB cable(Phone Cable)

-Try to run project(For first time: Right Click to App Class>Run As>Run Configurations> Write "-mx256m" as VM argument and click Run) 

-Check console, you need to establish a successful connection and program must start to listen.

-If you have a problems in connection try to reconnect cable again(Sometimes works :)), if you can not solve problem, open device manager and
try to change UnknownDriver with Compaq Ricochet Modem Driver. Check this video, this is for Arduino but steps are completely same.
http://www.youtube.com/watch?v=CdE72XUYC7k

-It should work now, if you have questions or problems I will be glad to answer them.
