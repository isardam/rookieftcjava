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

            // An obstacle in the way means GO AROUND it.
            // (It does NOT mean "search" - the robot already
            //  knows where it wants to go, something is just
            //  blocking the path.)
            System.out.println("DECISION: AVOID OBSTACLE");
            System.out.println("Reason: Something is blocking "
                    + "the path.");

        } else if (!targetDetected) {

            System.out.println("DECISION: SEARCH");
            System.out.println("Reason: Target not detected.");

        } else if (distanceToTarget > 20) {

            System.out.println("DECISION: APPROACH TARGET");
            System.out.println("Reason: Target detected but "
                    + "robot is still more than 20 cm away.");

        } else {

            // We got here because: battery is fine, no obstacle,
            // target IS detected, and it is 20 cm or closer.
            // Close enough to pick it up!
            System.out.println("DECISION: COLLECT TARGET");
            System.out.println("Reason: Target is within 20 cm.");
        }

        System.out.println("---------------------------------");
        System.out.println("Decision complete.");
    }
}
