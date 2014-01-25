import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class TouchTest {
	DifferentialPilot pilot;
	TouchSensor bump = new TouchSensor(SensorPort.S1);

	public static void main(String[] args) {

		TouchTest traveler = new TouchTest();

		traveler.pilot = new DifferentialPilot(2.1f, 4.6f, Motor.B, Motor.C,
				false);

		Button.waitForAnyPress();

		while (Button.ESCAPE.isUp()) {
			traveler.go();
		}
	}

	public void go() {
		pilot.travel(10000000, true);

		while (pilot.isMoving()) {
			if (bump.isPressed()) {
				pilot.stop();
				pilot.travel(-5);
				pilot.rotate(90);
				pilot.stop();
			}
		}
		System.out.println(" " + pilot.getMovement().getDistanceTraveled());
	}

}