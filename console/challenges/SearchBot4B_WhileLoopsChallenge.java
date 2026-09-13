/*
 * ============================================================
 * SESSION 4B - WHILE LOOPS - CHALLENGES
 * ============================================================
 *
 * WARNING
 * ============================================================
 *
 * Today you can write a loop that never stops.
 *
 * If your program will not finish, that is an INFINITE LOOP.
 * Press the red STOP button in your editor (or Ctrl+C in a
 * terminal) and go re-read your loop.
 *
 * This is not a disaster. It happens to everyone. It is much
 * better to meet it here than during a match.
 *
 * ============================================================
 */


public class SearchBot4B_WhileLoopsChallenge {


    /*
     * ========================================================
     * CHALLENGE 1 - YOUR FIRST WHILE LOOP
     * ========================================================
     *
     * Check 5 squares using a WHILE loop (not a for loop).
     *
     * You need all three jobs:
     *
     *     1. int squaresChecked = 0;     BEFORE the loop
     *     2. while (squaresChecked < 5)  the condition
     *     3. squaresChecked++;           INSIDE the loop
     *
     * Forget job 3 and you get an infinite loop. Try it on
     * purpose once, so you know what it feels like.
     */
    static void challenge1() {

        System.out.println();
        System.out.println("=== CHALLENGE 1: WHILE LOOP ===");

        // TODO: create the counter


        // TODO: while loop that checks 5 squares


    }


    /*
     * ========================================================
     * CHALLENGE 2 - COUNT DOWN
     * ========================================================
     *
     * Print a countdown before autonomous starts:
     *
     *     5
     *     4
     *     3
     *     2
     *     1
     *     GO!
     *
     * HINT: start high and go DOWN. What has to change about
     * the condition and about job 3?
     */
    static void challenge2() {

        System.out.println();
        System.out.println("=== CHALLENGE 2: COUNTDOWN ===");

        // TODO: count down from 5, then print GO!


    }


    /*
     * ========================================================
     * CHALLENGE 3 - SEARCH UNTIL FOUND
     * ========================================================
     *
     * The robot should keep searching until it finds the
     * target.
     *
     *   1. boolean targetFound = false;
     *   2. Loop while NOT found.
     *   3. Move and check each time round.
     *   4. Pretend the sensor finds it on the 4th attempt.
     *   5. Print how many attempts it took.
     *
     * HINT: "while not found" is written:
     *
     *     while (!targetFound) {
     */
    static void challenge3() {

        System.out.println();
        System.out.println("=== CHALLENGE 3: UNTIL FOUND ===");

        // TODO: search until targetFound becomes true.


    }


    /*
     * ========================================================
     * CHALLENGE 4 - ADD A SAFETY NET
     * ========================================================
     *
     * Take your Challenge 3 answer. Now imagine the target is
     * NOT on the field at all - the sensor will never find it.
     *
     * Your loop would run forever. In a match that means you
     * score ZERO.
     *
     * Fix it: stop after 6 attempts even if nothing is found.
     *
     * HINT:
     *
     *     while (!targetFound && attempts < 6) {
     *
     * Then AFTER the loop, use if/else to print either
     * "TARGET FOUND" or "GAVE UP".
     *
     * THIS IS THE MOST IMPORTANT HABIT IN AUTONOMOUS
     * PROGRAMMING. An autonomous that gives up and parks beats
     * an autonomous that hangs.
     */
    static void challenge4() {

        System.out.println();
        System.out.println("=== CHALLENGE 4: SAFETY NET ===");

        // TODO: search until found OR out of attempts.


        // TODO: report which one happened.


    }


    /*
     * ========================================================
     * CHALLENGE 5 - READ THE FTC LOOP
     * ========================================================
     *
     * No code to write. Just answer these out loud.
     *
     * Every TeleOp you write this season contains this:
     *
     *     while (opModeIsActive()) {
     *
     *         double power = -gamepad1.left_stick_y;
     *         leftMotor.setPower(power);
     *         telemetry.update();
     *     }
     *
     * QUESTIONS:
     *
     *   1. What are the three jobs of a while loop? Which ones
     *      can you see here, and which does FTC do for us?
     *
     *   2. What makes opModeIsActive() become false?
     *
     *   3. What would happen if we DELETED the while loop and
     *      left just the three lines inside it?
     *
     *   4. Why does reading the gamepad only once not work?
     */
    static void challenge5() {

        System.out.println();
        System.out.println("=== CHALLENGE 5: DISCUSSION ===");
        System.out.println("See the comments above. Talk it out.");
    }


    /*
     * ========================================================
     * DEBUGGING CHALLENGE - THE INFINITE LOOP
     * ========================================================
     *
     * This method is SUPPOSED to check 4 squares and stop.
     *
     * It never stops.
     *
     * BEFORE YOU RUN IT: read it and predict why.
     *
     * Then run it, watch it spin, and press STOP.
     *
     *     1. OBSERVE     What does it keep printing?
     *     2. REPRODUCE   It does it every time.
     *     3. DESCRIBE    "squaresChecked never changes, so the
     *                     condition is always true."
     *     4. LOCATE      Which of the three jobs is missing?
     *     5. CHANGE ONE  Add the missing line.
     *     6. TEST        Does it stop after 4 now?
     *     7. EXPLAIN     Why did it never stop before?
     *
     * NOTE: we print the counter so you can SEE that it is
     * stuck. Printing the thing your condition looks at is a
     * debugging trick you will use for the rest of your life.
     */
    static void debugChallenge_infiniteLoop() {

        System.out.println();
        System.out.println("=== DEBUG: WHY WON'T IT STOP? ===");
        System.out.println("(press STOP after a few seconds)");

        int squaresChecked = 0;

        while (squaresChecked < 4) {

            System.out.println(
                    "squaresChecked is " + squaresChecked
            );

            checkSquare();

            // Something is missing here.
        }

        System.out.println("You will never see this line.");
    }


    static void moveForward() {

        System.out.println("ROBOT: Moving forward");
    }


    static void checkSquare() {

        System.out.println("ROBOT: Checking square");
    }


    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("  SEARCHBOT 4B - CHALLENGES");
        System.out.println("=================================");

        // challenge1();
        // challenge2();
        // challenge3();
        // challenge4();
        // challenge5();

        // WARNING: this one never stops on purpose.
        // debugChallenge_infiniteLoop();

        System.out.println();
        System.out.println("Uncomment the challenge you are on.");
    }
}
