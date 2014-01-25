import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class PaternChange {

	DifferentialPilot pilot;

	public static void main(String[] args) {

		PaternChange viktor = new PaternChange();

		viktor.pilot = new DifferentialPilot(2.1f, 4.6f, Motor.B, Motor.C,
				false);

		Button.waitForAnyPress();

		while (Button.ESCAPE.isUp()) {
			viktor.go();
		}
	}

	@SuppressWarnings("deprecation")
	public void go() {
		while (!Button.ENTER.isPressed()) {
			pilot.travel(5, true);
			pilot.rotate(90, true);
		}

		Delay.msDelay(1000);

		while (!Button.ENTER.isPressed()) {
			pilot.travel(5, true);
			pilot.rotate(-90, true);
		}
	}

}
