package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * ============================================================
 * SESSION 6 - DRIVER CONTROL
 * ============================================================
 *
 * THE ROBOT HOOK
 *
 *     Last week you made the robot move at a power you TYPED.
 *     Today the DRIVER chooses the power.
 *
 *     That is the whole lesson. One line.
 *
 *
 * WHAT WE ALREADY KNOW
 * ============================================================
 *
 *   Blocks                      Java
 *   -----------------------     -----------------------------
 *   set leftDrive power to 0.5  leftDrive.setPower(0.5);
 *   gamepad1 left stick Y       gamepad1.left_stick_y
 *   -----------------------     -----------------------------
 *   ...and today: CONNECT THEM.
 *
 *
 * THE NEW IDEA
 * ============================================================
 *
 *     A joystick is a double that changes every single loop.
 *
 * Three things to discover by experiment (not by being told):
 *
 *   1. What is the value when you are NOT touching the stick?
 *        -> about 0.0 ... but maybe 0.02. That is called DRIFT.
 *
 *   2. Push the stick FORWARD. Positive or negative?
 *        -> NEGATIVE. This surprises everyone.
 *
 *   3. What is the biggest value you can get?
 *        -> about 1.0 - which is exactly what setPower() wants.
 *
 *
 * WHY THE MINUS SIGN?
 * ============================================================
 *
 *     double drivePower = -gamepad1.left_stick_y;
 *     //                  ^ ^        ^
 *     //                  | |        the stick's value right now
 *     //                  | the gamepad
 *     //                  FLIP THE SIGN
 *
 * On a gamepad, pushing forward gives a NEGATIVE number. That
 * is just how the hardware is wired.
 *
 * But setPower(1.0) means FORWARD.
 *
 * So if we hand the stick straight to the motor, pushing
 * forward drives the robot backward. We fix it with a minus
 * sign - and that minus sign is the single most common bug in
 * rookie FTC code.
 *
 * ============================================================
 */
@TeleOp(name = "S06 Driver Control", group = "Training")
public class S06_DriverControl extends LinearOpMode {

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intake;


    @Override
    public void runOpMode() {

        // ----------------------------------------------------
        // INIT - find the hardware (Session 5)
        // ----------------------------------------------------

        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        intake     = hardwareMap.get(DcMotor.class, "intake");

        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Iron Angels ready.");
        telemetry.addLine("Left stick = drive");
        telemetry.addLine("Hold B      = turbo");
        telemetry.addLine("Left bumper = intake");
        telemetry.addLine("Press PLAY.");
        telemetry.update();

        waitForStart();


        // ----------------------------------------------------
        // THE DRIVER LOOP
        // ----------------------------------------------------
        //
        // Everything inside here happens over and over, about
        // 50 times a second, until the driver presses STOP.
        //
        // That is what makes the robot feel "alive" - it keeps
        // re-reading the gamepad and re-deciding what to do.

        while (opModeIsActive()) {

            // 1. READ THE DRIVER
            //    Forward on the stick is NEGATIVE, so flip it.
            double drivePower = -gamepad1.left_stick_y;
            double turnPower  =  gamepad1.right_stick_x;


            // 2. DECIDE
            //    Precision mode unless the driver holds B.
            //    Half power is much easier to drive accurately.
            boolean turbo = gamepad1.b;

            if (!turbo) {
                drivePower = drivePower * 0.5;
                turnPower  = turnPower  * 0.5;
            }


            // 3. COMMAND THE MOTORS
            //    THIS is the line the whole course was building
            //    toward. The driver's thumb is now connected to
            //    the wheels.
            //
            //    Adding turnPower to one side and subtracting it
            //    from the other is what makes the robot curve.
            leftDrive.setPower(clamp(drivePower + turnPower));
            rightDrive.setPower(clamp(drivePower - turnPower));


            // 4. THE INTAKE
            //    A button is a boolean: true while held.
            if (gamepad1.left_bumper) {
                intake.setPower(1.0);
            } else {
                intake.setPower(0.0);
            }


            // 5. SHOW OUR WORK
            //    The robot does not need telemetry. YOU need
            //    telemetry. This is your debugging tool.
            telemetry.addData("Mode", turbo ? "TURBO" : "PRECISION");
            telemetry.addData("Drive", "%.2f", drivePower);
            telemetry.addData("Turn", "%.2f", turnPower);
            telemetry.addData("Intake", gamepad1.left_bumper);
            telemetry.update();
        }


        // ----------------------------------------------------
        // ALWAYS STOP THE MOTORS WHEN THE OPMODE ENDS
        // ----------------------------------------------------

        leftDrive.setPower(0);
        rightDrive.setPower(0);
        intake.setPower(0);
    }


