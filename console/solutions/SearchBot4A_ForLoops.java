/*
 * ============================================================
 * SESSION 4A - FOR LOOPS
 * ============================================================
 *
 * ONE new idea today: a loop repeats a block of code a counted
 * number of times.
 *
 * That is it. Nothing else.
 *
 *
 * THE ROBOT STORY
 * ============================================================
 *
 *     "Autonomous has to drive a square around the field.
 *      Are we really going to write the same four lines
 *      four times?"
 *
 *
 * WHAT WE ALREADY KNOW
 * ============================================================
 *
 *   Blocks                    Java
 *   ---------------------     -------------------------------
 *   repeat 4 times            for (int i = 0; i < 4; i++) {
 *     move forward                moveForward();
 *     check square                checkSquare();
 *   end                       }
 *   ---------------------     -------------------------------
 *
 * You already used "repeat" in Blocks. This is the same thing
 * with different punctuation.
 *
 * ============================================================
 */


public class SearchBot4A_ForLoops {


    /*
     * --------------------------------------------------------
     * STEP 1 - THE PROBLEM
     * --------------------------------------------------------
     *
     * The robot needs to move forward and check a square
     * FOUR times.
     *
     * Here it is written out the long way.
     *
     * Read it and notice how annoying it is. THAT FEELING is
     * the reason loops exist.
     */
    static void step1_repeatedCode() {

        System.out.println();
        System.out.println("===== STEP 1: THE LONG WAY =====");

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
     * STEP 2 - THE FOR LOOP
     * --------------------------------------------------------
     *
     * Exactly the same behaviour, written once.
     *
     * The for loop has THREE parts, separated by semicolons:
     *
     *     for (int i = 0 ; i < 4 ; i++)
     *          |           |       |
     *          |           |       +-- AFTER each turn:
     *          |           |           add 1 to i
     *          |           |
     *          |           +---------- KEEP GOING while
     *          |                       this is true
     *          |
     *          +---------------------- START by making a
     *                                  counter called i,
     *                                  set to 0
     */
    static void step2_forLoop() {

        System.out.println();
        System.out.println("===== STEP 2: THE FOR LOOP =====");

        for (int i = 0; i < 4; i++) {

            moveForward();
            checkSquare();
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 3 - WHAT IS i?
     * --------------------------------------------------------
     *
     * i is just a variable. We can look at it.
     *
     * Notice it starts at 0 and stops at 3 - NOT 4.
     *
     * The loop runs 4 times: 0, 1, 2, 3.
     *
     * Counting from zero feels strange at first. Every
     * programmer went through this. It becomes normal.
     */
    static void step3_counter() {

        System.out.println();
        System.out.println("===== STEP 3: THE COUNTER =====");

        for (int i = 0; i < 4; i++) {

            System.out.println("Loop number: " + i);
        }
    }


    /*
     * --------------------------------------------------------
     * STEP 4 - COUNTING THE ROBOT'S WORK
     * --------------------------------------------------------
     *
     * A robot can use a counter to remember how much work it
     * has done.
     *
     * IMPORTANT: squaresChecked is declared OUTSIDE the loop.
     *
     * If we declared it inside, it would be created fresh
     * (back to 0) every single time round, and it could never
     * count anything.
     */
    static void step4_searchCounter() {

        System.out.println();
        System.out.println("===== STEP 4: COUNTING =====");

        int squaresChecked = 0;

        for (int i = 0; i < 4; i++) {

            moveForward();
            checkSquare();

            squaresChecked++;

            System.out.println(
                    "Squares checked so far: " + squaresChecked
            );
        }

        System.out.println();
        System.out.println("Final count: " + squaresChecked);
    }


    /*
     * --------------------------------------------------------
     * STEP 5 - DRIVE A SQUARE
     * --------------------------------------------------------
     *
     * This is the one we will run on the real robot.
     *
     * Four sides. Each side: drive, then turn.
     */
    static void step5_driveASquare() {

        System.out.println();
        System.out.println("===== STEP 5: DRIVE A SQUARE =====");

        for (int side = 0; side < 4; side++) {

            System.out.println("--- Side " + (side + 1) + " ---");

            moveForward();
            turnRight();
        }

        System.out.println();
        System.out.println("Back where we started!");
    }


    /*
     * --------------------------------------------------------
     * ROBOT ACTIONS
     * --------------------------------------------------------
     *
     * For now these just print. In Session 5 we will replace
     * them with real motor commands, and this exact same loop
     * will drive a real square on the field.
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


    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("     SEARCHBOT 4A - FOR LOOPS");
        System.out.println("=================================");

        step1_repeatedCode();
        step2_forLoop();
        step3_counter();
        step4_searchCounter();
        step5_driveASquare();

        System.out.println();
        System.out.println("=================================");
        System.out.println("         DONE");
        System.out.println("=================================");
    }
}
