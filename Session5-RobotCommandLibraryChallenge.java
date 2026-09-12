 
/*
 * ============================================================
 * SESSION 5 — BUILD YOUR OWN ROBOT FUNCTIONS
 * ============================================================
 *
 * FTC JAVA — ROOKIE TEAM
 *
 * PROJECT:
 *     Robot Command Library
 *
 * BIG IDEA:
 *
 *     Don't write the same robot instructions over and over.
 *     Teach the robot a skill once.
 *     Give that skill a name.
 *     Reuse it!
 *
 *
 * CHALLENGE SEQUENCE
 * ============================================================
 *
 * Challenge 1  - Call a Robot Function
 * Challenge 2  - Create a Robot Function
 * Challenge 3  - Build a Turn Function
 * Challenge 4  - Use Parameters
 * Challenge 5  - Move Different Distances
 * Challenge 6  - Return a Value
 * Challenge 7  - Ask the Robot a Question
 * Challenge 8  - Build a Search Function
 * Challenge 9  - Build a Delivery Function
 * Challenge 10 - Combine Robot Skills
 * Challenge 11 - Build Your Own Command
 * Challenge 12 - Final Autonomous Mission
 *
 * ============================================================
 *
 * COACH RULE:
 *
 * Students should complete the challenges IN ORDER.
 *
 * Don't show them the final solution.
 *
 * ============================================================
 */


public class RobotCommandLibrary {


    // ========================================================
    // ROBOT STATE
    // ========================================================
    //
    // These are CLASS MEMBERS.
    //
    // They allow our methods to share information.
    //
    // ========================================================

    static int row = 0;

    static int column = 0;

    static String direction = "NORTH";

    static int distanceTraveled = 0;

    static boolean carryingObject = false;

    static boolean targetFound = false;


    // ========================================================
    // CHALLENGE 1
    // CALL A ROBOT FUNCTION
    // ========================================================
    //
    // A method is a named action.
    //
    // We already created moveForward().
    //
    // Your job:
    //
    //     Call moveForward() three times.
    //
    // DO NOT create a loop.
    //
    // Question:
    //
    //     Why might this become annoying?
    //
    // ========================================================

    static void challenge1() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 1");
        System.out.println("CALL A ROBOT FUNCTION");
        System.out.println("================================");

