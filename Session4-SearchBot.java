
/*
 * ============================================================
 * SESSION 4: LOOPS
 * SEARCHBOT - STUDENT VERSION
 * ============================================================
 *
 * Mission:
 * Build a robot that searches a 4 x 4 grid for a hidden target.
 *
 * Concepts:
 *   - for loops
 *   - while loops
 *   - counters
 *   - repeated actions
 *   - boolean variables
 *   - if / else
 *   - comparisons
 *   - break
 *   - nested loops
 *
 * RULE:
 * Do not jump to the final challenge!
 *
 * Complete each step as we learn it.
 *
 * ============================================================
 */


public class SearchBot {


    /*
     * ========================================================
     * STEP 1 — REPEATED CODE
     * ========================================================
     *
     * The robot needs to move forward and check a square
     * FOUR times.
     *
     * This is repetitive code.
     *
     * TODO:
     * Write the commands needed to:
     *
     *   1. Move forward
     *   2. Check the square
     *
     * Repeat those two commands FOUR times.
     *
     * Don't use a loop yet!
     */
    static void step1_repeatedCode() {

        System.out.println();
        System.out.println("===== STEP 1 =====");

        // TODO: Move forward and check square

        // TODO: Move forward and check square

        // TODO: Move forward and check square

        // TODO: Move forward and check square
    }


    /*
     * ========================================================
     * STEP 2 — FOR LOOP
     * ========================================================
     *
     * Now let's eliminate repeated code.
     *
     * We want the robot to:
     *
     *     move
     *     check
     *     move
     *     check
     *     move
     *     check
     *     move
     *     check
     *
     * TODO:
     * Create a for loop that runs FOUR times.
     */
    static void step2_forLoop() {

        System.out.println();
        System.out.println("===== STEP 2 =====");

        // TODO:
        // Create a for loop that repeats 4 times.
        //
        // for (__________; __________; __________) {
        //
        //     moveForward();
        //     checkSquare();
        //
        // }


    }


    /*
     * ========================================================
     * STEP 3 — UNDERSTANDING THE COUNTER
     * ========================================================
     *
     * The variable i is a counter.
     *
     * TODO:
     * Create a loop that prints:
     *
     *     Loop number: 0
     *     Loop number: 1
     *     Loop number: 2
     *     Loop number: 3
     *
     */
    static void step3_counter() {

        System.out.println();
        System.out.println("===== STEP 3 =====");

        // TODO:
        // Create a for loop.
        //
        // Start i at 0.
        // Continue while i is less than 4.
        // Increase i by 1 each time.
        //
        // Print the value of i.


    }


    /*
     * ========================================================
     * STEP 4 — COUNTING SQUARES
     * ========================================================
     *
     * Now the robot needs to remember how many squares
     * it has checked.
     *
     * TODO:
     *
     * 1. Create an integer called squaresChecked.
     * 2. Start it at 0.
     * 3. Create a loop that runs 4 times.
     * 4. Check a square.
     * 5. Increase squaresChecked.
     * 6. Print the counter.
     */
    static void step4_searchCounter() {

        System.out.println();
        System.out.println("===== STEP 4 =====");

        // TODO: Create counter
        // int squaresChecked = ____;


        // TODO: Create for loop


            // TODO: Check square


            // TODO: Increase counter


            // TODO: Print counter


        // TODO: Print final count
    }


    /*
     * ========================================================
     * STEP 5 — WHILE LOOP
     * ========================================================
     *
     * A while loop repeats while a condition is true.
     *
     * We want:
     *
     *     while squaresChecked < 4
     *
     * TODO:
     *
     * 1. Create squaresChecked.
     * 2. Create a while loop.
     * 3. Check a square.
     * 4. Increase the counter.
     */
    static void step5_whileLoop() {

        System.out.println();
        System.out.println("===== STEP 5 =====");

        // TODO:
        // int squaresChecked = 0;


        // TODO:
        // while (________________) {


            // TODO: Check square


            // TODO: Increase counter


        // }


        System.out.println("Search finished.");
    }


    /*
     * ========================================================
     * STEP 6 — BOOLEAN
     * ========================================================
     *
     * A boolean can only be:
     *
     *     true
     *     false
     *
     * The robot needs to remember whether it found the target.
     *
     * TODO:
     *
     * Create:
     *
     *     boolean targetFound = false;
     *
     * Then print targetFound.
     *
     * Then change targetFound to true.
     *
     * Print it again.
     */
    static void step6_boolean() {

        System.out.println();
        System.out.println("===== STEP 6 =====");

        // TODO: Create boolean


        // TODO: Print targetFound


        // Pretend the robot found the target.

        // TODO: Change targetFound to true


        // TODO: Print targetFound
    }


