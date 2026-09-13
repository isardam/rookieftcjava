/*
 * ============================================================
 * SESSION 5 - DEBUGGING CHALLENGE
 * ============================================================
 *
 * THE BUG REPORT
 * ============================================================
 *
 * Our delivery robot came back from a test run and the log
 * said the mission was a SUCCESS.
 *
 * The drive team says the robot never left the starting square.
 *
 * One of them is wrong.
 *
 * Run this program and read the last few lines carefully:
 *
 *     Position: (0, 0)
 *     Distance traveled: 5
 *     At home: true
 *
 * The robot says it drove 5 squares.
 * The robot also says it is still at (0, 0).
 *
 * Both cannot be true.
 *
 *
 * YOUR JOB
 * ============================================================
 *
 * Use the debugging process. Do NOT just start changing code.
 *
 *     1. OBSERVE      What exactly does the program print?
 *     2. REPRODUCE    Can you make it happen again? (Yes - run it.)
 *     3. DESCRIBE     Say the problem out loud to your partner.
 *                     "The robot says it moved but its position
 *                      never changes."
 *     4. LOCATE       Which method is SUPPOSED to change the
 *                     position? Go read it. Does it?
 *     5. CHANGE ONE   Fix ONE thing. Not three things.
 *     6. TEST         Run it again. Did the position change?
 *     7. EXPLAIN      Why did your fix work?
 *
 *
 * HINTS (only if you are stuck for more than 10 minutes)
 * ============================================================
 *
 * Hint 1: Find every method that is supposed to move the robot.
 *         There are more than you think.
 *
 * Hint 2: moveForward() and moveNorth() both move the robot.
 *         Do they both change x and y? Compare them line by line.
 *
 * Hint 3: What does turnRight() actually change? Does
 *         moveForward() ever look at that?
 *
 *
 * QUESTIONS TO ANSWER WHEN YOU ARE DONE
 * ============================================================
 *
 * 1. Why is this bug DANGEROUS on a real FTC robot? Think about
 *    an autonomous routine that drives to a scoring position.
 *
 * 2. The program never crashed. It printed "MISSION COMPLETE"
 *    and "At home: true". Why is a bug that does not crash
 *    harder to find than one that does?
 *
 * 3. How could we change the code so these two ways of moving
 *    can NEVER disagree with each other again?
 *    (This is what the fixed version in solutions/ does.)
 *
 * ============================================================
 */


public class DebugDeliveryMission {


    // ========================================================
    // ROBOT STATE
    // ========================================================

    static int x = 0;
    static int y = 0;

    static String direction = "NORTH";

    static int distanceTraveled = 0;

    static boolean carryingPackage = false;


    // ========================================================
    // ROBOT COMMANDS
    // ========================================================
    //
    // One of the methods below has a bug.
    //
    // (Or maybe more than one...)
    //
    // ========================================================

    static void moveForward() {

        System.out.println("ROBOT: Moving forward");

        distanceTraveled++;
    }


    static void moveForward(int squares) {

        System.out.println(
                "ROBOT: Moving forward " +
                squares +
                " squares"
        );

        for (int i = 0; i < squares; i++) {

            distanceTraveled++;

            System.out.println("  Step " + (i + 1));
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


    static void moveNorth(int squares) {

        for (int i = 0; i < squares; i++) {

            y++;

            distanceTraveled++;

            System.out.println(
                    "Moving NORTH -> (" + x + ", " + y + ")"
            );
        }
    }


    static void moveSouth(int squares) {

        for (int i = 0; i < squares; i++) {

            y--;

            distanceTraveled++;

            System.out.println(
                    "Moving SOUTH -> (" + x + ", " + y + ")"
            );
        }
    }


    static void moveEast(int squares) {

        for (int i = 0; i < squares; i++) {

            x++;

            distanceTraveled++;

            System.out.println(
                    "Moving EAST -> (" + x + ", " + y + ")"
            );
        }
    }


    static void moveWest(int squares) {

        for (int i = 0; i < squares; i++) {

            x--;

            distanceTraveled++;

            System.out.println(
                    "Moving WEST -> (" + x + ", " + y + ")"
            );
        }
    }


    static void pickUpPackage() {

        System.out.println("ROBOT: Picking up package");

        carryingPackage = true;
    }


    static void deliver() {

        System.out.println();
        System.out.println("ROBOT: Starting delivery");

        if (!carryingPackage) {

            System.out.println("ERROR: Robot has no package!");

            return;
        }

        System.out.println("ROBOT: Delivering package");

        carryingPackage = false;

        System.out.println("ROBOT: Package delivered!");
    }


    static void returnHome() {

        System.out.println();
        System.out.println("ROBOT: Returning home");

        while (y > 0) {
            moveSouth(1);
        }

        while (y < 0) {
            moveNorth(1);
        }

        while (x > 0) {
            moveWest(1);
        }

        while (x < 0) {
            moveEast(1);
        }

        System.out.println(
                "ROBOT: HOME (" + x + ", " + y + ")"
        );
    }


    static boolean isHome() {

        return x == 0 && y == 0;
    }


    static void printStatus() {

        System.out.println();
        System.out.println("========== ROBOT STATUS ==========");

        System.out.println("Position: (" + x + ", " + y + ")");
        System.out.println("Direction: " + direction);
        System.out.println("Distance traveled: " + distanceTraveled);
        System.out.println("Carrying package: " + carryingPackage);
        System.out.println("At home: " + isHome());

        System.out.println("==================================");
    }


    // ========================================================
    // THE MISSION THAT "SUCCEEDS"
    // ========================================================
    //
    // Read this mission. It looks completely reasonable.
    //
    // The bug is NOT in here.
    //
    // ========================================================

    static void deliveryMission() {

        System.out.println();
        System.out.println("===== DELIVERY MISSION =====");

        pickUpPackage();

        moveForward(3);

        turnRight();

        moveForward(2);

        deliver();

        returnHome();
    }


    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("   DELIVERY ROBOT - BUG REPORT");
        System.out.println("==================================");

        deliveryMission();

        printStatus();

        System.out.println();
        System.out.println("MISSION COMPLETE");
        System.out.println();
        System.out.println("...but is it? Read the status above.");
    }
}
