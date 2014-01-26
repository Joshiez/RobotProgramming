 import lejos.nxt.TouchSensor;
 import lejos.nxt.UltrasonicSensor;
 import lejos.robotics.navigation.DifferentialPilot;
+import lejos.robotics.navigation.Move;
+import lejos.robotics.navigation.MoveListener;
 import lejos.util.Delay;
 
 public class Maze {
+
 	static DifferentialPilot pilot;
 	TouchSensor bump = new TouchSensor(SensorPort.S1);
 	static UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S4);
-	
-	static int[] scanArray = new int[4];
-	
-	static int rotateValue;
+	static int[] scanArray = new int[20];
+	static int rotateValue = 0;
+	static int scanCount = 0;
+	static int largestScan;
+	static int selfRightValue;
+	static int distance;
 
 	public static void main(String[] args) {
-		
+
 		Maze traveler = new Maze();
-		
 		Maze.pilot = new DifferentialPilot(2.1f, 4.6f, Motor.B, Motor.C, false);
-		
+
 		Button.waitForAnyPress();
-		
-		while(!Button.ESCAPE.isDown()){
-		traveler.go();
+
+		while (!Button.ESCAPE.isDown()) {
+			pilot.setTravelSpeed(7);
+			traveler.go();
 		}
 	}
 
 	public void go() {
-		pilot.travel(10000000, true);
 		
+		Delay.msDelay(1000);
+
+		pilot.travel(5, true);
+
+		while (pilot.isMoving()) {
+			if (bump.isPressed()) {
+				pilot.travel(-5);
+				scanForHoles();
+				getLargestScan();
+				moveForHole();
+				scanReset();
+			}
+		}
+
+		pilot.travel(10000000, true);
+
 		while (pilot.isMoving()) {
 			if (bump.isPressed()) {
 				pilot.stop();
 				pilot.travel(-5);
+				//selfRight();
 				scanArea();
 				getLargestScan();
 				direction();
@@ -43,76 +64,203 @@ public class Maze {
 		}
 	}
 	
-	public static void direction(){
-		if(rotateValue == 0){
-			pilot.rotate(45);
+	public static void scanForHoles(){
+		Motor.A.rotate(90);
+		scan();
+		Motor.A.rotate(90);
+		scan();
+		Motor.A.rotate(-270);
+		scan();
+		Motor.A.rotate(90);
+		scan();
+	}
+	
+	public static void moveForHole(){
+		selfRightValue = 0;
+		if (rotateValue == 0) {
+			pilot.rotate(90);
+		}
+		if (rotateValue == 1) {
+			pilot.rotate(180);
+		}
+		if (rotateValue == 2) {
+			pilot.rotate(-90);
+		}
+		if (rotateValue == 3) {
+			pilot.rotate(0);
+		}
+	}
+
+	public static void selfRight() {
+		pilot.rotate(selfRightValue);
+	}
+
+	public static void direction() {
+		// switch case statements
+		// switch(rotateValue) {
+		// case 0:
+		// doStuff;
+		// break;
+		// case 1:
+		// }
+		if (rotateValue == 0) {
+			pilot.rotate(10);
+			selfRightValue = -10;
+		}
+		if (rotateValue == 1) {
+			pilot.rotate(20);
+			selfRightValue = -20;
+		}
+		if (rotateValue == 2) {
+			pilot.rotate(30);
+			selfRightValue = -30;
+		}
+		if (rotateValue == 3) {
+			pilot.rotate(40);
+			selfRightValue = -40;
+		}
+		if (rotateValue == 4) {
+			pilot.rotate(50);
+			selfRightValue = -50;
+		}
+		if (rotateValue == 5) {
+			pilot.rotate(60);
+			selfRightValue = -60;
 		}
-		if(rotateValue == 1){
+		if (rotateValue == 6) {
+			pilot.rotate(70);
+			selfRightValue = -70;
+		}
+		if (rotateValue == 7) {
+			pilot.rotate(80);
+			selfRightValue = -80;
+		}
+		if (rotateValue == 8) {
 			pilot.rotate(90);
+			selfRightValue = -90;
+		}
+		if (rotateValue == 9) {
+			pilot.rotate(100);
+			selfRightValue = -100;
 		}
-		if(rotateValue == 2){
+		if (rotateValue == 10) {
+			pilot.rotate(-100);
+			selfRightValue = 100;
+		}
+		if (rotateValue == 11) {
 			pilot.rotate(-90);
+			selfRightValue = 90;
+		}
+		if (rotateValue == 12) {
+			pilot.rotate(-80);
+			selfRightValue = 80;
+		}
+		if (rotateValue == 13) {
+			pilot.rotate(-70);
+			selfRightValue = 70;
 		}
-		if(rotateValue == 3){
-			pilot.rotate(-45);
+		if (rotateValue == 14) {
+			pilot.rotate(-60);
+			selfRightValue = 60;
 		}
+		if (rotateValue == 15) {
+			pilot.rotate(-50);
+			selfRightValue = 50;
+		}
+		if (rotateValue == 16) {
+			pilot.rotate(-40);
+			selfRightValue = 40;
+		}
+		if (rotateValue == 17) {
+			pilot.rotate(-30);
+			selfRightValue = 30;
+		}
+		if (rotateValue == 18) {
+			pilot.rotate(-20);
+			selfRightValue = 20;
+		}
+		if (rotateValue == 19) {
+			pilot.rotate(-10);
+			selfRightValue = 10;
+		}
+
 	}
-	
-	public static void scanArea(){
-		Motor.A.rotate(45);
+
+	public static void scanArea() {
+
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
 		scan();
-		Motor.A.rotate(45);
+		Motor.A.rotate(10);
 		scan();
-		Motor.A.rotate(-180);
+		Motor.A.rotate(-200);
 		scan();
-		Motor.A.rotate(45);
+		Motor.A.rotate(10);
 		scan();
-		Motor.A.rotate(45);
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+		scan();
+		Motor.A.rotate(10);
+
 	}
-	
-	public static void scan(){
-		 int scanCount = 0;
-		 
-		 if(scanCount == 0){
-			 scanArray[0] = sonar.getDistance();
-			 scanCount++;
-		 }
-		 if(scanCount == 1){
-			 scanArray[1] = sonar.getDistance();
-			 scanCount++;
-		 }
-		 if(scanCount == 2){
-			 scanArray[2] = sonar.getDistance();
-			 scanCount++;
-		 } 
-		 if(scanCount == 3){
-			 scanArray[3] = sonar.getDistance();
-			 scanCount++;
-		 }
+
+	public static void scan() {
+
+		scanArray[scanCount] = sonar.getDistance();
+		System.out.println(sonar.getDistance());
+		scanCount++;
 	}
-	
-	public static void getLargestScan(){
-		int largestScan = 0;
-		for(int i = 0; i < 4; i++){
-			if(scanArray[i] > largestScan){
+
+	public static void getLargestScan() {
+
+		largestScan = 0;
+		for (int i = 0; i < 20; i++) {
+			if (scanArray[i] > largestScan) {
 				largestScan = scanArray[i];
 				rotateValue = i;
 			}
 		}
-		System.out.println("The Largest Scan is " + largestScan);
+		// System.out.println("The Largest Scan is " + largestScan);
 		Delay.msDelay(1000);
-		System.out.println("The Rotate Value is " + rotateValue);
+		// System.out.println("The Rotate Value is " + rotateValue);
 		Delay.msDelay(1000);
-		
+
 	}
-	
-	public static void scanReset(){
-		for(int i = 0; i < 4; i++){
+
+	public static void scanReset() {
+		for (int i = 0; i < 20; i++) {
 			scanArray[i] = 0;
 		}
+		scanCount = 0;
+		rotateValue = 0;
 	}
-	
-	
-	
 
 }
