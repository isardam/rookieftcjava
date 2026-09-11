/*
 * Robot Decision Engine
 * Session 3 - Make the Robot Think
 *
 * The robot uses information about its environment
 * to decide what it should do next.
 */

public class RobotDecisionEngine {

    public static void main(String[] args) {

        // ========================================
        // ROBOT DATA
        // ========================================

        String robotName = "Iron Angel";

        double distanceToTarget = 75.0;  // centimeters
        int batteryPercent = 82;

        boolean targetDetected = true;
        boolean obstacleDetected = false;


        // ========================================
        // DISPLAY ROBOT STATUS
        // ========================================

        System.out.println("=================================");
        System.out.println("       ROBOT DECISION ENGINE");
        System.out.println("=================================");

        System.out.println("Robot: " + robotName);
        System.out.println("Battery: " + batteryPercent + "%");
        System.out.println("Target Detected: " + targetDetected);
        System.out.println("Obstacle Detected: " + obstacleDetected);
        System.out.println("Distance to Target: "
                + distanceToTarget + " cm");

        System.out.println("---------------------------------");


        // ========================================
        // MAKE A DECISION
        // ========================================

        if (batteryPercent < 20) {

            System.out.println("DECISION: RETURN HOME");
            System.out.println("Reason: Battery is too low.");

        } else if (obstacleDetected) {

            System.out.println("DECISION: SEARCH");
            System.out.println("Reason: Obstacle detected.");

        } else if (targetDetected && distanceToTarget > 20) {

            System.out.println("DECISION: APPROACH TARGET");
            System.out.println("Reason: Target detected and "
                    + "robot is still far away.");

        } else if (targetDetected && distanceToTarget <= 20) {

            System.out.println("DECISION: APPROACH TARGET");
            System.out.println("Reason: Target is nearby.");

        } else {

            System.out.println("DECISION: SEARCH");
            System.out.println("Reason: Target not detected.");
        }

        System.out.println("---------------------------------");
        System.out.println("Decision complete.");
    }
}
