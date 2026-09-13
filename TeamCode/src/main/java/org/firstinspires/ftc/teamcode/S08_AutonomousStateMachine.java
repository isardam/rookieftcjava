package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * ============================================================
 * SESSION 8 - AUTONOMOUS STATE MACHINE
 * ============================================================
 *
 * OPERATION HOMECOMING
 *
 * MISSION:
 *
 *     1. Leave base
 *     2. Search for the target
 *     3. Collect it
 *     4. Return home
 *     5. Park
 *
 *
 * THE PROBLEM WITH OUR OLD AUTONOMOUS
 * ============================================================
 *
 * Our first autonomous was a straight list of instructions:
 *
 *     leaveBase();
 *     findTarget();
 *     collectObject();
 *     returnHome();
 *
 * That works, but it has two problems:
 *
 *   1. When it fails, we have no idea WHERE it failed.
 *
 *   2. It cannot easily react. What if the target is not there?
 *      What if we want to skip a step and still park for points?
 *
 *
 * THE NEW IDEA: A STATE MACHINE
 * ============================================================
 *
 * Instead of a list of steps, we give the robot a list of
 * SITUATIONS it can be in, and rules for moving between them.
 *
 *     START
 *       |
 *       v
 *     LEAVE_BASE
 *       |
 *       v
 *     SEARCH -----------> COLLECT
 *       |  ^                 |
 *       |  |  (not found,    |
 *       |  |   try again)    |
 *       |  +-----------------+
 *       |
 *       |  (gave up after 3 tries)
 *       v
 *     DRIVE_HOME <-----------+
 *       |
 *       v
 *     PARK
 *       |
 *       v
 *     DONE
 *
 * Only ONE state is active at a time. We always know exactly
 * which one, because we put it on telemetry - so when
 * autonomous misbehaves, the Driver Station tells us where it
 * was when things went wrong.
 *
 * This is how real competitive autonomous programs are built.
 *
 *
 * THE GOLDEN RULE OF AUTONOMOUS
 * ============================================================
 *
 *     An autonomous that gives up gracefully and parks
 *     beats an autonomous that hangs and scores nothing.
 *
 * Notice how many times this file gives up on purpose:
 *   - searching stops after MAX_SEARCH_ATTEMPTS
 *   - every drive has a timeout
 *   - failing to find the target still drives home and parks
 *
 * ============================================================
 */
@Autonomous(name = "S08 Operation Homecoming", group = "Iron Angels")
public class S08_AutonomousStateMachine extends LinearOpMode {

    // ========================================================
    // THE STATES
    // ========================================================
    //
    // An enum is a list of named values. It is perfect here
    // because the robot can only be in ONE of these at a time,
    // and we want Java to stop us from typo-ing a state name.
    //
    // (With a String, "SERCH" would compile and fail silently.
    //  With an enum, it will not compile at all. Let Java catch
    //  your typos.)

    private enum State {
        START,
        LEAVE_BASE,
        SEARCH,
        COLLECT,
        DRIVE_HOME,
        PARK,
        DONE
    }


    // ========================================================
    // MISSION SETTINGS
    // ========================================================
    //
    // Named constants instead of mystery numbers scattered
    // through the code. Change the mission by editing these.

    private static final int MAX_SEARCH_ATTEMPTS = 3;

    private static final double DRIVE_POWER = 0.4;
    private static final double LEAVE_BASE_INCHES = 24;
    private static final double SEARCH_STEP_INCHES = 8;
    private static final double APPROACH_INCHES = 6;

    private static final double MOVE_TIMEOUT_SECONDS = 4.0;
    private static final double TURN_SECONDS = 0.4;


    // ========================================================
    // MISSION STATE
    // ========================================================

    private Robot robot;

    private State state = State.START;

    private int searchAttempts = 0;

    private boolean targetFound = false;
    private boolean objectCollected = false;

    private double inchesTravelled = 0;


