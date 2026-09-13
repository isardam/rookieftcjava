package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * ============================================================
 * SESSION 2 - ROBOT DASHBOARD (on the real Driver Station)
 * ============================================================
 *
 * THE ROBOT STORY
 *
 *     "In our last match the robot stopped and nobody knew why.
 *      Was it the battery? Did it not see the target? We were
 *      blind. Today we build the dashboard so that never
 *      happens again."
 *
 *
 * WHAT WE ALREADY KNOW
 * ============================================================
 *
 * In the console version of this lesson we wrote:
 *
 *     System.out.println("Battery: " + batteryPercent);
 *
 * That printed to a computer screen. But there is no computer
 * screen on a robot in a match.
 *
 * The robot's screen is the DRIVER STATION, and the way we
 * write to it is TELEMETRY:
 *
 *     telemetry.addData("Battery", batteryPercent);
 *     telemetry.update();
 *
 * Same idea. Right tool.
 *
 *
 * TELEMETRY HAS TWO PARTS
 * ============================================================
 *
 *     telemetry.addData(...)   "here is something to show"
 *     telemetry.update()       "now actually send it"
 *
 * addData() only builds up a list. NOTHING appears until
 * update() is called. Forgetting update() is one of the most
 * common rookie mistakes - your dashboard just sits there
 * frozen and you assume the robot is broken.
 *
 *
 * addData vs addLine
 * ============================================================
 *
 *     addLine("text")            just a line of text
 *     addData("Label", value)    a label and a value
 *
 * Use addData for anything that CHANGES. Use addLine for
 * headings and instructions.
 *
 *
 * GOOD TELEMETRY vs USELESS TELEMETRY
 * ============================================================
 *
 * USELESS:
 *
 *     telemetry.addLine("HERE");
 *     telemetry.addLine("got to line 42");
 *
 * Why useless? In two months you will not remember where
 * "HERE" was, and it tells you nothing about the robot.
 *
 * USEFUL:
 *
 *     telemetry.addData("Arm position", armPosition);
 *     telemetry.addData("Target found", targetFound);
 *
 * THE TEST: if the robot misbehaved right now, would this line
 * help me work out why?
 *
 * ============================================================
 */
@TeleOp(name = "S02 Robot Dashboard", group = "Training")
public class S02_TelemetryDashboard extends LinearOpMode {

    @Override
    public void runOpMode() {

        // ====================================================
        // ROBOT INFORMATION - our four data types
        // ====================================================

        // A String holds text. Text always goes in "quotes".
        String robotName = "Iron Angel";

        // An int holds a WHOLE number. No decimal point.
        // Battery is a percentage, so 87 - never 87.5.
        int batteryPercent = 87;

        // A double holds a number WITH a decimal point.
        //
        // Motor power MUST be a double, because it runs:
        //     -1.0 = full reverse
        //      0.0 = stopped
        //      1.0 = full forward
        //
        // If this were an int, the only powers we could ever
        // ask for would be -1, 0 and 1. No half speed!
        double motorPower = 0.75;

        // A boolean holds true or false. Nothing else.
        boolean targetReached = false;

        // How many times the loop has run.
        int loopCounter = 0;


        // ====================================================
        // INIT DASHBOARD
        // ====================================================
        //
        // This shows up before the driver presses PLAY.

        telemetry.addLine("================================");
        telemetry.addLine("        ROBOT DASHBOARD");
        telemetry.addLine("================================");
        telemetry.addData("Robot", robotName);
        telemetry.addLine("Press PLAY to start.");
        telemetry.update();

        waitForStart();


        // ====================================================
        // THE LIVE DASHBOARD
        // ====================================================
        //
        // Now we rebuild the dashboard over and over, so it
        // always shows the CURRENT values.

        while (opModeIsActive()) {

            loopCounter++;

            // Pretend the battery slowly drains, so we can
            // watch a number change on the real Driver Station.
            if (loopCounter % 100 == 0 && batteryPercent > 0) {
                batteryPercent--;
            }

            // Pretend we reached the target after a while.
            if (loopCounter > 500) {
                targetReached = true;
            }


            telemetry.addLine("======= ROBOT DASHBOARD =======");

            // A String value
            telemetry.addData("Robot name", robotName);

            // An int value
            telemetry.addData("Battery", batteryPercent + "%");

            // A double value.
            // "%.2f" means "show 2 digits after the point".
            // Without it you get 0.7500000001 and it looks
            // broken even though it is fine.
            telemetry.addData("Motor power", "%.2f", motorPower);

            // A boolean value
            telemetry.addData("Target reached", targetReached);

            telemetry.addLine();
            telemetry.addData("Loop count", loopCounter);

            // NOTHING above appears until this line runs.
            telemetry.update();
        }
    }
}


/*
 * ============================================================
 * CHALLENGES
 * ============================================================
 *
 * CHALLENGE 1 - PREDICT FIRST
 *
 *     Before running: what will "Motor power" show?
 *     Now run it. Were you right?
 *
 *
 * CHALLENGE 2 - BREAK update()
 *
 *     Comment out telemetry.update() inside the while loop.
 *     Run it.
 *
 *     -> What happens to the dashboard?
 *     -> Is the robot program still running? How can you tell?
 *     -> Who is telemetry actually FOR?
 *
 *
 * CHALLENGE 3 - THE WRONG TYPE
 *
 *     Try each of these. Some will not build at all. For each
 *     one, write down whether the ERROR happens on your
 *     computer or on the robot:
 *
 *         int batteryPercent = "87";
 *         int batteryPercent = 87.5;
 *         double motorPower = 75;
 *         boolean targetReached = "false";
 *         String robotName = Iron Angel;
 *
 *     -> Which of these COMPILES but is still wrong?
 *
 *
 * CHALLENGE 4 - THE DANGEROUS ONE
 *
 *     double motorPower = 75;
 *
 *     This compiles fine. Java turns 75 into 75.0.
 *
 *     -> What do you THINK the robot would do with power 75.0?
 *     -> The answer: setPower() quietly uses 1.0 instead.
 *     -> Why is a bug that quietly works WORSE than a crash?
 *
 *
 * CHALLENGE 5 - BUILD A REAL DASHBOARD
 *
 *     Your driver says: "I can't tell why the robot won't move."
 *
 *     Build the dashboard that answers their question.
 *
 *     Use at least 8 variables: 2 int, 2 double, 2 boolean,
 *     2 String. But every single one must be something a
 *     DRIVER would actually want to know mid-match.
 *
 *     If you cannot explain why a driver needs it, take it out.
 *
 *
 * ============================================================
 * COACH CHECKOFF
 * ============================================================
 *
 *   [ ] Dashboard visible on the real Driver Station
 *   [ ] Student can explain why motorPower must be a double
 *   [ ] Student can explain what update() does
 *   [ ] Student found which wrong-type lines still compile
 *   [ ] Every line of their dashboard answers a driver question
 *
 * ============================================================
 */
