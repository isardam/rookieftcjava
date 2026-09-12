 /*
 * ============================================================
 * SESSION 7 — WELCOME TO FTC JAVA
 * ============================================================
 *
 * FTC ROOKIE TEAM — IRON ANGELS
 *
 * DELIVERABLE:
 *
 *     IronAngelTeleOp.java
 *
 *
 * JAVA CONCEPTS:
 *
 *     OpMode
 *     @TeleOp
 *     init()
 *     loop()
 *     gamepad1
 *     telemetry
 *     boolean variables
 *     if / else
 *     methods
 *     class members
 *
 *
 * FTC BIG IDEA:
 *
 *     Java programs normally start at main().
 *
 *     FTC programs work differently.
 *
 *     The FTC Robot Controller calls:
 *
 *         init()
 *         loop()
 *
 *
 *     The robot controller is responsible for
 *     repeatedly running our code.
 *
 * ============================================================
 *
 * CHALLENGE SEQUENCE
 *
 *     Challenge 1  - Meet the OpMode
 *     Challenge 2  - Add Telemetry
 *     Challenge 3  - Read the Joystick
 *     Challenge 4  - Read Buttons
 *     Challenge 5  - Create Robot Modes
 *     Challenge 6  - Build Turbo Mode
 *     Challenge 7  - Create Helper Methods
 *     Challenge 8  - Build the Dashboard
 *     Challenge 9  - Add a Driver Message
 *     Challenge 10 - Final Team Challenge
 *
 * ============================================================
 *
 * IMPORTANT:
 *
 *     Run ONE challenge at a time.
 *
 *     In Android Studio, comment/uncomment the call
 *     inside loop() to activate the challenge you want.
 *
 * ============================================================
 */


package org.firstinspires.ftc.teamcode;


/*
 * FTC SDK IMPORTS
 *
 * These classes come from the FTC SDK.
 */

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/*
 * ============================================================
 * TELEOP DECLARATION
 * ============================================================
 *
 * @TeleOp tells the FTC Driver Station:
 *
 *     "This is a TeleOp program."
 *
 * The name appears on the Driver Station.
 *
 * ============================================================
 */

@TeleOp(
        name = "Iron Angel TeleOp",
        group = "Iron Angels"
)


public class IronAngelTeleOp extends LinearOpMode {


    /*
     * ========================================================
     * ROBOT STATE
     * ========================================================
     *
     * These are CLASS MEMBERS.
     *
     * They remember information while the OpMode is running.
     *
     * ========================================================
     */


    // Current robot mode

    private String robotMode = "NORMAL";


    // Whether turbo mode is active

    private boolean turboMode = false;


    // Number of loops completed

    private int loopCounter = 0;


    /*
     * ========================================================
     * CHALLENGE 1
     *
     * UNDERSTAND THE OPMODE
     * ========================================================
     *
     * FTC calls runOpMode().
     *
     * Unlike our earlier Java programs, we don't need:
     *
     *     public static void main()
     *
     * The FTC Robot Controller starts our program.
     *
     * ========================================================
     */


    @Override
    public void runOpMode() {


        /*
         * ====================================================
         * INIT
         * ====================================================
         *
         * This section runs ONCE before the driver presses
         * PLAY.
         *
         * Think:
         *
         *     "Prepare the robot."
         *
         * ====================================================
         */


        telemetry.addLine(
                "================================"
        );

        telemetry.addLine(
                "   IRON ANGELS TELEOP"
        );

        telemetry.addLine(
                "================================"
        );

        telemetry.addLine(
                "Robot is initializing..."
        );

        telemetry.update();


        /*
         * ====================================================
         * TODO — FUTURE HARDWARE
         * ====================================================
         *
         * Later we will initialize:
         *
         *     motors
         *     servos
         *     sensors
         *
         * Example:
         *
         *     leftMotor =
         *         hardwareMap.get(
         *             DcMotor.class,
         *             "leftMotor"
         *         );
         *
         * We are intentionally NOT using hardware yet.
         *
         * Today's goal is learning the FTC programming model.
         *
         * ====================================================
         */


        telemetry.addLine(
                "Initialization complete!"
        );

        telemetry.addLine(
                "Press PLAY."
        );

        telemetry.update();


        /*
         * ====================================================
         * WAIT FOR DRIVER
         * ====================================================
         *
         * The program pauses here until the driver presses PLAY.
         * ====================================================
         */

        waitForStart();


        /*
         * ====================================================
         * LOOP
         * ====================================================
         *
         * FTC now repeatedly executes this section.
         *
         * This is one of the most important ideas in FTC Java.
         *
         * loop
         * loop
         * loop
         * loop
         * loop
         * ...
         *
         * until the driver stops the OpMode.
         *
         * ====================================================
         */

        while (opModeIsActive()) {


            /*
             * Count how many times the loop has run.
             */

            loopCounter++;


            /*
             * =================================================
             * CHALLENGE SELECTION
             * =================================================
             *
             * Run ONE challenge at a time.
             *
             * Uncomment ONE method call.
             *
             * =================================================
             */


            // CHALLENGE 1
            // challenge1();


            // CHALLENGE 2
            // challenge2();


            // CHALLENGE 3
            // challenge3();


            // CHALLENGE 4
            // challenge4();


            // CHALLENGE 5
            // challenge5();


            // CHALLENGE 6
            // challenge6();


            // CHALLENGE 7
            // challenge7();


            // CHALLENGE 8
            // challenge8();


            // CHALLENGE 9
            // challenge9();


            // FINAL CHALLENGE
            // challenge10();


            /*
             * =================================================
             * DEFAULT
             * =================================================
             *
             * Until students choose a challenge, show the
             * basic FTC dashboard.
             * =================================================
             */

            basicDashboard();


            /*
             * FTC telemetry does not automatically appear.
             *
             * update() sends our telemetry to the Driver Station.
             */

            telemetry.update();
        }
    }


