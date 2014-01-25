import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Maze {
	static DifferentialPilot pilot;
	TouchSensor bump = new TouchSensor(SensorPort.S1);
	static UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S4);
	
	static int[] scanArray = new int[4];
	
	static int rotateValue;

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
				pilot.travel(-5);
				scanArea();
				getLargestScan();
				direction();
				scanReset();
			}
		}
	}
	
	public static void direction(){
		if(rotateValue == 0){
			pilot.rotate(45);
		}
		if(rotateValue == 1){
			pilot.rotate(90);
		}
		if(rotateValue == 2){
			pilot.rotate(-90);
		}
		if(rotateValue == 3){
			pilot.rotate(-45);
		}
	}
	
	public static void scanArea(){
		Motor.A.rotate(45);
		scan();
		Motor.A.rotate(45);
		scan();
		Motor.A.rotate(-180);
		scan();
		Motor.A.rotate(45);
		scan();
		Motor.A.rotate(45);
	}
	
	public static void scan(){
		 int scanCount = 0;
		 
		 if(scanCount == 0){
			 scanArray[0] = sonar.getDistance();
			 scanCount++;
		 }
		 if(scanCount == 1){
			 scanArray[1] = sonar.getDistance();
			 scanCount++;
		 }
		 if(scanCount == 2){
			 scanArray[2] = sonar.getDistance();
			 scanCount++;
		 } 
		 if(scanCount == 3){
			 scanArray[3] = sonar.getDistance();
			 scanCount++;
		 }
	}
	
	public static void getLargestScan(){
		int largestScan = 0;
		for(int i = 0; i < 4; i++){
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
		for(int i = 0; i < 4; i++){
			scanArray[i] = 0;
		}
	}
	
	
	

}