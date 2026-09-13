package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * ============================================================
 * SESSION 7 - THE FTC LOOP (and our real TeleOp)
 * ============================================================
 *
 * FTC ROOKIE TEAM - IRON ANGELS
 *
 * DELIVERABLE: the TeleOp we actually compete with.
 *
 *
 * THE FTC BIG IDEA
 * ============================================================
 *
 *     Java programs normally start at main().
 *
 *     FTC programs work differently.
 *
 *     The FTC Robot Controller calls OUR code:
 *
 *         runOpMode()
 *
 *     We never call it ourselves. We do not write main().
 *     The framework starts our program FOR us.
 *
 *
 * WHO IS CALLING OUR CODE?
 * ============================================================
 *
 *     Driver presses INIT
 *            |
 *            v
 *     FTC calls runOpMode()   <-- our code starts here
 *            |
 *            v
 *     ...our setup code runs...
 *            |
 *            v
 *     waitForStart()          <-- we PAUSE here
 *            |
 *       (driver presses PLAY)
 *            |
 *            v
 *     while (opModeIsActive())
 *            |
 *            v
 *          loop  --+
 *            |     |
 *          loop  <-+   about 50 times per second
 *            |
 *          loop
 *            |
 *           ...
 *            |
 *       (driver presses STOP)
 *            |
 *            v
 *     opModeIsActive() becomes false, loop exits
 *
 *
 * WHY IS THERE A while LOOP?
 * ============================================================
 *
 * Because reading the gamepad ONCE is useless. The driver keeps
 * moving their thumbs, so we have to keep looking.
 *
 * TRY THIS: delete the while loop. The robot will respond to
 * the gamepad exactly once and then sit there forever. Put it
 * back. Now the loop means something.
 *
 *
 * NOTICE WHAT IS NOT HERE
 * ============================================================
 *
 * There is no motor code in this file. Not one setPower().
 *
 * It all lives in Robot.java, and BOTH this TeleOp and our
 * Autonomous use the same Robot class. When we fix a driving
 * bug, we fix it once.
 *
 * That is the payoff for everything we learned about methods
 * and classes.
 *
 * ============================================================
 */
@TeleOp(name = "Iron Angel TeleOp", group = "Iron Angels")
public class S07_IronAngelTeleOp extends LinearOpMode {

    // ========================================================
    // OUR SOFTWARE ROBOT
    // ========================================================

    private Robot robot;


    // ========================================================
    // ROBOT STATE
    // ========================================================
    //
    // These remember things BETWEEN loops.
    //
    // A variable declared inside the loop would be forgotten and
    // recreated every time around. These are declared out here,
    // so they survive.

    private boolean turboMode = false;

    // Needed for the turbo TOGGLE. See updateTurboMode().
    private boolean bWasPressedLastLoop = false;

    private int loopCounter = 0;


    @Override
    public void runOpMode() {

        // ====================================================
        // INIT - runs ONCE, when the driver presses INIT
        // ====================================================

        telemetry.addLine("================================");
        telemetry.addLine("   IRON ANGELS TELEOP");
        telemetry.addLine("================================");
        telemetry.addLine("Initializing robot...");
        telemetry.update();

        // Creating the Robot object finds every motor, servo and
        // sensor. If a name in the robot configuration is wrong,
        // we crash HERE - before the match starts - which is
        // exactly when we want to find out.
        robot = new Robot(hardwareMap, telemetry);

        telemetry.addLine("Initialization complete!");
        telemetry.addLine();
        showDriverControls();
        telemetry.addLine();
        telemetry.addLine("Press PLAY.");
        telemetry.update();


        // ====================================================
        // WAIT FOR THE DRIVER
        // ====================================================
        //
        // The program pauses on this line until PLAY is pressed.

        waitForStart();


        // ====================================================
        // THE DRIVER LOOP
        // ====================================================

        while (opModeIsActive()) {

            loopCounter++;

            // 1. READ THE DRIVER
            double drivePower = -gamepad1.left_stick_y;
            double turnPower  =  gamepad1.right_stick_x;

            // 2. DECIDE
            updateTurboMode();

            if (!turboMode) {
                drivePower = drivePower * 0.5;
                turnPower  = turnPower  * 0.5;
            }

            // 3. COMMAND THE ROBOT
            //
            // Look how readable this is. We are not thinking
            // about motors any more - we are thinking about
            // what the ROBOT should do.
            robot.arcadeDrive(drivePower, turnPower);

            if (gamepad1.left_bumper) {
                robot.startIntake();
            } else if (gamepad1.right_bumper) {
                robot.reverseIntake();
            } else {
                robot.stopIntake();
            }

            if (gamepad1.a) {
                robot.openClaw();
            }

            if (gamepad1.y) {
                robot.closeClaw();
            }

            // 4. SHOW THE DRIVER WHAT IS GOING ON
            showDashboard(drivePower, turnPower);

            // FTC telemetry does not appear on its own.
            // update() is what actually sends it to the Driver
            // Station. Forget this line and your dashboard
            // freezes while the robot keeps driving.
            telemetry.update();
        }


        // ====================================================
        // CLEAN UP
        // ====================================================
        //
        // The OpMode is ending. Leave the robot safe and still.

        robot.stopDriving();
        robot.stopIntake();
    }