    @Override
    public void runOpMode() {

        // ----------------------------------------------------
        // INIT
        // ----------------------------------------------------
        //
        // We hand the Robot our whole OpMode (not just
        // hardwareMap) so that long movements can notice if the
        // driver presses STOP.

        telemetry.addLine("OPERATION HOMECOMING");
        telemetry.addLine("Initializing...");
        telemetry.update();

        robot = new Robot(this);

        telemetry.addLine("Robot ready.");
        telemetry.addLine("Waiting for START...");
        telemetry.update();

        waitForStart();

        if (isStopRequested()) {
            return;
        }

        ElapsedTime missionTimer = new ElapsedTime();

        state = State.LEAVE_BASE;


        // ----------------------------------------------------
        // THE STATE MACHINE LOOP
        // ----------------------------------------------------
        //
        // ONE loop runs the whole mission. Each time around, we
        // look at which state we are in and do that state's job.

        while (opModeIsActive() && state != State.DONE) {

            // Always show where we are. This single line is what
            // makes autonomous debuggable.
            telemetry.addData("State", state);
            telemetry.addData("Mission time", "%.1f s",
                    missionTimer.seconds());
            telemetry.addData("Search attempts", searchAttempts);
            telemetry.addData("Target found", targetFound);
            telemetry.addData("Object collected", objectCollected);
            telemetry.addData("Inches travelled", "%.0f",
                    inchesTravelled);
            telemetry.update();


            switch (state) {

                // --------------------------------------------
                case LEAVE_BASE:

                    driveForward(LEAVE_BASE_INCHES);

                    state = State.SEARCH;
                    break;


                // --------------------------------------------
                // SEARCH is the interesting state: it can go to
                // COLLECT, or back to itself, or give up.
                case SEARCH:

                    searchAttempts++;

                    // Ask the real sensor.
                    targetFound = robot.seesTarget();

                    if (targetFound) {

                        state = State.COLLECT;

                    } else if (searchAttempts >= MAX_SEARCH_ATTEMPTS) {

                        // GIVE UP - but still go park for points.
                        telemetry.addLine("Target not found. Parking.");
                        telemetry.update();

                        state = State.DRIVE_HOME;

                    } else {

                        // Move somewhere new and look again.
                        driveForward(SEARCH_STEP_INCHES);
                        robot.turnRightForSeconds(DRIVE_POWER,
                                TURN_SECONDS);

                        state = State.SEARCH;
                    }
                    break;


                // --------------------------------------------
                case COLLECT:

                    driveForward(APPROACH_INCHES);

                    robot.closeClaw();
                    sleep(500);

                    robot.startIntake();
                    sleep(1000);
                    robot.stopIntake();

                    objectCollected = true;

                    state = State.DRIVE_HOME;
                    break;


                // --------------------------------------------
                case DRIVE_HOME:

                    // Turn around and drive back however far we
                    // actually came. Because we tracked it, this
                    // works whether we searched once or three
                    // times.
                    robot.turnRightForSeconds(DRIVE_POWER,
                            TURN_SECONDS * 4);

                    driveForward(inchesTravelled);

                    state = State.PARK;
                    break;


                // --------------------------------------------
                case PARK:

                    robot.stopDriving();
                    robot.stopIntake();

                    state = State.DONE;
                    break;


                // --------------------------------------------
                default:

                    // We should never get here. If we do,
                    // something is wrong - so stop safely rather
                    // than carrying on in an unknown state.
                    telemetry.addLine("Unexpected state!");
                    telemetry.update();

                    state = State.DONE;
                    break;
            }
        }


        // ----------------------------------------------------
        // MISSION OVER
        // ----------------------------------------------------

        robot.stopDriving();
        robot.stopIntake();

        telemetry.addLine("--------------------------------");

        if (objectCollected) {

            telemetry.addLine("MISSION SUCCESS!");
            telemetry.addLine("Object collected and returned.");

        } else if (targetFound) {

            telemetry.addLine("PARTIAL SUCCESS");
            telemetry.addLine("Target seen but not collected.");

        } else {

            telemetry.addLine("PARKED WITHOUT TARGET");
            telemetry.addLine("No target found - parked for points.");
        }

        telemetry.addData("Total time", "%.1f s",
                missionTimer.seconds());
        telemetry.addLine("--------------------------------");
        telemetry.update();

        sleep(3000);
    }


    /**
     * Drive forward and remember how far we went, so
     * DRIVE_HOME knows how far to come back.
     *
     * Wrapping robot.driveInches() like this means the
     * bookkeeping can never be forgotten at one of the three
     * call sites.
     */
    private void driveForward(double inches) {

        robot.driveInches(inches, DRIVE_POWER, MOVE_TIMEOUT_SECONDS);

        inchesTravelled = inchesTravelled + inches;
    }
}


/*
 * ============================================================
 * CHALLENGES
 * ============================================================
 *
 *   1. READ IT FIRST. Trace the mission on paper. Write down
 *      the order the states run in if the target is found on
 *      the second try.
 *
 *   2. Change MAX_SEARCH_ATTEMPTS to 1. What happens if the
 *      target is not right in front of the robot?
 *
 *   3. Add a new state called DELIVER between COLLECT and
 *      DRIVE_HOME. Make it open the claw to drop the object.
 *
 *   4. Right now SEARCH always turns right. Make it alternate
 *      left and right so the robot sweeps instead of circling.
 *
 *   5. STRETCH: add a state that reads the distance sensor and
 *      chooses a DIFFERENT route depending on whether the path
 *      is blocked.
 *
 *
 * ============================================================
 * DEBUGGING CHALLENGE - THE GAUNTLET
 * ============================================================
 *
 * Your coach will introduce ONE bug into your working
 * autonomous without telling you which. You must find it using
 * TELEMETRY ONLY - no coach help.
 *
 * This is the real exam. A programmer who can do this can
 * contribute to a competition codebase.
 *
 *   BUG                                SYMPTOM ON THE FIELD
 *   ------------------------------     ----------------------------
 *   SEARCH forgets searchAttempts++    robot searches forever
 *   COLLECT never sets state           robot repeats COLLECT
 *   driveInches timeout set to 0        robot never moves
 *   DRIVE_HOME uses a fixed distance   robot stops in wrong place
 *   inchesTravelled never updated      robot drives home too far
 *   state set to SEARCH in PARK        mission never ends
 *
 * FOR EACH ONE, ASK:
 *
 *   - What was the LAST state shown on the Driver Station?
 *   - Was the robot stuck in a state, or did it skip one?
 *   - Which lines can change that state?
 *
 *
 * ============================================================
 * QUESTIONS
 * ============================================================
 *
 *   1. Walk us through your state machine.
 *
 *   2. What happens if the sensor never sees the target?
 *
 *   3. Where does the robot get its motors?
 *
 *   4. Show us the line that makes the robot drive forward.
 *
 *   5. Why does every movement have a timeout?
 *
 *   6. Why is State an enum instead of a String?
 *
 *   7. What was your hardest bug and how did you find it?
 *
 * ============================================================
 */
