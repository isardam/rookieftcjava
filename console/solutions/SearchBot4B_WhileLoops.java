/*
 * ============================================================
 * SESSION 4B - WHILE LOOPS (and the FTC loop)
 * ============================================================
 *
 * ONE new idea: a while loop repeats as long as something
 * stays true.
 *
 *
 * THE ROBOT STORY
 * ============================================================
 *
 *     "We don't know how many times to search. We want the
 *      robot to keep going UNTIL it finds the target."
 *
 * A for loop is for "do this 4 times".
 * A while loop is for "keep doing this until something changes".
 *
 *
 * WHY THIS SESSION MATTERS MORE THAN IT LOOKS
 * ============================================================
 *
 * The most important loop in all of FTC is a while loop:
 *
 *     while (opModeIsActive()) {
 *         ...
 *     }
 *
 * Every TeleOp you will ever write has it. After today you
 * will be able to READ it.
 *
 * ============================================================
 */


public class SearchBot4B_WhileLoops {


    /*
     * --------------------------------------------------------
     * STEP 1 - FOR vs WHILE
     * --------------------------------------------------------
     *
     * These two do EXACTLY the same thing.
     *
     * The for loop keeps its three parts on one line.
     * The while loop spreads them out.
     */
    static void step1_forVsWhile() {

        System.out.println();
        System.out.println("===== STEP 1: FOR vs WHILE =====");

        System.out.println("-- with a for loop --");

        for (int i = 0; i < 3; i++) {

            checkSquare();
        }


        System.out.println("-- with a while loop --");

        int squaresChecked = 0;          // 1. start the counter

        while (squaresChecked < 3) {     // 2. keep going while...

            checkSquare();

            squaresChecked++;            // 3. change the counter
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 2 - THE THREE JOBS
     * --------------------------------------------------------
     *
     * A while loop only works if all THREE jobs are done:
     *
     *     1. Something is set up BEFORE the loop.
     *     2. The condition can eventually become false.
     *     3. Something INSIDE the loop changes what the
     *        condition looks at.
     *
     * Miss job 3 and the loop runs forever.
     */
    static void step2_countingSquares() {

        System.out.println();
        System.out.println("===== STEP 2: COUNTING =====");

        int squaresChecked = 0;

        while (squaresChecked < 4) {

            System.out.println(
                    "Checking square " + squaresChecked
            );

            checkSquare();

            // THIS LINE is job 3. Without it, squaresChecked
            // stays 0 forever, 0 < 4 is always true, and the
            // robot searches the same square for eternity.
            squaresChecked++;
        }

        System.out.println("Search finished.");
    }


    /*
     * --------------------------------------------------------
     * STEP 3 - SEARCH UNTIL FOUND
     * --------------------------------------------------------
     *
     * Now the loop stops for a REASON, not after a count.
     *
     * This is what while loops are really for.
     *
     * Notice the condition has TWO parts joined by &&:
     *
     *     !targetFound && searchAttempts < 5
     *
     * "keep going while we have NOT found it AND we have not
     *  run out of attempts"
     *
     * That second part is a SAFETY NET. Without it, if the
     * target were never findable, the robot would search
     * forever and we would score nothing.
     *
     * Real autonomous code is full of safety nets like this.
     */
    static void step3_searchUntilFound() {

        System.out.println();
        System.out.println("===== STEP 3: SEARCH UNTIL FOUND =====");

        boolean targetFound = false;
        int searchAttempts = 0;

        while (!targetFound && searchAttempts < 5) {

            searchAttempts++;

            System.out.println("Attempt " + searchAttempts);

            moveForward();
            checkSquare();

            // Pretend the sensor finds it on attempt 3.
            if (searchAttempts == 3) {
                targetFound = true;
            }
        }

        System.out.println();

        if (targetFound) {

            System.out.println(
                    "TARGET FOUND after " +
                    searchAttempts +
                    " attempts!"
            );

        } else {

            System.out.println(
                    "Gave up after " +
                    searchAttempts +
                    " attempts."
            );
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 4 - THE FTC LOOP
     * --------------------------------------------------------
     *
     * Here is the loop you will use all season.
     *
     * In a real OpMode it looks like this:
     *
     *     while (opModeIsActive()) {
     *
     *         // read the gamepad
     *         // drive the motors
     *         // update telemetry
     *     }
     *
     * opModeIsActive() is a method that answers true or false.
     * It becomes false the moment the driver presses STOP.
     *
     * SO: the robot keeps responding to the driver because we
     * keep asking "are we still running?" about 50 times a
     * second.
     *
     * IMPORTANT DIFFERENCE:
     *
     *     Our loops repeat because WE told Java to repeat.
     *     The FTC framework ALSO runs our program for us -
     *     it calls runOpMode() once, and we do the looping
     *     inside.
     */
    static void step4_theFtcLoop() {

        System.out.println();
        System.out.println("===== STEP 4: THE FTC LOOP =====");

        // We are pretending to be the FTC framework here.
        int fakeLoopLimit = 5;
        int loopCounter = 0;

        while (opModeIsActive(loopCounter, fakeLoopLimit)) {

            loopCounter++;

            System.out.println(
                    "Loop " + loopCounter +
                    " : read gamepad, drive motors, update telemetry"
            );
        }

        System.out.println();
        System.out.println("Driver pressed STOP. Loop exited.");
        System.out.println("Total loops: " + loopCounter);
    }


    /*
     * A stand-in for the real FTC method. In a real OpMode you
     * do NOT write this - the SDK gives it to you.
     */
    static boolean opModeIsActive(int loopCounter, int limit) {

        return loopCounter < limit;
    }


    static void moveForward() {

        System.out.println("ROBOT: Moving forward");
    }


    static void checkSquare() {

        System.out.println("ROBOT: Checking square");
    }


    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("   SEARCHBOT 4B - WHILE LOOPS");
        System.out.println("=================================");

        step1_forVsWhile();
        step2_countingSquares();
        step3_searchUntilFound();
        step4_theFtcLoop();

        System.out.println();
        System.out.println("=================================");
        System.out.println("            DONE");
        System.out.println("=================================");
    }
}