    /*
     * ========================================================
     * STEP 7 — IF / ELSE
     * ========================================================
     *
     * The robot needs to make decisions.
     *
     * TODO:
     *
     * If targetFound is true:
     *
     *     print "TARGET FOUND!"
     *
     * Otherwise:
     *
     *     print "Keep searching."
     */
    static void step7_ifStatement() {

        System.out.println();
        System.out.println("===== STEP 7 =====");

        boolean targetFound = true;

        // TODO:
        // Write an if / else statement.


    }


    /*
     * ========================================================
     * STEP 8 — COMPARISONS
     * ========================================================
     *
     * The robot has a location:
     *
     *     robotRow
     *     robotColumn
     *
     * The target has a location:
     *
     *     targetRow
     *     targetColumn
     *
     * TODO:
     *
     * Determine whether the robot is on the target.
     *
     * The robot is on the target ONLY when:
     *
     *     robotRow == targetRow
     *
     * AND
     *
     *     robotColumn == targetColumn
     *
     */
    static void step8_comparisons() {

        System.out.println();
        System.out.println("===== STEP 8 =====");

        int robotRow = 2;
        int robotColumn = 3;

        int targetRow = 2;
        int targetColumn = 3;

        // TODO:
        // Write an if statement that checks BOTH
        // row and column.


    }


    /*
     * ========================================================
     * STEP 9 — BREAK
     * ========================================================
     *
     * Once the robot finds the target, it should STOP
     * searching.
     *
     * TODO:
     *
     * 1. Create a target variable.
     * 2. Create a for loop from 0 to 9.
     * 3. Print the square being checked.
     * 4. If square == target:
     *       print "TARGET FOUND!"
     *       break;
     *
     */
    static void step9_break() {

        System.out.println();
        System.out.println("===== STEP 9 =====");

        // TODO:
        // int target = ____;


        // TODO:
        // Create a for loop that checks squares 0 through 9.


            // TODO:
            // Print the square being checked.


            // TODO:
            // Check if this is the target.


                // TODO: Print TARGET FOUND!


                // TODO: Stop the loop with break;


        System.out.println("Search stopped.");
    }


    /*
     * ========================================================
     * STEP 10 — NESTED LOOPS
     * ========================================================
     *
     * NOW WE HAVE A GRID!
     *
     * Our field is:
     *
     *       0   1   2   3
     *
     *   0   +---+---+---+---+
     *       |   |   |   |   |
     *       +---+---+---+---+
     *
     *   1   +---+---+---+---+
     *       |   |   |   |   |
     *       +---+---+---+---+
     *
     *   2   +---+---+---+---+
     *       |   |   |   |   |
     *       +---+---+---+---+
     *
     *   3   +---+---+---+---+
     *       |   |   |   |   |
     *       +---+---+---+---+
     *
     * We need:
     *
     *     4 rows
     *     4 columns
     *
     * TODO:
     *
     * Create a nested loop that prints every location.
     *
     * Expected:
     *
     *     [0][0]
     *     [0][1]
     *     [0][2]
     *     [0][3]
     *     [1][0]
     *     ...
     *     [3][3]
     *
     */
    static void step10_nestedLoops() {

        System.out.println();
        System.out.println("===== STEP 10 =====");

        // TODO:
        // Outer loop = rows


            // TODO:
            // Inner loop = columns


                // TODO:
                // Print [row][column]


    }


    /*
     * ========================================================
     * STEP 11 — GRID + COUNTER
     * ========================================================
     *
     * Combine everything we have learned so far.
     *
     * TODO:
     *
     * 1. Create squaresChecked.
     * 2. Create an outer loop for rows.
     * 3. Create an inner loop for columns.
     * 4. Move to the square.
     * 5. Check the square.
     * 6. Increase squaresChecked.
     * 7. Print the final number.
     *
     * Remember:
     *
     *     4 x 4 = 16
     *
     */
    static void step11_gridCounter() {

        System.out.println();
        System.out.println("===== STEP 11 =====");

        // TODO:
        // int squaresChecked = 0;


        // TODO:
        // Outer loop


            // TODO:
            // Inner loop


                // TODO: Move to square


                // TODO: Check square


                // TODO: Increase counter


        // TODO:
        // Print total squares checked


    }