    /*
     * ============================================================
     * CHALLENGE 1
     *
     * MEET THE OPMODE
     * ============================================================
     *
     * GOAL:
     *
     * Understand:
     *
     *     runOpMode()
     *     waitForStart()
     *     opModeIsActive()
     *
     * ============================================================
     */

    private void challenge1() {


        telemetry.addLine(
                "CHALLENGE 1"
        );

        telemetry.addLine(
                "Welcome to FTC Java!"
        );

        telemetry.addLine();
        
        telemetry.addLine(
                "The FTC Robot Controller"
        );

        telemetry.addLine(
                "is running your Java program."
        );

        telemetry.addLine();

        telemetry.addData(
                "Loop Count",
                loopCounter
        );


        /*
         * STUDENT QUESTION:
         *
         * Does loopCounter keep increasing?
         *
         * Why?
         */
    }


    /*
     * ============================================================
     * CHALLENGE 2
     *
     * TELEMETRY
     * ============================================================
     *
     * GOAL:
     *
     * Learn to send information to the Driver Station.
     *
     * ============================================================
     */

    private void challenge2() {


        telemetry.addLine(
                "CHALLENGE 2 — TELEMETRY"
        );


        /*
         * TODO:
         *
         * Add your own telemetry.
         *
         * Example:
         *
         * telemetry.addData(
         *     "Team",
         *     "Iron Angels"
         * );
         */


        /*
         * TODO:
         *
         * Display the current robot mode.
         */


        /*
         * TODO:
         *
         * Display the loop counter.
         */


        telemetry.addLine();

        telemetry.addLine(
                "Press buttons and watch"
        );

        telemetry.addLine(
                "the Driver Station."
        );
    }


    /*
     * ============================================================
     * CHALLENGE 3
     *
     * READ THE JOYSTICK
     * ============================================================
     *
     * gamepad1.left_stick_y
     *
     * returns a double.
     *
     * Typical values:
     *
     *     -1.0
     *      0.0
     *      1.0
     *
     * ============================================================
     */

    private void challenge3() {


        double leftY =
                gamepad1.left_stick_y;


        double rightX =
                gamepad1.right_stick_x;


        telemetry.addLine(
                "CHALLENGE 3 — JOYSTICKS"
        );


        telemetry.addData(
                "Left Stick Y",
                leftY
        );


        telemetry.addData(
                "Right Stick X",
                rightX
        );


        /*
         * TODO:
         *
         * Add:
         *
         *     left_stick_x
         *
         *     right_stick_y
         *
         * to telemetry.
         */


        telemetry.addLine();

        telemetry.addLine(
                "Move the joysticks!"
        );
    }


    /*
     * ============================================================
     * CHALLENGE 4
     *
     * READ BUTTONS
     * ============================================================
     *
     * Gamepad buttons are boolean values.
     *
     *     true
     *     false
     *
     * ============================================================
     */

    private void challenge4() {


        telemetry.addLine(
                "CHALLENGE 4 — BUTTONS"
        );


        telemetry.addData(
                "A",
                gamepad1.a
        );


        telemetry.addData(
                "B",
                gamepad1.b
        );


        telemetry.addData(
                "X",
                gamepad1.x
        );


        telemetry.addData(
                "Y",
                gamepad1.y
        );


        telemetry.addLine();


        telemetry.addData(
                "DPad Up",
                gamepad1.dpad_up
        );


        telemetry.addData(
                "DPad Down",
                gamepad1.dpad_down
        );


        telemetry.addData(
                "DPad Left",
                gamepad1.dpad_left
        );


        telemetry.addData(
                "DPad Right",
                gamepad1.dpad_right
        );


        /*
         * TODO:
         *
         * Add the left and right bumper
         * values to telemetry.
         */
    }


