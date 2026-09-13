package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * ============================================================
 * SESSION 5 - MAKING THE ROBOT MOVE
 * ============================================================
 *
 * THIS IS THE ONE.
 *
 * Everything up to now has been words on a screen. Today the
 * robot moves because of Java YOU wrote.
 *
 *
 * WHAT WE ALREADY KNOW
 * ============================================================
 *
 * In Blocks you dragged a block that said:
 *
 *     set leftDrive power to 0.5
 *
 * In Java that same instruction is:
 *
 *     leftDrive.setPower(0.5);
 *
 * Let us take that ONE line apart completely.
 *
 *     leftDrive . setPower ( 0.5 ) ;
 *     |           |          |     |
 *     |           |          |     +-- SEMICOLON: end of the
 *     |           |          |         instruction, like a
 *     |           |          |         period ends a sentence.
 *     |           |          |
 *     |           |          +-------- THE DETAIL: how much
 *     |           |                    power. -1.0 is full
 *     |           |                    reverse, 0.0 is stop,
 *     |           |                    1.0 is full forward.
 *     |           |
 *     |           +------------------- THE ACTION: what we want
 *     |                                done. The parentheses are
 *     |                                the socket the detail
 *     |                                goes into.
 *     |
 *     +------------------------------- THE THING: which motor we
 *                                      are talking to.
 *
 * And the DOT means "'s":
 *
 *     leftDrive.setPower   =   leftDrive'S setPower
 *
 * That is the whole shape of Java. A thing, a dot, an action,
 * the details in parentheses, a semicolon. You will see that
 * shape ten thousand times this season.
 *
 *
 * THE NEW IDEA: hardwareMap
 * ============================================================
 *
 * In Blocks, the motor already existed in a dropdown menu.
 *
 * In Java we have to ask for it first:
 *
 *     leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
 *
 * hardwareMap is the robot's PHONE BOOK. We give it:
 *
 *     - a TYPE   (DcMotor.class - "I want a motor")
 *     - a NAME   ("leftDrive"   - "this specific one")
 *
 * and it hands back the real motor.
 *
 * THE NAME IN QUOTES MUST MATCH THE ROBOT CONFIGURATION FILE
 * EXACTLY. Capital letters count. "leftdrive" is NOT
 * "leftDrive".
 *
 * This is the number one reason rookie OpModes crash. You will
 * do it today ON PURPOSE so that you recognise the error
 * message when it happens in a real match.
 *
 * ============================================================
 */
@Autonomous(name = "S05 First Motor", group = "Training")
public class S05_FirstMotor extends LinearOpMode {

    // ========================================================
    // OUR MOTORS
    // ========================================================
    //
    // We declare them up here so every part of the program can
    // use them. Right now they are empty - we have not asked
    // hardwareMap for them yet.

    private DcMotor leftDrive;
    private DcMotor rightDrive;


    @Override
    public void runOpMode() {

        // ====================================================
        // STEP 1: FIND THE HARDWARE
        // ====================================================
        //
        // This runs when you press INIT on the Driver Station.
        //
        // If a name does not match the configuration file, the
        // OpMode crashes RIGHT HERE and the Driver Station shows
        // you the name it could not find.

        leftDrive  = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");


        // ====================================================
        // STEP 2: FIX THE MOTOR DIRECTIONS
        // ====================================================
        //
        // The two drive motors are bolted to the robot facing
        // OPPOSITE ways. So if we tell both of them "0.5", one
        // pushes the robot forward and the other pushes it
        // backward, and the robot spins instead of driving.
        //
        // Reversing one motor in software fixes that forever.
        //
        // TRY IT: comment this line out and watch what the robot
        // does. Then put it back.

        rightDrive.setDirection(DcMotor.Direction.REVERSE);


        telemetry.addLine("Motors found!");
        telemetry.addLine("Press PLAY to drive forward.");
        telemetry.update();


        // ====================================================
        // STEP 3: WAIT FOR THE DRIVER
        // ====================================================

        waitForStart();


        // ====================================================
        // STEP 4: DRIVE!
        // ====================================================
        //
        // This is the moment. Two lines of Java, and 40 pounds
        // of aluminium does what you told it to.

        telemetry.addLine("DRIVING FORWARD");
        telemetry.update();

        leftDrive.setPower(0.3);
        rightDrive.setPower(0.3);

        // Keep driving for 2 seconds.
        //
        // sleep() means "do nothing for this many milliseconds".
        // 2000 milliseconds = 2 seconds.
        //
        // The motors keep running the whole time, because we
        // never told them to stop.
        sleep(2000);


        // ====================================================
        // STEP 5: STOP!
        // ====================================================
        //
        // NEVER forget this part.
        //
        // A motor keeps doing the last thing you told it. If you
        // delete these two lines, the robot drives forward until
        // it hits a wall or someone pulls the battery.
        //
        // "Stop" is an instruction, not something that happens
        // automatically.

        leftDrive.setPower(0);
        rightDrive.setPower(0);

        telemetry.addLine("STOPPED");
        telemetry.update();

        sleep(1000);
    }
}


