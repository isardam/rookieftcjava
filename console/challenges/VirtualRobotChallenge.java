 
/*
 * ============================================================
 * SESSION 6 — OBJECT-ORIENTED ROBOT
 * ============================================================
 *
 * FTC JAVA — ROOKIE TEAM
 *
 * PROJECT:
 *     Build a Virtual Robot
 *
 * JAVA CONCEPTS:
 *     Classes
 *     Objects
 *     Constructors
 *     this
 *     Encapsulation
 *
 * BIG IDEA:
 *
 *     An object combines:
 *
 *          STATE + BEHAVIOR
 *
 *     STATE:
 *          position
 *          heading
 *          battery
 *          target status
 *
 *     BEHAVIOR:
 *          move()
 *          turn()
 *          scan()
 *
 * ============================================================
 *
 * CHALLENGE SEQUENCE
 *
 *     Challenge 1  - Create the Robot
 *     Challenge 2  - Create a Robot Object
 *     Challenge 3  - Add Robot State
 *     Challenge 4  - Add a Constructor
 *     Challenge 5  - Learn "this"
 *     Challenge 6  - Add Robot Behavior
 *     Challenge 7  - Change Robot State
 *     Challenge 8  - Add a Sensor
 *     Challenge 9  - Create Multiple Robots
 *     Challenge 10 - Protect Robot State
 *     Challenge 11 - Build a Mission
 *     Challenge 12 - Create Your Own Method
 *     Challenge 13 - Final Mission
 *
 * ============================================================
 */


/*
 * ============================================================
 * CHALLENGE 1
 *
 * CREATE THE ROBOT CLASS
 * ============================================================
 *
 * A CLASS is a blueprint.
 *
 * Think:
 *
 *     LEGO instruction book
 *     cookie cutter
 *     blueprint
 *
 * The class tells us what a Robot object will contain.
 *
 * ============================================================
 */

class SimRobot {

    /*
     * ========================================================
     * CHALLENGE 3
     *
     * ADD ROBOT STATE
     * ========================================================
     *
     * Every robot should know:
     *
     *     x position
     *     y position
     *     heading
     *     battery
     *     whether it found the target
     *
     * These are called INSTANCE VARIABLES.
     *
     * Each Robot object gets its own copy.
     *
     * ========================================================
     */

    private int x;
    private int y;
    private String heading;
    private int battery;
    private boolean targetFound;


    /*
     * ========================================================
     * CHALLENGE 4
     *
     * DEFAULT CONSTRUCTOR
     * ========================================================
     *
     * A constructor runs when we create an object.
     *
     * Example:
     *
     *     SimRobot robot = new SimRobot();
     *
     * ========================================================
     */

    public SimRobot() {

        x = 0;
        y = 0;
        heading = "NORTH";
        battery = 100;
        targetFound = false;
    }


    /*
     * ========================================================
     * CHALLENGE 5
     *
     * PARAMETERIZED CONSTRUCTOR
     * ========================================================
     *
     * We can create a robot with a custom starting state.
     *
     * Example:
     *
     *     SimRobot robot = new SimRobot(
     *         5,
     *         3,
     *         "EAST",
     *         80
     *     );
     *
     * ========================================================
     */

    public SimRobot(
            int x,
            int y,
            String heading,
            int battery) {

        /*
         * "this" means:
         *
         *     THIS robot object.
         *
         * this.x
         * means the x belonging to THIS robot.
         *
         * x
         * means the constructor parameter.
         */

        this.x = x;
        this.y = y;
        this.heading = heading;
        this.battery = battery;
        this.targetFound = false;
    }


    /*
     * ========================================================
     * CHALLENGE 6
     *
     * GIVE THE ROBOT BEHAVIOR
     * ========================================================
     *
     * A METHOD describes something an object can DO.
     *
     * ========================================================
     */

