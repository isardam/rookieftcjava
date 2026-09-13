package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * ============================================================
 * THE ROBOT CLASS
 * ============================================================
 *
 * BIG IDEA:
 *
 *     This is a SOFTWARE ROBOT that knows how to control our
 *     REAL robot.
 *
 * Everything that touches a motor, a servo, or a sensor lives
 * in HERE. Nothing else in our code is allowed to talk to the
 * hardware directly.
 *
 * WHY DO WE WANT THAT?
 *
 *     Our TeleOp needs to drive.
 *     Our Autonomous needs to drive.
 *
 *     Without this class, the motor code would be copy-pasted
 *     into both - and when we fix a bug in one, we would forget
 *     to fix it in the other. (This happens to every rookie
 *     team exactly once, and it costs them a match.)
 *
 *     With this class, both programs say:
 *
 *         robot.driveForward(0.5);
 *
 *     and there is only ONE place that code lives.
 *
 *
 * THE LAYERS
 * ============================================================
 *
 *     Our OpMode  (TeleOp / Autonomous)
 *          |
 *          v
 *     Robot       (this file - "driveForward", "openClaw")
 *          |
 *          v
 *     DcMotor / Servo / DistanceSensor   (the FTC SDK)
 *          |
 *          v
 *     Actual metal that moves
 *
 * ============================================================
 *
 * ROBOT CONFIGURATION
 * ============================================================
 *
 * The names in quotes below MUST match the names in the robot
 * configuration file on the Robot Controller phone / Control Hub.
 *
 *     "leftDrive"       left drive motor
 *     "rightDrive"      right drive motor
 *     "intake"          intake motor
 *     "claw"            claw servo
 *     "distance"        distance sensor
 *
 * If a name does not match, the OpMode CRASHES when you press
 * INIT, and the Driver Station will say something like:
 *
 *     "Unable to find a hardware device with name leftDrive"
 *
 * That error message is your friend. It is telling you exactly
 * what to fix.
 *
 * ============================================================
 */
public class Robot {

    // ========================================================
    // HARDWARE
    // ========================================================
    //
    // These are PRIVATE so that no other file can reach in and
    // change a motor behind our back. If some other code wants
    // the robot to drive, it has to ask us nicely by calling
    // driveForward().

    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor intake;
    private Servo claw;
    private DistanceSensor distanceSensor;

    private Telemetry telemetry;

    // If an Autonomous hands us its OpMode, we can check whether
    // the driver pressed STOP in the middle of a long movement.
    // In TeleOp this stays null, which is fine.
    private LinearOpMode opMode;


    // ========================================================
    // ENCODER MATH
    // ========================================================
    //
    // An encoder counts "ticks" as the motor turns. To drive a
    // distance, we need to know how many ticks make one inch.
    //
    //     TICKS_PER_REVOLUTION  comes from the motor's spec sheet
    //     WHEEL_DIAMETER_INCHES comes from measuring the wheel
    //
    // CALIBRATE THIS! Tell the robot to drive 24 inches, measure
    // what it ACTUALLY drove with a tape measure, and adjust.
    // Every drivetrain is a little different.

    private static final double TICKS_PER_REVOLUTION = 537.7;
    private static final double WHEEL_DIAMETER_INCHES = 3.78;

    private static final double TICKS_PER_INCH =
            TICKS_PER_REVOLUTION /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    // Claw servo positions, found by experimenting.
    private static final double CLAW_OPEN = 0.7;
    private static final double CLAW_CLOSED = 0.2;

    // How close something has to be before we call it "detected".
    private static final double TARGET_DISTANCE_CM = 20.0;


    // ========================================================
    // CONSTRUCTOR - used by TeleOp
    // ========================================================
    //
    // A CONSTRUCTOR runs once, when we write:
    //
    //     Robot robot = new Robot(hardwareMap, telemetry);
    //
    // Its job is to find every piece of hardware and get the
    // robot ready to drive.
    //
    // ========================================================

