
/*
 * ============================================================
 * SEARCHBOT CHALLENGES
 * FTC JAVA - SESSION 4
 * ============================================================
 *
 * Mission:
 * Teach SearchBot to search a 4 x 4 grid for a hidden target.
 *
 * RULE:
 * Complete the challenges IN ORDER.
 *
 * Each challenge introduces one new programming idea.
 *
 * ============================================================
 *
 * CHALLENGE ROADMAP
 *
 * Challenge 1  - Repeat an action
 * Challenge 2  - for loop
 * Challenge 3  - Count repetitions
 * Challenge 4  - while loop
 * Challenge 5  - Boolean targetFound
 * Challenge 6  - Compare locations
 * Challenge 7  - Stop with break
 * Challenge 8  - Search a row
 * Challenge 9  - Search a 4 x 4 grid
 * Challenge 10 - Find the target
 * Challenge 11 - Search efficiently
 * Challenge 12 - Final FTC-style mission
 *
 * ============================================================
 */


public class SearchBotChallenges {


    /*
     * ========================================================
     * CHALLENGE 1
     * MAKE THE ROBOT MOVE
     * ========================================================
     *
     * Mission:
     * Make SearchBot move forward FOUR times.
     *
     * RULE:
     * Do NOT use a loop.
     *
     * This challenge demonstrates why repeated code can
     * become annoying.
     *
     * Expected:
     *
     * ROBOT: Moving forward
     * ROBOT: Moving forward
     * ROBOT: Moving forward
     * ROBOT: Moving forward
     *
     */
    static void challenge1() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 1: MAKE THE ROBOT MOVE");
        System.out.println("================================");