    public void move() {

        if (battery < 5) {

            System.out.println(
                    "Robot cannot move. Battery too low."
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
                "Robot moved to (" +
                x +
                ", " +
                y +
                ")"
        );
    }


    /*
     * ========================================================
     * TURN RIGHT
     * ========================================================
     */

    public void turnRight() {

        if (battery < 2) {

            System.out.println(
                    "Robot cannot turn. Battery too low."
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
                "Robot turned RIGHT."
        );
    }


    /*
     * ========================================================
     * TURN LEFT
     * ========================================================
     */

    public void turnLeft() {

        if (battery < 2) {

            System.out.println(
                    "Robot cannot turn. Battery too low."
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
                "Robot turned LEFT."
        );
    }


    /*
     * ========================================================
     * CHALLENGE 7
     *
     * METHOD WITH A PARAMETER
     * ========================================================
     *
     * This connects back to Session 5.
     *
     *     move()
     *
     * means move once.
     *
     *     move(5)
     *
     * means move five times.
     *
     * ========================================================
     */

    public void move(int spaces) {

        for (int i = 0; i < spaces; i++) {

            move();
        }
    }


    /*
     * ========================================================
     * CHALLENGE 8
     *
     * ADD A SENSOR
     * ========================================================
     *
     * In a real FTC robot this could eventually become:
     *
     *     color sensor
     *     distance sensor
     *     camera
     *     AprilTag detector
     *
     * For now, we simulate a sensor.
     *
     * Target location:
     *
     *     (3, 2)
     *
     * scan() RETURNS a boolean.
     *
     * ========================================================
     */

    public boolean scan() {

        if (battery < 3) {

            System.out.println(
                    "Robot cannot scan. Battery too low."
            );

            return false;
        }


        battery -= 3;


        System.out.println(
                "Robot is scanning..."
        );


        if (x == 3 && y == 2) {

            targetFound = true;

            System.out.println(
                    "TARGET FOUND!"
            );

        } else {

            targetFound = false;

            System.out.println(
                    "Target not found."
            );
        }


        return targetFound;
    }


    /*
     * ========================================================
     * RECHARGE
     * ========================================================
     */

    public void recharge() {

        battery = 100;

        System.out.println(
                "Robot recharged."
        );
    }


    /*
     * ========================================================
     * CHALLENGE 10
     *
     * ENCAPSULATION
     * ========================================================
     *
     * The variables are PRIVATE.
     *
     * Other code cannot directly change them.
     *
     * Instead we use GETTERS.
     * ========================================================
     */

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


    /*
     * ========================================================
     * DISPLAY ROBOT STATE
     * ========================================================
     */

    public void reportStatus() {

        System.out.println();
        System.out.println(
                "--------------------------------"
        );

        System.out.println(
                "ROBOT STATUS"
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
                "Target Found: " +
                targetFound
        );

        System.out.println(
                "--------------------------------"
        );
    }
}


/*
 * ============================================================
 * VIRTUAL ROBOT
 * ============================================================
 *
 * This class contains our challenge sequence.
 *
 * ============================================================
 */

public class VirtualRobotChallenge {


    /*
     * ========================================================
     * CHALLENGE 1
     *
     * UNDERSTAND THE CLASS
     * ========================================================
     */

    static void challenge1() {

        System.out.println();
        System.out.println(
                "CHALLENGE 1: ROBOT CLASS"
        );

        System.out.println(
                "Robot is a blueprint."
        );

        System.out.println(
                "It describes robot STATE and BEHAVIOR."
        );
    }


    /*
     * ========================================================
     * CHALLENGE 2
     *
     * CREATE A ROBOT OBJECT
     * ========================================================
     *
     * STUDENT TASK:
     *
     * Create a Robot object.
     *
     * ========================================================
     */

    static void challenge2() {

        System.out.println();
        System.out.println(
                "CHALLENGE 2: CREATE AN OBJECT"
        );


        // TODO:
        //
        // Create a Robot object.
        //
        // Example:
        //
        // SimRobot robot = new SimRobot();


        // TODO:
        //
        // Display the robot's status.
    }


    /*
     * ========================================================
     * CHALLENGE 3
     *
     * WATCH ROBOT STATE
     * ========================================================
     */

    static void challenge3() {

        System.out.println();
        System.out.println(
                "CHALLENGE 3: ROBOT STATE"
        );


        SimRobot robot = new SimRobot();


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
        // Move once more.


        System.out.println(
                "AFTER:"
        );

        robot.reportStatus();
    }


    /*
     * ========================================================
     * CHALLENGE 4
     *
     * CONSTRUCTOR
     * ========================================================
     */

    static void challenge4() {

        System.out.println();
        System.out.println(
                "CHALLENGE 4: CONSTRUCTOR"
        );


        // TODO:
        //
        // Create a robot at:
        //
        // x = 5
        // y = 3
        // heading = EAST
        // battery = 80


        // TODO:
        //
        // Display its status.
    }


    /*
     * ========================================================
     * CHALLENGE 5
     *
     * THIS
     * ========================================================
     */

    static void challenge5() {

        System.out.println();
        System.out.println(
                "CHALLENGE 5: THIS"
        );


        SimRobot robot = new SimRobot(
                5,
                3,
                "EAST",
                80
        );


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
                "this.x = robot's x"
        );

        System.out.println(
                "x = constructor parameter"
        );
    }


    /*
     * ========================================================
     * CHALLENGE 6
     *
     * ROBOT BEHAVIOR
     * ========================================================
     */

    static void challenge6() {

        System.out.println();
        System.out.println(
                "CHALLENGE 6: ROBOT BEHAVIOR"
        );


        SimRobot robot = new SimRobot();


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


    /*
     * ========================================================
     * CHALLENGE 7
     *
     * PARAMETERS
     * ========================================================
     */

    static void challenge7() {

        System.out.println();
        System.out.println(
                "CHALLENGE 7: PARAMETERS"
        );


        SimRobot robot = new SimRobot();


        // TODO:
        //
        // Use:
        //
        // robot.move(5);
        //
        // to move five spaces.


        robot.reportStatus();
    }


    /*
     * ========================================================
     * CHALLENGE 8
     *
     * RETURN VALUES
     * ========================================================
     */

    static void challenge8() {

        System.out.println();
        System.out.println(
                "CHALLENGE 8: SCAN"
        );


        SimRobot robot = new SimRobot(
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
        // Use an if statement:
        //
        // if found:
        //     print SUCCESS
        //
        // otherwise:
        //     print TRY AGAIN


        robot.reportStatus();
    }


    /*
     * ========================================================
     * CHALLENGE 9
     *
     * OBJECTS HAVE THEIR OWN STATE
     * ========================================================
     */

    static void challenge9() {

        System.out.println();
        System.out.println(
                "CHALLENGE 9: TWO ROBOTS"
        );


        SimRobot robotA = new SimRobot(
                0,
                0,
                "NORTH",
                100
        );


        SimRobot robotB = new SimRobot(
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
        // Move Robot A twice.


        // TODO:
        //
        // Turn Robot B right.


        // TODO:
        //
        // Move Robot B twice.


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
                "Why does each robot have"
        );

        System.out.println(
                "different state?"
        );
    }


    /*
     * ========================================================
     * CHALLENGE 10
     *
     * ENCAPSULATION
     * ========================================================
     */

    static void challenge10() {

        System.out.println();
        System.out.println(
                "CHALLENGE 10: ENCAPSULATION"
        );


        SimRobot robot = new SimRobot();


        /*
         * This would NOT work:
         *
         * robot.battery = 200;
         *
         * Why?
         *
         * Because battery is PRIVATE.
         */


        // TODO:
        //
        // Use getBattery()
        // to read the battery.


        System.out.println();

        System.out.println(
                "Private variables protect"
        );

        System.out.println(
                "the robot's internal state."
        );
    }


    /*
     * ========================================================
     * CHALLENGE 11
     *
     * BUILD A ROBOT MISSION
     * ========================================================
     *
     * Mission:
     *
     *     Start at (0,0)
     *
     *     Travel to (3,2)
     *
     *     Scan for the target.
     *
     * ========================================================
     */

    static void challenge11() {

        System.out.println();
        System.out.println(
                "CHALLENGE 11: ROBOT MISSION"
        );


        SimRobot robot = new SimRobot();


        /*
         * Starting position:
         *
         *     (0,0)
         *
         * Target:
         *
         *     (3,2)
         *
         * Robot starts facing NORTH.
         */


        // TODO:
        //
        // Navigate to (3,2).


        // TODO:
        //
        // Scan.


        // TODO:
        //
        // Print whether the mission succeeded.


        robot.reportStatus();
    }


    /*
     * ========================================================
     * CHALLENGE 12
     *
     * CREATE YOUR OWN METHOD
     * ========================================================
     *
     * Students return to the Robot class and create
     * a new behavior.
     *
     * Examples:
     *
     *     park()
     *     collectSample()
     *     score()
     *     rescue()
     *     returnHome()
     *
     * ========================================================
     */

    static void challenge12() {

        System.out.println();
        System.out.println(
                "CHALLENGE 12: CREATE A ROBOT COMMAND"
        );


        SimRobot robot = new SimRobot();


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


    /*
     * ========================================================
     * CHALLENGE 13
     *
     * FINAL ROBOT MISSION
     * ========================================================
     *
     * 🚀 TEAM CHALLENGE
     *
     * Your robot starts at:
     *
     *     (0,0)
     *
     * facing:
     *
     *     NORTH
     *
     * Target:
     *
     *     (3,2)
     *
     * REQUIREMENTS:
     *
     *     1. Create a Robot object.
     *
     *     2. Navigate to the target.
     *
     *     3. Scan.
     *
     *     4. Determine whether the target was found.
     *
     *     5. Report the final robot state.
     *
     * BONUS:
     *
     *     Create a method:
     *
     *         navigateToTarget()
     *
     *     that performs the navigation.
     *
     * ========================================================
     */

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


        SimRobot robot = new SimRobot();


        robot.reportStatus();


        // TODO:
        //
        // Navigate to:
        //
        //     (3,2)


        // TODO:
        //
        // Scan for the target.


        // TODO:
        //
        // Check the result.


        // TODO:
        //
        // Report final status.


        System.out.println();

        System.out.println(
                "MISSION COMPLETE!"
        );
    }


    /*
     * ========================================================
     * MAIN
     * ========================================================
     *
     * Run ONE challenge at a time.
     *
     * Uncomment the challenge you are working on.
     *
     * ========================================================
     */

    public static void main(String[] args) {

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "       VIRTUAL ROBOT — SESSION 6"
        );

        System.out.println(
                "=========================================="
        );


        // CHALLENGE 1
        // challenge1();


        // CHALLENGE 2
        // challenge2();


        // CHALLENGE 3
        // challenge3();


        // CHALLENGE 4
        // challenge4();


        // CHALLENGE 5
        // challenge5();


        // CHALLENGE 6
        // challenge6();


        // CHALLENGE 7
        // challenge7();


        // CHALLENGE 8
        // challenge8();


        // CHALLENGE 9
        // challenge9();


        // CHALLENGE 10
        // challenge10();


        // CHALLENGE 11
        // challenge11();


        // CHALLENGE 12
        // challenge12();


        // FINAL CHALLENGE
        // challenge13();


        System.out.println();

        System.out.println(
                "Uncomment ONE challenge in main()."
        );
    }
} 