    /**
     * TURBO AS A TOGGLE
     * ============================================================
     *
     * The easy version is:
     *
     *     turboMode = gamepad1.b;
     *
     * but then turbo is only on while the driver HOLDS B, which
     * is tiring in a two-minute match.
     *
     * We want: TAP B to switch turbo on, TAP it again to switch
     * it off.
     *
     * The problem: this loop runs ~50 times a second. A human
     * "tap" lasts about 10 loops. So a naive check would flip
     * turbo on and off 10 times and land somewhere random.
     *
     * The fix is to act only on the loop where the button
     * CHANGED from up to down. That is called EDGE DETECTION,
     * and it needs us to remember what the button was doing
     * last loop.
     */
    private void updateTurboMode() {

        boolean bIsPressedNow = gamepad1.b;

        // Pressed now, but NOT pressed last loop => a fresh tap.
        if (bIsPressedNow && !bWasPressedLastLoop) {

            turboMode = !turboMode;
        }

        // Remember for next time around the loop.
        bWasPressedLastLoop = bIsPressedNow;
    }


    /** What the buttons do - shown during INIT. */
    private void showDriverControls() {

        telemetry.addLine("DRIVER CONTROLS");
        telemetry.addLine("  Left stick    = drive / turn");
        telemetry.addLine("  B             = toggle turbo");
        telemetry.addLine("  Left bumper   = intake in");
        telemetry.addLine("  Right bumper  = intake out");
        telemetry.addLine("  A             = open claw");
        telemetry.addLine("  Y             = close claw");
    }


    /**
     * THE DRIVER DASHBOARD
     *
     * Good telemetry answers a question the driver actually has
     * in the middle of a match.
     *
     *     USELESS:  telemetry.addLine("HERE");
     *     USEFUL:   telemetry.addData("Distance (cm)", 14.2);
     *
     * Ask yourself: if the robot misbehaved right now, would
     * this line help me work out why?
     */
    private void showDashboard(double drivePower, double turnPower) {

        telemetry.addLine("======= IRON ANGELS =======");

        telemetry.addData("Mode",
                turboMode ? "TURBO" : "PRECISION");

        telemetry.addData("Drive", "%.2f", drivePower);
        telemetry.addData("Turn", "%.2f", turnPower);

        telemetry.addLine();

        // Ask the Robot about itself - it knows its own motors
        // and sensors, so it is the right place to ask.
        robot.reportStatus();

        telemetry.addLine();

        // Proof that the loop really is running over and over.
        telemetry.addData("Loop count", loopCounter);
    }
}


/*
 * ============================================================
 * STUDENT QUESTIONS - answer these out loud
 * ============================================================
 *
 *   1. Who is calling our code?
 *
 *   2. Why don't we need main()?
 *
 *   3. What happens if we remove the while loop?
 *
 *   4. What does gamepad1 represent?
 *
 *   5. Why do we call telemetry.update()?
 *
 *   6. Why is there no setPower() anywhere in this file?
 *
 *   7. Our Autonomous uses the same Robot class as this TeleOp.
 *      Why is that better than copying the driving code?
 *
 *   8. bWasPressedLastLoop is declared outside the while loop.
 *      What would break if we declared it inside instead?
 *
 *
 * ============================================================
 * CHALLENGES
 * ============================================================
 *
 *   1. Make precision mode 0.3 instead of 0.5. Which is easier
 *      to drive through a gap?
 *
 *   2. Add a control of your own using the D-pad.
 *
 *   3. The triggers are doubles (0.0 to 1.0), not booleans.
 *      Use one to control the intake speed smoothly.
 *
 *   4. Add a "slow mode" on the left trigger that scales drive
 *      power by how far the trigger is pulled.
 *
 *   5. STRETCH: add a deadband so the robot does not creep when
 *      nobody is touching the sticks.
 *
 *
 * ============================================================
 * DEBUGGING CHALLENGE
 * ============================================================
 *
 *   BUG 1: Comment out telemetry.update().
 *          -> The dashboard freezes. Does the robot still drive?
 *          -> What does that tell you about who telemetry is for?
 *
 *   BUG 2: Change updateTurboMode() to just:
 *              turboMode = gamepad1.b;
 *          -> Tap B. What happens? Now HOLD B. What happens?
 *          -> Explain why edge detection was needed.
 *
 *   BUG 3: Move bWasPressedLastLoop INSIDE the while loop as a
 *          local variable starting at false.
 *          -> Does the toggle still work? Why not?
 *
 *   BUG 4: Move robot.arcadeDrive(...) to AFTER the while loop.
 *          -> When does the robot drive now?
 *
 *   BUG 5: Delete robot.stopDriving() at the end.
 *          -> Press STOP while driving. What does the robot do?
 *          -> (The SDK will stop the motors for you here, but
 *             never rely on that. Stop your own motors.)
 *
 * ============================================================
 */
