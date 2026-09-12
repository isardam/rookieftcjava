
/*
 * ============================================================
 * SESSION 6 — OBJECT-ORIENTED ROBOT
 * ============================================================
 *
 * FTC JAVA — ROOKIE TEAM
 *
 * PROJECT:
 *     Virtual Robot
 *
 * BIG IDEA:
 *
 *     A ROBOT OBJECT has:
 *
 *         STATE + BEHAVIOR
 *
 *     STATE:
 *         position
 *         heading
 *         battery
 *         target status
 *
 *     BEHAVIOR:
 *         move()
 *         turnLeft()
 *         turnRight()
 *         scan()
 *         recharge()
 *         reportStatus()
 *
 *
 * JAVA CONCEPTS:
 *
 *     Classes
 *     Objects
 *     Constructors
 *     this
 *     Encapsulation
 *
 *
 * CHALLENGE SEQUENCE
 * ============================================================
 *
 * Challenge 1  - Understand a Class
 * Challenge 2  - Create a Robot Object
 * Challenge 3  - Give the Robot State
 * Challenge 4  - Build a Constructor
 * Challenge 5  - Use "this"
 * Challenge 6  - Give the Robot Behavior
 * Challenge 7  - Change Robot State
 * Challenge 8  - Add a Sensor / Scan
 * Challenge 9  - Create Two Robots
 * Challenge 10 - Encapsulation
 * Challenge 11 - Build a Robot Mission
 * Challenge 12 - Team Challenge
 * Challenge 13 - Final Robot Mission
 *
 * ============================================================
 *
 * IMPORTANT:
 *
 * Complete the challenges in order.
 *
 * Only run ONE challenge at a time.
 *
 * ============================================================
 */


/*
 * ============================================================
 * CLASS: Robot
 * ============================================================
 *
 * This is the BLUEPRINT for a robot.
 *
 * It describes:
 *
 *     What a robot KNOWS
 *
 * and
 *
 *     What a robot CAN DO
 *
 * ============================================================
 */

class Robot {


    // ========================================================
    // CHALLENGE 3
    // ROBOT STATE
    // ========================================================
    //
    // These variables describe the robot.
    //
    // They are INSTANCE VARIABLES because every Robot object
    // gets its OWN copy.
    //
    // ========================================================

    private int x;

    private int y;

    private String heading;

    private int battery;

    private boolean targetFound;


    // ========================================================
    // CHALLENGE 4
    // DEFAULT CONSTRUCTOR
    // ========================================================
    //
    // A constructor prepares a new object.
    //
    // When we write:
    //
    //     Robot robot = new Robot();
    //
    // this constructor runs.
    //
    // ========================================================

    public Robot() {

        x = 0;

        y = 0;

        heading = "NORTH";

        battery = 100;

        targetFound = false;
    }


    // ========================================================
    // CHALLENGE 5
    // CONSTRUCTOR WITH PARAMETERS
    // ========================================================
    //
    // We can create a robot with a custom starting state.
    //
    // Example:
    //
    //     Robot robot = new Robot(
    //         5,
    //         3,
    //         "EAST",
    //         80
    //     );
    //
    // ========================================================

    public Robot(
            int x,
            int y,
            String heading,
            int battery) {


        /*
         * "this" means:
         *
         *     THIS robot object
         *
         * this.x means:
         *
         *     the x belonging to THIS robot
         *
         * x means:
         *
         *     the parameter sent into the constructor
         *
         */

        this.x = x;

        this.y = y;

        this.heading = heading;

        this.battery = battery;

        this.targetFound = false;
    }


    // ========================================================
    // CHALLENGE 6
    // MOVE
    // ========================================================
    //
    // A method gives an object BEHAVIOR.
    //
    // ========================================================

    public void move() {

        if (battery <= 0) {

            System.out.println(
                    "ROBOT: Cannot move. Battery empty!"
            );

            return;
        }


        if (heading.equals("NORTH")) {

            y++;

        } else if (heading.equals("SOUTH")) {

            y--;

        } else if (heading.equals("EAST")) {

            x++;

        } else if (heading.equals("WEST")) {

            x--;
        }


        battery -= 5;


        System.out.println(
                "ROBOT: Moved to (" +
                x +
                ", " +
                y +
                ")"
        );
    }


    // ========================================================
    // CHALLENGE 6B
    // TURN RIGHT
    // ========================================================

    public void turnRight() {

        if (battery < 2) {

            System.out.println(
                    "ROBOT: Not enough battery to turn!"
            );

            return;
        }


        if (heading.equals("NORTH")) {

            heading = "EAST";

        } else if (heading.equals("EAST")) {

            heading = "SOUTH";

        } else if (heading.equals("SOUTH")) {

            heading = "WEST";

        } else {

            heading = "NORTH";
        }


        battery -= 2;


        System.out.println(
                "ROBOT: Turned RIGHT"
        );
    }


