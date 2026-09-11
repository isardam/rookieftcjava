/*
 * SearchBot.java
 *
 * FTC Java Programming - Session 4
 * Loops: Teach the Robot to Repeat
 *
 * Project:
 * Build a robot that searches a 4 x 4 grid
 * for a hidden target.
 *
 * Concepts:
 * - for loops
 * - while loops
 * - counters
 * - nested loops
 * - boolean variables
 * - if / else
 * - comparisons
 * - repeated actions
 *
 * IMPORTANT:
 * This program simulates robot behavior using
 * console output. It is NOT yet an FTC OpMode.
 */

/*
 * SearchBot.java
 *
 * Session 4 Final Project
 *
 * The robot searches a 4 x 4 field
 * for a target.
 */
public class SearchBot {

    public static void main(String[] args) {

        // =====================================================
        // 1. VARIABLES
        // =====================================================

        String robotName = "Iron Angel";

        int gridSize = 4;

        int batteryPercent = 100;

        int targetRow = 3;
        int targetColumn = 2;

        int locationsSearched = 0;

        boolean targetFound = false;
        boolean robotRunning = true;


        // =====================================================
        // 2. ROBOT STARTUP
        // =====================================================

        System.out.println("=================================");
        System.out.println("          SEARCH BOT");
        System.out.println("=================================");

        System.out.println("Robot: " + robotName);
        System.out.println("Field: "
                + gridSize + " x " + gridSize);

        System.out.println("Target Location: SECRET");

        System.out.println("---------------------------------");
        System.out.println("MISSION START");
        System.out.println("---------------------------------");


        // =====================================================
        // 3. FOR LOOP
        //
        // The robot searches each row.
        // =====================================================

        for (int row = 1; row <= gridSize; row++) {


            // =================================================
            // 4. NESTED FOR LOOP
            //
            // The robot searches each column
            // inside the current row.
            // =================================================

            for (int column = 1;
                 column <= gridSize;
                 column++) {


                // =============================================
                // 5. BOOLEAN LOGIC
                //
                // The robot should only search when:
                //
                // robotRunning = true
                // AND
                // targetFound = false
                // =============================================

                if (robotRunning && !targetFound) {


                    // =========================================
                    // 6. REPEATED ACTION
                    // =========================================

                    System.out.println(
                        "Robot moves to Row "
                        + row
                        + ", Column "
                        + column
                    );

                    System.out.println(
                        "Scanning location..."
                    );


                    // =========================================
                    // 7. COUNTER
                    //
                    // Keep track of how many locations
                    // the robot has searched.
                    // =========================================

                    locationsSearched++;


                    System.out.println(
                        "Locations searched: "
                        + locationsSearched
                    );


                    // =========================================
                    // 8. SIMULATE BATTERY USAGE
                    // =========================================

                    batteryPercent =
                        batteryPercent - 5;

                    System.out.println(
                        "Battery: "
                        + batteryPercent
                        + "%"
                    );


                    // =========================================
                    // 9. COMPARISONS
                    //
                    // Is the robot at the target?
                    // =========================================

                    if (row == targetRow &&
                        column == targetColumn) {

                        targetFound = true;

                        System.out.println();
                        System.out.println(
                            "************************"
                        );

                        System.out.println(
                            "     TARGET FOUND!"
                        );

                        System.out.println(
                            "************************"
                        );

                        System.out.println(
                            "Target Row: "
                            + row
                        );

                        System.out.println(
                            "Target Column: "
                            + column
                        );
                    }


                    // =========================================
                    // 10. BATTERY CHECK
                    //
                    // Another comparison.
                    // =========================================

                    if (batteryPercent <= 20 &&
                        !targetFound) {

                        robotRunning = false;

                        System.out.println();
                        System.out.println(
                            "WARNING: LOW BATTERY!"
                        );

                        System.out.println(
                            "Robot must return home."
                        );
                    }


                    System.out.println();
                }
            }
        }


        // =====================================================
        // 11. IF / ELSE
        //
        // Decide what happened during the mission.
        // =====================================================

        System.out.println("---------------------------------");
        System.out.println("SEARCH COMPLETE");
        System.out.println("---------------------------------");


        if (targetFound) {

            System.out.println(
                "MISSION STATUS: SUCCESS"
            );

            System.out.println(
                "The robot found the target!"
            );

        } else if (!robotRunning) {

            System.out.println(
                "MISSION STATUS: ABORTED"
            );

            System.out.println(
                "The robot ran out of battery."
            );

        } else {

            System.out.println(
                "MISSION STATUS: TARGET NOT FOUND"
            );
        }


        // =====================================================
        // 12. FINAL REPORT
        // =====================================================

        System.out.println("---------------------------------");

        System.out.println(
            "Locations searched: "
            + locationsSearched
        );

        System.out.println(
            "Final battery: "
            + batteryPercent
            + "%"
        );

        System.out.println(
            "Target found: "
            + targetFound
        );

        System.out.println("---------------------------------");
        System.out.println("MISSION ENDED");
        System.out.println("=================================");
    }
}
