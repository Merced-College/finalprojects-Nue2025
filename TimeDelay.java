/**
 * TimeDelay.java
 */
public class TimeDelay {
//	Taken from online: https://www.baeldung.com/java-delay-code-execution
	public void pauseTime(int secondDelay) {
		//I used AI and a website to help me with pausing the console. I asked if I did anything wrong and it gave me 
		//a suggestion. Idea from AI is to ensure a second delay isn't negative. However, it won't affect the program 
		//by the user. The programmer will determine how long the console will pause.
		if(secondDelay < 0) return;
		
//		System.out.println("pauseTime() will stop after " + secondDelay +" seconds.");
		try {
			Thread.sleep(secondDelay * 1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
//		System.out.println("pauseTime() resumed.");
	}
}
