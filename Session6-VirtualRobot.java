 
/*
 * ============================================================
 * SESSION 6 — OBJECT-ORIENTED ROBOT
 * ============================================================
 *
 * FTC JAVA — ROOKIE TEAM
 *
 * DELIVERABLE:
 *     VirtualRobot.java
 *
 * BIG IDEA:
 *
 *     OBJECT = STATE + BEHAVIOR
 *
 * STATE:
 *     position
 *     heading
 *     battery
 *     target state
 *
 * BEHAVIOR:
 *     move()
 *     turnLeft()
 *     turnRight()
 *     scan()
 *     recharge()
 *     reportStatus()
 *
 *
 * IMPORTANT:
 * ============================================================
 *
 * EVERY CHALLENGE IS INDEPENDENT.
 *
 * You can run Challenge 1 by itself.
 * You can run Challenge 8 by itself.
 * You can run Challenge 13 by itself.
 *
 * A challenge NEVER depends on another challenge having
 * been run first.
 *
 * ============================================================
 */


/*
 * ============================================================
 * ROBOT CLASS
 * ============================================================
 *
 * This is our robot BLUEPRINT.
 *
 * Every Robot object gets its own:
 *
 *     x
 *     y
 *     heading
 *     battery
 *     targetFound
 *
 * ============================================================
 */

class Robot {


    // ========================================================
    // ROBOT STATE
    // ========================================================

    private int x;

    private int y;

    private String heading;

    private int battery;

    private boolean targetFound;


    // ========================================================
    // DEFAULT CONSTRUCTOR
    // ========================================================
    //
    // Creates a robot at:
    //
    //     (0, 0)
    //
    // facing:
    //
    //     NORTH
    //
    // with:
    //
    //     100% battery
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
    // PARAMETERIZED CONSTRUCTOR
    // ========================================================
    //
    // Allows us to create a custom robot.
    //
    // ========================================================

    public Robot(
            int x,
            int y,
            String heading,
            int battery) {

        /*
         * "this" means THIS robot object.
         */

        this.x = x;

        this.y = y;

        this.heading = heading;

        this.battery = battery;

        this.targetFound = false;
    }


    // ========================================================
    // MOVE
    // ========================================================