    /*
     * ============================================================
     * CHALLENGE 5
     *
     * ROBOT MODES
     * ============================================================
     *
     * We will use:
     *
     *     A = NORMAL
     *
     *     B = TURBO
     *
     * ============================================================
     */

    private void challenge5() {


        /*
         * TODO:
         *
         * If A is pressed:
         *
         *     robotMode = "NORMAL";
         *
         *
         * If B is pressed:
         *
         *     robotMode = "TURBO";
         *
         */


        telemetry.addLine(
                "CHALLENGE 5 — ROBOT MODES"
        );


        telemetry.addData(
                "Current Mode",
                robotMode
        );


        telemetry.addLine();

        telemetry.addLine(
                "A = NORMAL"
        );

        telemetry.addLine(
                "B = TURBO"
        );
    }


    /*
     * ============================================================
     * CHALLENGE 6
     *
     * TURBO MODE
     * ============================================================
     *
     * Students learn that joystick values can be processed
     * before being used by motors.
     *
     * ============================================================
     */

    private void challenge6() {


        double drivePower =
                -gamepad1.left_stick_y;


        /*
         * TODO:
         *
         * If B is pressed:
         *
         *     turboMode = true;
         *
         * Otherwise:
         *
         *     turboMode = false;
         */


        /*
         * TODO:
         *
         * If turboMode is false:
         *
         *     reduce drivePower
         *
         * Example:
         *
         *     drivePower = drivePower * 0.5;
         */


        telemetry.addLine(
                "CHALLENGE 6 — TURBO"
        );


        telemetry.addData(
                "Joystick Power",
                drivePower
        );


        telemetry.addData(
                "Turbo",
                turboMode
        );
    }


    /*
     * ============================================================
     * CHALLENGE 7
     *
     * HELPER METHODS
     * ============================================================
     *
     * Remember Session 5?
     *
     * Methods allow us to organize reusable code.
     *
     * ============================================================
     */

    private void challenge7() {


        showDriverControls();


        showRobotMode();


        showJoystickValues();
    }


    /*
     * ============================================================
     * HELPER METHOD
     *
     * DRIVER CONTROLS
     * ============================================================
     */

    private void showDriverControls() {


        telemetry.addLine(
                "DRIVER CONTROLS"
        );


        telemetry.addLine(
                "A = Normal Mode"
        );


        telemetry.addLine(
                "B = Turbo Mode"
        );


        telemetry.addLine(
                "X = Team Message"
        );
    }


    /*
     * ============================================================
     * HELPER METHOD
     *
     * ROBOT MODE
     * ============================================================
     */

    private void showRobotMode() {


        telemetry.addData(
                "Robot Mode",
                robotMode
        );


        telemetry.addData(
                "Turbo",
                turboMode
        );
    }


    /*
     * ============================================================
     * HELPER METHOD
     *
     * JOYSTICKS
     * ============================================================
     */

    private void showJoystickValues() {


        telemetry.addData(
                "Left Y",
                gamepad1.left_stick_y
        );


        telemetry.addData(
                "Right X",
                gamepad1.right_stick_x
        );
    }


    /*
     * ============================================================
     * CHALLENGE 8
     *
     * BUILD THE DASHBOARD
     * ============================================================
     *
     * Combine:
     *
     *     telemetry
     *     gamepad
     *     methods
     *     state
     *
     * ============================================================
     */

    private void challenge8() {


        /*
         * TODO:
         *
         * Build a useful driver dashboard.
         *
         * It should show:
         *
         *     Team
         *     Robot Mode
         *     Turbo Mode
         *     Loop Count
         *     Left Stick
         *     Right Stick
         *     A
         *     B
         *     X
         *     Y
         */


        telemetry.addLine(
                "================================"
        );


        telemetry.addLine(
                "       IRON ANGELS"
        );


        telemetry.addLine(
                "      DRIVER DASHBOARD"
        );


        telemetry.addLine(
                "================================"
        );


        // TODO:
        //
        // Add your telemetry here.
    }


    /*
     * ============================================================
     * CHALLENGE 9
     *
     * DRIVER MESSAGE
     * ============================================================
     *
     * Use a button to display a special message.
     *
     * ============================================================
     */

    private void challenge9() {


        telemetry.addLine(
                "CHALLENGE 9 — DRIVER MESSAGE"
        );


        if (gamepad1.x) {


            telemetry.addLine(
                    "IRON ANGELS — READY TO BUILD!"
            );


        } else {


            telemetry.addLine(
                    "Press X for a team message."
            );
        }


        telemetry.addData(
                "Loop",
                loopCounter
        );
    }


