/* 
Challenges
1. What does this robot know about itself?
2. What can we do to fix this dashboard
3. The robot's brain has corrupted data. Find the errors and repair the dashboard
4. Practice scenarios
   4.1. Scenario A: You just received fresh battery
   4.2. Scenario B: Your battery is low 
   4.3. Scenario C: Mission Complete. How would you track that?
   4.4. Scenario D: Give your robot full speed

5. Final challenge - create a Mission Status Dashboard containing at least 8 variables.
You must use:
 - atleast 2 int variables
 - atleast 2 double variables
 - atleast 2 boolean variables
 - atleast 2 String variables
*/

public class RobotDashboardChallenge {

    public static void main(String[] args) {

        String robotName = Iron Angel;

        int batteryPercent = "87";

        double motorPower = 75;

        boolean targetReached = "false";

        System.out.println("Robot Name: " + robotName);
        System.out.println("Battery: " + batteryPercent + "%");
        System.out.println("Motor Power: " + motorPower);
        System.out.println("Target Reached: " + targetReached);
    }
}
