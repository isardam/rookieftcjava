/*
 * ============================================================
 * SESSION 4C - NESTED LOOPS AND break - CHALLENGES
 * ============================================================
 *
 * This is the hardest set in the console course.
 *
 * Take your time. Draw the grid on paper. Trace with a finger.
 *
 * Prerequisites: 4A, 4B, and Session 5 (parameters).
 *
 * ============================================================
 */


public class SearchBot4C_NestedLoopsChallenge {


    /*
     * ========================================================
     * CHALLENGE 1 - SEARCH ONE ROW
     * ========================================================
     *
     * Before we do a whole grid, do a single row.
     *
     * Visit:
     *
     *     [0][0]  [0][1]  [0][2]  [0][3]
     *
     * Keep row fixed at 0. Loop over the columns.
     *
     * ONE loop only.
     */
    static void challenge1() {

        System.out.println();
        System.out.println("=== CHALLENGE 1: ONE ROW ===");

        int row = 0;

        // TODO: loop over the 4 columns, printing [row][column].


    }


    /*
     * ========================================================
     * CHALLENGE 2 - PREDICT THE ORDER
     * ========================================================
     *
     * BEFORE writing any code, write on paper the order these
     * squares will be visited by a nested loop:
     *
     *     outer loop = row     (0 to 3)
     *     inner loop = column  (0 to 3)
     *
     * Write all 16 in order. THEN write the code and check.
     *
     * If your prediction was wrong, work out why before moving
     * on. Understanding the ORDER is the whole point.
     */
    static void challenge2() {

        System.out.println();
        System.out.println("=== CHALLENGE 2: THE WHOLE GRID ===");

        // TODO: outer loop for rows


            // TODO: inner loop for columns


                // TODO: print [row][column]


    }


    /*
     * ========================================================
     * CHALLENGE 3 - SWAP THE LOOPS
     * ========================================================
     *
     * Take Challenge 2 and SWAP which loop is outside:
     *
     *     outer loop = column
     *     inner loop = row
     *
     * Run it.
     *
     * QUESTIONS:
     *
     *   1. Are all 16 squares still visited?
     *   2. Is the ORDER different?
     *   3. On a real robot, would one order be faster to drive
     *      than the other? Why?
     */
    static void challenge3() {

        System.out.println();
        System.out.println("=== CHALLENGE 3: SWAP THEM ===");

        // TODO: nested loop with column on the OUTSIDE.


    }


    /*
     * ========================================================
     * CHALLENGE 4 - STOP WHEN YOU FIND IT
     * ========================================================
     *
     * Search squares 0 to 9 in a single loop.
     *
     * The target is square 6.
     *
     * When you reach it: print "TARGET FOUND!" and break.
     *
     * QUESTION: how many squares did you check? How many did
     * you NOT have to check?
     */
    static void challenge4() {

        System.out.println();
        System.out.println("=== CHALLENGE 4: break ===");

        int target = 6;

        // TODO: loop 0 to 9, break when you hit the target.


    }


    /*
     * ========================================================
     * CHALLENGE 5 - FIND THE TARGET IN THE GRID
     * ========================================================
     *
     * The target is at [2][3].
     *
     * Requirements:
     *
     *     - nested loops
     *     - a boolean called targetFound
     *     - a counter for squares checked
     *     - compare BOTH row and column
     *     - stop as soon as you find it
     *
     * To check both at once, use && :
     *
     *     if (row == targetRow && column == targetColumn) {
     *
     * CAREFUL: getting the outer loop to stop as well as the
     * inner one is the tricky part. See the debugging
     * challenge below if it searches too many squares.
     */
    static void challenge5() {

        System.out.println();
        System.out.println("=== CHALLENGE 5: FIND IT ===");

        int targetRow = 2;
        int targetColumn = 3;

        // TODO: search the grid and stop when found.


        // TODO: print how many squares were checked.


    }


    /*
     * ========================================================
     * CHALLENGE 6 - MOVE THE TARGET
     * ========================================================
     *
     * Using your Challenge 5 answer, try each of these and
     * write down how many squares it took:
     *
     *     [0][0]  ->  ?
     *     [0][3]  ->  ?
     *     [2][2]  ->  ?
     *     [3][3]  ->  ?
     *
     * QUESTION: explain why the number changes.
     *
     * BONUS: make the target location easy to change - the
     * numbers should appear in exactly ONE place in your code.
     */
    static void challenge6() {

        System.out.println();
        System.out.println("=== CHALLENGE 6: MOVE THE TARGET ===");

        // TODO: test the four target locations.


    }


    /*
     * ========================================================
     * DEBUGGING CHALLENGE - THE break TRAP
     * ========================================================
     *
     * THIS IS THE MOST IMPORTANT EXERCISE IN 4C.
     *
     * The target is at [1][1]. The robot should find it and
     * stop after 6 squares.
     *
     * Run it. It checks 14 squares.
     *
     * It DOES find the target. It DOES break. And it still
     * keeps going.
     *
     *     1. OBSERVE     How many squares? Where does it keep
     *                    searching AFTER "TARGET FOUND"?
     *     2. REPRODUCE   Same every run.
     *     3. DESCRIBE    "break stopped the inner loop, but the
     *                     outer loop started the next row."
     *     4. LOCATE      Look at the OUTER for loop condition.
     *                    What does it check? What does it NOT
     *                    check?
     *     5. CHANGE ONE  Add  && !targetFound  to the outer
     *                    loop condition.
     *     6. TEST        6 squares now?
     *     7. EXPLAIN     Why does break alone not escape a
     *                    nested loop?
     *
     * THE LESSON:
     *
     *     break only escapes the loop it is standing in.
     *
     * Remember this. It will bite you again.
     */
    static void debugChallenge_breakTrap() {

        System.out.println();
        System.out.println("=== DEBUG: THE break TRAP ===");

        int targetRow = 1;
        int targetColumn = 1;

        int squaresChecked = 0;
        boolean targetFound = false;

        // Something is missing from this condition.
        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 4; column++) {

                squaresChecked++;

                System.out.println(
                        "Checking [" + row + "][" + column + "]" +
                        "   (total: " + squaresChecked + ")"
                );

                if (row == targetRow && column == targetColumn) {

                    targetFound = true;

                    System.out.println("*** TARGET FOUND! ***");

                    break;
                }
            }
        }

        System.out.println();
        System.out.println("Target found: " + targetFound);
        System.out.println("Squares checked: " + squaresChecked);
        System.out.println("Should have been: 6");
    }


    static void moveToSquare(int row, int column) {

        System.out.println(
                "ROBOT: Moving to [" + row + "][" + column + "]"
        );
    }


    static void checkSquare() {

        System.out.println("ROBOT: Checking square");
    }


    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("  SEARCHBOT 4C - CHALLENGES");
        System.out.println("=================================");

        // challenge1();
        // challenge2();
        // challenge3();
        // challenge4();
        // challenge5();
        // challenge6();

        // debugChallenge_breakTrap();

        System.out.println();
        System.out.println("Uncomment the challenge you are on.");
    }
}