        // TODO:
        // Call moveForward() three times.


    }


    // ========================================================
    // CHALLENGE 2
    // CREATE A ROBOT FUNCTION
    // ========================================================
    //
    // Create a new method called:
    //
    //     scanArea()
    //
    // It should print:
    //
    //     ROBOT: Scanning area
    //
    // Then call scanArea() from this challenge.
    //
    // ========================================================

    static void challenge2() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 2");
        System.out.println("CREATE A ROBOT FUNCTION");
        System.out.println("================================");

        // TODO:
        // Create scanArea() below.
        //
        // Then call scanArea() here.


    }


    // ========================================================
    // CHALLENGE 3
    // BUILD A TURN FUNCTION
    // ========================================================
    //
    // Create:
    //
    //     turnLeft()
    //     turnRight()
    //
    // Each method should:
    //
    //     1. Print what the robot is doing.
    //     2. Update the direction.
    //
    // Example:
    //
    //     NORTH -> EAST
    //     EAST  -> SOUTH
    //     SOUTH -> WEST
    //     WEST  -> NORTH
    //
    // ========================================================

    static void challenge3() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 3");
        System.out.println("BUILD TURN FUNCTIONS");
        System.out.println("================================");

        // TODO:
        // Call turnRight().


        // TODO:
        // Call turnRight() again.


        // TODO:
        // Call turnLeft().


        System.out.println(
                "Robot direction: " + direction
        );
    }


    // ========================================================
    // CHALLENGE 4
    // METHODS WITH PARAMETERS
    // ========================================================
    //
    // Create:
    //
    //     moveForward(int spaces)
    //
    // Example:
    //
    //     moveForward(3);
    //
    // should move the robot 3 spaces.
    //
    // The parameter tells the method WHAT to do.
    //
    // ========================================================

    static void challenge4() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 4");
        System.out.println("METHOD PARAMETERS");
        System.out.println("================================");

        // TODO:
        // Create moveForward(int spaces).


        // TODO:
        // Test it with:
        //
        // moveForward(3);


    }


    // ========================================================
    // CHALLENGE 5
    // DIFFERENT DISTANCES
    // ========================================================
    //
    // A good method should be reusable.
    //
    // Test:
    //
    //     moveForward(2)
    //     moveForward(5)
    //     moveForward(1)
    //
    // WITHOUT creating:
    //
    //     moveForwardTwo()
    //     moveForwardFive()
    //     moveForwardOne()
    //
    // ========================================================

    static void challenge5() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 5");
        System.out.println("REUSABLE PARAMETERS");
        System.out.println("================================");

        // TODO:
        // Move 2 spaces.


        // TODO:
        // Move 5 spaces.


        // TODO:
        // Move 1 space.


        System.out.println(
                "Distance traveled: " +
                distanceTraveled
        );
    }


    // ========================================================
    // CHALLENGE 6
    // RETURN A VALUE
    // ========================================================
    //
    // Methods can DO something.
    //
    // But methods can also GIVE information back.
    //
    // Create:
    //
    //     getDistance()
    //
    // It should return distanceTraveled.
    //
    // Example:
    //
    //     int distance = getDistance();
    //
    // ========================================================

    static void challenge6() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 6");
        System.out.println("RETURN A VALUE");
        System.out.println("================================");

        // TODO:
        // Create getDistance() below.


        // TODO:
        // Store the returned value in a variable.


        // TODO:
        // Print the distance.


    }


    // ========================================================
    // CHALLENGE 7
    // ASK THE ROBOT A QUESTION
    // ========================================================
    //
    // Create:
    //
    //     isAtHome()
    //
    // It should return:
    //
    //     true
    //
    // if the robot is at [0][0].
    //
    // Otherwise return:
    //
    //     false
    //
    // Example:
    //
    //     boolean home = isAtHome();
    //
    // ========================================================

    static void challenge7() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 7");
        System.out.println("ASK THE ROBOT A QUESTION");
        System.out.println("================================");

        // TODO:
        // Create isAtHome() below.


        // TODO:
        // Call isAtHome().


        // TODO:
        // Print the result.


    }


    // ========================================================
    // CHALLENGE 8
    // BUILD A SEARCH FUNCTION
    // ========================================================
    //
    // We are going to turn our Session 4 search algorithm
    // into a reusable method.
    //
    // Create:
    //
    //     searchForTarget(int targetRow, int targetColumn)
    //
    // The method should:
    //
    //     1. Search a 4 x 4 grid.
    //     2. Check each location.
    //     3. Compare row and column.
    //     4. Return true when the target is found.
    //     5. Return false if it isn't found.
    //
    // Example:
    //
    //     boolean found = searchForTarget(2, 3);
    //
    // ========================================================

    static void challenge8() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 8");
        System.out.println("BUILD A SEARCH FUNCTION");
        System.out.println("================================");

        // TODO:
        // Create searchForTarget() below.


        // TODO:
        // Search for:
        //
        // row = 2
        // column = 3


        // TODO:
        // Print whether the target was found.


    }


    // ========================================================
    // CHALLENGE 9
    // BUILD A DELIVERY FUNCTION
    // ========================================================
    //
    // Create:
    //
    //     pickUp()
    //
    //     deliver()
    //
    // pickUp() should make:
    //
    //     carryingObject = true
    //
    // deliver() should:
    //
    //     1. Check whether the robot has an object.
    //     2. Deliver it if it does.
    //     3. Print an error if it doesn't.
    //     4. Set carryingObject to false.
    //
    // ========================================================

    static void challenge9() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 9");
        System.out.println("DELIVERY FUNCTIONS");
        System.out.println("================================");

        // TODO:
        // Create pickUp() below.


        // TODO:
        // Create deliver() below.


        // TODO:
        // Pick up an object.


        // TODO:
        // Deliver the object.


    }


    // ========================================================
    // CHALLENGE 10
    // COMBINE ROBOT SKILLS
    // ========================================================
    //
    // Now we create a larger behavior from smaller methods.
    //
    // Create:
    //
    //     deliveryMission()
    //
    // It should:
    //
    //     1. Pick up an object.
    //     2. Move forward 3 spaces.
    //     3. Turn right.
    //     4. Move forward 2 spaces.
    //     5. Deliver.
    //
    // IMPORTANT:
    //
    // Do NOT duplicate the code from those methods.
    //
    // Call the methods instead.
    //
    // ========================================================

    static void challenge10() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 10");
        System.out.println("COMBINE ROBOT SKILLS");
        System.out.println("================================");

        // TODO:
        // Create deliveryMission() below.


        // TODO:
        // Call deliveryMission() here.


    }


    // ========================================================
    // CHALLENGE 11
    // BUILD YOUR OWN COMMAND
    // ========================================================
    //
    // Your team now gets to create a robot skill.
    //
    // Create ONE new method.
    //
    // Examples:
    //
    //     collectSample()
    //     inspectArea()
    //     rescueMission()
    //     scoreGamePiece()
    //     parkRobot()
    //
    // REQUIREMENTS:
    //
    //     - Meaningful method name
    //     - Uses at least 3 existing methods
    //     - Performs a useful robot task
    //
    // ========================================================

    static void challenge11() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 11");
        System.out.println("BUILD YOUR OWN COMMAND");
        System.out.println("================================");

        // TODO:
        // Create your own robot command method.


        // TODO:
        // Call your new command here.


    }


    // ========================================================
    // CHALLENGE 12
    // FINAL AUTONOMOUS MISSION
    // ========================================================
    //
    // 🚀 FINAL MISSION 🚀
    //
    // Your robot starts at [0][0].
    //
    // A package must be delivered.
    //
    // MISSION:
    //
    //     1. Pick up package.
    //
    //     2. Travel to delivery location.
    //
    //     3. Deliver package.
    //
    //     4. Return home.
    //
    //     5. Print robot status.
    //
    //
    // RULE:
    //
    // Use your COMMAND LIBRARY.
    //
    // Do not write a giant sequence of low-level commands.
    //
    // Your mission should be readable like this:
    //
    //     pickUp();
    //     driveToDelivery();
    //     deliver();
    //     returnHome();
    //
    // ========================================================

    static void challenge12() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       FINAL AUTONOMOUS MISSION");
        System.out.println("==========================================");

        // TODO:
        // Create your mission using reusable methods.


        // TODO:
        // Pick up package.


        // TODO:
        // Travel to delivery location.


        // TODO:
        // Deliver package.


        // TODO:
        // Return home.


        // TODO:
        // Print robot status.


        System.out.println();
        System.out.println("MISSION COMPLETE!");
    }


    // ========================================================
    // ROBOT SIMULATION METHODS
    // ========================================================
    //
    // These methods simulate robot hardware.
    //
    // Students will eventually replace these with FTC
    // hardware commands.
    //
    // ========================================================


    static void moveForward() {

        System.out.println(
                "ROBOT: Moving forward"
        );

        distanceTraveled++;
    }


    static void moveBackward() {

        System.out.println(
                "ROBOT: Moving backward"
        );

        distanceTraveled++;
    }


    static void turnLeft() {

        System.out.println(
                "ROBOT: Turning LEFT"
        );


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

        System.out.println(
                "ROBOT: Turning RIGHT"
        );


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


    static void moveForward(int spaces) {

        System.out.println(
                "ROBOT: Moving forward " +
                spaces +
                " spaces"
        );


        for (int i = 0; i < spaces; i++) {

            distanceTraveled++;

            System.out.println(
                    "  Step " + (i + 1)
            );
        }
    }


    static void printStatus() {

        System.out.println();
        System.out.println(
                "========== ROBOT STATUS =========="
        );

        System.out.println(
                "Position: [" +
                row +
                "][" +
                column +
                "]"
        );

        System.out.println(
                "Direction: " +
                direction
        );

        System.out.println(
                "Distance traveled: " +
                distanceTraveled
        );

        System.out.println(
                "Carrying object: " +
                carryingObject
        );

        System.out.println(
                "Target found: " +
                targetFound
        );

        System.out.println(
                "=================================="
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
                "       ROBOT COMMAND LIBRARY"
        );
        System.out.println(
                "              SESSION 5"
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


        // FINAL MISSION
        // challenge12();


        System.out.println();
        System.out.println(
                "Uncomment the challenge you are working on."
        );
    }
}
