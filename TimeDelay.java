/**
 * TimeDelay.java
 */
public class TimeDelay {
	/**
	 * The function/method called pauseTime() pauses the console screen. I used both: an AI and a website (see link provided), 
	 * to help me with pausing the console. The programmer will determine how long the console will pause. Seconds are determined by
	 * the programmer, and multiplied by 1000 to remove the milliseconds.
	 * @see https://www.baeldung.com/java-delay-code-execution
	 * @param secondDelay
	 */
	public void pauseTime(int secondDelay) {
		if(secondDelay < 0) return;
		try { Thread.sleep(secondDelay * 1000);}
		catch(InterruptedException e) { e.printStackTrace(); }
	}
}