/*
 * ============================================================
 * SESSION 4C - NESTED LOOPS AND break  (ADVANCED)
 * ============================================================
 *
 * COACH NOTE - READ THIS FIRST
 * ============================================================
 *
 * This is the HARDEST session in the console part of the
 * course. Harder than classes and objects.
 *
 * Do NOT run it on the same day as 4A or 4B.
 *
 * Prerequisites:
 *     - 4A (for loops)
 *     - 4B (while loops)
 *     - Session 5 (methods with parameters) - because
 *       moveToSquare(row, column) takes TWO parameters
 *
 * If your team is tired, SKIP THIS and come back to it. Nested
 * loops are not needed to write a working TeleOp or a working
 * autonomous. They are a "nice to have".
 *
 *
 * THE ROBOT STORY
 * ============================================================
 *
 *     "The game element could be anywhere in a 4x4 zone.
 *      We need to check every square until we find it."
 *
 * ============================================================
 */


public class SearchBot4C_NestedLoops {


    /*
     * --------------------------------------------------------
     * STEP 1 - A LOOP INSIDE A LOOP
     * --------------------------------------------------------
     *
     * Our search zone:
     *
     *        column 0   1   2   3
     *              +---+---+---+---+
     *      row 0   |   |   |   |   |
     *              +---+---+---+---+
     *      row 1   |   |   |   |   |
     *              +---+---+---+---+
     *      row 2   |   |   |   |   |
     *              +---+---+---+---+
     *      row 3   |   |   |   |   |
     *              +---+---+---+---+
     *
     * To visit every square we need TWO counters: which row,
     * and which column.
     *
     * THINK OF IT LIKE READING A BOOK:
     *
     *     the OUTER loop picks the line   (row)
     *     the INNER loop reads across it  (column)
     *
     * The inner loop finishes COMPLETELY before the outer loop
     * moves on. So the order is:
     *
     *     [0][0] [0][1] [0][2] [0][3]
     *     [1][0] [1][1] ...
     *
     * 4 rows x 4 columns = 16 squares.
     */
    static void step1_nestedLoops() {

        System.out.println();
        System.out.println("===== STEP 1: NESTED LOOPS =====");

        for (int row = 0; row < 4; row++) {

            for (int column = 0; column < 4; column++) {

                System.out.println(
                        "Searching [" + row + "][" + column + "]"
                );
            }
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 2 - COUNT THEM
     * --------------------------------------------------------
     *
     * Proof that it really is 16.
     */
    static void step2_gridCounter() {

        System.out.println();
        System.out.println("===== STEP 2: COUNT THE SQUARES =====");

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
                "Total squares checked: " + squaresChecked
        );
    }


    /*
     * --------------------------------------------------------
     * STEP 3 - break
     * --------------------------------------------------------
     *
     * break means "leave this loop RIGHT NOW".
     *
     * Once we find the target there is no point looking at the
     * rest of the squares.
     *
     * In a real match, time is points.
     */
    static void step3_break() {

        System.out.println();
        System.out.println("===== STEP 3: break =====");

        int target = 5;

        for (int square = 0; square < 10; square++) {

            System.out.println("Checking square " + square);

            if (square == target) {

                System.out.println("TARGET FOUND!");

                break;      // stop looking
            }
        }

        System.out.println("Search stopped.");
    }


    /*
     * --------------------------------------------------------
     * STEP 4 - THE break TRAP
     * --------------------------------------------------------
     *
     * THIS IS THE IMPORTANT ONE. READ IT SLOWLY.
     *
     * break only escapes the loop it is standing in.
     *
     * Inside a nested loop, break gets us out of the INNER
     * loop - but the OUTER loop just carries on to the next
     * row!
     *
     * So we need TWO things to really stop searching:
     *
     *     1. break            to leave the inner loop
     *     2. && !targetFound  to stop the outer loop too
     *
     * Look at the outer loop condition below:
     *
     *     row < 4 && !targetFound
     *
     * That is what actually stops the search.
     *
     * The debugging challenge asks you to delete that part and
     * watch what goes wrong. Do it - it is the whole lesson.
     */
    static void step4_completeSearch() {

        System.out.println();
        System.out.println("===== STEP 4: COMPLETE SEARCH =====");

        int targetRow = 2;
        int targetColumn = 3;

        int squaresChecked = 0;
        boolean targetFound = false;

        // The outer loop stops when we find it...
        for (int row = 0; row < 4 && !targetFound; row++) {

            for (int column = 0; column < 4; column++) {

                moveToSquare(row, column);
                checkSquare();

                squaresChecked++;

                if (row == targetRow && column == targetColumn) {

                    targetFound = true;

                    System.out.println();
                    System.out.println("*** TARGET FOUND! ***");
                    System.out.println(
                            "Location: [" + row + "][" + column + "]"
                    );

                    // ...and break stops the inner loop.
                    break;
                }
            }
        }

        System.out.println();
        System.out.println("SEARCH COMPLETE");
        System.out.println("Squares checked: " + squaresChecked);
        System.out.println("Target found: " + targetFound);
    }


    /*
     * --------------------------------------------------------
     * STEP 5 - DOES THE ORDER MATTER?
     * --------------------------------------------------------
     *
     * Search for a target in four different places and count
     * how many squares it took.
     *
     * The target at [0][0] is found immediately.
     * The target at [3][3] takes all 16.
     *
     * QUESTION: if you knew the game element was usually near
     * the middle, how would you change the search order?
     *
     * (This is real FTC strategy. Teams tune their autonomous
     *  search order based on where the element usually is.)
     */
    static void step5_searchEfficiency() {

        System.out.println();
        System.out.println("===== STEP 5: EFFICIENCY =====");

        reportSearchCost(0, 0);
        reportSearchCost(0, 3);
        reportSearchCost(2, 2);
        reportSearchCost(3, 3);
    }


    /*
     * Counts squares checked, without printing every step.
     *
     * Returns the number of squares it took. (A method that
     * ANSWERS instead of DOING - Session 5 idea.)
     */
    static int countSquaresToFind(int targetRow, int targetColumn) {

        int squaresChecked = 0;
        boolean targetFound = false;

        for (int row = 0; row < 4 && !targetFound; row++) {

            for (int column = 0; column < 4; column++) {

                squaresChecked++;

                if (row == targetRow && column == targetColumn) {

                    targetFound = true;
                    break;
                }
            }
        }

        return squaresChecked;
    }


    static void reportSearchCost(int targetRow, int targetColumn) {

        int cost = countSquaresToFind(targetRow, targetColumn);

        System.out.println(
                "Target at [" + targetRow + "][" + targetColumn +
                "] took " + cost + " squares."
        );
    }


    /*
     * --------------------------------------------------------
     * ROBOT ACTIONS
     * --------------------------------------------------------
     *
     * moveToSquare takes TWO parameters. If that looks strange,
     * go back and finish Session 5 first.
     */
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
        System.out.println("  SEARCHBOT 4C - NESTED LOOPS");
        System.out.println("=================================");

        step1_nestedLoops();
        step2_gridCounter();
        step3_break();
        step4_completeSearch();
        step5_searchEfficiency();

        System.out.println();
        System.out.println("=================================");
        System.out.println("            DONE");
        System.out.println("=================================");
    }
}