    /*
     * ========================================================
     * STEP 12 — COMPLETE SEARCHBOT
     * ========================================================
     *
     * FINAL MISSION!
     *
     * The target is hidden somewhere in the 4 x 4 grid.
     *
     * Target:
     *
     *     row = 2
     *     column = 3
     *
     * SearchBot must:
     *
     *     1. Search the grid.
     *     2. Keep track of squares checked.
     *     3. Compare the robot's location to the target.
     *     4. Set targetFound to true when it finds it.
     *     5. Stop searching.
     *
     * REQUIRED:
     *
     *     for loop
     *     nested loop
     *     counter
     *     boolean
     *     if
     *     comparison
     *     break
     *
     */
    static void step12_completeSearchBot() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       COMPLETE SEARCHBOT");
        System.out.println("=================================");


        // TODO:
        // Set the target row.


        // TODO:
        // Set the target column.


        // TODO:
        // Create squaresChecked.


        // TODO:
        // Create targetFound and start it as false.


        // TODO:
        // Create the outer row loop.
        //
        // IMPORTANT:
        // The robot should stop searching rows
        // once targetFound becomes true.


            // TODO:
            // Create the inner column loop.


                // TODO:
                // Move to the current square.


                // TODO:
                // Check the square.


                // TODO:
                // Increase squaresChecked.


                // TODO:
                // Check whether the robot is
                // currently on the target.


                    // TODO:
                    // Set targetFound to true.


                    // TODO:
                    // Print TARGET FOUND!


                    // TODO:
                    // Print target location.


                    // TODO:
                    // break;


        // TODO:
        // Print SEARCH COMPLETE.


        // TODO:
        // Print number of squares checked.


        // TODO:
        // Print whether target was found.

    }


    /*
     * ========================================================
     * ROBOT ACTIONS
     * ========================================================
     *
     * DON'T CHANGE THESE YET!
     *
     * These methods simulate a robot.
     *
     * Later, we will replace these with actual FTC robot
     * commands.
     */
    static void moveForward() {

        System.out.println(
                "ROBOT: Moving forward"
        );
    }


    static void turnRight() {

        System.out.println(
                "ROBOT: Turning right"
        );
    }


    static void checkSquare() {

        System.out.println(
                "ROBOT: Checking square"
        );
    }


    static void moveToSquare(int row, int column) {

        System.out.println(
                "ROBOT: Moving to [" +
                row +
                "][" +
                column +
                "]"
        );
    }


    /*
     * ========================================================
     * FTC CONNECTION
     * ========================================================
     *
     * IMPORTANT IDEA:
     *
     * A Java loop:
     *
     *     for (...)
     *
     * repeats code because WE tell Java to repeat it.
     *
     *
     * FTC also repeatedly updates the robot while an
     * OpMode is running.
     *
     * Conceptually:
     *
     *       FTC
     *        |
     *        v
     *      loop()
     *        |
     *        v
     *      loop()
     *        |
     *        v
     *      loop()
     *        |
     *        v
     *       ...
     *
     * We will learn actual FTC OpMode code later.
     */
    static void explainFTCBehavior() {

        System.out.println();
        System.out.println("===== FTC LOOP BEHAVIOR =====");

        System.out.println(
                "FTC repeatedly updates the robot."
        );

        System.out.println(
                "Java loops repeat our instructions."
        );
    }


    /*
     * ========================================================
     * MAIN PROGRAM
     * ========================================================
     *
     * COACH:
     *
     * Initially, run ONLY the step the students are working on.
     *
     * Example:
     *
     *     step1_repeatedCode();
     *
     * Later add:
     *
     *     step2_forLoop();
     *
     * Then:
     *
     *     step3_counter();
     *
     * and so on.
     *
     * ========================================================
     */
    public static void main(String[] args) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("          SEARCHBOT");
        System.out.println("          SESSION 4");
        System.out.println("=================================");


        /*
         * STEP 1
         *
         * Uncomment when students are working on Step 1.
         */
        // step1_repeatedCode();


        /*
         * STEP 2
         */
        // step2_forLoop();


        /*
         * STEP 3
         */
        // step3_counter();


        /*
         * STEP 4
         */
        // step4_searchCounter();


        /*
         * STEP 5
         */
        // step5_whileLoop();


        /*
         * STEP 6
         */
        // step6_boolean();


        /*
         * STEP 7
         */
        // step7_ifStatement();


        /*
         * STEP 8
         */
        // step8_comparisons();


        /*
         * STEP 9
         */
        // step9_break();


        /*
         * STEP 10
         */
        // step10_nestedLoops();


        /*
         * STEP 11
         */
        // step11_gridCounter();


        /*
         * STEP 12
         *
         * FINAL CHALLENGE
         */
        // step12_completeSearchBot();


        /*
         * STEP 13
         *
         * FTC CONNECTION
         */
        // explainFTCBehavior();


        System.out.println();
        System.out.println("=================================");
        System.out.println("          MISSION COMPLETE");
        System.out.println("=================================");
    }
}
 
