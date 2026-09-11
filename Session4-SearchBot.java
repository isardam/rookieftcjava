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

public class SearchBot {

    public static void main(String[] args) {

        // ==========================================
        // ROBOT INFORMATION
        // ==========================================

        String robotName = "Iron Angel";

        int gridSize = 4;

        int batteryPercent = 100;

        boolean robotRunning = true;

        boolean targetFound = false;


        // ==========================================
        // TARGET LOCATION
        // ==========================================

        int targetRow = 3;
        int targetColumn = 2;


        // ==========================================
        // START MISSION
        // ==========================================

        System.out.println("=================================");
        System.out.println("           SEARCH BOT");
        System.out.println("=================================");

        System.out.println("Robot: " + robotName);
        System.out.println("Grid Size: "
                + gridSize + " x " + gridSize);

        System.out.println("Battery: "
                + batteryPercent + "%");

        System.out.println("---------------------------------");
        System.out.println("MISSION START");
        System.out.println("---------------------------------");


        // ==========================================
        // SEARCH THE GRID
        // ==========================================

        /*
         * The outer loop moves through each row.
         */

        for (int row = 1; row <= gridSize; row++) {

            /*
             * The inner loop moves through
             * each column in the current row.
             */

            for (int column = 1;
                 column <= gridSize;
                 column++) {


                // ==================================
                // CHECK ROBOT STATUS
                // ==================================

                if (robotRunning && !targetFound) {

                    System.out.println(
                        "Searching Row " + row
                        + ", Column " + column
                    );


                    // ==============================
                    // CHECK FOR TARGET
                    // ==============================

                    if (row == targetRow
                            && column == targetColumn) {

                        targetFound = true;

                        System.out.println();
                        System.out.println(
                            ">>> TARGET FOUND! <<<"
                        );

                        System.out.println(
                            "Target Location: Row "
                            + row
                            + ", Column "
                            + column
                        );

                        System.out.println();


                    } else {

                        System.out.println(
                            "No target here."
                        );
                    }


                    // ==============================
                    // SIMULATE BATTERY USE
                    // ==============================

                    batteryPercent =
                            batteryPercent - 2;

                    System.out.println(
                        "Battery: "
                        + batteryPercent
                        + "%"
                    );

                    System.out.println();
                }


                // ==================================
                // LOW BATTERY CHECK
                // ==================================

                if (batteryPercent <= 20
                        && !targetFound) {

                    System.out.println(
                        "WARNING: LOW BATTERY!"
                    );

                    System.out.println(
                        "Robot must return home."
                    );

                    robotRunning = false;
                }
            }
        }


        // ==========================================
        // MISSION RESULTS
        // ==========================================

        System.out.println("---------------------------------");
        System.out.println("MISSION COMPLETE");
        System.out.println("---------------------------------");


        if (targetFound) {

            System.out.println(
                "STATUS: SUCCESS"
            );

            System.out.println(
                "Target successfully located."
            );

        } else if (!robotRunning) {

            System.out.println(
                "STATUS: ABORTED"
            );

            System.out.println(
                "Robot stopped before finding target."
            );

        } else {

            System.out.println(
                "STATUS: TARGET NOT FOUND"
            );
        }


        System.out.println(
            "Final Battery: "
            + batteryPercent
            + "%"
        );

        System.out.println("=================================");
        System.out.println("SEARCH BOT SHUTDOWN");
        System.out.println("=================================");
    }
}