    /**
     * Force a power value to stay between -1.0 and 1.0.
     *
     * WHY THIS EXISTS:
     *
     *     drivePower + turnPower can add up to more than 1.0.
     *     If you push the stick fully forward AND fully right,
     *     you get 1.0 + 1.0 = 2.0.
     *
     *     setPower() silently uses 1.0 instead of crashing, so
     *     the robot still works - it just turns unevenly, and
     *     you spend an hour wondering why.
     *
     *     Clamping makes the behaviour predictable.
     */
    private double clamp(double power) {

        if (power > 1.0) {
            return 1.0;
        }

        if (power < -1.0) {
            return -1.0;
        }

        return power;
    }
}


/*
 * ============================================================
 * TEAM CODING CHALLENGES
 * ============================================================
 *
 * Robot on blocks until Challenge 1 is checked off.
 *
 *
 * CHALLENGE 1 - DRIVE IT
 *
 *     Type it in. Drive forward and backward with the left
 *     stick. Everyone gets here.
 *
 *
 * CHALLENGE 2 - PRECISION MODE
 *
 *     Already in the code above. Now change it:
 *     make precision mode 0.25 instead of 0.5.
 *     Which is easier to drive?
 *
 *
 * CHALLENGE 3 - TURNING
 *
 *     Already in the code. Now figure out WHY it works.
 *
 *     Draw the robot on paper. Push the right stick right.
 *     turnPower is positive. So the left motor gets MORE power
 *     and the right motor gets LESS.
 *
 *     Which way does the robot turn? Does that match what the
 *     robot actually does?
 *
 *
 * CHALLENGE 4 - ADD A CONTROL OF YOUR OWN
 *
 *     Ideas:
 *       - right bumper reverses the intake (to spit out)
 *       - dpad_up runs the intake slowly
 *       - the triggers control intake speed smoothly
 *         (a trigger is a double from 0.0 to 1.0, not a boolean!)
 *
 *
 * CHALLENGE 5 - DEADBAND (stretch)
 *
 *     Does your robot creep forward when nobody touches it?
 *     That is stick drift.
 *
 *     Fix it: if the stick value is very small, treat it as 0.
 *
 *         if (Math.abs(drivePower) < 0.05) {
 *             drivePower = 0;
 *         }
 *
 *
 * ============================================================
 * DEBUGGING CHALLENGE
 * ============================================================
 *
 * Your coach will break ONE thing in your working TeleOp
 * without telling you which. Find it using the process:
 *
 *     1. OBSERVE     What exactly happened?
 *     2. REPRODUCE   Can you make it happen again?
 *     3. DESCRIBE    Say it out loud to your partner.
 *     4. LOCATE      Which line could cause THAT?
 *     5. CHANGE ONE  One thing. Not three.
 *     6. TEST        Did it fix it?
 *     7. EXPLAIN     Why did that work?
 *
 * The bugs your coach will choose from:
 *
 *   BUG                                  SYMPTOM
 *   ---------------------------------    ------------------------
 *   minus sign removed from drivePower   forward drives backward
 *   rightDrive gets -drivePower          robot spins in place
 *   telemetry.update() deleted           dashboard frozen,
 *                                        robot drives fine
 *   setPower moved outside the while     responds once, then dead
 *   "leftDrive" misspelled in config     crash at INIT
 *   clamp() returns power unchanged      uneven turns at full stick
 *
 * A ROBOT DOING THE WRONG THING IS NOT A FAILURE. It is a bug.
 * Bugs have causes, and causes can be found. Every line of FTC
 * code you will ever admire was broken first.
 *
 *
 * ============================================================
 * COACH CHECKOFF
 * ============================================================
 *
 *   [ ] Robot drives forward and back from the stick
 *   [ ] Student explains the minus sign
 *   [ ] Precision mode works
 *   [ ] Student explains why setPower is INSIDE the while loop
 *   [ ] Telemetry shows drive power changing as the stick moves
 *   [ ] Student found and fixed their assigned bug
 *   [ ] Student described the bug using the 7-step process
 *   [ ] Student explained their code to another pair
 *   [ ] (Stretch) Turning works and student can explain it
 *   [ ] (Stretch) Added a control of their own design
 *
 * PASS = the first 8.
 *
 * ============================================================
 */
