 
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
 *
 * COACH: RUN CHALLENGE 9 FIRST
 * ============================================================
 *
 * Challenge 9 ("Two Robots") is the best way into this whole
 * topic. Students create two robots, move one, and discover
 * for themselves that the other one did not move.
 *
 * That DISCOVERY is what "each object has its own state"
 * actually means. Teaching the definition first and the
 * discovery second is backwards - the words land on nothing.
 *
 * Suggested order:
 *
 *     9   two robots      <- start here, discover it
 *     2   create an object
 *     3   watch state change
 *     4   constructor
 *     1   predict the robot
 *     6   behaviour
 *     7   battery
 *     8   scan (returns a value)
 *     5   break 'this' on purpose
 *     10  encapsulation
 *     11  build a mission
 *     12  your own behaviour
 *     13  final mission
 *
 * NOTE ON COORDINATES
 * ============================================================
 *
 * We use the SAME meaning as Session 5:
 *
 *     EAST  = x bigger      NORTH = y bigger
 *     WEST  = x smaller     SOUTH = y smaller
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

class SimRobot {


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

    public SimRobot() {

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

    public SimRobot(
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

        title(1, "PREDICT THE ROBOT");


        /*
         * ACTIVITY - do this BEFORE running the program.
         *
         * Go and read the SimRobot class at the top of this
         * file. Do not run anything yet.
         *
         * On paper, write down what you think each of these
         * will print:
         *
         *     1. A brand new robot's position
         *     2. A brand new robot's heading
         *     3. A brand new robot's battery
         *
         *     4. After move() twice, what position?
         *     5. After move() twice, what battery?
         *        (hint: find where battery -= 5 happens)
         *
         *     6. After turnRight(), what heading?
         *     7. After turnRight(), what battery?
         *
         * NOW run it and check your answers.
         *
         * Every answer you got wrong is a place where your
         * mental model of the code does not match the code.
         * Those are the valuable ones - go find out why.
         */

        SimRobot robot = new SimRobot();

        System.out.println("A brand new robot:");
        robot.reportStatus();

        robot.move();
        robot.move();

        System.out.println("After moving twice:");
        robot.reportStatus();

        robot.turnRight();

        System.out.println("After turning right:");
        robot.reportStatus();


        System.out.println();
        System.out.println(
                "How many did you predict correctly?"
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
        // SimRobot robot = new SimRobot();


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
        // SimRobot robot =
        //     new SimRobot(5, 3, "EAST", 75);


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

        title(5, "BREAK 'this' ON PURPOSE");


        SimRobot robot = new SimRobot(
                10,
                4,
                "WEST",
                90
        );


        System.out.println(
                "We asked for a robot at (10, 4),"
        );

        System.out.println(
                "facing WEST, with 90% battery."
        );

        System.out.println();

        robot.reportStatus();


        /*
         * ACTIVITY - now go and break it.
         *
         * Find the constructor in SimRobot that takes four
         * parameters. It says:
         *
         *     this.x = x;
         *     this.y = y;
         *
         * Delete the word "this." from the first two lines,
         * so they read:
         *
         *     x = x;
         *     y = y;
         *
         * Run this challenge again.
         *
         * WHAT HAPPENS:
         *
         *     Position goes back to (0, 0) - the robot forgot
         *     where we told it to start.
         *
         * WHY:
         *
         *     Inside the constructor there are TWO things
         *     called x:
         *
         *         x        the PARAMETER (the 10 we passed in)
         *         this.x   the ROBOT'S OWN x (the field)
         *
         *     Writing "x = x" copies the parameter into
         *     itself. It does nothing at all. The robot's own
         *     x is never touched, so it stays 0.
         *
         *     "this." is how we say "the robot's own one".
         *
         * Notice Java did NOT complain. It compiled fine and
         * silently did nothing useful. Those are the bugs that
         * cost you matches.
         *
         * Now put "this." back.
         */


        System.out.println();
        System.out.println(
                "Now go break the constructor - see the"
        );

        System.out.println(
                "instructions in the comments above."
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


        SimRobot robot = new SimRobot();


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


        SimRobot robot = new SimRobot();


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


        SimRobot robot = new SimRobot();


        /*
         * ACTIVITY - try to cheat, and watch Java stop you.
         *
         * STEP 1. Add this line right here and try to run it:
         *
         *     robot.battery = 200;
         *
         *     -> It will NOT compile.
         *     -> Read the error. Write down what it says.
         *
         *     Java is refusing because battery is PRIVATE -
         *     it belongs to the robot, and only the robot's
         *     own methods are allowed to change it.
         *
         * STEP 2. Delete that line. Now read the battery the
         *     allowed way, using the getter:
         *
         *     System.out.println(robot.getBattery());
         *
         *     -> This works. Reading is fine. Changing is not.
         *
         * STEP 3. Think about WHY we would want this.
         *
         *     A battery of 200% is not a real thing. If any
         *     part of our code could set it to 200, then a
         *     bug anywhere in the program could put the robot
         *     into an impossible state - and we would have to
         *     search the WHOLE program to find out who did it.
         *
         *     With private, there are only a handful of places
         *     that can change battery, and they are all in the
         *     SimRobot class. That is a much smaller haystack.
         *
         * THE REAL LESSON:
         *
         *     private is not about secrecy.
         *     It is about shrinking the number of places a bug
         *     can come from.
         */

        System.out.println("Battery is private.");

        System.out.println(
                "Try adding:  robot.battery = 200;"
        );

        System.out.println(
                "It will not compile. That is the point."
        );

        System.out.println();

        // The allowed way to look at it:
        System.out.println(
                "Battery (via getter): " +
                robot.getBattery() +
                "%"
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


        SimRobot robot = new SimRobot();


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


        SimRobot robot = new SimRobot();


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


        SimRobot robot = new SimRobot();


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
 