    /*
     * ============================================================
     * CHALLENGE 10
     *
     * 🚀 FINAL TEAM CHALLENGE
     * ============================================================
     *
     * BUILD THE COMPLETE DRIVER DASHBOARD.
     *
     * REQUIREMENTS:
     *
     * 1. Display team name.
     *
     * 2. Display loop count.
     *
     * 3. Display joystick values.
     *
     * 4. Display A/B/X/Y.
     *
     * 5. Create NORMAL mode.
     *
     * 6. Create TURBO mode.
     *
     * 7. Display the current mode.
     *
     * 8. Use at least TWO helper methods.
     *
     * 9. Create one additional feature.
     *
     *
     * BONUS:
     *
     * Add:
     *
     *     left/right trigger
     *     bumpers
     *     D-pad
     *
     * or create your own robot state.
     *
     * ============================================================
     */

    private void challenge10() {


        /*
         * ====================================================
         * MODE CONTROL
         * ====================================================
         *
         * TODO:
         *
         * A -> NORMAL
         *
         * B -> TURBO
         *
         * ====================================================
         */


        if (gamepad1.a) {

            robotMode = "NORMAL";

            turboMode = false;
        }


        if (gamepad1.b) {

            robotMode = "TURBO";

            turboMode = true;
        }


        /*
         * ====================================================
         * DASHBOARD
         * ====================================================
         */

        telemetry.addLine(
                "================================"
        );


        telemetry.addLine(
                "       IRON ANGELS"
        );


        telemetry.addLine(
                "       FINAL DASHBOARD"
        );


        telemetry.addLine(
                "================================"
        );


        telemetry.addData(
                "Mode",
                robotMode
        );


        telemetry.addData(
                "Turbo",
                turboMode
        );


        telemetry.addData(
                "Loop Count",
                loopCounter
        );


        telemetry.addLine();


        /*
         * ====================================================
         * JOYSTICKS
         * ====================================================
         */

        telemetry.addData(
                "Left Stick X",
                gamepad1.left_stick_x
        );


        telemetry.addData(
                "Left Stick Y",
                gamepad1.left_stick_y
        );


        telemetry.addData(
                "Right Stick X",
                gamepad1.right_stick_x
        );


        telemetry.addData(
                "Right Stick Y",
                gamepad1.right_stick_y
        );


        telemetry.addLine();


        /*
         * ====================================================
         * BUTTONS
         * ====================================================
         */

        telemetry.addData(
                "A",
                gamepad1.a
        );


        telemetry.addData(
                "B",
                gamepad1.b
        );


        telemetry.addData(
                "X",
                gamepad1.x
        );


        telemetry.addData(
                "Y",
                gamepad1.y
        );


        telemetry.addLine();


        /*
         * ====================================================
         * BONUS CONTROLS
         * ====================================================
         */

        telemetry.addData(
                "Left Bumper",
                gamepad1.left_bumper
        );


        telemetry.addData(
                "Right Bumper",
                gamepad1.right_bumper
        );


        telemetry.addData(
                "Left Trigger",
                gamepad1.left_trigger
        );


        telemetry.addData(
                "Right Trigger",
                gamepad1.right_trigger
        );


        telemetry.addLine();


        /*
         * ====================================================
         * FINAL TEAM MESSAGE
         * ====================================================
         */

        if (gamepad1.x) {

            telemetry.addLine(
                    "IRON ANGELS — BUILD WITH PURPOSE!"
            );
        }


        /*
         * ====================================================
         * FINAL STUDENT QUESTION
         * ====================================================
         *
         * Ask the team:
         *
         * "What happens if we remove the while loop?"
         *
         * "Who is calling our code?"
         *
         * "Why don't we need main()?"
         *
         * "What does gamepad1 represent?"
         *
         * "Why do we call telemetry.update()?"
         *
         * ====================================================
         */
    }


    /*
     * ============================================================
     * BASIC DASHBOARD
     * ============================================================
     *
     * This is displayed when students have not selected
     * a challenge yet.
     *
     * ============================================================
     */

    private void basicDashboard() {


        telemetry.addLine(
                "================================"
        );


        telemetry.addLine(
                "       IRON ANGELS TELEOP"
        );


        telemetry.addLine(
                "================================"
        );


        telemetry.addData(
                "Status",
                "Running"
        );


        telemetry.addData(
                "Loop Count",
                loopCounter
        );


        telemetry.addData(
                "Robot Mode",
                robotMode
        );


        telemetry.addLine();


        telemetry.addLine(
                "Choose a challenge in the code."
        );
    }
} 
