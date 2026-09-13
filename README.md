# Rookie FTC Java

A Java curriculum for a rookie middle-school FTC team coming from Blocks.

**Goal:** teach enough Java that a rookie can confidently **read, modify, debug, and eventually write** FTC robot code. Not to produce professional Java developers.

---

## Layout

```
console/                 Sessions 1-4: plain Java. No robot, no SDK.
  solutions/             Working code the coach demonstrates.
  challenges/            Student versions with TODOs and deliberate bugs.

TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
                         Sessions 2-8: real FTC OpModes. Needs the SDK + robot.
```

Two folders because the first sessions should not be blocked on Android Studio, a
charged battery, and a working robot config. Once students reach `hardwareMap`
(Session 5), everything happens on the robot.

Sessions 2 and 3 appear in **both** folders on purpose: learn the idea in the
console, then do the same thing on the real Driver Station.

---

## Running the console sessions

No IDE required. Any computer with a JDK:

```bash
cd console/solutions
javac MissionControl.java
java MissionControl
```

The file name always matches the class name, so `javac <ClassName>.java` always works.

> **Sessions 1 and 2 challenge files do not compile on purpose.** Finding and
> fixing those errors *is* the lesson. Everything else compiles.

---

## Running the FTC sessions

1. Copy the contents of `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/`
   into the same folder in your `FtcRobotController` project.
2. Build and deploy to the Robot Controller.
3. The OpModes appear on the Driver Station under the names in `@TeleOp` /
   `@Autonomous`.

### Required robot configuration

These names must match your robot config **exactly** — capital letters count.
A mismatch crashes the OpMode at INIT with a message naming the device it
could not find.

| Config name  | Type             | Used by |
|--------------|------------------|---------|
| `leftDrive`  | DcMotor          | S05, S06, Robot |
| `rightDrive` | DcMotor          | S05, S06, Robot |
| `intake`     | DcMotor          | S06, Robot |
| `claw`       | Servo            | Robot |
| `distance`   | DistanceSensor   | S03, Robot |

`S02_TelemetryDashboard` and `S03_SensorDecisions` need less than this — S02
needs no hardware at all, S03 needs only `distance`. Start there if the robot
is half-built.

### Calibrate before trusting autonomous

`Robot.java` contains:

```java
private static final double TICKS_PER_REVOLUTION = 537.7;   // motor spec sheet
private static final double WHEEL_DIAMETER_INCHES = 3.78;   // measure yours
```

Tell the robot to drive 24 inches, measure what it *actually* drove, and adjust.
Every drivetrain differs.

---

## Session map

| # | Session | Java concept | FTC concept | Where |
|---|---------|--------------|-------------|-------|
| 1 | Mission Control | statements, `;`, `{}` | reading an OpMode | `console` |
| 2 | Robot Dashboard | variables, `int`/`double`/`boolean`/`String` | `telemetry` | both |
| 3 | Decision Engine | `if`/`else if`/`else`, `&&`, `!` | sensor decisions | both |
| 4A | SearchBot: for loops | `for`, counters | repeated robot actions | `console` |
| 4B | SearchBot: while loops | `while`, conditions | `opModeIsActive()` | `console` |
| 4C | SearchBot: nested loops 🟠 | nested loops, `break` | grid search | `console` |
| 5 | **First Motor** | using objects + methods | **`hardwareMap`, `setPower`** | `TeamCode` |
| 5 | Command Library | methods, parameters, returns | reusable commands | `console` |
| 6 | Driver Control | joystick math | **`gamepad` → motor** | `TeamCode` |
| 6 | Virtual Robot | classes, objects, constructors | Robot object | `console` |
| 7 | Iron Angel TeleOp | the FTC framework, `while` | TeleOp, edge detection | `TeamCode` |
| 8 | Operation Homecoming | `enum`, `switch` | autonomous state machine | `TeamCode` |

🟠 **4C is optional.** Nested loops are the hardest topic here — harder than
classes — and are not needed for a working TeleOp or autonomous. Skip it if the
team is tired and come back.

---

## How a session runs

Roughly 90 minutes:

1. **Robot hook** (5–10 min) — a real FTC problem
2. **What we already know** (5 min) — connect to Blocks or last session
3. **One new concept** (10–15 min) — only one
4. **Coach live coding** (15 min) — deploy after every line
5. **Team coding** (20–30 min) — students modify, coach does not type
6. **Robot test** (15–20 min) — on hardware
7. **Debugging challenge** (10 min) — a planted bug
8. **FTC connection** (5 min) — where this appears in real code
9. **Student explanation** (5 min) — explain it to another student

---

## The debugging process

Every session has a deliberate bug. Post this on the wall:

```
1. OBSERVE      What exactly happened?
2. REPRODUCE    Can you make it happen again?
3. DESCRIBE     Say it out loud to your partner.
4. LOCATE       Which line could cause THAT?
5. CHANGE ONE   One thing. Not three.
6. TEST         Did it fix it?
7. EXPLAIN      Why did that work?
```

> **A robot doing the wrong thing is not a failure. It's a bug.** Bugs have
> causes, and causes can be found. Every line of FTC code you will ever admire
> was broken first.

---

## Coach notes

- **Don't type on a student's laptop.** Ask "what does telemetry say right now?"
- **Make them predict before running.** A wrong prediction is where the learning is.
- **Robot on blocks** until motor code is checked off.
- **One challenge at a time** — uncomment a single call in `main()` / the loop.
- **Good telemetry answers a question a driver actually has.** `addLine("HERE")`
  is useless; `addData("Distance (cm)", 14.2)` is not.

---

## Standard for success

A student finishing this should be able to say:

> *"I may not know all the Java yet, but I know how to read the code, figure out
> what it does, change it, test it, and debug it."*
