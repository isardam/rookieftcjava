/*
 * Mission Control
 * Session 1 - Meet Your Robot
 *
 * Our first robot program!
 */

public class MissionControl {

    // This is where our program starts
    public static void main(String[] args) {

        // Robot introduction
        System.out.println("=== ROBOT MISSION CONTROL ===");
        System.out.println("Robot: Iron Angel");
        System.out.println("Status: READY");

        // Mission sequence
        System.out.println();
        System.out.println("MISSION START");

        System.out.println("1. Wake up robot");
        System.out.println("2. Check battery");
        System.out.println("3. Move forward");
        System.out.println("4. Detect obstacle");
        System.out.println("5. Stop");
        System.out.println("6. Mission complete");

        // Mission complete
        System.out.println();
        System.out.println("MISSION COMPLETE!");
        System.out.println("Robot Status: SUCCESS");
    }
}
