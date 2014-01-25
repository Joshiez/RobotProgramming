import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Maze2 {
	
	static DifferentialPilot pilot;
	TouchSensor bump = new TouchSensor(SensorPort.S1);
	static UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S4);
	static int[] scanArray = new int[10];
	static int rotateValue;
	static int scanCount = 0;
	static int largestScan;

	public static void main(String[] args) {
		
		Maze traveler = new Maze();
		Maze.pilot = new DifferentialPilot(2.1f, 4.6f, Motor.B, Motor.C, false);

		Button.waitForAnyPress();
		
		while(!Button.ESCAPE.isDown()){
		traveler.go();
		}
	}

	public void go() {
		
		pilot.travel(10000000, true);
		
		while (pilot.isMoving()) {
			if (bump.isPressed()) {
				pilot.stop();
				pilot.travel(-3);
				scanArea();
				getLargestScan();
				direction();
				scanReset();
			}
		}
	}
	
	public static void direction(){
		
		if(rotateValue == 0){
			pilot.rotate(20);
		}
		if(rotateValue == 1){
			pilot.rotate(40);
		}
		if(rotateValue == 2){
			pilot.rotate(60);
		}
		if(rotateValue == 3){
			pilot.rotate(80);
		}
		if(rotateValue == 4){
			pilot.rotate(100);
		}
		if(rotateValue == 5){
			pilot.rotate(-20);
		}
		if(rotateValue == 6){
			pilot.rotate(-40);
		}
		if(rotateValue == 7){
			pilot.rotate(-60);
		}
		if(rotateValue == 8){
			pilot.rotate(-80);
		}
		if(rotateValue == 9){
			pilot.rotate(-100);
		}
	
	}
	
	public static void scanArea(){
		
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(-200);
		scan();
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(20);
		scan();
		Motor.A.rotate(20);
	}
	
	public static void scan(){
		 	 
		scanArray[scanCount] = sonar.getDistance();

		scanCount++;
	}
	
	public static void getLargestScan(){
		
		largestScan = 0;
		for(int i = 0; i < 10; i++){
			if(scanArray[i] > largestScan){
				largestScan = scanArray[i];
				rotateValue = i;
			}
		}
		System.out.println("The Largest Scan is " + largestScan);
		Delay.msDelay(1000);
		System.out.println("The Rotate Value is " + rotateValue);
		Delay.msDelay(1000);
		
	}
	
	public static void scanReset(){
		for(int i = 0; i < 10; i++){
			scanArray[i] = 0;
		}
		scanCount = 0;
	}
	
}