        // TODO: Call moveForward() four times.


    }


    /*
     * ========================================================
     * CHALLENGE 2
     * INTRODUCE THE FOR LOOP
     * ========================================================
     *
     * Mission:
     * Rewrite Challenge 1 using a for loop.
     *
     * Requirements:
     *
     * - Loop exactly 4 times.
     * - Move forward each time.
     *
     * Do NOT copy/paste moveForward() four times.
     */
    static void challenge2() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 2: FOR LOOP");
        System.out.println("================================");

        // TODO:
        // Create a for loop that runs 4 times.


            // TODO: Move forward.


    }


    /*
     * ========================================================
     * CHALLENGE 3
     * COUNT THE MOVES
     * ========================================================
     *
     * Mission:
     * Teach the robot to remember how many times it moved.
     *
     * Requirements:
     *
     * - Create an integer called moves.
     * - Start it at 0.
     * - Move 5 times.
     * - Increase moves after every movement.
     * - Print the final number.
     *
     * Expected:
     *
     * Moves completed: 5
     */
    static void challenge3() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 3: COUNT THE MOVES");
        System.out.println("================================");

        // TODO:
        // Create moves and set it to 0.


        // TODO:
        // Create a loop that runs 5 times.


            // TODO: Move forward.


            // TODO: Increase moves.


        // TODO:
        // Print moves.


    }


    /*
     * ========================================================
     * CHALLENGE 4
     * WHILE LOOP
     * ========================================================
     *
     * Mission:
     * Search until the robot has checked 5 squares.
     *
     * Requirements:
     *
     * - Use a while loop.
     * - Use a counter.
     * - Check one square per loop.
     * - Increase the counter.
     *
     * WARNING:
     *
     * If you forget to increase the counter,
     * you may create an INFINITE LOOP!
     */
    static void challenge4() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 4: WHILE LOOP");
        System.out.println("================================");

        // TODO:
        // Create squaresChecked and set it to 0.


        // TODO:
        // Create while loop.
        //
        // Continue while squaresChecked is less than 5.


            // TODO: Check square.


            // TODO: Increase counter.


    }


    /*
     * ========================================================
     * CHALLENGE 5
     * HAS THE ROBOT FOUND THE TARGET?
     * ========================================================
     *
     * Mission:
     * Create a Boolean variable that tells the robot
     * whether it has found the target.
     *
     * Requirements:
     *
     * - Create targetFound.
     * - Start it as false.
     * - Print its value.
     * - Change it to true.
     * - Print its value again.
     */
    static void challenge5() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 5: BOOLEAN");
        System.out.println("================================");

        // TODO:
        // Create boolean targetFound.


        // TODO: Print targetFound.


        // The robot finds the target.

        // TODO:
        // Change targetFound to true.


        // TODO:
        // Print targetFound.


    }


    /*
     * ========================================================
     * CHALLENGE 6
     * IS THIS THE TARGET?
     * ========================================================
     *
     * Mission:
     * The robot knows where it is.
     *
     * Robot:
     *     row = 2
     *     column = 3
     *
     * Target:
     *     row = 2
     *     column = 3
     *
     * Determine whether the robot is standing on the target.
     *
     * Requirements:
     *
     * - Compare row.
     * - Compare column.
     * - BOTH must match.
     *
     */
    static void challenge6() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 6: COMPARE LOCATIONS");
        System.out.println("================================");

        int robotRow = 2;
        int robotColumn = 3;

        int targetRow = 2;
        int targetColumn = 3;

        // TODO:
        // Use an if statement to determine whether
        // robotRow AND robotColumn match the target.


    }


    /*
     * ========================================================
     * CHALLENGE 7
     * STOP WHEN YOU FIND IT
     * ========================================================
     *
     * Mission:
     * Search squares 0 through 9.
     *
     * The target is square 6.
     *
     * Once the robot reaches square 6:
     *
     *     Print "TARGET FOUND!"
     *     STOP searching.
     *
     * Requirements:
     *
     * - for loop
     * - if statement
     * - comparison
     * - break
     */
    static void challenge7() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 7: BREAK");
        System.out.println("================================");

        int target = 6;

        // TODO:
        // Create a loop from 0 through 9.


            // TODO:
            // Print the square being checked.


            // TODO:
            // If this square is the target:


                // TODO: Print TARGET FOUND!


                // TODO: Stop the loop.


    }


    /*
     * ========================================================
     * CHALLENGE 8
     * SEARCH ONE ROW
     * ========================================================
     *
     * Mission:
     * Search four squares in a single row.
     *
     * The robot should visit:
     *
     *     [0][0]
     *     [0][1]
     *     [0][2]
     *     [0][3]
     *
     * Requirements:
     *
     * - Use a for loop.
     * - Use a column variable.
     * - Keep row fixed at 0.
     */
    static void challenge8() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 8: SEARCH ONE ROW");
        System.out.println("================================");

        int row = 0;

        // TODO:
        // Create a for loop for the columns.


            // TODO:
            // Print [row][column]


    }


    /*
     * ========================================================
     * CHALLENGE 9
     * SEARCH THE ENTIRE 4 x 4 GRID
     * ========================================================
     *
     * Mission:
     *
     * Search ALL 16 squares.
     *
     * Requirements:
     *
     * - Outer loop = rows
     * - Inner loop = columns
     * - Print every location.
     *
     * IMPORTANT:
     *
     * 4 rows x 4 columns = 16 squares.
     *
     */
    static void challenge9() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 9: 4 x 4 GRID");
        System.out.println("================================");

        // TODO:
        // Outer loop for rows.


            // TODO:
            // Inner loop for columns.


                // TODO:
                // Print [row][column]


    }


    /*
     * ========================================================
     * CHALLENGE 10
     * FIND THE TARGET
     * ========================================================
     *
     * Mission:
     *
     * Search a 4 x 4 grid for the target.
     *
     * Target:
     *
     *     [2][3]
     *
     * Requirements:
     *
     * - Nested loops
     * - Boolean
     * - Counter
     * - if statement
     * - comparison
     * - break
     *
     * The robot must STOP when it finds the target.
     */
    static void challenge10() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 10: FIND THE TARGET");
        System.out.println("================================");

        int targetRow = 2;
        int targetColumn = 3;

        int squaresChecked = 0;

        boolean targetFound = false;

        // TODO:
        // Create outer loop.
        //
        // Stop the outer loop when targetFound becomes true.


            // TODO:
            // Create inner loop.


                // TODO:
                // Move to the square.


                // TODO:
                // Check square.


                // TODO:
                // Increase squaresChecked.


                // TODO:
                // Determine whether this is the target.


                    // TODO:
                    // Set targetFound to true.


                    // TODO:
                    // Print TARGET FOUND!


                    // TODO:
                    // Print target location.


                    // TODO:
                    // break;


        // TODO:
        // Print total squares checked.


    }


    /*
     * ========================================================
     * CHALLENGE 11
     * SEARCH EFFICIENTLY
     * ========================================================
     *
     * Mission:
     *
     * Your robot found the target.
     *
     * But did it search more squares than necessary?
     *
     * Target:
     *
     *     [0][0]
     *
     * Your robot should find the target immediately.
     *
     * Then test:
     *
     *     [0][3]
     *     [2][2]
     *     [3][3]
     *
     * Record how many squares were checked.
     *
     * QUESTION:
     *
     * Can you explain why the number of squares checked
     * changes?
     *
     */
    static void challenge11() {

        System.out.println();
        System.out.println("================================");
        System.out.println("CHALLENGE 11: SEARCH EFFICIENTLY");
        System.out.println("================================");

        int targetRow = 0;
        int targetColumn = 0;

        int squaresChecked = 0;

        boolean targetFound = false;

        // TODO:
        // Implement the search.


        System.out.println(
                "Squares checked: " +
                squaresChecked
        );
    }


    /*
     * ========================================================
     * CHALLENGE 12 — FINAL MISSION
     * ========================================================
     *
     * 🚀 SEARCHBOT FINAL MISSION 🚀
     *
     * Your team has been given a 4 x 4 field.
     *
     * A target is hidden somewhere on the field.
     *
     * Your robot must systematically search for it.
     *
     * TARGET:
     *
     *     [3][2]
     *
     *
     * REQUIREMENTS
     * --------------------------------------------------------
     *
     * 1. Use nested loops.
     *
     * 2. Track the robot's row.
     *
     * 3. Track the robot's column.
     *
     * 4. Use a Boolean called targetFound.
     *
     * 5. Count squares checked.
     *
     * 6. Check whether the robot is on the target.
     *
     * 7. Stop when the target is found.
     *
     * 8. Print every square visited.
     *
     * 9. Print the final number of squares checked.
     *
     * 10. Print the target location.
     *
     * --------------------------------------------------------
     *
     * BONUS:
     *
     * Make the target location easy to change.
     *
     * EXTRA BONUS:
     *
     * Add a method that prints:
     *
     *     TARGET FOUND!
     *
     * --------------------------------------------------------
     */
    static void challenge12_finalMission() {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("       🚀 SEARCHBOT FINAL MISSION 🚀");
        System.out.println("==========================================");

        // TODO:
        // Set targetRow.


        // TODO:
        // Set targetColumn.


        // TODO:
        // Create squaresChecked.


        // TODO:
        // Create targetFound.


        // TODO:
        // Search the 4 x 4 grid.


            // TODO:
            // Search columns.


                // TODO:
                // Move to square.


                // TODO:
                // Check square.


                // TODO:
                // Increase counter.


                // TODO:
                // Check whether this is the target.


                    // TODO:
                    // Set targetFound to true.


                    // TODO:
                    // Print TARGET FOUND!


                    // TODO:
                    // Print target location.


                    // TODO:
                    // Stop searching this row.


        System.out.println();

        // TODO:
        // Print final results.


        System.out.println("==========================================");
        System.out.println("             MISSION COMPLETE");
        System.out.println("==========================================");
    }


    /*
     * ========================================================
     * ROBOT SIMULATION METHODS
     * ========================================================
     *
     * These simulate robot actions.
     *
     * DO NOT MODIFY THESE METHODS YET.
     *
     * Later we will replace these with actual FTC commands.
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
     * MAIN
     * ========================================================
     *
     * COACH:
     *
     * Run ONE challenge at a time.
     *
     * Uncomment the challenge students are currently solving.
     *
     */

    public static void main(String[] args) {

        System.out.println();
        System.out.println("==========================================");
        System.out.println("             SEARCHBOT");
        System.out.println("          SESSION 4 CHALLENGES");
        System.out.println("==========================================");


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
        // challenge12_finalMission();


        System.out.println();
        System.out.println("Run the challenge you are working on!");
    }
}
