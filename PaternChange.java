import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;

public class PaternChange {

	static DifferentialPilot pilot;
	static int paternValue = 1;

	public static void main(String[] args) {

		PaternChange viktor = new PaternChange();

		viktor.pilot = new DifferentialPilot(2.1f, 4.6f, Motor.B, Motor.C,
				false);
		
		
		Button.waitForAnyPress();

		Button.ENTER.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				paternChange();
			}

			public void buttonReleased(Button b) {
				runPatern();
			}
		}); 

		Button.ESCAPE.waitForPressAndRelease();
	}

	public static void runPatern() {

		if (paternValue == 1) {
			System.out.println("1");
			while (Button.ENTER.isUp()) {
				pilot.rotate(90,true);
				pilot.travel(5);
			}
		} else {
			System.out.println("2");
			while (Button.ENTER.isUp()) {
				pilot.rotate(-90,true);
				pilot.travel(5);
			}
		}

	}
	
	public static void runPatern1(){
		System.out.println("1");
		while(Button.ESCAPE.isUp()){
			pilot.rotate(90);
			pilot.travel(5);
		}
	}
	
	public static void runPatern2(){
		System.out.println("2");
		while(Button.ENTER.isUp()){
			pilot.rotate(-90);
			pilot.travel(5);
		}
	}
	
	public static void paternChange() {
		if (paternValue == 1) {
			paternValue = 2;
		} else {
			paternValue = 1;
		}
	}

}