    // ========================================================
    // CHALLENGE 6C
    // TURN LEFT
    // ========================================================

    public void turnLeft() {

        if (battery < 2) {

            System.out.println(
                    "ROBOT: Not enough battery to turn!"
            );

            return;
        }


        if (heading.equals("NORTH")) {

            heading = "WEST";

        } else if (heading.equals("WEST")) {

            heading = "SOUTH";

        } else if (heading.equals("SOUTH")) {

            heading = "EAST";

        } else {

            heading = "NORTH";
        }


        battery -= 2;


        System.out.println(
                "ROBOT: Turned LEFT"
        );
    }


    // ========================================================
    // CHALLENGE 7
    // MOVE MULTIPLE SPACES
    // ========================================================
    //
    // This connects Session 5 methods/parameters with
    // today's object-oriented programming.
    //
    // ========================================================

    public void move(int spaces) {

        for (int i = 0; i < spaces; i++) {

            move();
        }
    }


    // ========================================================
    // CHALLENGE 8
    // SCAN
    // ========================================================
    //
    // In a real FTC robot this could eventually use:
    //
    //     color sensor
    //     distance sensor
    //     camera
    //     AprilTag detection
    //
    // For now, we simulate a sensor.
    //
    // ========================================================

    public boolean scan() {

        if (battery < 3) {

            System.out.println(
                    "ROBOT: Not enough battery to scan!"
            );

            return false;
        }


        battery -= 3;


        System.out.println(
                "ROBOT: Scanning..."
        );


        /*
         * Demo target:
         *
         * The target exists at:
         *
         *     x = 3
         *     y = 2
         */

        if (x == 3 && y == 2) {

            targetFound = true;

            System.out.println(
                    "ROBOT: TARGET FOUND!"
            );

        } else {

            targetFound = false;

            System.out.println(
                    "ROBOT: Target not found."
            );
        }


        return targetFound;
    }


    // ========================================================
    // CHALLENGE 9
    // RECHARGE
    // ========================================================

    public void recharge() {

        battery = 100;

        System.out.println(
                "ROBOT: Battery recharged!"
        );
    }


    // ========================================================
    // CHALLENGE 10
    // ENCAPSULATION
    // ========================================================
    //
    // The variables are PRIVATE.
    //
    // Other classes cannot directly change them.
    //
    // Instead, we provide GETTER methods.
    //
    // ========================================================


    public int getX() {

        return x;
    }


    public int getY() {

        return y;
    }


    public String getHeading() {

        return heading;
    }


    public int getBattery() {

        return battery;
    }


    public boolean hasFoundTarget() {

        return targetFound;
    }


    // ========================================================
    // ROBOT STATUS
    // ========================================================

    public void reportStatus() {

        System.out.println();

        System.out.println(
                "========== ROBOT STATUS =========="
        );

        System.out.println(
                "Position: (" +
                x +
                ", " +
                y +
                ")"
        );

        System.out.println(
                "Heading: " +
                heading
        );

        System.out.println(
                "Battery: " +
                battery +
                "%"
        );

        System.out.println(
                "Target found: " +
                targetFound
        );

        System.out.println(
                "=================================="
        );
    }
}


/*
 * ============================================================
 * CLASS: VirtualRobot
 * ============================================================
 *
 * This class runs our challenges.
 *
 * Robot is the blueprint.
 *
 * VirtualRobot is the program controlling the robots.
 *
 * ============================================================
 */

public class VirtualRobot {


    // ========================================================
    // CHALLENGE 1
    // UNDERSTAND THE BLUEPRINT
    // ========================================================
    //
    // No coding yet.
    //
    // Discuss:
    //
    //     What does the Robot class describe?
    //
    //     What information does a robot have?
    //
    //     What actions can a robot perform?
    //
    // ========================================================

