/*
 * ============================================================
 * SESSION 4: LOOPS - TEACH THE ROBOT TO REPEAT
 * ============================================================
 *
 * Project:
 *     SearchBot - A robot that searches a 4 x 4 grid
 *                 for a hidden target.
 *
 * Java Concepts:
 *     1. Repeated code
 *     2. for loops
 *     3. Counters
 *     4. while loops
 *     5. Boolean variables
 *     6. if / else
 *     7. break
 *     8. Nested loops
 *     9. Comparisons
 *    10. FTC loop behavior
 *
 * NOTE:
 * This file starts as a normal Java program so students
 * can learn the programming concepts before working with
 * an actual FTC robot.
 *
 * ============================================================
 */


public class SearchBot {

    /*
     * --------------------------------------------------------
     * STEP 1
     * Repeated Code
     * --------------------------------------------------------
     *
     * Problem:
     * The robot needs to perform the same action multiple
     * times.
     *
     * Bad programming practice:
     * Writing the same command over and over.
     */
    static void step1_repeatedCode() {

        System.out.println();
        System.out.println("===== STEP 1: REPEATED CODE =====");

        moveForward();
        checkSquare();

        moveForward();
        checkSquare();

        moveForward();
        checkSquare();

        moveForward();
        checkSquare();
    }


