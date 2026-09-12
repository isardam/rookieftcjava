 
/*
 * ============================================================
 * SESSION 5 — BUILD YOUR OWN ROBOT FUNCTIONS
 * ============================================================
 *
 * Project:
 *     Robot Command Library
 *
 * Mission:
 *     Teach the robot reusable skills.
 *
 * Java Concepts:
 *     - Methods
 *     - Parameters
 *     - Return values
 *     - Class members
 *     - Reusable code
 *
 * IMPORTANT:
 *
 * Complete this file from TOP to BOTTOM.
 *
 * Each STEP introduces a new concept.
 *
 * ============================================================
 */


public class RobotCommandLibrary {


    // ========================================================
    // STEP 1 — CLASS MEMBERS
    // ========================================================
    //
    // These variables belong to our RobotCommandLibrary.
    //
    // They allow different methods to share information.
    //
    // ========================================================

    static int currentRow = 0;
    static int currentColumn = 0;

    static String direction = "NORTH";

    static int distanceTraveled = 0;

    static boolean targetFound = false;

    static boolean carryingPackage = false;


    // ========================================================
    // STEP 2 — YOUR FIRST ROBOT METHOD
    // ========================================================
    //
    // A method is a named block of code that performs a task.
    //
    // Instead of writing:
    //
    //     System.out.println("Moving forward");
    //
    // every time, we can write:
    //
    //     moveForward();
    //
    // ========================================================

    static void moveForward() {

        System.out.println("ROBOT: Moving forward");

        distanceTraveled++;
    }


    // ========================================================
    // STEP 3 — MORE ROBOT METHODS
    // ========================================================

    static void moveBackward() {

        System.out.println("ROBOT: Moving backward");

        distanceTraveled++;
    }


    static void turnLeft() {

        System.out.println("ROBOT: Turning LEFT");

        if (direction.equals("NORTH")) {

            direction = "WEST";

        } else if (direction.equals("WEST")) {

            direction = "SOUTH";

        } else if (direction.equals("SOUTH")) {

            direction = "EAST";

        } else {

            direction = "NORTH";
        }
    }


    static void turnRight() {

        System.out.println("ROBOT: Turning RIGHT");

        if (direction.equals("NORTH")) {

            direction = "EAST";

        } else if (direction.equals("EAST")) {

            direction = "SOUTH";

        } else if (direction.equals("SOUTH")) {

            direction = "WEST";

        } else {

            direction = "NORTH";
        }
    }


    // ========================================================
    // STEP 4 — METHOD WITH A PARAMETER
    // ========================================================
    //
    // Instead of:
    //
    //     moveForward();
    //     moveForward();
    //     moveForward();
    //
    // We can write:
    //
    //     moveForward(3);
    //
    // The number 3 is a PARAMETER.
    //
    // ========================================================

    static void moveForward(int squares) {

        System.out.println(
                "ROBOT: Moving forward " +
                squares +
                " squares"
        );


        for (int i = 0; i < squares; i++) {

            distanceTraveled++;

            System.out.println(
                    "  Step " + (i + 1)
            );
        }
    }


    // ========================================================
    // STEP 5 — METHOD USING OTHER METHODS
    // ========================================================
    //
    // Methods can call other methods.
    //
    // This allows us to build complex robot behaviors
    // from small reusable commands.
    //
    // ========================================================

    static void turnAround() {

        System.out.println(
                "ROBOT: Turning around"
        );

        turnRight();
        turnRight();
    }


    // ========================================================
    // STEP 6 — MOVEMENT METHODS WITH PARAMETERS
    // ========================================================
    //
    // These methods demonstrate how parameters can control
    // robot behavior.
    //
    // ========================================================

    static void moveNorth(int squares) {

        for (int i = 0; i < squares; i++) {

            currentRow--;

            distanceTraveled++;

            System.out.println(
                    "Moving NORTH -> [" +
                    currentRow +
                    "][" +
                    currentColumn +
                    "]"
            );
        }
    }


    static void moveSouth(int squares) {

        for (int i = 0; i < squares; i++) {

            currentRow++;

            distanceTraveled++;

            System.out.println(
                    "Moving SOUTH -> [" +
                    currentRow +
                    "][" +
                    currentColumn +
                    "]"
            );
        }
    }


    static void moveEast(int squares) {

        for (int i = 0; i < squares; i++) {

            currentColumn++;

            distanceTraveled++;

            System.out.println(
                    "Moving EAST -> [" +
                    currentRow +
                    "][" +
                    currentColumn +
                    "]"
            );
        }
    }


    static void moveWest(int squares) {

        for (int i = 0; i < squares; i++) {

            currentColumn--;

            distanceTraveled++;

            System.out.println(
                    "Moving WEST -> [" +
                    currentRow +
                    "][" +
                    currentColumn +
                    "]"
            );
        }
    }