    public Robot(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;

        // hardwareMap is the FTC SDK's phone book. We give it a
        // NAME and a TYPE, and it hands back the real device.
        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        intake     = hardwareMap.get(DcMotor.class, "intake");
        claw       = hardwareMap.get(Servo.class, "claw");

        distanceSensor =
                hardwareMap.get(DistanceSensor.class, "distance");

        // The two drive motors are mounted facing OPPOSITE
        // directions on the robot. Without this line, telling
        // both to go "forward" makes the robot spin in place.
        rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // BRAKE makes the robot stop crisply instead of coasting.
        leftDrive.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(
                DcMotor.ZeroPowerBehavior.BRAKE);

        // Start from a known state: encoders at zero, robot still.
        resetDriveEncoders();
        stopDriving();
    }


    // ========================================================
    // CONSTRUCTOR - used by Autonomous
    // ========================================================
    //
    // Same as above, but we also keep a reference to the OpMode
    // so long movements can be interrupted if the driver presses
    // STOP.
    //
    // (Two constructors with the same name is OVERLOADING -
    //  the same idea as moveForward() and moveForward(3).)
    //
    // ========================================================

    public Robot(LinearOpMode opMode) {

        this(opMode.hardwareMap, opMode.telemetry);

        this.opMode = opMode;
    }


    // ========================================================
    // DRIVING - the simple commands
    // ========================================================

    /** Drive straight forward. power is 0.0 to 1.0. */
    public void driveForward(double power) {

        setDrivePower(power, power);
    }


    /** Drive straight backward. power is 0.0 to 1.0. */
    public void driveBackward(double power) {

        setDrivePower(-power, -power);
    }


    /** Spin in place to the right. */
    public void turnRight(double power) {

        setDrivePower(power, -power);
    }


    /** Spin in place to the left. */
    public void turnLeft(double power) {

        setDrivePower(-power, power);
    }


    /** Stop both drive motors. */
    public void stopDriving() {

        setDrivePower(0, 0);
    }


    /**
     * Drive and turn at the same time - this is what TeleOp uses.
     *
     * @param drive  forward/backward amount, -1.0 to 1.0
     * @param turn   left/right amount,       -1.0 to 1.0
     */
    public void arcadeDrive(double drive, double turn) {

        setDrivePower(drive + turn, drive - turn);
    }


    // ========================================================
    // THE ONE PLACE MOTOR POWER IS EVER SET
    // ========================================================
    //
    // Every driving method above calls THIS method.
    //
    // Why? Because this is where we make the power SAFE.
    //
    // A motor only understands -1.0 to 1.0. If we hand it 1.7
    // it will silently use 1.0 instead - which means the robot
    // turns unevenly and you spend an hour wondering why.
    // arcadeDrive() can easily produce 1.7, so we clamp here.
    //
    // ========================================================

    private void setDrivePower(double leftPower, double rightPower) {

        leftDrive.setPower(clamp(leftPower));
        rightDrive.setPower(clamp(rightPower));
    }


    /** Force a number to stay between -1.0 and 1.0. */
    private double clamp(double power) {

        if (power > 1.0) {
            return 1.0;
        }

        if (power < -1.0) {
            return -1.0;
        }

        return power;
    }


    // ========================================================
    // DRIVING AN EXACT DISTANCE (encoders)
    // ========================================================
    //
    // driveForward(0.5) drives forward until we tell it to stop.
    // driveInches(24, ...) drives forward EXACTLY 24 inches.
    //
    // Autonomous needs the second one.
    //
    // NOTE THE TIMEOUT. If the robot drives into a wall, the
    // encoders stop counting up and isBusy() stays true forever.
    // Without a timeout, autonomous would hang and we would score
    // nothing. ALWAYS give a blocking movement a timeout.
    //
    // ========================================================