    /*
     * --------------------------------------------------------
     * STEP 2
     * FOR LOOP
     * --------------------------------------------------------
     *
     * Instead of writing the same code four times,
     * we tell Java to repeat it.
     */
    static void step2_forLoop() {

        System.out.println();
        System.out.println("===== STEP 2: FOR LOOP =====");

        for (int i = 0; i < 4; i++) {

            moveForward();
            checkSquare();
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 3
     * UNDERSTANDING THE COUNTER
     * --------------------------------------------------------
     *
     * i starts at 0.
     *
     * Each time the loop runs:
     *
     *     i++
     *
     * increases i by 1.
     */
    static void step3_counter() {

        System.out.println();
        System.out.println("===== STEP 3: COUNTER =====");

        for (int i = 0; i < 4; i++) {

            System.out.println("Loop number: " + i);

            moveForward();
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 4
     * COUNTING SEARCHED SQUARES
     * --------------------------------------------------------
     *
     * A robot can use a counter to remember how much work
     * it has done.
     */
    static void step4_searchCounter() {

        System.out.println();
        System.out.println("===== STEP 4: SEARCH COUNTER =====");

        int squaresChecked = 0;

        for (int i = 0; i < 4; i++) {

            moveForward();
            checkSquare();

            squaresChecked++;

            System.out.println(
                    "Squares checked: " + squaresChecked
            );
        }

        System.out.println(
                "Final count: " + squaresChecked
        );
    }


    /*
     * --------------------------------------------------------
     * STEP 5
     * WHILE LOOP
     * --------------------------------------------------------
     *
     * A while loop continues as long as its condition
     * is true.
     */
    static void step5_whileLoop() {

        System.out.println();
        System.out.println("===== STEP 5: WHILE LOOP =====");

        int squaresChecked = 0;

        while (squaresChecked < 4) {

            System.out.println(
                    "Checking square " + squaresChecked
            );

            checkSquare();

            squaresChecked++;
        }

        System.out.println("Search finished.");
    }


    /*
     * --------------------------------------------------------
     * STEP 6
     * BOOLEAN VARIABLE
     * --------------------------------------------------------
     *
     * A boolean can store:
     *
     *     true
     *     false
     *
     * Our robot can use this to remember whether it found
     * the target.
     */
    static void step6_boolean() {

        System.out.println();
        System.out.println("===== STEP 6: BOOLEAN =====");

        boolean targetFound = false;

        System.out.println(
                "Target found? " + targetFound
        );

        // The robot searches...
        checkSquare();

        // Pretend we found it.
        targetFound = true;

        System.out.println(
                "Target found? " + targetFound
        );
    }


    /*
     * --------------------------------------------------------
     * STEP 7
     * IF STATEMENT
     * --------------------------------------------------------
     *
     * The robot can make a decision.
     */
    static void step7_ifStatement() {

        System.out.println();
        System.out.println("===== STEP 7: IF STATEMENT =====");

        boolean targetFound = true;

        if (targetFound) {

            System.out.println("TARGET FOUND!");

        } else {

            System.out.println("Keep searching.");
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 8
     * COMPARISONS
     * --------------------------------------------------------
     *
     * We can compare two numbers.
     *
     * ==    equal to
     * !=    not equal to
     * <     less than
     * >     greater than
     * <=    less than or equal to
     * >=    greater than or equal to
     */
    static void step8_comparisons() {

        System.out.println();
        System.out.println("===== STEP 8: COMPARISONS =====");

        int robotRow = 2;
        int robotColumn = 3;

        int targetRow = 2;
        int targetColumn = 3;

        if (robotRow == targetRow &&
                robotColumn == targetColumn) {

            System.out.println("TARGET FOUND!");

        } else {

            System.out.println("Not here.");
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 9
     * BREAK
     * --------------------------------------------------------
     *
     * Once the robot finds the target, we don't need to
     * keep searching.
     *
     * break exits the loop immediately.
     */
    static void step9_break() {

        System.out.println();
        System.out.println("===== STEP 9: BREAK =====");

        int target = 5;

        for (int square = 0; square < 10; square++) {

            System.out.println(
                    "Checking square " + square
            );

            if (square == target) {

                System.out.println("TARGET FOUND!");

                break;
            }
        }

        System.out.println("Search stopped.");
    }


    /*
     * --------------------------------------------------------
     * STEP 10
     * NESTED LOOPS
     * --------------------------------------------------------
     *
     * Now we introduce the 4 x 4 grid.
     *
     * Outer loop = rows
     * Inner loop = columns
     *
     * 4 rows x 4 columns = 16 squares
     */
    static void step10_nestedLoops() {

        System.out.println();
        System.out.println("===== STEP 10: NESTED LOOPS =====");

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 4; column++) {

                System.out.println(
                        "Searching [" +
                        row +
                        "][" +
                        column +
                        "]"
                );
            }
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 11
     * 4 x 4 GRID + COUNTER
     * --------------------------------------------------------
     *
     * Now we combine:
     *
     *     nested loops
     *     counters
     *     robot actions
     */
    static void step11_gridCounter() {

        System.out.println();
        System.out.println("===== STEP 11: GRID SEARCH =====");

        int squaresChecked = 0;

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 4; column++) {

                moveToSquare(row, column);

                checkSquare();

                squaresChecked++;
            }
        }

        System.out.println();
        System.out.println(
                "Total squares checked: " +
                squaresChecked
        );
    }


    /*
     * --------------------------------------------------------
     * STEP 12
     * COMPLETE SEARCH BOT
     * --------------------------------------------------------
     *
     * The target is hidden somewhere in the grid.
     *
     * SearchBot will:
     *
     *     1. Start at [0][0]
     *     2. Search every square
     *     3. Keep track of squares checked
     *     4. Compare its location to the target
     *     5. Stop when it finds the target
     */
    static void step12_completeSearchBot() {

        System.out.println();
        System.out.println("=================================");
        System.out.println("      COMPLETE SEARCH BOT");
        System.out.println("=================================");

        int targetRow = 2;
        int targetColumn = 3;

        int squaresChecked = 0;

        boolean targetFound = false;


        // Search rows
        for (int row = 0;
             row < 4 && !targetFound;
             row++) {


            // Search columns
            for (int column = 0;
                 column < 4;
                 column++) {


                // Move to the square
                moveToSquare(row, column);


                // Search the square
                checkSquare();


                // Increase counter
                squaresChecked++;


                // Check whether this is the target
                if (row == targetRow &&
                        column == targetColumn) {

                    targetFound = true;

                    System.out.println();
                    System.out.println(
                            "*************************"
                    );

                    System.out.println(
                            "      TARGET FOUND!"
                    );

                    System.out.println(
                            "Location: [" +
                            row +
                            "][" +
                            column +
                            "]"
                    );

                    System.out.println(
                            "*************************"
                    );

                    // Stop searching this row
                    break;
                }
            }
        }


        System.out.println();
        System.out.println("SEARCH COMPLETE");

        System.out.println(
                "Squares checked: " +
                squaresChecked
        );

        System.out.println(
                "Target found: " +
                targetFound
        );
    }


    /*
     * --------------------------------------------------------
     * ROBOT ACTION
     * --------------------------------------------------------
     *
     * For now, these methods simulate a robot.
     *
     * Later, these can become actual FTC motor commands.
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
     * --------------------------------------------------------
     * STEP 13
     * FTC LOOP BEHAVIOR
     * --------------------------------------------------------
     *
     * IMPORTANT:
     *
     * This is NOT the same thing as a Java for/while loop.
     *
     * FTC repeatedly calls loop() while the OpMode is running.
     *
     * Conceptually:
     *
     *     FTC
     *      |
     *      v
     *    loop()
     *      |
     *      v
     *    loop()
     *      |
     *      v
     *    loop()
     *      |
     *      v
     *     ...
     *
     * The FTC framework controls this repetition.
     *
     * We will learn actual FTC OpMode code in a later lesson.
     */
    static void explainFTCBehavior() {

        System.out.println();
        System.out.println("===== FTC LOOP BEHAVIOR =====");

        System.out.println(
                "FTC repeatedly calls loop()"
        );

        System.out.println(
                "The Java for loop repeats YOUR code."
        );

        System.out.println(
                "The FTC framework repeatedly updates the robot."
        );
    }


    /*
     * --------------------------------------------------------
     * MAIN
     * --------------------------------------------------------
     *
     * Students can run each step one at a time.
     *
     * START WITH STEP 1.
     *
     * Then uncomment the next step as the lesson progresses.
     */
    public static void main(String[] args) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("         SEARCHBOT");
        System.out.println("         SESSION 4");
        System.out.println("=================================");


        // STEP 1
        step1_repeatedCode();


        // STEP 2
        step2_forLoop();


        // STEP 3
        step3_counter();


        // STEP 4
        step4_searchCounter();


        // STEP 5
        step5_whileLoop();


        // STEP 6
        step6_boolean();


        // STEP 7
        step7_ifStatement();


        // STEP 8
        step8_comparisons();


        // STEP 9
        step9_break();


        // STEP 10
        step10_nestedLoops();


        // STEP 11
        step11_gridCounter();


        // STEP 12
        step12_completeSearchBot();


        // STEP 13
        explainFTCBehavior();


        System.out.println();
        System.out.println("=================================");
        System.out.println("        MISSION COMPLETE!");
        System.out.println("=================================");
    }
}