    // ========================================================
    // STEP 7 — SEARCH METHOD
    // ========================================================
    //
    // We are turning the SearchBot from Session 4 into
    // a reusable command.
    //
    // Instead of rewriting the search algorithm every time:
    //
    //     search();
    //
    // ========================================================

    static void search() {

        System.out.println();
        System.out.println(
                "ROBOT: Starting search"
        );


        targetFound = false;


        for (int row = 0; row < 4 && !targetFound; row++) {

            for (int column = 0; column < 4; column++) {

                System.out.println(
                        "Checking [" +
                        row +
                        "][" +
                        column +
                        "]"
                );


                // Demo target
                if (row == 2 && column == 3) {

                    targetFound = true;

                    System.out.println(
                            "TARGET FOUND!"
                    );

                    return;
                }
            }
        }


        System.out.println(
                "Target was not found."
        );
    }


    // ========================================================
    // STEP 8 — PARAMETERS + RETURN VALUE
    // ========================================================
    //
    // Now we make search() reusable.
    //
    // The method accepts:
    //
    //     targetRow
    //     targetColumn
    //
    // And returns:
    //
    //     true
    //     false
    //
    // ========================================================

    static boolean searchForTarget(
            int targetRow,
            int targetColumn) {


        System.out.println();
        System.out.println(
                "Searching for target at [" +
                targetRow +
                "][" +
                targetColumn +
                "]"
        );


        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 4; column++) {

                System.out.println(
                        "Checking [" +
                        row +
                        "][" +
                        column +
                        "]"
                );


                if (row == targetRow &&
                        column == targetColumn) {

                    System.out.println(
                            "TARGET FOUND!"
                    );

                    return true;
                }
            }
        }


        return false;
    }


    // ========================================================
    // STEP 9 — PICK UP A PACKAGE
    // ========================================================

    static void pickUpPackage() {

        System.out.println(
                "ROBOT: Picking up package"
        );

        carryingPackage = true;
    }


    // ========================================================
    // STEP 10 — DELIVER
    // ========================================================
    //
    // Notice that deliver() uses an IF statement.
    //
    // The robot checks whether it actually has a package.
    //
    // ========================================================

    static void deliver() {

        System.out.println();
        System.out.println(
                "ROBOT: Starting delivery"
        );


        if (!carryingPackage) {

            System.out.println(
                    "ERROR: Robot has no package!"
            );

            return;
        }


        System.out.println(
                "ROBOT: Delivering package"
        );


        carryingPackage = false;


        System.out.println(
                "ROBOT: Package delivered!"
        );
    }


    // ========================================================
    // STEP 11 — RETURN HOME
    // ========================================================
    //
    // Home = [0][0]
    //
    // This method demonstrates reusable behavior.
    //
    // ========================================================

    static void returnHome() {

        System.out.println();
        System.out.println(
                "ROBOT: Returning home"
        );


        while (currentRow > 0) {

            moveNorth(1);
        }


        while (currentRow < 0) {

            moveSouth(1);
        }


        while (currentColumn > 0) {

            moveWest(1);
        }


        while (currentColumn < 0) {

            moveEast(1);
        }


        System.out.println(
                "ROBOT: HOME [" +
                currentRow +
                "][" +
                currentColumn +
                "]"
        );
    }


    // ========================================================
    // STEP 12 — RETURN VALUES
    // ========================================================
    //
    // A method can DO something.
    //
    // A method can also GIVE us information.
    //
    // ========================================================

    static int getDistanceTraveled() {

        return distanceTraveled;
    }


    static boolean isHome() {

        return currentRow == 0 &&
                currentColumn == 0;
    }


    static String getPosition() {

        return "[" +
                currentRow +
                "][" +
                currentColumn +
                "]";
    }


    // ========================================================
    // STEP 13 — ROBOT STATUS
    // ========================================================

    static void printStatus() {

        System.out.println();
        System.out.println(
                "========== ROBOT STATUS =========="
        );


        System.out.println(
                "Position: " +
                getPosition()
        );


        System.out.println(
                "Direction: " +
                direction
        );


        System.out.println(
                "Distance traveled: " +
                getDistanceTraveled()
        );


        System.out.println(
                "Target found: " +
                targetFound
        );


        System.out.println(
                "Carrying package: " +
                carryingPackage
        );


        System.out.println(
                "At home: " +
                isHome()
        );


        System.out.println(
                "=================================="
        );
    }


    // ========================================================
    // STEP 14 — MISSION 1
    // ========================================================
    //
    // Use existing commands.
    //
    // DO NOT create new methods.
    //
    // Mission:
    //
    //     Move forward twice.
    //     Turn right.
    //     Move forward.
    //     Turn left.
    //     Move backward.
    //
    // ========================================================

    static void mission1() {

        System.out.println();
        System.out.println(
                "===== MISSION 1 ====="
        );


        moveForward();
        moveForward();

        turnRight();

        moveForward();

        turnLeft();

        moveBackward();
    }


    // ========================================================
    // STEP 15 — MISSION 2
    // ========================================================
    //
    // Use a PARAMETER.
    //
    // Mission:
    //
    //     Move forward 5 squares.
    //     Turn around.
    //     Move forward 3 squares.
    //
    // ========================================================

    static void mission2() {

        System.out.println();
        System.out.println(
                "===== MISSION 2 ====="
        );


        moveForward(5);

        turnAround();

        moveForward(3);
    }


    // ========================================================
    // STEP 16 — MISSION 3
    // ========================================================
    //
    // Use the reusable search() method.
    //
    // ========================================================

    static void mission3() {

        System.out.println();
        System.out.println(
                "===== MISSION 3 ====="
        );


        search();
    }


    // ========================================================
    // STEP 17 — MISSION 4
    // ========================================================
    //
    // Use parameters AND a return value.
    //
    // ========================================================

    static void mission4() {

        System.out.println();
        System.out.println(
                "===== MISSION 4 ====="
        );


        boolean found;


        found = searchForTarget(0, 0);

        System.out.println(
                "Target found? " +
                found
        );


        found = searchForTarget(3, 3);

        System.out.println(
                "Target found? " +
                found
        );
    }


    // ========================================================
    // STEP 18 — MISSION 5
    // ========================================================
    //
    // DELIVERY MISSION
    //
    // The robot must:
    //
    //     1. Pick up package
    //     2. Travel
    //     3. Deliver
    //     4. Return home
    //
    // ========================================================

    static void mission5() {

        System.out.println();
        System.out.println(
                "===== MISSION 5 ====="
        );


        pickUpPackage();


        moveForward(3);


        turnRight();


        moveForward(2);


        deliver();


        returnHome();
    }


    // ========================================================
    // STEP 19 — FINAL TEAM CHALLENGE
    // ========================================================
    //
    // CREATE YOUR OWN ROBOT COMMAND.
    //
    // Your method must:
    //
    //     - Have a meaningful name.
    //     - Perform a useful robot task.
    //     - Use at least THREE existing methods.
    //
    // Examples:
    //
    //     rescueMission()
    //     collectSample()
    //     scoreGamePiece()
    //     inspectArea()
    //
    // ========================================================

    static void teamChallenge() {

        System.out.println();
        System.out.println(
                "===== TEAM CHALLENGE ====="
        );


        /*
         * TODO:
         *
         * Create your own method.
         *
         * Example:
         *
         * static void rescueMission() {
         *
         *     moveForward(3);
         *     turnRight();
         *     moveForward(2);
         *     turnAround();
         *
         * }
         */


        // TODO:
        // Call your new method here.
    }


    // ========================================================
    // STEP 20 — FINAL ROBOT MISSION
    // ========================================================
    //
    // Your team is now going to write a complete mission
    // using the Robot Command Library.
    //
    // MISSION:
    //
    //     Start at home.
    //
    //     1. Pick up a package.
    //     2. Move to the delivery location.
    //     3. Deliver the package.
    //     4. Return home.
    //     5. Print the robot status.
    //
    // CHALLENGE:
    //
    // Use ONLY reusable methods.
    //
    // Do NOT put raw System.out.println() statements
    // into your mission to simulate robot actions.
    //
    // ========================================================

    static void finalMission() {

        System.out.println();
        System.out.println(
                "======================================"
        );
        System.out.println(
                "       FINAL ROBOT MISSION"
        );
        System.out.println(
                "======================================"
        );


        // TODO:
        // Pick up the package.


        // TODO:
        // Move to the delivery location.


        // TODO:
        // Deliver the package.


        // TODO:
        // Return home.


        // TODO:
        // Print robot status.


        System.out.println();
        System.out.println(
                "======================================"
        );
        System.out.println(
                "          MISSION COMPLETE"
        );
        System.out.println(
                "======================================"
        );
    }


    // ========================================================
    // MAIN
    // ========================================================
    //
    // Run ONE step/mission at a time.
    //
    // ========================================================

    public static void main(String[] args) {

        System.out.println();
        System.out.println(
                "=========================================="
        );
        System.out.println(
                "       ROBOT COMMAND LIBRARY"
        );
        System.out.println(
                "              SESSION 5"
        );
        System.out.println(
                "=========================================="
        );


        // STEP 14
        // mission1();


        // STEP 15
        // mission2();


        // STEP 16
        // mission3();


        // STEP 17
        // mission4();


        // STEP 18
        // mission5();


        // STEP 19
        // teamChallenge();


        // STEP 20
        // finalMission();


        System.out.println();
        System.out.println(
                "Run the mission you are working on!"
        );
    }
}