    static void challenge1() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 1"
        );

        System.out.println(
                "UNDERSTAND THE ROBOT CLASS"
        );

        System.out.println(
                "================================"
        );


        System.out.println(
                "A class is a blueprint for objects."
        );

        System.out.println(
                "A Robot object has state and behavior."
        );
    }


    // ========================================================
    // CHALLENGE 2
    // CREATE A ROBOT OBJECT
    // ========================================================
    //
    // Class:
    //
    //     Robot
    //
    // Object:
    //
    //     robot
    //
    // ========================================================

    static void challenge2() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 2"
        );

        System.out.println(
                "CREATE A ROBOT OBJECT"
        );

        System.out.println(
                "================================"
        );


        // TODO:
        //
        // Create a Robot object.
        //
        // Example:
        //
        // Robot robot = new Robot();


        // TODO:
        //
        // Call reportStatus().


    }


    // ========================================================
    // CHALLENGE 3
    // CHANGE ROBOT STATE
    // ========================================================
    //
    // Create a robot.
    //
    // Move it.
    //
    // Turn it.
    //
    // Move it again.
    //
    // Display its state before and after.
    //
    // ========================================================

    static void challenge3() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 3"
        );

        System.out.println(
                "CHANGE ROBOT STATE"
        );

        System.out.println(
                "================================"
        );


        Robot robot = new Robot();


        robot.reportStatus();


        // TODO:
        // Move the robot twice.


        // TODO:
        // Turn right.


        // TODO:
        // Move the robot again.


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 4
    // USE A CONSTRUCTOR
    // ========================================================
    //
    // Create a robot starting at:
    //
    //     x = 5
    //     y = 3
    //     heading = EAST
    //     battery = 75
    //
    // ========================================================

    static void challenge4() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 4"
        );

        System.out.println(
                "CUSTOM ROBOT CONSTRUCTOR"
        );

        System.out.println(
                "================================"
        );


        // TODO:
        //
        // Create a Robot using the constructor
        // with parameters.


        // Example:
        //
        // Robot robot = new Robot(
        //     5,
        //     3,
        //     "EAST",
        //     75
        // );


        // TODO:
        // Print the robot status.


    }


    // ========================================================
    // CHALLENGE 5
    // UNDERSTAND "this"
    // ========================================================
    //
    // Look at:
    //
    //     this.x = x;
    //
    // Discuss with your partner:
    //
    //     What does "this" mean?
    //
    // ========================================================

    static void challenge5() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 5"
        );

        System.out.println(
                "UNDERSTAND THIS"
        );

        System.out.println(
                "================================"
        );


        Robot robot = new Robot(
                10,
                4,
                "WEST",
                90
        );


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 6
    // ROBOT BEHAVIOR
    // ========================================================
    //
    // Use the robot's methods to create a path.
    //
    // ========================================================

    static void challenge6() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 6"
        );

        System.out.println(
                "ROBOT BEHAVIOR"
        );

        System.out.println(
                "================================"
        );


        Robot robot = new Robot();


        // TODO:
        //
        // Create this path:
        //
        // Move
        // Move
        // Turn Right
        // Move
        // Move
        // Turn Left
        // Move


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 7
    // BATTERY
    // ========================================================
    //
    // Every action uses battery.
    //
    // Observe how the robot's STATE changes.
    //
    // ========================================================

    static void challenge7() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 7"
        );

        System.out.println(
                "BATTERY STATE"
        );

        System.out.println(
                "================================"
        );


        Robot robot = new Robot();


        robot.reportStatus();


        // TODO:
        //
        // Move several times.
        //
        // Watch the battery decrease.


        robot.reportStatus();


        // TODO:
        //
        // Recharge the robot.


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 8
    // SENSOR / SCAN
    // ========================================================
    //
    // The robot can ask:
    //
    //     "Do I see the target?"
    //
    // scan() returns:
    //
    //     true
    //
    // or
    //
    //     false
    //
    // ========================================================

    static void challenge8() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 8"
        );

        System.out.println(
                "SCAN FOR TARGET"
        );

        System.out.println(
                "================================"
        );


        Robot robot = new Robot(
                3,
                2,
                "NORTH",
                100
        );


        // TODO:
        //
        // Scan the robot.


        // TODO:
        //
        // Store the result in a boolean variable.


        // TODO:
        //
        // Print whether the target was found.


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 9
    // TWO ROBOTS
    // ========================================================
    //
    // THIS is an important OOP challenge.
    //
    // Create two Robot objects.
    //
    // They each have their OWN:
    //
    //     position
    //     heading
    //     battery
    //     target state
    //
    // Move Robot A.
    //
    // Move Robot B.
    //
    // Compare them.
    //
    // ========================================================

    static void challenge9() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 9"
        );

        System.out.println(
                "TWO ROBOTS"
        );

        System.out.println(
                "================================"
        );


        Robot robotA = new Robot(
                0,
                0,
                "NORTH",
                100
        );


        Robot robotB = new Robot(
                5,
                5,
                "SOUTH",
                100
        );


        System.out.println(
                "ROBOT A:"
        );

        robotA.reportStatus();


        System.out.println(
                "ROBOT B:"
        );

        robotB.reportStatus();


        // TODO:
        //
        // Move robotA twice.


        // TODO:
        //
        // Turn robotB right.


        // TODO:
        //
        // Move robotB three times.


        System.out.println();
        System.out.println(
                "AFTER MOVEMENT:"
        );


        robotA.reportStatus();

        robotB.reportStatus();
    }


    // ========================================================
    // CHALLENGE 10
    // ENCAPSULATION
    // ========================================================
    //
    // Try to do this:
    //
    //     robot.battery = 200;
    //
    // It should NOT work.
    //
    // Why?
    //
    // Because battery is PRIVATE.
    //
    // We use:
    //
    //     getBattery()
    //
    // to safely read it.
    //
    // ========================================================

    static void challenge10() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 10"
        );

        System.out.println(
                "ENCAPSULATION"
        );

        System.out.println(
                "================================"
        );


        Robot robot = new Robot();


        // DO NOT do this:
        //
        // robot.battery = 200;
        //
        // It will cause an error because battery is private.


        // TODO:
        //
        // Use getBattery() to read
        // the battery level.


        // TODO:
        //
        // Print the battery level.


    }


    // ========================================================
    // CHALLENGE 11
    // BUILD A ROBOT MISSION
    // ========================================================
    //
    // Mission:
    //
    //     Start at [0,0].
    //
    //     1. Move forward twice.
    //     2. Turn right.
    //     3. Move forward three times.
    //     4. Scan.
    //
    // ========================================================

    static void challenge11() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 11"
        );

        System.out.println(
                "BUILD A ROBOT MISSION"
        );

        System.out.println(
                "================================"
        );


        Robot robot = new Robot();


        // TODO:
        //
        // Complete the mission.


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 12
    // TEAM CHALLENGE
    // ========================================================
    //
    // CREATE YOUR OWN ROBOT METHOD.
    //
    // Examples:
    //
    //     park()
    //     rescue()
    //     collectSample()
    //     scoreGamePiece()
    //     inspectArea()
    //
    // Your method must:
    //
    //     - Belong to Robot.
    //     - Use robot state.
    //     - Perform at least two actions.
    //
    // ========================================================

    static void challenge12() {

        System.out.println();
        System.out.println(
                "================================"
        );

        System.out.println(
                "CHALLENGE 12"
        );

        System.out.println(
                "CREATE YOUR OWN ROBOT BEHAVIOR"
        );

        System.out.println(
                "================================"
        );


        /*
         * TODO:
         *
         * Go back to the Robot class.
         *
         * Create your own method.
         *
         * Example:
         *
         * public void park() {
         *
         *     move();
         *     turnRight();
         *     move();
         *
         * }
         */


        Robot robot = new Robot();


        // TODO:
        //
        // Call your new method.


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 13
    // FINAL ROBOT MISSION
    // ========================================================
    //
    // 🚀 FINAL TEAM CHALLENGE 🚀
    //
    // Your robot must complete a mission.
    //
    // START:
    //
    //     (0,0)
    //
    // TARGET:
    //
    //     (3,2)
    //
    //
    // MISSION:
    //
    //     1. Start the robot.
    //
    //     2. Navigate to the target.
    //
    //     3. Scan for the target.
    //
    //     4. If the target is found:
    //
    //            Print a success message.
    //
    //        Otherwise:
    //
    //            Print a failure message.
    //
    //     5. Print final robot status.
    //
    //
    // BONUS:
    //
    //     Create:
    //
    //         navigateToTarget()
    //
    //     so the main mission becomes readable.
    //
    // ========================================================

    static void challenge13() {

        System.out.println();
        System.out.println(
                "=========================================="
        );

        System.out.println(
                "       FINAL ROBOT MISSION"
        );

        System.out.println(
                "=========================================="
        );


        Robot robot = new Robot();


        // TODO:
        //
        // Navigate to:
        //
        //     x = 3
        //     y = 2
        //
        // Remember:
        //
        // The robot starts facing NORTH.


        // TODO:
        //
        // Scan for the target.


        // TODO:
        //
        // Use the returned boolean to decide
        // whether the mission succeeded.


        // TODO:
        //
        // Display final robot status.


        System.out.println();
        System.out.println(
                "=========================================="
        );

        System.out.println(
                "          MISSION COMPLETE"
        );

        System.out.println(
                "=========================================="
        );
    }


    // ========================================================
    // MAIN
    // ========================================================
    //
    // Run ONE challenge at a time.
    //
    // ========================================================

    public static void main(String[] args) {

        System.out.println();
        System.out.println(
                "=========================================="
        );

        System.out.println(
                "          VIRTUAL ROBOT"
        );

        System.out.println(
                "             SESSION 6"
        );

        System.out.println(
                "=========================================="
        );


        // Challenge 1
        // challenge1();


        // Challenge 2
        // challenge2();


        // Challenge 3
        // challenge3();


        // Challenge 4
        // challenge4();


        // Challenge 5
        // challenge5();


        // Challenge 6
        // challenge6();


        // Challenge 7
        // challenge7();


        // Challenge 8
        // challenge8();


        // Challenge 9
        // challenge9();


        // Challenge 10
        // challenge10();


        // Challenge 11
        // challenge11();


        // Challenge 12
        // challenge12();


        // FINAL CHALLENGE
        // challenge13();


        System.out.println();
        System.out.println(
                "Uncomment ONE challenge and run it."
        );
    }
}
