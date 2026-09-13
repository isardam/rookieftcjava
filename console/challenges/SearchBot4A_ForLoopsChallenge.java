/*
 * ============================================================
 * SESSION 4A - FOR LOOPS - CHALLENGES
 * ============================================================
 *
 * RULE: do these IN ORDER.
 *
 * Run ONE challenge at a time by uncommenting it in main().
 *
 * ============================================================
 */


public class SearchBot4A_ForLoopsChallenge {


    /*
     * ========================================================
     * CHALLENGE 1 - THE LONG WAY
     * ========================================================
     *
     * Make the robot move forward FOUR times.
     *
     * RULE: do NOT use a loop yet.
     *
     * Expected output:
     *
     *     ROBOT: Moving forward
     *     ROBOT: Moving forward
     *     ROBOT: Moving forward
     *     ROBOT: Moving forward
     *
     * When you are done, answer: what if the coach now says
     * "make it forty times"?
     */
    static void challenge1() {

        System.out.println();
        System.out.println("=== CHALLENGE 1: THE LONG WAY ===");

        // TODO: call moveForward() four times.


    }


    /*
     * ========================================================
     * CHALLENGE 2 - THE SAME THING WITH A LOOP
     * ========================================================
     *
     * Now do Challenge 1 again using a for loop.
     *
     * Fill in the three blanks:
     *
     *     for (__________; __________; __________) {
     *
     *         moveForward();
     *     }
     *
     * Blank 1: make a counter called i, starting at 0
     * Blank 2: keep going while i is less than 4
     * Blank 3: add 1 to i each time
     */
    static void challenge2() {

        System.out.println();
        System.out.println("=== CHALLENGE 2: FOR LOOP ===");

        // TODO: write a for loop that runs 4 times
        //       and calls moveForward() each time.


    }


    /*
     * ========================================================
     * CHALLENGE 3 - PREDICT BEFORE YOU RUN
     * ========================================================
     *
     * Write a loop that prints:
     *
     *     Loop number: 0
     *     Loop number: 1
     *     Loop number: 2
     *     Loop number: 3
     *
     * BEFORE you run it, write on paper what you think it
     * prints. Then run it.
     *
     * QUESTION: why does it stop at 3 and not 4?
     */
    static void challenge3() {

        System.out.println();
        System.out.println("=== CHALLENGE 3: THE COUNTER ===");

        // TODO: for loop that prints the value of i.


    }


    /*
     * ========================================================
     * CHALLENGE 4 - COUNT THE MOVES
     * ========================================================
     *
     * Teach the robot to remember how many times it moved.
     *
     *   1. Create an int called moves, starting at 0.
     *   2. Loop 5 times.
     *   3. Move forward each time.
     *   4. Add 1 to moves each time.
     *   5. AFTER the loop, print the total.
     *
     * Expected last line:
     *
     *     Moves completed: 5
     *
     * WATCH OUT: where do you declare moves? Inside the loop
     * or outside it? Try both and see what happens.
     */
    static void challenge4() {

        System.out.println();
        System.out.println("=== CHALLENGE 4: COUNT THE MOVES ===");

        // TODO: create the counter (outside the loop!)


        // TODO: loop 5 times, moving and counting


        // TODO: print the total


    }


    /*
     * ========================================================
     * CHALLENGE 5 - DRIVE A SQUARE
     * ========================================================
     *
     * A square has four sides. For each side the robot must:
     *
     *     move forward
     *     turn right
     *
     * Write ONE loop that drives the whole square.
     *
     * THIS IS THE ONE WE RUN ON THE REAL ROBOT. Get it right
     * here first.
     */
    static void challenge5() {

        System.out.println();
        System.out.println("=== CHALLENGE 5: DRIVE A SQUARE ===");

        // TODO: loop four times: move forward, then turn right.


    }


    /*
     * ========================================================
     * CHALLENGE 6 - OTHER SHAPES
     * ========================================================
     *
     * Change your square into a TRIANGLE.
     *
     * How many sides? What has to change in the loop?
     *
     * Then try a hexagon (6 sides).
     *
     * QUESTION: you only changed ONE number to change the
     * shape. Why is that better than copy-pasting?
     */
    static void challenge6() {

        System.out.println();
        System.out.println("=== CHALLENGE 6: OTHER SHAPES ===");

        // TODO: drive a triangle.


    }


    /*
     * ========================================================
     * DEBUGGING CHALLENGE - OFF BY ONE
     * ========================================================
     *
     * This method is SUPPOSED to drive a four-sided square.
     *
     * Run it and count the sides. It drives FIVE.
     *
     * Use the process:
     *
     *     1. OBSERVE     How many sides did it actually drive?
     *     2. REPRODUCE   Run it again. Same every time?
     *     3. DESCRIBE    Say the problem out loud.
     *     4. LOCATE      Which part of the for loop decides
     *                    how many times it runs?
     *     5. CHANGE ONE  Fix one character.
     *     6. TEST        Four sides now?
     *     7. EXPLAIN     Why did <= run one extra time?
     *
     * This mistake is so common it has a name:
     * an OFF-BY-ONE ERROR. You will make it again. Now you
     * will recognise it.
     */
    static void debugChallenge_offByOne() {

        System.out.println();
        System.out.println("=== DEBUG: COUNT THE SIDES ===");

        for (int side = 0; side <= 4; side++) {

            System.out.println("--- Side " + (side + 1) + " ---");

            moveForward();
            turnRight();
        }
    }


    /*
     * ========================================================
     * ROBOT ACTIONS - do not change these yet
     * ========================================================
     */
    static void moveForward() {

        System.out.println("ROBOT: Moving forward");
    }


    static void turnRight() {

        System.out.println("ROBOT: Turning right");
    }


    static void checkSquare() {

        System.out.println("ROBOT: Checking square");
    }


    /*
     * ========================================================
     * MAIN
     * ========================================================
     *
     * COACH: uncomment ONE challenge at a time.
     */
    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("  SEARCHBOT 4A - CHALLENGES");
        System.out.println("=================================");

        // challenge1();
        // challenge2();
        // challenge3();
        // challenge4();
        // challenge5();
        // challenge6();

        // debugChallenge_offByOne();

        System.out.println();
        System.out.println("Uncomment the challenge you are on.");
    }
}
