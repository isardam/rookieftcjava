/* 
Challenges
1. How to make our robot decide using sensor data?
                 START
                   |
            Battery < 20%?
              /          \
            YES           NO
             |             |
        RETURN HOME    Obstacle?
                         /     \
                       YES      NO
                        |        |
                     SEARCH   Target?
                              /     \
                            NO       YES
                            |         |
                          SEARCH   Distance?
                                   /     \
                              > 20 cm   <= 20 cm
                                 |          |
                              APPROACH   APPROACH

  2. Scenario 1: low battery
  3. Scenario 2: target found
  4. Scenario 3: no target
  5. Scenario 4: obstacle
*/

public class RobotDecisionEngine {

    public static void main(String[] args) {

        // ========================================
        // ROBOT DATA
        // ========================================

        String robotName = "Iron Angel";

        int batteryPercent = 85;

        double distanceToTarget = 75.0;

        boolean targetDetected = true;

        boolean obstacleDetected = false;


        // ========================================
        // ROBOT STATUS
        // ========================================

        System.out.println("=================================");
        System.out.println("      ROBOT DECISION ENGINE");
        System.out.println("=================================");

        System.out.println("Robot: " + robotName);
        System.out.println("Battery: " + batteryPercent + "%");
        System.out.println("Distance to Target: "
                + distanceToTarget + " cm");
        System.out.println("Target Detected: "
                + targetDetected);
        System.out.println("Obstacle Detected: "
                + obstacleDetected);

        System.out.println("---------------------------------");


        // ========================================
        // ROBOT DECISION ENGINE
        // ========================================

        if (batteryPercent < 20) {

            System.out.println("DECISION: RETURN HOME");
            System.out.println("Reason: Battery is too low.");

        } else if (obstacleDetected) {

            System.out.println("DECISION: SEARCH");
            System.out.println("Reason: Obstacle detected.");

        } else if (!targetDetected) {

            System.out.println("DECISION: SEARCH");
            System.out.println("Reason: Target not detected.");

        } else if (distanceToTarget > 20) {

            System.out.println("DECISION: APPROACH TARGET");
            System.out.println("Reason: Target is more than 20 cm away.");

        } else {

            System.out.println("DECISION: COLLECT TARGET");
            System.out.println("Reason: Target is within 20 cm.");
        }

        System.out.println("---------------------------------");
        System.out.println("Decision complete.");
    }
}