    public void move() {

        if (battery < 5) {

            System.out.println(
                    "ROBOT: Not enough battery to move!"
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
    // MOVE MULTIPLE SPACES
    // ========================================================

    public void move(int spaces) {

        for (int i = 0; i < spaces; i++) {

            move();
        }
    }


    // ========================================================
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
    // SCAN
    // ========================================================
    //
    // Demo target:
    //
    //     (3, 2)
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
    // RECHARGE
    // ========================================================

    public void recharge() {

        battery = 100;

        System.out.println(
                "ROBOT: Battery recharged!"
        );
    }


    // ========================================================
    // GETTERS
    // ========================================================
    //
    // These demonstrate BASIC ENCAPSULATION.
    //
    // The variables are private.
    //
    // Other code cannot directly change them.
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
    // STATUS
    // ========================================================

    public void reportStatus() {

        System.out.println();

        System.out.println(
                "--------------------------------"
        );

        System.out.println(
                "ROBOT STATUS"
        );

        System.out.println(
                "--------------------------------"
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
                "--------------------------------"
        );
    }
}


/*
 * ============================================================
 * MAIN CLASS
 * ============================================================
 */

public class VirtualRobot {


    // ========================================================
    // HELPER
    // ========================================================

    static void title(
            int number,
            String name) {

        System.out.println();

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "CHALLENGE " +
                number +
                " - " +
                name
        );

        System.out.println(
                "=========================================="
        );
    }


    // ========================================================
    // CHALLENGE 1
    // UNDERSTAND THE CLASS
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Understand that Robot is a CLASS / BLUEPRINT.
    //
    // ========================================================

    static void challenge1() {

        title(1, "UNDERSTAND THE CLASS");


        System.out.println(
                "A class is a blueprint."
        );

        System.out.println(
                "Robot describes what a robot HAS"
        );

        System.out.println(
                "and what a robot CAN DO."
        );


        System.out.println();

        System.out.println(
                "Robot has:"
        );

        System.out.println(
                "  position"
        );

        System.out.println(
                "  heading"
        );

        System.out.println(
                "  battery"
        );

        System.out.println(
                "  target state"
        );


        System.out.println();

        System.out.println(
                "Robot can:"
        );

        System.out.println(
                "  move"
        );

        System.out.println(
                "  turn"
        );

        System.out.println(
                "  scan"
        );
    }


    // ========================================================
    // CHALLENGE 2
    // CREATE AN OBJECT
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Create an actual Robot object from the class.
    //
    // ========================================================

    static void challenge2() {

        title(2, "CREATE A ROBOT OBJECT");


        // TODO:
        //
        // Create a Robot object.
        //
        // Example:
        //
        // Robot robot = new Robot();


        // TODO:
        //
        // Display the robot's status.


    }


    // ========================================================
    // CHALLENGE 3
    // WATCH STATE CHANGE
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Observe how an object's state changes.
    //
    // ========================================================

    static void challenge3() {

        title(3, "WATCH STATE CHANGE");


        // Every challenge creates its OWN robot.
        //
        // This makes the challenge independent.

        Robot robot = new Robot();


        System.out.println(
                "BEFORE:"
        );

        robot.reportStatus();


        // TODO:
        //
        // Move the robot twice.


        // TODO:
        //
        // Turn right.


        // TODO:
        //
        // Move again.


        System.out.println(
                "AFTER:"
        );

        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 4
    // CONSTRUCTOR
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Create a robot with a custom starting state.
    //
    // ========================================================

    static void challenge4() {

        title(4, "USE A CONSTRUCTOR");


        // TODO:
        //
        // Create a robot with:
        //
        // x = 5
        // y = 3
        // heading = EAST
        // battery = 75
        //
        // Example:
        //
        // Robot robot =
        //     new Robot(5, 3, "EAST", 75);


        // TODO:
        //
        // Display the robot's status.


    }


    // ========================================================
    // CHALLENGE 5
    // UNDERSTAND "this"
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Understand:
    //
    //     this.x = x;
    //
    // ========================================================

    static void challenge5() {

        title(5, "UNDERSTAND THIS");


        Robot robot = new Robot(
                10,
                4,
                "WEST",
                90
        );


        System.out.println(
                "The robot was created with:"
        );

        System.out.println(
                "x = 10"
        );

        System.out.println(
                "y = 4"
        );

        System.out.println(
                "heading = WEST"
        );

        System.out.println(
                "battery = 90"
        );


        System.out.println();

        robot.reportStatus();


        System.out.println();

        System.out.println(
                "Look at the constructor:"
        );

        System.out.println(
                "this.x = x;"
        );

        System.out.println();

        System.out.println(
                "this.x means the robot's x."
        );

        System.out.println(
                "x means the parameter."
        );
    }


    // ========================================================
    // CHALLENGE 6
    // ROBOT BEHAVIOR
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Use object methods to control the robot.
    //
    // ========================================================

    static void challenge6() {

        title(6, "ROBOT BEHAVIOR");


        Robot robot = new Robot();


        // TODO:
        //
        // Create this path:
        //
        // move
        // move
        // turnRight
        // move
        // move
        // turnLeft
        // move


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 7
    // BATTERY
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Watch state change as the robot performs actions.
    //
    // ========================================================

    static void challenge7() {

        title(7, "BATTERY MANAGEMENT");


        Robot robot = new Robot();


        robot.reportStatus();


        // TODO:
        //
        // Move the robot several times.


        // TODO:
        //
        // Check the battery using:
        //
        // robot.getBattery();


        // TODO:
        //
        // Recharge the robot.


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 8
    // SCAN FOR TARGET
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Use a method that RETURNS a value.
    //
    // ========================================================

    static void challenge8() {

        title(8, "SCAN FOR TARGET");


        // The target is at:
        //
        //     (3, 2)

        Robot robot = new Robot(
                3,
                2,
                "NORTH",
                100
        );


        // TODO:
        //
        // Call scan().
        //
        // Store the result in:
        //
        // boolean found


        // TODO:
        //
        // Use an if statement to print:
        //
        // "SUCCESS!"
        //
        // or
        //
        // "TARGET NOT FOUND!"


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 9
    // TWO ROBOTS
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Discover that every object has its OWN state.
    //
    // ========================================================

    static void challenge9() {

        title(9, "TWO ROBOTS");


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
                "BEFORE:"
        );

        robotA.reportStatus();

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


        System.out.println(
                "AFTER:"
        );

        robotA.reportStatus();

        robotB.reportStatus();


        System.out.println();

        System.out.println(
                "QUESTION:"
        );

        System.out.println(
                "Why did Robot A's position not"
        );

        System.out.println(
                "change when Robot B moved?"
        );
    }


    // ========================================================
    // CHALLENGE 10
    // ENCAPSULATION
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Understand PRIVATE variables and getters.
    //
    // ========================================================

    static void challenge10() {

        title(10, "ENCAPSULATION");


        Robot robot = new Robot();


        System.out.println(
                "Battery is private."
        );

        System.out.println(
                "We cannot do:"
        );

        System.out.println(
                "robot.battery = 200;"
        );

        System.out.println();


        // TODO:
        //
        // Use:
        //
        // robot.getBattery()
        //
        // to read the battery.


        // TODO:
        //
        // Print the battery.


        System.out.println();

        System.out.println(
                "PRIVATE variables protect"
        );

        System.out.println(
                "the object's state."
        );
    }


    // ========================================================
    // CHALLENGE 11
    // BUILD A MISSION
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Combine multiple robot behaviors.
    //
    // ========================================================

    static void challenge11() {

        title(11, "BUILD A ROBOT MISSION");


        Robot robot = new Robot();


        System.out.println(
                "MISSION:"
        );

        System.out.println(
                "1. Move twice"
        );

        System.out.println(
                "2. Turn right"
        );

        System.out.println(
                "3. Move three times"
        );

        System.out.println(
                "4. Scan"
        );


        // TODO:
        //
        // Write the mission.


        robot.reportStatus();
    }


    // ========================================================
    // CHALLENGE 12
    // CREATE YOUR OWN ROBOT BEHAVIOR
    // ========================================================
    //
    // OBJECTIVE:
    //
    // Go into the Robot class and create a new method.
    //
    // Examples:
    //
    //     park()
    //     collectSample()
    //     score()
    //     rescue()
    //     inspectArea()
    //
    // ========================================================

    static void challenge12() {

        title(12, "CREATE YOUR OWN BEHAVIOR");


        Robot robot = new Robot();


        /*
         * TODO:
         *
         * Go to the Robot class.
         *
         * Add your own method.
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
    // 🚀 FINAL TEAM CHALLENGE
    //
    // START:
    //
    //     (0,0)
    //
    // TARGET:
    //
    //     (3,2)
    //
    // MISSION:
    //
    //     Navigate to the target.
    //
    //     Scan.
    //
    //     If found:
    //
    //         SUCCESS!
    //
    //     Otherwise:
    //
    //         FAILURE!
    //
    // BONUS:
    //
    //     Use a method called:
    //
    //         navigateToTarget()
    //
    // ========================================================

    static void challenge13() {

        title(13, "FINAL ROBOT MISSION");


        Robot robot = new Robot();


        System.out.println(
                "STARTING MISSION..."
        );


        robot.reportStatus();


        // ====================================================
        // TODO:
        //
        // Navigate to (3,2).
        //
        // Remember:
        //
        // The robot starts at (0,0)
        // facing NORTH.
        //
        // ====================================================


        // ====================================================
        // TODO:
        //
        // Scan for the target.
        //
        // Store the returned boolean.
        // ====================================================


        // ====================================================
        // TODO:
        //
        // Check whether the target was found.
        // ====================================================


        robot.reportStatus();


        System.out.println();

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "FINAL MISSION COMPLETE!"
        );

        System.out.println(
                "=========================================="
        );
    }


    // ========================================================
    // MAIN
    // ========================================================
    //
    // IMPORTANT:
    //
    // Every challenge can be run independently.
    //
    // Uncomment EXACTLY ONE line below.
    //
    // ========================================================

    public static void main(String[] args) {


        System.out.println();

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "        VIRTUAL ROBOT — SESSION 6"
        );

        System.out.println(
                "=========================================="
        );


        /*
         * ====================================================
         * PICK ONE CHALLENGE
         * ====================================================
         *
         * Uncomment ONE line.
         *
         */


        // challenge1();

        // challenge2();

        // challenge3();

        // challenge4();

        // challenge5();

        // challenge6();

        // challenge7();

        // challenge8();

        // challenge9();

        // challenge10();

        // challenge11();

        // challenge12();

        // challenge13();


        System.out.println();

        System.out.println(
                "Choose ONE challenge in main()."
        );

        System.out.println(
                "Uncomment it and run the program."
        );
    }
}
 