    public void driveInches(
            double inches,
            double power,
            double timeoutSeconds) {

        int ticks = (int) (inches * TICKS_PER_INCH);

        resetDriveEncoders();

        leftDrive.setTargetPosition(ticks);
        rightDrive.setTargetPosition(ticks);

        leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // RUN_TO_POSITION ignores the SIGN of the power, so we
        // always hand it a positive number. Direction comes from
        // the target position being negative.
        setDrivePower(Math.abs(power), Math.abs(power));

        ElapsedTime timer = new ElapsedTime();

        while (isBusyDriving() && timer.seconds() < timeoutSeconds) {

            if (shouldStop()) {
                break;
            }

            telemetry.addData("Driving to", "%.1f in", inches);
            telemetry.addData("Left ticks", leftDrive.getCurrentPosition());
            telemetry.addData("Right ticks", rightDrive.getCurrentPosition());
            telemetry.update();
        }

        stopDriving();

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    /**
     * Turn for a set amount of time. This is the simple rookie
     * way to turn. It is not perfectly accurate - battery level
     * changes it - but it is good enough to start, and it needs
     * no extra sensors.
     *
     * (Next season: use the IMU for accurate turns.)
     */
    public void turnRightForSeconds(double power, double seconds) {

        turnRight(power);

        waitForSeconds(seconds);

        stopDriving();
    }


    public void turnLeftForSeconds(double power, double seconds) {

        turnLeft(power);

        waitForSeconds(seconds);

        stopDriving();
    }


    private boolean isBusyDriving() {

        return leftDrive.isBusy() || rightDrive.isBusy();
    }


    private void resetDriveEncoders() {

        leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    // ========================================================
    // INTAKE AND CLAW
    // ========================================================

    public void startIntake() {

        intake.setPower(1.0);
    }


    public void reverseIntake() {

        intake.setPower(-1.0);
    }


    public void stopIntake() {

        intake.setPower(0);
    }


    public void openClaw() {

        claw.setPosition(CLAW_OPEN);
    }


    public void closeClaw() {

        claw.setPosition(CLAW_CLOSED);
    }


    // ========================================================
    // SENSORS - methods that ANSWER a question
    // ========================================================
    //
    // Notice these RETURN something instead of DOING something.
    //
    //     driveForward()  ->  does a thing
    //     seesTarget()    ->  answers a question
    //
    // ========================================================

    /** How far away is the nearest thing, in centimetres? */
    public double getDistanceCm() {

        return distanceSensor.getDistance(DistanceUnit.CM);
    }


    /** Is something close enough to count as the target? */
    public boolean seesTarget() {

        return getDistanceCm() < TARGET_DISTANCE_CM;
    }


    // ========================================================
    // TELEMETRY
    // ========================================================
    //
    // GOOD telemetry answers a question the driver actually has.
    //
    //     BAD:  telemetry.addLine("HERE");
    //     GOOD: telemetry.addData("Distance (cm)", 14.2);
    //
    // ========================================================

    public void reportStatus() {

        telemetry.addData("Left power", "%.2f", leftDrive.getPower());
        telemetry.addData("Right power", "%.2f", rightDrive.getPower());
        telemetry.addData("Left ticks", leftDrive.getCurrentPosition());
        telemetry.addData("Right ticks", rightDrive.getCurrentPosition());
        telemetry.addData("Distance (cm)", "%.1f", getDistanceCm());
        telemetry.addData("Sees target", seesTarget());
    }


    // ========================================================
    // HELPERS
    // ========================================================

    /** True if the driver pressed STOP and we should bail out. */
    private boolean shouldStop() {

        return opMode != null && opMode.isStopRequested();
    }


    private void waitForSeconds(double seconds) {

        ElapsedTime timer = new ElapsedTime();

        while (timer.seconds() < seconds) {

            if (shouldStop()) {
                return;
            }
        }
    }
}
