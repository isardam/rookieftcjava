/*
 * Robot Dashboard
 * Session 2 - Variables & Data
 *
 * Our robot needs information about itself
 * before it can make decisions.
 */

public class RobotDashboard {

    public static void main(String[] args) {

        // ==============================
        // ROBOT INFORMATION
        // ==============================

        String robotName = "Iron Angel";

        // Battery percentage
        int batteryPercent = 87;

        // Motor power: -1.0 = full reverse
        //               0.0 = stopped
        //               1.0 = full forward
        double motorPower = 0.75;

        // Has the robot reached its target?
        boolean targetReached = false;


        // ==============================
        // ROBOT DASHBOARD
        // ==============================

        System.out.println("================================");
        System.out.println("        ROBOT DASHBOARD");
        System.out.println("================================");

        System.out.println("Robot Name: " + robotName);
        System.out.println("Battery: " + batteryPercent + "%");
        System.out.println("Motor Power: " + motorPower);
        System.out.println("Target Reached: " + targetReached);

        System.out.println("================================");
    }
}