/*
 * ============================================================
 * HANDS-ON CHALLENGES
 * ============================================================
 *
 * Do these IN ORDER. Put the robot on blocks (wheels off the
 * ground) until Challenge 3.
 *
 *
 * CHALLENGE 1 - CHANGE THE SPEED
 *
 *     Change 0.3 to 0.6. Predict what happens BEFORE you run it.
 *     Then try 1.0. Then try 0.1.
 *
 *     QUESTION: at what power does the robot stop moving at all?
 *     Why? (Hint: it takes some force just to overcome friction.)
 *
 *
 * CHALLENGE 2 - DRIVE BACKWARD
 *
 *     Make the robot drive backward instead.
 *
 *     Do NOT add new lines. Change the numbers only.
 *
 *
 * CHALLENGE 3 - DRIVE A MEASURED DISTANCE
 *
 *     Put the robot on the floor. Put a piece of tape on the
 *     floor 3 feet away.
 *
 *     Change the sleep() time until the robot stops on the tape.
 *
 *     QUESTION: your teammate's robot needs a different number
 *     than yours. Why? What happens to your number when the
 *     battery gets low?
 *
 *     (This is exactly why real autonomous uses ENCODERS instead
 *      of sleep(). We will get there.)
 *
 *
 * CHALLENGE 4 - SPIN IN PLACE
 *
 *     Make the robot spin in place instead of driving straight.
 *
 *     HINT: what would happen if the two motors got OPPOSITE
 *     powers?
 *
 *
 * CHALLENGE 5 - DRIVE A SQUARE
 *
 *     Combine what you know: drive forward, turn 90 degrees,
 *     four times.
 *
 *     Use a for loop! You learned it in Session 4:
 *
 *         for (int i = 0; i < 4; i++) {
 *             // drive forward
 *             // turn right
 *         }
 *
 *     This is the first time a loop you wrote makes the robot
 *     do something you can watch.
 *
 *
 * ============================================================
 * DEBUGGING CHALLENGE - BREAK IT ON PURPOSE
 * ============================================================
 *
 * Do each of these, one at a time, and WRITE DOWN the exact
 * error or behaviour. Then undo it.
 *
 * You are building a mental library of "what this symptom
 * means", which is the actual skill of debugging.
 *
 *   BUG 1: Change "leftDrive" to "leftdrive" (lowercase d).
 *          Press INIT.
 *          -> What does the Driver Station say?
 *          -> Does it crash at INIT or at PLAY? Why does that
 *             matter?
 *
 *   BUG 2: Delete the line that reverses rightDrive.
 *          -> What does the robot do instead of driving?
 *
 *   BUG 3: Delete the two setPower(0) lines at the end.
 *          -> What does the robot do after the OpMode ends?
 *          -> Why is this DANGEROUS?
 *
 *   BUG 4: Delete a semicolon.
 *          -> Does this crash on the robot, or does it refuse to
 *             build on the computer? What is the difference?
 *
 *   BUG 5: Move waitForStart() to the very end of runOpMode().
 *          -> When does the robot move now? Why?
 *
 *
 * ============================================================
 * COACH CHECKOFF
 * ============================================================
 *
 *   [ ] Robot drives forward and stops, from student's own code
 *   [ ] Student can point at leftDrive.setPower(0.3) and explain
 *       every part: the thing, the dot, the action, the detail,
 *       the semicolon
 *   [ ] Student can explain what hardwareMap is FOR
 *   [ ] Student fixed a config-name mismatch WITHOUT help
 *   [ ] Student can explain why the setPower(0) lines matter
 *   [ ] Student drove a measured distance
 *   [ ] (Stretch) Robot drives a square using a for loop
 *
 * ============================================================
 */
