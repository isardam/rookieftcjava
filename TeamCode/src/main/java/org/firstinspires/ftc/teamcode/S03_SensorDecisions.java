package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * ============================================================
 * SESSION 3 - SENSOR DECISIONS (with a REAL sensor)
 * ============================================================
 *
 * THE ROBOT STORY
 *
 *     "Mission Control says the robot must not drive into the
 *      wall. It needs to decide for itself when to stop."
 *
 *
 * WHAT WE ALREADY KNOW
 * ============================================================
 *
 * In the console version we wrote decisions using variables we
 * typed in ourselves:
 *
 *     boolean obstacleDetected = false;    <- we decided this
 *
 * That taught us how if/else works, but the robot was not
 * really deciding anything. WE decided, and the robot read it.
 *
 * Today the number comes from a real sensor, and it changes
 * while you watch.
 *
 *
 * THE NEW IDEA
 * ============================================================
 *
 *     double distanceToWall =
 *             distanceSensor.getDistance(DistanceUnit.CM);
 *
 * Read that the way we read setPower():
 *
 *     distanceSensor . getDistance ( DistanceUnit.CM ) ;
 *     |                |             |
 *     |                |             +-- the detail: what UNIT
 *     |                |                 do you want the answer
 *     |                |                 in? (CM, INCH, MM...)
 *     |                |
 *     |                +---------------- the action: "how far?"
 *     |
 *     +--------------------------------- the thing: our sensor
 *
 * The big difference from setPower(): this method RETURNS an
 * answer. setPower() does something; getDistance() tells us
 * something. So we catch the answer in a variable.
 *
 *
 * WHY DECISION ORDER MATTERS
 * ============================================================
 *
 * The robot checks things in the order WE write them, and it
 * stops at the FIRST one that is true.
 *
 * So the most important, most urgent check goes FIRST. On a
 * real robot that is almost always safety: low battery, or
 * about to hit something.
 *
 * ============================================================
 */
@TeleOp(name = "S03 Sensor Decisions", group = "Training")
public class S03_SensorDecisions extends LinearOpMode {

    private DistanceSensor distanceSensor;


    @Override
    public void runOpMode() {

        // The name "distance" must match the robot config file.
        distanceSensor =
                hardwareMap.get(DistanceSensor.class, "distance");

        telemetry.addLine("Distance sensor found.");
        telemetry.addLine("Press PLAY, then move your hand");
        telemetry.addLine("toward the sensor.");
        telemetry.update();

        waitForStart();


        while (opModeIsActive()) {

            // ================================================
            // 1. READ THE WORLD
            // ================================================

            double distanceToWall =
                    distanceSensor.getDistance(DistanceUnit.CM);

            // Battery is pretend for now - we will read the real
            // one later. Change it to test the first branch.
            int batteryPercent = 85;

            boolean obstacleDetected = distanceToWall < 20;
            boolean targetDetected = distanceToWall < 60;


            // ================================================
            // 2. SHOW WHAT THE ROBOT SEES
            // ================================================

            telemetry.addLine("===== ROBOT DECISION ENGINE =====");
            telemetry.addData("Distance (cm)", "%.1f", distanceToWall);
            telemetry.addData("Battery", batteryPercent + "%");
            telemetry.addData("Obstacle detected", obstacleDetected);
            telemetry.addData("Target detected", targetDetected);
            telemetry.addLine("---------------------------------");


            // ================================================
            // 3. DECIDE
            // ================================================
            //
            // Safety first, then obstacles, then the mission.

            if (batteryPercent < 20) {

                telemetry.addData("DECISION", "RETURN HOME");
                telemetry.addLine("Reason: battery too low.");

            } else if (obstacleDetected) {

                // Something is in the way. Go AROUND it.
                // (Not "search" - we know where we are going,
                //  something is just blocking the path.)
                telemetry.addData("DECISION", "AVOID OBSTACLE");
                telemetry.addLine("Reason: something is too close.");

            } else if (!targetDetected) {

                // The ! means NOT.
                // "if NOT targetDetected" = "if we cannot see it"
                telemetry.addData("DECISION", "SEARCH");
                telemetry.addLine("Reason: target not detected.");

            } else {

                // We got here because: battery fine, nothing too
                // close, and we CAN see the target.
                telemetry.addData("DECISION", "APPROACH TARGET");
                telemetry.addLine("Reason: target seen, path clear.");
            }

            telemetry.update();
        }
    }
}


/*
 * ============================================================
 * HANDS-ON: WATCH THE DECISION CHANGE
 * ============================================================
 *
 * Press PLAY. Slowly move your hand toward the sensor and watch
 * the DECISION line on the Driver Station change:
 *
 *     far away    ->  SEARCH
 *     60 cm       ->  APPROACH TARGET
 *     20 cm       ->  AVOID OBSTACLE
 *
 * THIS is what if/else is for. The code did not change. The
 * WORLD changed, and the robot decided differently.
 *
 *
 * ============================================================
 * CHALLENGES
 * ============================================================
 *
 *   1. PREDICT: what decision do you get at exactly 20.0 cm?
 *      Look at the code and work it out before you test it.
 *      (Hint: is it < 20 or <= 20?)
 *
 *   2. Change the obstacle threshold from 20 to 35. Test it.
 *
 *   3. Set batteryPercent to 15. Now move your hand right up to
 *      the sensor.
 *      -> Why does it STILL say RETURN HOME?
 *      -> What does that tell you about the ORDER of the checks?
 *
 *   4. Add a new decision: if the target is closer than 10 cm,
 *      say "COLLECT TARGET" instead of "AVOID OBSTACLE".
 *      Where in the if/else chain does it have to go? Why?
 *
 *   5. STRETCH: use && to add a decision that needs TWO things
 *      to be true at once.
 *
 *
 * ============================================================
 * DEBUGGING CHALLENGE
 * ============================================================
 *
 *   BUG 1: Move the battery check to the END of the if/else
 *          chain. Set batteryPercent to 15.
 *          -> Does the robot ever say RETURN HOME? When?
 *          -> Why is this DANGEROUS on a real robot?
 *
 *   BUG 2: Change  if (batteryPercent < 20)
 *              to  if (batteryPercent = 20)
 *          -> What does the compiler say?
 *          -> = means "put this value in". == means "is equal?"
 *             They are different things.
 *
 *   BUG 3: Remove the ! from !targetDetected.
 *          -> Trace it on paper. What does the robot do now
 *             when it CAN see the target?
 *
 *   BUG 4: Change every "else if" to just "if".
 *          -> Run it. How many DECISION lines appear at once?
 *          -> Explain the difference between "if" and "else if".
 *
 *
 * ============================================================
 * COACH CHECKOFF
 * ============================================================
 *
 *   [ ] Student made the decision change by moving their hand
 *   [ ] Student can explain !targetDetected in plain English
 *   [ ] Student can explain why battery is checked FIRST
 *   [ ] Student can explain the difference between = and ==
 *   [ ] Student predicted a result correctly before testing
 *
 * ============================================================
 */
