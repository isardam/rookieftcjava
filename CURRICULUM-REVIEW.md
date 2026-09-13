# FTC Java Rookie Curriculum — Full Review

**Reviewer role:** FTC Lead Coach / Java instructor
**Repo reviewed:** `rookieftcjava` @ `3a5128e` (16 files, ~8,900 lines)
**Audience assumed:** middle-school rookies, barebones Blocks experience, robot + Driver Station available some sessions
**Goal assumed:** read, modify, debug, and eventually write FTC robot code — *not* produce Java developers

---

## 1. Executive Curriculum Review

### Finding 1 — The robot never moves. 🔴 *Most important finding.*

Across all 8 sessions there is **not one line of student-written motor code**. No `setPower()`. No `hardwareMap.get()`. No `DcMotor`. No `Servo`.

- Session 7 (`Session7-IronAngelTeleOp.java:211`) states outright: *"We are intentionally NOT using hardware yet."*
- Session 8's `moveForward(int distance)` (`Session8-FTCMiniSW:424-439`) is `telemetry.addData(...)` + `sleep(300)`. The comment `/* Real FTC drivetrain code goes here. */` is the entire implementation.
- `hardwareMap` appears **twice in the whole repo, both times commented out** (`Session7:206`, `Session8:408`).

The stated ramp is Level 0 Blocks → … → Level 4 *"Can we use it to make the robot do something?"* **Level 4 is never reached.** A student completes the full curriculum having produced console text and Driver Station text, and the drivetrain has never turned. For a team that *has* a robot, this is the defect to fix before any other.

### Finding 2 — Level 0 (Blocks) is completely absent. 🔴

Zero files mention Blocks, and there is no Blocks→Java bridge anywhere. Worse, the very first thing a Blocks student sees is:

```java
public class MissionControl {
    public static void main(String[] args) {
```

This is the single most alien construct in Java, it is never explained, and — as Session 7 eventually admits (`Session7:29-36`) — **it is not used in FTC at all.** Students are drilled on `main()` for six sessions, then told it's irrelevant. The one syntactic ritual they mastered turns out to be throwaway.

### Finding 3 — Not a single file compiles under its own name. 🔴

Java requires a public class to live in `<ClassName>.java`; hyphens are illegal in identifiers, so these filenames *can never* match. Verified:

```
$ javac Session1-MissionControl.java
Session1-MissionControl.java:8: error: class MissionControl is public,
  should be declared in a file named MissionControl.java
```

Additionally there are **7 duplicate class-name collisions** — no pair can coexist in one folder or Android Studio module:

| Class | Declared in |
|---|---|
| `MissionControl` | `Session1-Challenge.java`, `Session1-MissionControl.java` |
| `RobotDashboard` | `Session2-Challenge.java`, `Session2-RobotDashboard.java` |
| `RobotDecisionEngine` | `Session3-Challenge.java`, `Session3-RobotDecisionEngine.java` |
| `SearchBot` | `Session4-SearchBot.java`, `Session4-SearchBot_solution.java` |
| `RobotCommandLibrary` | `Session5-RobotCommandLibrary.java`, `…Challenge.java` |
| `Robot` + `VirtualRobot` | `Session6-VirtualRobot.java`, `Session6-Challenge.java` |

And `Session8-FTCMiniSW` has **no `.java` extension at all** while declaring `public class Session8_AutonomousMission`.

A rookie's first-ever experience of Java is therefore an error message about something they did not do and cannot understand. Fix: `solutions/` and `challenges/` subfolders, `MissionControl.java` / `MissionControlChallenge.java` naming.

### Finding 4 — Session 4 carries ~8 concepts and 24 exercises in one session. 🔴

`Session4-SearchBot.java` (12 steps) plus `Session4-Challenge.java` (12 challenges) introduce: `for`, `while`, counters, `boolean`, `if/else`, six comparison operators, `&&`, `break`, and nested loops. That is **4× the 1–2 concepts-per-session budget**, and nested loops + `break` semantics is genuinely the hardest topic in the whole repo — harder than Session 6's classes.

It is also **partly redundant**: Session 2 already taught `boolean`, and Session 3 already taught `if/else`/comparisons. Steps 6, 7, and 8 re-teach what students did two sessions ago.

### Finding 5 — Methods are used as magic for a full session before being taught. 🟠

Session 4 has students call `moveForward()`, `checkSquare()`, and even the **parameterized** `moveToSquare(row, column)` (`Session4-SearchBot.java:674`), under the instruction *"DON'T CHANGE THESE YET"* (`:643`). Methods and parameters are the entire subject of Session 5. So students spend Session 4 invoking machinery they've been explicitly forbidden to understand — then Session 5 introduces it as new.

This inversion is fixable and worth fixing: methods-as-named-robot-actions is *easier* than nested loops, and it is the concept with the most immediate FTC payoff.

### Finding 6 — Session 5's simulation is internally broken, and the bug is in untouchable coach code. 🔴 *Verified by execution.*

There are two **disconnected** movement systems in `Session5-RobotCommandLibrary.java`:

- `moveForward()` / `moveForward(int)` (`:71`, `:155`) update **only** `distanceTraveled`. They never touch `currentRow`/`currentColumn`, and they **ignore `direction` entirely** — so `turnRight()` is purely decorative.
- `moveNorth/South/East/West(int)` (`:206-279`) *do* update position.

Consequence, confirmed by running `mission5()`:

```
===== MISSION 5 =====
ROBOT: Picking up package
ROBOT: Moving forward 3 squares     ← position unchanged
ROBOT: Turning RIGHT                ← has no effect on anything
ROBOT: Moving forward 2 squares     ← position unchanged
ROBOT: Delivering package
ROBOT: Returning home
ROBOT: HOME [0][0]                  ← was never away
========== ROBOT STATUS ==========
Position: [0][0]
Distance traveled: 5
At home: true                       ← after a "delivery mission"
```

The delivery mission is a **positional no-op that reports success.** `returnHome()` (`:469`) can never do anything. `isHome()` is always `true`.

This is the most damaging single defect after Finding 1, because it teaches the opposite of the intended lesson: students learn that robot state is decorative and that "mission complete" means nothing. And they cannot debug it — the broken code is in the section marked *"These methods simulate robot hardware"* that they're told not to modify. Challenge 12 then asks them to "Return home" and "Print robot status," which will silently show a robot that never left.

### Finding 7 — Method overloading is introduced silently, twice. 🟠

`moveForward()` and `moveForward(int spaces)` coexist from Session 5 Step 4 with no acknowledgment that Java permits two methods of the same name. Same again in Session 6 with `move()` / `move(int)` (`Session6-VirtualRobot.java:145`, `:192`). For a 12-year-old, "there are two things called the same name and Java picks one" is a major concept arriving as an unremarked accident.

### Finding 8 — The coordinate system changes three times, and "north" flips sign. 🟠

| Session | Notation | "North" is |
|---|---|---|
| 4 | `[row][column]`, row 0 top | (no heading concept) |
| 5 | `[row][column]` | `currentRow--` |
| 6 | `(x, y)` | `y++` |
| 7–8 | inches / degrees | (no position concept) |

Nothing in the robotics domain justifies this churn; it is pure cognitive load. None of the three matches FTC field convention either. Pick `(x, y)` in inches with heading in degrees at Session 4 and never change it.

### Finding 9 — Session 7 is a telemetry lesson wearing a TeleOp label, and it stops one line short. 🔴

All ten Session 7 challenges read the gamepad and **print it**. The robot does not respond to the driver in any way. Challenge 6 is the painful one:

```java
double drivePower = -gamepad1.left_stick_y;   // :694
// TODO: if turboMode is false: drivePower = drivePower * 0.5;   // :711-721
telemetry.addData("Joystick Power", drivePower);   // :729
```

Students compute a drive power, scale it for turbo mode… and **throw it away**. The brief's required progression is `gamepad → motor power`; this is that exact lesson, halted immediately before the payoff. One line (`leftMotor.setPower(drivePower)`) separates "we printed a number" from "we drove the robot," and it is the line the entire curriculum exists to reach.

There is also a **dispatch bug**: `basicDashboard()` is called unconditionally at `:336`, *after* the challenge calls. So a student who uncomments `challenge8()` ("Build the Dashboard") gets their dashboard plus the stock dashboard appended underneath, on every loop.

### Finding 10 — Session 8 teaches an FTC pattern students will have to unlearn. 🟠

`MissionRobot` is a **non-static inner class** of the OpMode (`Session8-FTCMiniSW:390`), reaching outward into the OpMode's `telemetry`, `sleep()`, and mutable `searchAttempts`. `detectTarget()` decides whether a *sensor* sees a target by reading the outer class's loop counter (`:488`).

Session 8 is the one place that should model the real thing — a top-level `Robot` class whose constructor takes `HardwareMap` — and instead it models tight coupling to the OpMode. It also calls itself the capstone "Autonomous Mission" while being a straight-line script with no motors, no encoders, no timers, and no state machine.

### Finding 11 — Debugging peaks at Session 2 and then disappears. 🟠

Sessions 1–3 have genuinely excellent broken-code challenges. `Session2-Challenge.java:24-30` is the best teaching artifact in the repo:

```java
String robotName = Iron Angel;      // missing quotes
int batteryPercent = "87";          // String into int
double motorPower = 75;             // "full speed"? it's 7500%
boolean targetReached = "false";     // String into boolean
```

Four distinct error classes, all in FTC vocabulary, all discoverable. Then **Sessions 4–8 contain zero deliberate bugs** — exactly when programs grow long enough that debugging is the skill that matters. The brief asks for debugging in *every* session; the repo has it in the first three only.

### Finding 12 — Telemetry arrives at Session 7; it belongs in Session 2.

Session 2 is literally titled **"Robot Dashboard"** and builds a dashboard out of `System.out.println`. That *is* telemetry, in the wrong API. Every `println` in Sessions 1–6 is a `telemetry.addData` that students could be writing instead, meaning the debugging tool they most need arrives five sessions after the dashboard lesson that motivates it.

---

### Also worth fixing (lower severity)

- **`Session3-RobotDecisionEngine.java:58-68`** — the two `targetDetected` branches print *identical* output (`"DECISION: APPROACH TARGET"`), so the `> 20` / `<= 20` distinction is behaviorally meaningless. The Challenge file (`Session3-Challenge.java:88-97`) correctly fixes this to `COLLECT TARGET`. **The teaching file is the weaker of the two** — port the fix back.
- **`Session3-RobotDecisionEngine.java:53-56`** — `obstacleDetected → DECISION: SEARCH`. Robotically wrong: an obstacle should trigger *avoid/go around*, not *search*. Students reason from robot behavior, and this branch doesn't make sense as robot behavior.
- **Session 6 Challenges 1, 5, and 10 have students write no code at all.** They are `println` lectures — *"A class is a blueprint"* (`Session6-VirtualRobot.java:498`), *"this.x means the robot's x"* (`:744`). This is precisely the abstract-OO-theory-first approach the brief warns against.
- **Nested `break` trap (`Session4-SearchBot_solution.java:422-476`)** — correctness depends on the outer loop's `row < 4 && !targetFound`, because `break` exits only the inner loop. This is a great *deliberate* debugging exercise and currently passes by silently. Make it explicit: have students remove `&& !targetFound`, observe the robot searching past the target, and fix it.
- **Session 5 `search()` (`:295`)** uses bare `return;` in a `void` method, making the outer `&& !targetFound` guard dead code. Early-return is a real concept arriving unannounced.
- **`Session7:97-103`** — the `@TeleOp` annotation is separated from the class declaration by four blank lines and a comment block. Legal, but it visually detaches the annotation from the thing it annotates, and annotation-binding is already a confusing idea.

---

## 2. Current Curriculum Scorecard

| Dimension | Score | Notes |
|---|---|---|
| Java progression | **3 / 5** | Sessions 1→2→3 are a model ramp. Breaks at 4 (overloaded) and 4→5 (methods used before taught). |
| FTC relevance | **2 / 5** | Vocabulary is genuinely excellent; hardware is entirely absent. |
| Middle-school accessibility | **2 / 5** | Sessions 1–3 would score 5. Sessions 4–8 are 900–1,300-line files with 12–24 exercises each. |
| Hands-on learning | **1 / 5** | Console-only through Session 6; Sessions 7–8 are explicitly hardware-free. The robot never moves. |
| Blocks → Java transition | **1 / 5** | Does not exist. Zero mentions of Blocks anywhere. |
| Debugging | **2 / 5** | Strong in 1–3 (would be 4), absent in 4–8. |
| Autonomous preparation | **2 / 5** | Session 8 is a linear script. No state machine, encoders, timers, or real sensors. |
| TeleOp preparation | **2 / 5** | Gamepad *reading* taught well; gamepad → motor never closed. |
| Code architecture | **2 / 5** | `static` everything through Session 5; inner-class Robot in Session 8; 7 class-name collisions. |
| Student confidence | **3 / 5** | Good robot framing and vocabulary, but students finish unable to make the robot move. |

**Overall: 2.0 / 5 — a strong pedagogical skeleton with the robot missing.**

This score should not be read as "start over." The *instincts* here are better than most rookie curricula I've seen: every variable is `motorPower` / `batteryPercent` / `targetFound` / `distanceToTarget` / `obstacleDetected`, never `studentAge` or `shoppingCart`. The robot-mission framing is consistent. Sessions 1–3 are legitimately well-built and should be kept nearly as-is. The problem is that the curriculum is a **robot-themed Java course** rather than an **FTC programming course** — and the gap between those two is about six lines of `hardwareMap` code that were never written.

---

## 2b. FTC Ramp Map (actual, derived from the repo)

The brief's example mapping assumed Session 3 = if/else, 4 = loops, 5 = methods, 6 = classes, 7 = OpMode, 8 = integration. **The repo's actual mapping matches that assumption closely** — the sequencing instinct is right. What differs is *what's inside* each session: Session 4 carries four sessions' worth of content, methods are used in Session 4 before being taught in Session 5, and the "FTC connection" column is simulated rather than real from Session 1 through Session 8.

| Session | Java Skill | Current Difficulty | FTC Connection | Student Risk | Recommended Change | Robot Activity |
|---|---|---|---|---|---|---|
| **1** Mission Control | `println`, statements, `;`, `main()` | 🟢 correct | Mission sequence *(narrated, not run)* | `public static void main` is 5 unexplained keywords FTC never uses; no Blocks link | Add Blocks↔Java side-by-side; label `main()` as temporary debt, retired at S4 | **None** → run their existing Blocks OpMode on the robot, then read the Java |
| **2** Robot Dashboard | variables, `int`/`double`/`boolean`/`String` | 🟢 correct | Robot state → dashboard *(wrong API: `println`)* | Builds a "dashboard" with the non-FTC tool; `motorPower = 75` safety issue unremarked | **Rewrite in `telemetry`** — pulls the FTC framework 5 sessions earlier | **None** → deploy a telemetry OpMode, read own dashboard on Driver Station |
| **3** Decision Engine | `if`/`else if`/`else`, comparisons, `&&`, `!` | 🟢 correct | Sensor decisions *(hardcoded booleans)* | Two branches print identical output; `obstacle → SEARCH` is robotically wrong; `!` unexplained | Fix both logic defects; teach `!` explicitly | **None** → **add a real distance sensor**; watch the decision flip live |
| **4** SearchBot | `for`, `while`, counters, nested loops, `break` *(+ re-teaches boolean, if/else)* | 🔴 **~8 concepts, 24 exercises, 1,700 lines** | "FTC calls `loop()`" *(explained well, never used)*; grid search is not an FTC task | Overload; **uses methods + parameters a full session before S5 teaches them**; nested `break` is the repo's hardest topic; 2 files ~80% duplicated | **Split into 4A `for` / 4B `while`+`opModeIsActive` / 4C nested+`break` (after methods)**; drop redundant steps 6–8; merge the duplicate files | **None** → `for` loop drives the robot in a **square on the field** |
| **5** Command Library | methods, parameters, return values, *(silently: overloading, early `return`)* | 🟡 right concept | Reusable robot commands *(good names, no hardware)* | **Simulation is broken — Mission 5 is a no-op reporting success (verified)**; bug sits in code students are told not to touch; overloading unnamed | **Turn the broken movement into the debugging exercise**; name overloading; put methods on real motors | **None** → `driveForward()`/`stop()` as real `setPower` calls |
| **6** Virtual Robot | classes, objects, constructors, `this`, `private`, getters | 🟡 good instinct | "Robot object" *(a simulation that gets discarded)* | Challenges 1, 5, 10 = **zero code written**, pure OO theory; `private`/`this` premature; coordinates contradict S5 (`NORTH` flips sign) | Cut C1/C5/C10; **lead with C9 (Two Robots) — best exercise in the repo**; defer `private`; make it the **real `Robot(HardwareMap)`** | **None** → `Robot` class owning motors; one class used by TeleOp *and* auto |
| **7** Iron Angel TeleOp | `LinearOpMode`, `runOpMode`, `waitForStart`, `opModeIsActive`, `telemetry`, `gamepad1` | 🔴 **stops one line short** | Real FTC framework — **and explained better than most curricula** | **No motors** (`:211` "intentionally NOT using hardware"); C6 computes `drivePower`, scales it, **discards it**; `basicDashboard()` runs unconditionally at `:336`, shadowing every challenge; 6 of 10 challenges are "add telemetry" | **Add `leftMotor.setPower(drivePower)`** — full redesign in §6. Keep the framework explanation verbatim | **None** → **drive the robot with the gamepad** |
| **8** FTC Mini SW Challenge | integration, `@Autonomous`, `isStopRequested()` | 🟠 right ambition | Autonomous *(simulated: `moveForward` = `sleep(300)`)* | **No `.java` extension — cannot compile**; `MissionRobot` is an inner class coupled to the OpMode; `detectTarget()` reads the outer loop counter; `missionStep` is a telemetry label, **not a state machine**; `detectObstacle()` hardcoded `true` makes the `else` unreachable | Rename file; promote `Robot` to top-level taking `HardwareMap`; **build a real `enum` state machine**; add a debugging capstone | **None** → full autonomous with encoders, real sensor, timeouts |

**The column that tells the story:** *Robot Activity* reads **"None"** for all eight sessions. That is Finding 1 in one column.

---

## 3. Session-by-Session Review

Each session is assessed against the 10 review questions and the Level 0–6 ramp.

---

### Session 1 — Mission Control 🟢 *Keep, with one addition*

| | |
|---|---|
| **Java concept** | Statements, `println`, semicolons, program structure |
| **FTC connection** | Mission sequencing (mirrors autonomous step lists) |
| **Levels hit** | L2 ✅, L5 ✅ (challenge file) · **L0 ❌ L1 ❌ L3 ⚠️ L4 ❌ L6 ⚠️** |

**What works.** 34 lines. One idea. The mission-sequence framing ("wake up robot / check battery / move forward / detect obstacle / stop") is exactly right — it *is* an autonomous routine in comment form, which sets up Sessions 3 and 8. The challenge file removing three semicolons is a perfect first debugging exercise, and Challenge 3 ("can you think how to create a method `startMission()`") plants methods early without teaching them.

**Problems.**
1. `public static void main(String[] args)` — five unexplained keywords in the first line students ever see, for a construct FTC never uses.
2. No Blocks connection. This session's whole job should be "your Blocks stack, in text."
3. Sequence is *printed*, not *performed*. The robot is a narrator.

**Changes.**
- Open with a **side-by-side Blocks screenshot and Java text** of the same 5-step sequence. Name the goal: *"Same instructions. Different notation. That's all Java is today."*
- Give students a one-page **"syntax survival card"** — `;` = end of instruction, `{}` = group of instructions, `//` = note to humans — and explicitly tell them `public static void main` is a spell they may copy without understanding for now, *because we will delete it in Session 4.* Naming the debt prevents the Session 7 betrayal.
- **Robot activity:** run the equivalent Blocks OpMode on the actual robot, then read the Java that would replace it. Robot moves in session 1 — in Blocks — so "Java" and "robot" are linked from the start.

---

### Session 2 — Robot Dashboard 🟢 *Keep the challenge file; upgrade to telemetry*

| | |
|---|---|
| **Java concept** | Variables; `int` / `double` / `boolean` / `String`; concatenation |
| **FTC connection** | Robot state → driver dashboard |
| **Levels hit** | L2 ✅, L3 ✅, L5 ✅✅ · **L0 ❌ L1 ❌ L4 ❌ L6 ❌** |

**What works.** Best-chosen variables in the repo: `robotName`, `batteryPercent`, `motorPower`, `targetReached`. The `motorPower` comment block (`:22-24`) explaining `-1.0 / 0.0 / 1.0` is excellent and is the single most important number in FTC. `Session2-Challenge.java` is the strongest teaching artifact in the repo (see Finding 11) — four distinct type errors in FTC vocabulary.

**Problems.**
1. This is a dashboard built with `System.out.println`. The FTC dashboard is `telemetry`. Wrong API for the right idea, five sessions early.
2. `double motorPower = 75;` in the challenge is subtle and *good* (it compiles, it's just 7500% power) — but nothing prompts students to notice why it's dangerous. That's a safety lesson going spare.
3. Challenge 5 asks for "at least 8 variables … 2 int, 2 double, 2 boolean, 2 String" — a checkbox exercise with no robot purpose. Students will invent filler.

**Changes.**
- **Rewrite in `telemetry`.** This session should be the first OpMode: `telemetry.addData("Battery", batteryPercent)`. This single move pulls the FTC framework 5 sessions earlier and replaces every `println` in the curriculum with the tool students actually debug with.
- Teach **useful vs. useless telemetry** here, while the dashboard is the topic: `telemetry.addLine("HERE")` vs `telemetry.addData("Arm Position", armPosition)`.
- Replace Challenge 5 with: *"Your driver says they can't tell why the robot won't move. Build the dashboard that answers their question."* Same variable count, real motivation.
- Add a `motorPower = 75` **safety discussion**: what would the real robot do? (`setPower` clips to 1.0 — so it silently works, which is worse.)

---

### Session 3 — Robot Decision Engine 🟢 *Keep; fix the logic; add a real sensor*

| | |
|---|---|
| **Java concept** | `if` / `else if` / `else`, comparisons, `&&`, `!` |
| **FTC connection** | Sensor-driven decisions |
| **Levels hit** | L2 ✅, L3 ✅, L5 ⚠️ · **L0 ❌ L1 ❌ L4 ❌ L6 ❌** |

**What works.** The decision tree drawn as ASCII art in `Session3-Challenge.java:4-22` is outstanding — it's the mental model, on the page, before the code. Battery-first priority ordering is authentic FTC reasoning. The four scenarios ("low battery / target found / no target / obstacle") are proper tracing exercises: students predict, then run.

**Problems.**
1. **The two `targetDetected` branches produce identical output** (`:58-68`) — the distance comparison changes nothing. The Challenge file fixes this; the teaching file didn't get the fix.
2. **`obstacleDetected → SEARCH` is robotically wrong.** An obstacle means avoid, not search. Students reason from behavior, and this branch doesn't behave sensibly.
3. `targetDetected` and `obstacleDetected` are hardcoded `boolean` literals. The session is *about* sensor decisions and contains no sensor.
4. `&&` and `!` are introduced with no fanfare — `!targetDetected` (`Challenge:83`) is a genuine leap for a 12-year-old ("not" as a symbol in front of a thing).

**Changes.**
- Port the Challenge file's `COLLECT TARGET` branch back into the teaching file, and change the obstacle branch to `AVOID`.
- Teach `!` explicitly with a boolean-flip prediction drill before it appears in a condition.
- **Robot activity — this is the highest-value addition to Sessions 1–3.** Put a real distance sensor on the robot:
  ```java
  double distanceToWall = distanceSensor.getDistance(DistanceUnit.CM);
  telemetry.addData("Distance", distanceToWall);
  if (distanceToWall < 20) { telemetry.addLine("DECISION: STOP"); }
  ```
  Students walk a hand toward the sensor and watch the decision flip on the Driver Station. Sensor → variable → decision, physically visible, with no motors needed yet. This is the moment `if` stops being syntax.

---

### Session 4 — SearchBot 🔴 *Split into three sessions*

| | |
|---|---|
| **Java concept** | `for`, `while`, counters, nested loops, `break` (+ re-teaches `boolean`, `if/else`, comparisons) |
| **FTC connection** | Repeated actions; grid search; "FTC calls `loop()` repeatedly" |
| **Levels hit** | L2 ✅, L3 ⚠️, L5 ❌ · **L0 ❌ L1 ❌ L4 ❌ L6 ❌** |

**What works.** Step 1 → Step 2 is the best-motivated transition in the repo: write `moveForward(); checkSquare();` four times by hand, *feel* the tedium, then get the `for` loop as relief. That's how you teach loops. The scaffolded fill-in-the-blank (`for (____; ____; ____)`) is well-judged. The 4×4 ASCII grid is concrete. The `explainFTCBehavior()` section correctly distinguishes *"Java loops repeat our instructions"* from *"FTC repeatedly calls `loop()`"* — a distinction most rookie curricula never make, and it matters enormously.

**Problems.**
1. **~8 concepts, 24 exercises, 1,700 lines across two files.** 4× the cognitive budget. This is not a session; it's a unit.
2. **Redundancy:** Steps 6 (`boolean`), 7 (`if/else`), and 8 (comparisons) re-teach Sessions 2 and 3.
3. **Methods used a full session before Session 5 teaches them**, including the parameterized `moveToSquare(row, column)`, under "DON'T CHANGE THESE YET."
4. **Nested loops + `break` are the hardest topic in the repo** — harder than Session 6's classes — and land here, in the middle of a loops introduction.
5. **The two files are ~80% duplicates.** `Session4-SearchBot.java` (steps) and `Session4-Challenge.java` (challenges) walk the same progression twice with different numbering (challenge 4 = step 5, challenge 7 = step 9…). A coach cannot tell which to assign, and students who do both do everything twice.
6. **Grid search is not an FTC task.** No FTC robot searches a 4×4 grid; it drives to known field positions and looks for one game element. The nested-loop content is a computer-science exercise wearing a robot costume — the clearest instance of "abstract concept that could have been taught with a robot" not being.
7. Zero deliberate bugs, in the first session complex enough to need them.

**Changes — split into 4A / 4B / 4C:**

- **4A — `for` loops = repeated robot actions.** Keep Steps 1–4 exactly as written; they're excellent. Drop Steps 6–8 (already taught). **Robot activity:** `for (int i = 0; i < 4; i++) { driveForward(12); turnRight(90); }` — the robot drives a square. A loop that produces a visible square on the field is worth twenty console loops.
- **4B — `while` loops and the OpMode loop.** Steps 5 and 13, expanded. The headline idea: `while (opModeIsActive())` is the most important loop in FTC and it is a `while` loop students can now read. Teach the infinite-loop failure mode on purpose — forget the counter increment, watch the Driver Station hang, learn why `opModeIsActive()` exists.
- **4C (optional / advanced) — nested loops and `break`.** Move *after* methods. Retarget from grid search to something FTC-shaped: a scan sweep, or 3 attempts × 2 retries. Make the `break` trap a deliberate exercise: delete `&& !targetFound`, watch the robot search past the target, diagnose it.
- **Merge the two files into one**, with solutions in `solutions/`.

---

### Session 5 — Robot Command Library 🟡 *Right concept, broken implementation*

| | |
|---|---|
| **Java concept** | Methods, parameters, return values, class-level state, (silently) overloading + early return |
| **FTC connection** | Reusable robot commands |
| **Levels hit** | L2 ✅, L3 ✅, L6 ✅ · **L0 ❌ L1 ❌ L4 ❌ L5 ❌** |

**What works.** The framing is exactly what the brief asks for: *"Teach the robot a skill once. Give it a name. Reuse it."* Method names are authentic FTC (`pickUpPackage`, `deliver`, `returnHome`, `scoreGamePiece`, `collectSample`). `turnAround()` calling `turnRight()` twice (`:186`) is a lovely, tiny demonstration of composition. The `deliver()` guard — *"ERROR: Robot has no package!"* (`:435`) — teaches defensive checks in a way students will recognize from real matches. Challenge 5's constraint ("test `moveForward(2)`, `(5)`, `(1)` **without** creating `moveForwardTwo()`") nails why parameters exist. This session is the conceptual high point of the curriculum.

**Problems.**
1. **The simulation is broken (Finding 6, verified).** Two disconnected movement systems; `direction` is decorative; `returnHome()` can never act; Mission 5 is a positional no-op reporting success. Students learn that robot state is meaningless, and cannot debug it because the bug is in protected code.
2. **Overloading introduced silently** (Finding 7).
3. **Early `return;`** in `void search()` (`:328`) is a new control-flow concept passing unremarked, and it makes the outer `&& !targetFound` guard dead.
4. 20 steps + 12 challenges again (1,800 lines, two files, duplicate class names).
5. `turnLeft()`/`turnRight()` are 20-line `if/else if` chains over `String` direction — verbose, and `String`-as-enum is a habit worth not forming.
6. Still zero hardware, in the session whose entire subject (`driveForward()`, `openClaw()`) is the natural first hardware lesson.

**Changes.**
- **Fix the simulation first, and make the fix a lesson.** Have `moveForward()` respect `direction` and update position — as a **student debugging challenge**, which is exactly the kind of bug FTC students meet constantly ("the robot says it moved but it's in the wrong place"):
  > *"Run Mission 5. The robot reports `At home: true` after a delivery. It never left. Find out why."*

  This converts the repo's worst defect into its best debugging exercise. Single most valuable change available in Session 5.
- **Name overloading** when `moveForward(int)` appears: *"Java lets two methods share a name if they take different information. It picks by what you hand it."*
- **Teach methods on real motors.** This is where the curriculum should cross into hardware:
  ```java
  void driveForward(double power) { leftMotor.setPower(power); rightMotor.setPower(power); }
  void stop()                     { leftMotor.setPower(0);     rightMotor.setPower(0); }
  ```
  Now `driveForward()` / `stop()` / `openClaw()` are *named robot actions* that actually act — and the Blocks student recognizes them as their own Blocks blocks.
- Replace `String direction` with `int headingDegrees`, or keep `String` but drop the 20-line chains.

---

### Session 6 — Virtual Robot 🟡 *Good instinct, too much theory, wrong `Robot`*

| | |
|---|---|
| **Java concept** | Classes, objects, constructors, `this`, `private`, getters, instance state |
| **FTC connection** | "Robot object"; sensor method returning `boolean` |
| **Levels hit** | L2 ✅, L3 ⚠️, L6 ✅ · **L0 ❌ L1 ❌ L4 ❌ L5 ❌** |

**What works.** `OBJECT = STATE + BEHAVIOR` (`:14`) is the right one-line framing. **Challenge 9 (Two Robots) is the best-designed exercise in the repo** — two robots, move one, observe the other is unaffected, then *"Why did Robot A's position not change when Robot B moved?"*. That is how you teach instance state: by discovery, not definition. Making every challenge independently runnable (each creates its own `Robot`) is thoughtful engineering that avoids a whole class of confusing state bugs. The battery-cost model (move = 5%, turn = 2%, scan = 3%) is a genuinely clever touch — it makes state changes *consequential*, and it mirrors a real FTC concern.

**Problems.**
1. **Challenges 1, 5, and 10 have students write no code.** They print definitions: *"A class is a blueprint," "this.x means the robot's x," "Private variables protect the object's state."* This is academic OO theory delivered as `println`, which the brief explicitly rules out.
2. **`this`, `private`, and encapsulation are premature.** A rookie has no mental model for a problem that `private` solves — nobody has yet been burned by external code corrupting state. Teach `private` the day someone writes `robot.battery = 200;` and breaks a match.
3. **`(x, y)` with `NORTH → y++` contradicts Session 5's `[row][column]` with `NORTH → currentRow--`** (Finding 8).
4. **This `Robot` class is not the FTC `Robot` class.** It's a self-contained simulation. The FTC `Robot` students need takes a `HardwareMap` and owns motors. Session 6 spends a whole session on a `Robot` that must be discarded.
5. Overloading appears again unremarked (`move()` / `move(int)`).
6. Two files, duplicate class names, 13 challenges each.

**Changes.**
- **Cut Challenges 1, 5, 10.** Keep the *content* as a 3-minute coach explanation; don't spend student exercises on it.
- **Defer `private`/getters/`this` to a later "protect the robot" session,** motivated by a real bug.
- **Lead with Challenge 9.** Two robots, discover instance state, *then* name the concept. Reorder so discovery precedes vocabulary.
- **Make it the real `Robot`.** Restructure so the class students build is the one they keep:
  ```java
  public class Robot {
      private DcMotor leftMotor, rightMotor;
      public Robot(HardwareMap hardwareMap) {
          leftMotor  = hardwareMap.get(DcMotor.class, "leftMotor");
          rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
      }
      public void driveForward(double power) { … }
  }
  ```
  Then `robot.driveForward(0.5)` in an OpMode. *"We are building a software robot that knows how to control our real robot"* — the brief's exact framing, and it survives into competition code.
- Unify coordinates with Session 4/5.

---

### Session 7 — Iron Angel TeleOp 🔴 *Weakest lesson relative to its importance — redesigned in §6*

| | |
|---|---|
| **Java concept** | `LinearOpMode`, `@TeleOp`, `runOpMode()`, `waitForStart()`, `opModeIsActive()`, `telemetry`, `gamepad1`, helper methods |
| **FTC connection** | The FTC framework itself |
| **Levels hit** | L2 ✅, L3 ✅ · **L0 ❌ L1 ❌ L4 ❌ L5 ❌ L6 ⚠️** |

**What works.** The framework explanation is the best thing in the file and genuinely good pedagogy: *"Java programs normally start at `main()`. FTC programs work differently. The FTC Robot Controller calls `init()` / `loop()`"* (`:29-40`), with the vertical `loop / loop / loop` diagram (`:251-258`). The closing student questions (`:1203-1211`) are superb — *"Who is calling our code?" "Why don't we need `main()`?" "Why do we call `telemetry.update()`?"* — these are exactly the questions that separate a student who can modify FTC code from one who can't. `loopCounter` incrementing visibly on the Driver Station is a clever, concrete proof that the loop is real.

**Problems.**
1. **No motors.** The TeleOp session's robot does not respond to the driver (Finding 1, Finding 9). `:211` — *"We are intentionally NOT using hardware yet."*
2. **Challenge 6 computes `drivePower`, scales it for turbo, and discards it.** One line from the entire point of the curriculum.
3. **`basicDashboard()` runs unconditionally at `:336`,** appending to every challenge's output. Challenge 8 asks students to build a dashboard that will be shadowed by the built-in one.
4. **10 challenges, 1,272 lines**, and challenges 1–4, 8, and 9 are all "add things to telemetry" — one idea, six times.
5. The turbo pattern `if (B) turbo = true; else turbo = false;` is momentary (turbo only while held). Real TeleOp wants a toggle, which needs edge detection — a genuinely useful, genuinely teachable FTC idea that's skipped.
6. `hardwareMap` appears only as a comment.

**Change:** full redesign in §6. Headline: students write `leftMotor.setPower(drivePower)` and drive the robot in this session.

---

### Session 8 — FTC Mini Software Challenge 🟠 *Right ambition, no hardware, no state machine*

| | |
|---|---|
| **Java concept** | Integration; `@Autonomous`; mission decomposition; `isStopRequested()` |
| **FTC connection** | Autonomous routine |
| **Levels hit** | L2 ✅, L3 ✅, L6 ⚠️ · **L0 ❌ L1 ❌ L4 ❌ L5 ❌** |

**What works.** Mission decomposition into `leaveBase()` → `findTarget()` → `navigateObstacle()` → `collectObject()` → `returnHome()` is authentic autonomous structure and exactly the right shape for a capstone. `isStopRequested()` checks between phases (`:128`, `:141`, …) is **real, safety-critical FTC practice** that most rookie curricula omit entirely — genuinely impressive that it's here. The `updateTelemetry()` helper dumping all mission state at once (`:364-373`) is precisely the autonomous debugging technique students need. Bounded retry in `findTarget()` (`while (!targetFound && searchAttempts < maxSearchAttempts)`, `:236`) teaches that autonomous must give up rather than hang — an excellent, hard-won lesson. Guard clauses that abort the mission when a precondition fails (`:146`, `:181`) model defensive autonomous thinking.

**Problems.**
1. **The file has no `.java` extension** and its name doesn't match `Session8_AutonomousMission`. It cannot compile as delivered.
2. **No hardware.** `moveForward(int)` is `telemetry` + `sleep(300)`. The capstone autonomous never moves the robot.
3. **`MissionRobot` is a non-static inner class** coupled to the OpMode's `telemetry`, `sleep()`, and `searchAttempts` (Finding 10) — a pattern to unlearn, in the session that should model the pattern to keep.
4. **`detectTarget()` reads the outer class's `searchAttempts`** to decide what a "sensor" sees (`:488`). Students cannot reason about it as a sensor.
5. **It is not a state machine.** `missionStep` is assigned 1…6 (`:123`, `:136`, …) and used *only for telemetry* — a straight-line script with a progress label. The brief asks for `START / DRIVE_TO_TARGET / SEARCH / COLLECT / SCORE / PARK / DONE`; this is the session where that belongs.
6. **No deliberate bugs and no debugging exercise** in the integration capstone.
7. `detectObstacle()` hardcoded `return true` (`:507`) — the `else` branch of `navigateObstacle()` is unreachable, so half the logic students read never executes.

**Changes.**
- Rename to `Session8_AutonomousMission.java`.
- **Promote `MissionRobot` to a top-level `Robot` class taking `HardwareMap`** — the Session 6 class, now real. Motors in the constructor, `driveInches()` using encoders, real sensors behind `detectTarget()`.
- **Make it an actual state machine** with a named `enum` and a `switch` in one loop. This is the session's real Java concept and it's currently absent.
- **Add a debugging capstone:** hand students an autonomous that fails at step 3 and have them diagnose it from telemetry alone. That is the FTC competition skill.
- Make `detectObstacle()` read a real sensor so both branches live.

---

## 4. Missing Bridge Concepts

Assessed against the brief's candidate list — which are *genuinely* missing, not merely absent.

### Critical — add these

| # | Missing lesson | Why it's critical |
|---|---|---|
| 1 | **Blocks → Java bridge** | Level 0 of the stated ramp. Zero coverage. Students' entire prior mental model is unused, so Java arrives as an unrelated subject rather than a new notation for what they know. |
| 2 | **`hardwareMap` / first motor** | The missing keystone. Appears only in comments. Without it *nothing* students write can affect the robot — this single gap causes Finding 1. |
| 3 | **Java syntax survival guide** | `;` `{}` `.` `()` `""` are never explained, yet every error message is about them. The brief explicitly asks: why the dot? why parens? why the semicolon? Currently unanswered. |
| 4 | **Autonomous state machine** | Session 8 has `missionStep` as a telemetry label but no state machine. The organizing idea of competitive autonomous is absent. |
| 5 | **Sensors → decisions (real hardware)** | Session 3 teaches sensor decisions with hardcoded booleans. No sensor is ever read. |
| 6 | **Debugging as a recurring practice** | Present in Sessions 1–3, absent in 4–8. Needs a taught process (Observe → Reproduce → Describe → Locate → Change ONE thing → Test → Explain) applied every session. |

### Partly present — strengthen

| # | Lesson | Status |
|---|---|---|
| 7 | **Telemetry debugging** | Exists (Sessions 7–8) but 5 sessions late and taught as output, not as a debugging instrument. Move to Session 2; add useful-vs-useless. |
| 8 | **Gamepad → TeleOp** | Reading ✅, acting ❌. Close the loop to `setPower`. |
| 9 | **Classes → `Robot` object** | Session 6 builds a *simulated* `Robot`. Needs to become the `HardwareMap`-owning one. |
| 10 | **Code organization** | Implicit in Session 5's library idea; undermined by 900–1,300-line files and 7 class-name collisions. Model good structure rather than describing it. |
| 11 | **Encoders / accurate movement** | Absent. `moveForward(24)` implies inches with no mechanism. Needed before autonomous is real. |

### Deliberately omit at this stage

- **Inheritance** — `extends LinearOpMode` is the only inheritance rookies need, and "the SDK gives us a starting robot program" is sufficient explanation. Do not teach class hierarchies.
- **Interfaces, generics, collections, `ArrayList`** — no rookie FTC need.
- **PID / RoadRunner / trajectory libraries** — the brief is right; teach reasoning about autonomous first.
- **IMU** — after encoders, not before. Heading control is a second-season topic.
- **`private`/encapsulation as theory** — keep it, but motivated by a bug, not as doctrine.

---

## 5. Recommended 14-Session FTC Java Ramp

Design principles driving the restructure:

1. **The robot moves by Session 5**, not never.
2. **Blocks is Session 1's subject**, not an unmentioned prerequisite.
3. **Telemetry replaces `System.out.println` from Session 2** — students debug with the real tool all the way through.
4. **`main()` is explicitly labeled temporary** on day one and retired at Session 4; no bait-and-switch.
5. **1–2 new concepts per session**, hard cap.
6. **Every session has a deliberate bug.**
7. **One coordinate system** — `(x, y)` inches, heading degrees — from first use.
8. **Every class students build survives into competition code.**

| # | Title | Java | FTC | Diff |
|---|---|---|---|---|
| 1 | Blocks → Java: Same Robot, New Notation | statements, syntax anatomy | reading an OpMode | 🟢 |
| 2 | Robot Dashboard | variables, `int`/`double`/`boolean`/`String` | `telemetry`, first OpMode | 🟢 |
| 3 | Robot Decisions | `if`/`else if`/`else`, `&&`, `!` | sensor → decision (real sensor) | 🟢 |
| 4 | Repeat Yourself: `for` Loops | `for`, counters | repeated robot actions | 🟢 |
| 5 | **`hardwareMap`: Making the Robot Move** | objects/methods in use | `DcMotor`, `setPower` | 🟢 |
| 6 | Driver Control | `if` + `double`, joystick math | `gamepad1` → motor power | 🟡 |
| 7 | The FTC Loop | `while`, `opModeIsActive()` | the framework; who calls our code | 🟡 |
| 8 | Named Robot Actions | methods | reusable robot commands | 🟡 |
| 9 | Parameters & Answers | parameters, return values | `driveInches(24)`, `isTargetVisible()` | 🟡 |
| 10 | The Robot Class | classes, objects, constructors | `Robot(HardwareMap)` | 🟡 |
| 11 | Protecting the Robot | `private`, getters, `this` | safe subsystem boundaries | 🟠 |
| 12 | Driving Accurately | integer math, `while` + condition | encoders, `ElapsedTime` | 🟠 |
| 13 | Autonomous State Machine | `enum`, `switch` | autonomous architecture | 🟠 |
| 14 | **Graduation Challenge** | integration | full autonomous + TeleOp | 🟠 |

---

### Session 1 — Blocks → Java: Same Robot, New Notation 🟢

- **FTC story.** *"Our Blocks autonomous works. But the veteran team next to us has 400 lines of Java and can do things we can't. Today we find out their code isn't magic — it says the same things ours does."*
- **Java concepts.** Statement; `;`; `{}`; `//`; a program as an ordered list.
- **FTC concepts.** OpMode as a named, orderable program.
- **Already know.** Their own Blocks autonomous.
- **New.** Java is *notation*, not a new way of thinking.
- **Live coding.** Project their real Blocks stack. Beside it, write the Java line by line. Dissect one line completely (see §Blocks→Java Bridge below).
- **Hands-on.** Translation worksheet: 10 Blocks images → Java lines. Paper, no computer.
- **Robot.** Run their existing *Blocks* OpMode on the real robot, then read the Java equivalent. The robot moves in Session 1.
- **Debugging.** Three broken Java lines (missing `;`, missing `"`, missing `)`). Predict the error, then see it.
- **Extension.** Find `setPower` in a real team's GitHub TeleOp and explain what it does.
- **Deliverable.** Completed translation worksheet.
- **Checkoff.** Can point at any part of `leftMotor.setPower(0.5);` and say what it does.
- **Prereqs.** None.

> **Coach note:** Say out loud that `public static void main(String[] args)` is a temporary spell we will delete at Session 4. The current curriculum drills it for 6 sessions then reveals it's unused — that costs you credibility exactly when students need to trust you.

---

### Session 2 — Robot Dashboard 🟢

- **FTC story.** *"In our last match the robot stopped and nobody knew why. Was it battery? Was the target not seen? We were blind. Today we build the dashboard so that never happens again."*
- **Java.** Variables; `int`, `double`, `boolean`, `String`; concatenation.
- **FTC.** `telemetry.addData` / `telemetry.update()`; first real OpMode.
- **Already know.** Blocks telemetry blocks; Session 1 syntax.
- **New.** A variable is a labeled box holding one kind of thing.
- **Live coding.** `batteryPercent`, `motorPower`, `targetFound`, `robotState` → Driver Station.
- **Hands-on.** Build a 6-line dashboard. Then: *useful vs. useless telemetry* — replace `addLine("HERE")` with something a driver could act on.
- **Robot.** Deploy; read your own dashboard on the Driver Station.
- **Debugging.** Reuse `Session2-Challenge.java` verbatim — it's excellent. Add: *"`double motorPower = 75;` compiles fine. What happens on the real robot? Why is that worse than a crash?"*
- **Extension.** Add battery voltage from `hardwareMap.voltageSensor`.
- **Deliverable.** Working telemetry OpMode on the Driver Station.
- **Checkoff.** Can explain why `int` can't hold `0.5`, and why the driver cares.
- **Prereqs.** S1.

---

### Session 3 — Robot Decisions 🟢

- **FTC story.** *"Mission Control says the robot needs to know whether it has enough battery to start."*
  ```java
  if (batteryLevel > 30) { startMission(); } else { chargeRobot(); }
  ```
- **Java.** `if` / `else if` / `else`; comparisons; `&&`; `!`.
- **FTC.** Sensor reading → decision.
- **Already know.** Blocks `if` blocks; variables.
- **New.** A condition is a question with a yes/no answer.
- **Live coding.** Port the existing decision tree (ASCII art first — keep it, it's excellent), with the identical-branch and obstacle-logic bugs fixed.
- **Hands-on.** Trace all four scenarios by hand *before* running. Predict, then verify.
- **Robot.** **Real distance sensor.** Walk a hand toward it; watch `DECISION:` flip live on the Driver Station.
- **Debugging.** `=` vs `==`. Then: *"Why does the robot say RETURN HOME even at 85% battery?"* (branch order bug).
- **Extension.** Add a third condition with `&&`.
- **Deliverable.** Decision OpMode driven by a live sensor.
- **Checkoff.** Can explain `!targetFound` in plain English and why battery is checked first.
- **Prereqs.** S2.

---

### Session 4 — Repeat Yourself: `for` Loops 🟢

- **FTC story.** *"Autonomous has to drive a square around the field. Are we really writing the same four lines four times?"*
- **Java.** `for`; counters; `i++`.
- **FTC.** Repeated robot actions.
- **New.** One new idea only: a loop repeats a block a counted number of times.
- **Live coding.** Keep Session 4 Steps 1→4 as-is; the write-it-four-times-then-feel-the-pain motivation is the best in the repo.
- **Hands-on.** Fill-in-the-blank `for (____; ____; ____)`, as currently written.
- **Robot.** `for (int i = 0; i < 4; i++) { driveForward(12); turnRight(90); }` → the robot drives a square on the field. *(Uses Session 5's methods as given black boxes — flag this openly: "you'll build these next week.")*
- **Debugging.** Off-by-one: `i <= 4` drives five sides. Watch the robot get it wrong, then fix it.
- **Extension.** Triangle. Hexagon. What does the loop count have to be?
- **Deliverable.** Robot drives a square from a loop.
- **Checkoff.** Can say what `i` is, what it starts at, and when the loop stops.
- **Prereqs.** S3. **Retire `main()` here.**

---

### Session 5 — `hardwareMap`: Making the Robot Move 🟢 ⭐ *The missing keystone*

- **FTC story.** *"Everything so far has been words on a screen. Today the robot moves because of Java you wrote."*
- **Java.** Using an object; calling a method with an argument; `double` as power.
- **FTC.** `hardwareMap`, `DcMotor`, `setPower()`, configuration names.
- **New.**
  ```java
  DcMotor leftMotor = hardwareMap.get(DcMotor.class, "leftMotor");
  leftMotor.setPower(0.5);
  ```
- **Live coding.** Full dissection of both lines (§Blocks→Java Bridge). Show the robot config file; show the string `"leftMotor"` matching it. **The config-name-must-match lesson is the #1 cause of rookie FTC crashes** and belongs here, loudly.
- **Hands-on.** Drive forward at 0.3. Then 1.0. Then -0.5. Then both motors opposite → spin.
- **Robot.** The payoff moment. This is the day the curriculum is *for*.
- **Debugging.** **Deliberately misspell the config name.** Read the actual crash on the Driver Station. Every rookie hits this; they should hit it on purpose first, with a coach present.
- **Extension.** Add a `Servo`; open and close a claw.
- **Deliverable.** OpMode that drives the robot forward for 2 seconds and stops.
- **Checkoff.** Can explain what `hardwareMap` is *for*, and fix a config mismatch unaided.
- **Prereqs.** S2 (telemetry), S4.

> **Why here:** rookies need this to understand their first real OpMode, and every earlier session has been building state and decisions with no way to act on them. Currently this lesson does not exist at all.

---

### Session 6 — Driver Control 🟡

- **FTC story.** *"The driver wants the robot to move when they push the stick. Make it happen."*
- **Java.** `double` arithmetic; negation; `if` with gamepad booleans.
- **FTC.** `gamepad1.left_stick_y` → `setPower`; why the Y axis is inverted.
- **Live coding.**
  ```java
  double drivePower = -gamepad1.left_stick_y;
  leftMotor.setPower(drivePower);
  rightMotor.setPower(drivePower);
  telemetry.addData("Drive Power", drivePower);
  ```
  This is Session 7 Challenge 6 **with the last line added** — the line the current curriculum never writes.
- **Hands-on.** Turbo/precision scaling: `drivePower * 0.5` when B is not held. Then tank drive on two sticks.
- **Robot.** Drive the robot around the room with the gamepad. Highest-engagement session of the year.
- **Debugging.** Remove the minus sign. Robot drives backward. *Why?* Then: *"Why does the robot keep creeping when I let go?"* (stick drift → deadband).
- **Extension.** `if (gamepad1.left_bumper) { intake.setPower(1); } else { intake.setPower(0); }`
- **Deliverable.** Working driveable TeleOp.
- **Checkoff.** Can explain why `left_stick_y` is negated and what a 0.5 multiplier does.
- **Prereqs.** S5.

---

### Session 7 — The FTC Loop 🟡

- **FTC story.** *"Why does the robot keep responding? We only wrote the code once."*
- **Java.** `while`; loop conditions; infinite loops.
- **FTC.** `runOpMode()`, `waitForStart()`, `opModeIsActive()`, `isStopRequested()`; *the framework calls us*.
- **Live coding.** Keep Session 7's framework explanation and `loop / loop / loop` diagram verbatim — it's the best writing in the repo. Add the visible `loopCounter`.
- **Hands-on.** Delete `while (opModeIsActive())`. Robot responds once, then nothing. Restore it. *Now* the loop means something.
- **Robot.** Same TeleOp as S6, now understood rather than copied.
- **Debugging.** Write a `while` loop with no exit inside the OpMode; watch the Driver Station stop responding; learn why `opModeIsActive()` exists and why `isStopRequested()` is a safety feature.
- **Extension.** Toggle instead of momentary turbo (needs edge detection: remember last loop's button state). Genuinely useful FTC technique.
- **Deliverable.** Annotated OpMode explaining, in comments, who calls what.
- **Checkoff.** Answers Session 7's own closing questions: *"Who is calling our code? Why don't we need `main()`? Why `telemetry.update()`?"*
- **Prereqs.** S6.

---

### Session 8 — Named Robot Actions 🟡

- **FTC story.** *"Our TeleOp is 200 lines and we can't find anything. Also autonomous needs to drive forward too — do we copy-paste?"*
- **Java.** Writing and calling methods; `void`.
- **FTC.** `driveForward()`, `stop()`, `turnRight()`, `openClaw()` — as real motor code.
- **Live coding.** Extract the S6 motor lines into `driveForward(double power)` and `stopDriving()`. The body is code they already wrote.
- **Hands-on.** Extract 3 more. Rewrite TeleOp to call them. Same behavior, a third the length.
- **Robot.** Verify identical driving after refactoring — teaches that refactoring preserves behavior.
- **Debugging.** Write `driveForward()` that forgets one motor. Robot curves. Diagnose from telemetry.
- **Extension.** `turnAround()` calling `turnRight()` twice (keep this from Session 5 — it's a lovely composition example).
- **Deliverable.** TeleOp built from named actions.
- **Checkoff.** Can add a new robot action unaided.
- **Prereqs.** S7.

---

### Session 9 — Parameters & Answers 🟡

- **FTC story.** *"Autonomous needs to drive 24 inches, then 6. Do we write `driveForward24()` and `driveForward6()`?"*
- **Java.** Parameters; return values; **method overloading, named explicitly**.
- **FTC.** `driveInches(24)`; `boolean isTargetVisible()`.
- **Live coding.** `driveForward()` → `driveForward(double power)` → `driveInches(int inches)`. Name overloading when the second `driveForward` appears.
- **Hands-on.** Keep Session 5 Challenge 5's constraint: test `(2)`, `(5)`, `(1)` **without** writing three methods. It nails why parameters exist.
- **Robot.** `driveInches(24)` and measure with a tape measure. Accuracy is now visible, which motivates Session 12.
- **Debugging.** **Port the verified Mission-5 bug:** *"The robot reports `At home: true` after a delivery it never made. Find out why."* (Finding 6, converted into the exercise it should have been.)
- **Extension.** `boolean isAtTarget()` used in an `if`.
- **Deliverable.** `driveInches()` + one `boolean` query method.
- **Checkoff.** Can explain the difference between a method that *does* and one that *answers*.
- **Prereqs.** S8.

---

### Session 10 — The Robot Class 🟡

- **FTC story.** *"TeleOp and autonomous both need to drive. Right now the motor code lives in both. Let's build one software robot both can use."*
- **Java.** `class`, object, constructor, fields, `new`.
- **FTC.** `Robot(HardwareMap)` owning motors; `robot.driveForward(12)`.
- **New.** *"We're creating a software robot that knows how to control our real robot."*
- **Live coding.**
  ```java
  public class Robot {
      private DcMotor leftMotor, rightMotor;
      public Robot(HardwareMap hardwareMap) {
          leftMotor  = hardwareMap.get(DcMotor.class, "leftMotor");
          rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
      }
      public void driveForward(double power) { … }
  }
  ```
  Then in the OpMode: `Robot robot = new Robot(hardwareMap);`
- **Hands-on.** Move every motor call behind `robot.`. Rewrite TeleOp to use it.
- **Robot.** Same behavior, now via `robot.driveForward()`. Then write a 5-line autonomous using the *same* `Robot` — the reuse payoff (Level 6) made physical.
- **Debugging.** Forget `new Robot(hardwareMap)` → `NullPointerException`. Every FTC student meets NPE; meet it here.
- **Extension.** **Keep Session 6 Challenge 9 (Two Robots)** as a thought experiment for instance state — it's the best exercise in the existing repo.
- **Deliverable.** `Robot.java` used by both TeleOp and autonomous.
- **Checkoff.** Can explain what the constructor does and why `Robot` is better than copy-paste.
- **Prereqs.** S9.

---

### Session 11 — Protecting the Robot 🟠

- **FTC story.** *"Someone set motor power to 5.0 in autonomous and we shredded a gear. Let's make that impossible."*
- **Java.** `private`, getters, `this`, simple validation.
- **FTC.** Subsystem boundaries; clamping power.
- **New.** `private` exists to prevent a specific class of mistake — now demonstrated rather than asserted.
- **Live coding.** Make fields `private`; add `setDrivePower` that clamps to ±1.0.
- **Hands-on.** Try `robot.leftMotor = null;` → won't compile. *That's the point.*
- **Robot.** Verify clamping: command 5.0, observe 1.0 in telemetry.
- **Debugging.** Two methods fighting over the same motor. Diagnose.
- **Deliverable.** Encapsulated `Robot` with validated inputs.
- **Prereqs.** S10.

> **Why here, not Session 6:** `private` answers a question nobody has asked yet at Session 6. After a student has actually broken something, it lands in one sentence.

---

### Session 12 — Driving Accurately 🟠

- **FTC story.** *"`driveInches(24)` gave us 19 inches. The battery was lower. Autonomous can't work like this."*
- **Java.** Integer math; `while` with a compound condition; `ElapsedTime`.
- **FTC.** Encoders, `RUN_TO_POSITION`, ticks-per-inch, timeouts.
- **Live coding.** Ticks-per-inch derivation; `while (motor.isBusy() && opModeIsActive())`.
- **Hands-on.** Calibrate the constant with a tape measure. Real measurement, real math.
- **Robot.** Drive exactly 24 inches, three times, and measure.
- **Debugging.** Omit the timeout; stall the robot against a wall; autonomous hangs forever. **Then add the timeout.** This is the most valuable autonomous lesson in the curriculum.
- **Deliverable.** `driveInches()` accurate to ±1 inch, with a timeout.
- **Prereqs.** S10.

---

### Session 13 — Autonomous State Machine 🟠

- **FTC story.** *"Our autonomous is 150 straight-line lines. When it fails we have no idea where. And it can't react to what it sees."*
- **Java.** `enum`; `switch`; state transitions.
- **FTC.** `START → DRIVE_TO_TARGET → SEARCH → COLLECT → SCORE → PARK → DONE`.
- **Live coding.**
  ```java
  enum State { START, DRIVE_TO_TARGET, SEARCH, COLLECT, SCORE, PARK, DONE }
  State state = State.START;
  while (opModeIsActive() && state != State.DONE) {
      telemetry.addData("State", state);
      switch (state) {
          case DRIVE_TO_TARGET: robot.driveInches(24); state = State.SEARCH; break;
          case SEARCH: state = robot.seesTarget() ? State.COLLECT : State.PARK; break;
          …
      }
      telemetry.update();
  }
  ```
- **Hands-on.** Convert Session 8's existing linear mission into states. Its phases (`leaveBase`, `findTarget`, …) are already the right decomposition — this reveals that they were states all along.
- **Robot.** Run it; watch the state name change live on the Driver Station.
- **Debugging.** Forget `break` → fall-through. Or a transition to a state that never exits. Diagnose from the state telemetry — which is exactly why you print state.
- **Extension.** Bounded retry (keep Session 8's `maxSearchAttempts` — it's good).
- **Deliverable.** State-machine autonomous with state on telemetry.
- **Prereqs.** S12.

---

### Session 14 — Rookie FTC Java Graduation Challenge 🟠

See §7.

---

## 6. Example Improved Lesson — Session 7 Redesigned

**Why Session 7.** Session 4 has the worst cognitive overload, but Session 7 has the largest gap between *importance* and *delivery*. It is the Blocks→Java payoff, the framework transition, and the only session where the driver meets the code — and its robot does not move. Redesigned below as new **Session 6: Driver Control**, using the 9-part model. *(Session 4's fix is a 3-way split, specified in §3.)*

> **Retained from the original:** the `loop / loop / loop` diagram, the *"Who is calling our code?"* questions, and the visible `loopCounter`. These are genuinely good and appear here and in Session 7.

**Duration:** 90 min · **Prereqs:** `hardwareMap` + `setPower` (S5) · **Difficulty:** 🟡

---

### 1. Robot Hook (8 min)

Coach drives the robot around the room with a gamepad. Then stops and says:

> *"That took eleven lines of Java. You already know nine of them. By the end of today, every one of you will be driving this robot with code you wrote. And I'm not going to write it for you."*

Put the robot on blocks, gamepad on the table, and leave it visible all session.

### 2. What We Already Know (7 min)

Coach draws three columns on the whiteboard and fills the first two with students calling out answers:

| Blocks | Java (we know this) | Today's missing piece |
|---|---|---|
| `set left motor power to 0.5` | `leftMotor.setPower(0.5);` | |
| `gamepad1 left stick Y` | `gamepad1.left_stick_y` | |
| — | — | **connect them** |

> *"Last week you made the robot move at a power you typed. Today the driver chooses the power. That's the whole lesson. One line."*

### 3. One New Concept (12 min)

**A joystick is a `double` that changes every loop.**

Deploy a telemetry-only OpMode. Students pass the gamepad around and watch:

```
Left Stick Y : -0.87
```

Three questions, answered by experiment, not lecture:

1. What's the value at rest? *(≈0.0, maybe 0.02 — introduce drift.)*
2. Push **forward** — is it positive or negative? *(Negative. Surprise.)*
3. What's the biggest value you can get? *(≈1.0.)*

Then the reveal:

> *"Forward is negative. That's just how the hardware is wired. `setPower(1.0)` is forward. So if we hand the stick straight to the motor, pushing forward drives backward. We fix it with a minus sign — and that minus sign is the single most common bug in rookie FTC code."*

Write the anatomy on the board:

```java
double drivePower = -gamepad1.left_stick_y;
//     ↑            ↑ ↑        ↑
//  a box for    flip  the     the stick's
//  a decimal    sign  gamepad current value
```

### 4. Coach Live Coding (15 min)

Type it live, narrating. Deploy after **every** line so students see each step change the robot.

```java
while (opModeIsActive()) {
    double drivePower = -gamepad1.left_stick_y;   // 1. read the driver
    leftMotor.setPower(drivePower);               // 2. tell the motors
    rightMotor.setPower(drivePower);
    telemetry.addData("Drive Power", drivePower); // 3. show our work
    telemetry.update();
}
```

Stop after line 1 and ask: *"Robot on blocks — will it move? Why not?"* **(Prediction before running, every time.)**

Then delete `telemetry.update()` and let them watch the dashboard freeze while the robot still drives. Restore it.

> *"The robot doesn't need telemetry. **You** need telemetry."*

### 5. Team Coding (25 min)

Pairs, robot on blocks until checked off. Ascending difficulty:

1. **Drive it.** Type the 5 lines. Drive forward and back. *(Everyone reaches this.)*
2. **Precision mode.** Halve the power unless B is held:
   ```java
   if (!gamepad1.b) { drivePower = drivePower * 0.5; }
   ```
3. **Turning.** Right stick X turns. Motors get *opposite* powers. Let them work out the signs — this is the good struggle.
4. **Intake.** `if (gamepad1.left_bumper) { intake.setPower(1); } else { intake.setPower(0); }`

Coach rule: **do not type on a student's laptop.** Ask *"what does telemetry say `drivePower` is right now?"*

### 6. Robot Test (15 min)

Each pair drives a cone slalom, on the floor, at full speed. Time them. Then:

> *"Whose robot was easiest to drive? Why? Who used precision mode?"*

Driving quality becomes a code-quality argument — which is the actual FTC insight.

### 7. Debugging Challenge (10 min)

Coach hands out a pre-broken OpMode, `BrokenTeleOp.java`, with **exactly one** bug. Rotate bugs between pairs:

| Bug | Symptom | Lesson |
|---|---|---|
| `drivePower` missing the minus | forward drives backward | sign conventions |
| `rightMotor.setPower(-drivePower)` | robot spins in place | motor direction |
| `telemetry.update()` deleted | dashboard frozen, robot fine | telemetry is for humans |
| `setPower(drivePower)` outside `while` | responds once, then dead | the loop is what makes it live |
| `"leftmotor"` config typo | crash on INIT | read the error message |

Enforce the process on the whiteboard:

```
1. Observe      What exactly happened?
2. Reproduce    Can you make it happen again?
3. Describe     Say it out loud to your partner.
4. Locate       Which line could cause THAT?
5. Change ONE   One thing. Not three.
6. Test         Did it fix it?
7. Explain      Why did that work?
```

> *"A robot doing the wrong thing is not a failure. It's a bug. Bugs have causes, and causes can be found. Every single line of FTC code you'll ever admire was broken first."*

### 8. FTC Connection (5 min)

Project a real competitive team's TeleOp from GitHub. Find their `setPower` line inside their `while (opModeIsActive())`. It looks like the students' code, with more subsystems.

> *"That's a world-championship robot. Same five lines you wrote today."*

### 9. Student Explanation (5 min)

Each student explains their `drivePower` line to someone from another pair, and must answer: *"Why the minus sign?"*

---

### Solution

```java
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Iron Angel TeleOp", group = "Iron Angels")
public class IronAngelTeleOp extends LinearOpMode {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private DcMotor intake;

    @Override
    public void runOpMode() {

        leftMotor  = hardwareMap.get(DcMotor.class, "leftMotor");
        rightMotor = hardwareMap.get(DcMotor.class, "rightMotor");
        intake     = hardwareMap.get(DcMotor.class, "intake");

        // The motors face opposite directions on the robot,
        // so one must be reversed for "forward" to mean forward.
        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Iron Angels ready. Press PLAY.");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {

            // 1. READ THE DRIVER
            // Forward on the stick is NEGATIVE, so we flip it.
            double drivePower = -gamepad1.left_stick_y;
            double turnPower  =  gamepad1.right_stick_x;

            // 2. DECIDE
            // Precision mode unless the driver holds B.
            boolean turbo = gamepad1.b;
            if (!turbo) {
                drivePower = drivePower * 0.5;
                turnPower  = turnPower  * 0.5;
            }

            // 3. COMMAND THE MOTORS
            leftMotor.setPower(drivePower + turnPower);
            rightMotor.setPower(drivePower - turnPower);

            if (gamepad1.left_bumper) {
                intake.setPower(1.0);
            } else {
                intake.setPower(0.0);
            }

            // 4. SHOW OUR WORK
            telemetry.addData("Mode",   turbo ? "TURBO" : "PRECISION");
            telemetry.addData("Drive",  drivePower);
            telemetry.addData("Turn",   turnPower);
            telemetry.addData("Intake", gamepad1.left_bumper);
            telemetry.update();
        }
    }
}
```

**Note for the coach:** `drivePower + turnPower` can exceed 1.0. `setPower` clips silently, so the robot still works but turns feel uneven at full stick. Don't fix it today — bank it as the opening hook for Session 11 (clamping). *Leaving a visible, harmless imperfection that motivates the next lesson is better than a perfect program students can't explain.*

### Checkoff Rubric

| # | Criterion | Evidence |
|---|---|---|
| 1 | Robot drives forward and back from the stick | Coach observes |
| 2 | Explains the minus sign | Verbal |
| 3 | Precision mode works | Coach observes |
| 4 | Explains why `setPower` is inside the `while` | Verbal |
| 5 | Telemetry shows drive power changing | Driver Station |
| 6 | Found and fixed their assigned bug | Pair demo |
| 7 | Described the bug using the 7-step process | Verbal |
| 8 | Explained their code to another pair | Peer confirms |
| 9 | *(Stretch)* Turning works | Coach observes |
| 10 | *(Stretch)* Added a control of their own design | Demo |

**Pass = 1–8.** 9–10 are stretch. Every student who writes a driveable TeleOp and can explain the minus sign has met the bar for the session.

---

## 7. FTC Readiness Check

### After Session 14 a student should be able to…

**Read** — ✅ open a teammate's OpMode and narrate what it does · identify variables, `if`, loops, and method calls · find where a motor gets its power · recognize `hardwareMap`, `telemetry`, `gamepad1`, `DcMotor`, `LinearOpMode`.

**Explain** — ✅ why `int` can't hold `0.5` · what `-gamepad1.left_stick_y` does and why it's negated · why `telemetry.update()` is required · **who calls `runOpMode()`** and why there's no `main()` · the difference between a method that *does* and one that *answers* · what the constructor of `Robot` is for.

**Modify** — ✅ change a power value and predict the effect · remap a button · add a telemetry line a driver could act on · add a state to an autonomous state machine · add a method to `Robot`.

**Write** — ✅ a TeleOp that drives from the gamepad · a method taking a parameter and returning a value · a short autonomous using `robot.driveInches()` and a sensor decision · a `Robot` class that initializes its own hardware.

**Debug** — ✅ read a Driver Station stack trace and find the file and line · diagnose a config-name mismatch unaided · use telemetry to locate *where* autonomous went wrong · apply the 7-step process and change **one thing at a time** · say *"the robot did the wrong thing, so there's a bug"* rather than *"I'm bad at this."*

**Contribute** — ✅ make a small change without a coach touching the keyboard · explain that change to a teammate · **know which questions to ask** when stuck.

### FTC vocabulary ramp (recommended order)

| Session | Introduced |
|---|---|
| 1 | statement, `;`, `{}`, `.`, `()` |
| 2 | variable, data type, `int`, `double`, `boolean`, `String`, `telemetry` |
| 3 | `if`, `else`, condition, comparison, `&&`, `!`, sensor |
| 4 | `for`, loop, counter |
| 5 | `hardwareMap`, `DcMotor`, `setPower`, `Servo`, OpMode, config name |
| 6 | `gamepad`, `@TeleOp` |
| 7 | `while`, `LinearOpMode`, `runOpMode()`, `waitForStart()`, `opModeIsActive()` |
| 8 | method, `void`, call |
| 9 | parameter, argument, return value, overload |
| 10 | class, object, `new`, constructor, field, instance |
| 11 | `private`, getter, `this`, encapsulation |
| 12 | encoder, `ElapsedTime`, timeout |
| 13 | `enum`, `switch`, state, state machine, `@Autonomous` |

**Deliberately never taught:** inheritance hierarchies, interfaces, generics, collections, PID, trajectory libraries. `extends LinearOpMode` is explained as *"the SDK gives us a starting robot program"* and left there.

---

## 🚀 Rookie FTC Java Graduation Challenge

### "Operation Homecoming"

*Session 14 · 2 sessions (~3 hours) · Teams of 2–3 · Real robot, real field*

**The mission.** The robot starts in the base zone. It must:

1. **Leave base** — drive out by a measured distance.
2. **Search** — scan for the game element using a real sensor, with at most 3 attempts.
3. **Decide** — found it → collect it. Not found → go park and still score points. *(Autonomous that fails gracefully beats autonomous that hangs.)*
4. **Collect** — run the intake; verify it worked.
5. **Return** — drive home accurately.
6. **Report** — final state on the Driver Station.

**Requirements.**

| # | Requirement | Proves |
|---|---|---|
| 1 | `Robot` class owning all hardware, built from `hardwareMap` | S5, S10 |
| 2 | At least 4 named robot actions, one with a parameter | S8, S9 |
| 3 | At least one method returning `boolean`, used in an `if` | S9 |
| 4 | An `enum` state machine with the current state on telemetry | S13 |
| 5 | A real sensor reading that changes what the robot does | S3 |
| 6 | Encoder-based movement with a timeout | S12 |
| 7 | Bounded retry — gives up after 3 attempts | S13 |
| 8 | Telemetry a stranger could debug from | S2 |
| 9 | A TeleOp sharing the same `Robot` class | S10 |
| 10 | Graceful failure — never hangs, always parks | judgment |

**The debugging gauntlet (the real exam).** With 20 minutes left, the coach introduces **one** bug into each team's working autonomous — without telling them which. Teams must find and fix it *using telemetry only*, no coach help. A team that can do this can contribute to a real FTC codebase.

**Presentation.** Each team runs autonomous, then runs TeleOp, then answers:

1. Walk us through your state machine.
2. What happens if the sensor never sees the target?
3. Where does the robot get its motors?
4. Show us the line that makes the robot drive forward.
5. What was your hardest bug and how did you find it?
6. What would you add next?

**Graduation rubric.**

| Level | Standard |
|---|---|
| 🥉 **Rookie Programmer** | Autonomous completes without hanging. Can answer Q3 and Q4. Robot moves under their own code. |
| 🥈 **Team Programmer** | All 10 requirements. Found their planted bug. Can answer all 6 questions. |
| 🥇 **Lead Programmer** | The above, plus: taught another student something, and improved a teammate's code. |

---

### The standard for success

A graduate of this curriculum should be able to say:

> **"I may not know all the Java yet, but I know how to read the code, figure out what it does, change it, test it, and debug it."**

The current curriculum produces students who can read Java, explain variables, and trace an `if`. That's real progress and the foundations are sound — Sessions 1–3 are genuinely good teaching. But it stops short of the sentence above, because **nothing a student writes in all eight sessions ever moves the robot.** Confidence in FTC programming comes from the moment a student's own code makes 40 pounds of aluminum do what they told it to.

Everything else in this review is secondary to closing that gap.

---

# Appendix A — Blocks → Java Bridge

**None of this exists anywhere in the repo.** It is Level 0 and Level 1 of the stated ramp, and it is the highest-value addition after putting motors in students' hands. A Blocks student already knows what a robot program *is*; they need to learn that Java is **notation**, not a new subject.

## A.1 The one dissection every student needs

Do this once, slowly, in Session 1, and refer back to it all year.

### Blocks

> ⬛ `set` `leftMotor` `power to` `0.5`

### Java

```java
leftMotor.setPower(0.5);
```

### Every single character, explained

| Part | Question a 12-year-old actually asks | Answer to give |
|---|---|---|
| `leftMotor` | *What is this?* | **The thing you're talking to.** In Blocks you picked it from a dropdown. In Java you type its name. It's the same motor, named in the robot config file. |
| `.` | *Why is there a dot?* | **The dot means "'s".** `leftMotor.setPower` = "leftMotor**'s** setPower". The dot connects a thing to something that thing can do. |
| `setPower` | *What is this?* | **The action.** It's the purple part of your Blocks block. Things you can *do to* a motor: `setPower`, `setDirection`, `getCurrentPosition`. |
| `(` `)` | *Why the parentheses?* | **The parentheses hold the details.** In Blocks the `0.5` sat in a socket on the block. Parentheses are that socket. **Empty parentheses `()` mean the action needs no details** — `robot.stop()` needs nothing; "stop" is complete on its own. |
| `0.5` | *What is this number?* | **How much.** Motor power runs `-1.0` (full reverse) → `0.0` (stop) → `1.0` (full forward). `0.5` is half speed forward. *(This is the most important number in FTC.)* |
| `;` | *Why the semicolon?* | **It means "end of instruction," like a period ends a sentence.** In Blocks, one block = one instruction and the shape told you where it ended. Java has no shapes, so you say where the instruction ends. **Forgetting it is the #1 rookie error, and the error message will point at the *next* line** — teach that now, so it doesn't cost them 20 minutes later. |

**Closing line for the coach:**

> *"That's it. That's Java. A thing, a dot, an action, the details in parentheses, and a semicolon. You will see that shape ten thousand times this season."*

## A.2 Translation reference — Blocks to Java

Put this on the wall.

| # | Blocks | Java | Introduce at |
|---|---|---|---|
| 1 | `set leftMotor power to 0.5` | `leftMotor.setPower(0.5);` | S5 |
| 2 | `set leftMotor power to 0` | `leftMotor.setPower(0);` | S5 |
| 3 | `telemetry addData "Battery" [87]` | `telemetry.addData("Battery", 87);` | S2 |
| 4 | `telemetry update` | `telemetry.update();` | S2 |
| 5 | `set [batteryPercent] to [87]` | `int batteryPercent = 87;` | S2 |
| 6 | `set [motorPower] to [0.5]` | `double motorPower = 0.5;` | S2 |
| 7 | `set [targetFound] to [false]` | `boolean targetFound = false;` | S2 |
| 8 | `if <battery < 20> then … else …` | `if (battery < 20) { … } else { … }` | S3 |
| 9 | `gamepad1 left stick Y` | `gamepad1.left_stick_y` | S6 |
| 10 | `gamepad1 A` | `gamepad1.a` | S6 |
| 11 | `repeat 4 times` | `for (int i = 0; i < 4; i++) { … }` | S4 |
| 12 | `repeat while <opModeIsActive>` | `while (opModeIsActive()) { … }` | S7 |
| 13 | `wait 500 ms` | `sleep(500);` | S5 |
| 14 | *(call my block)* `driveForward` | `driveForward();` | S8 |
| 15 | *(my block with input)* `driveForward [24]` | `driveForward(24);` | S9 |
| 16 | `set servo position to 0.8` | `claw.setPosition(0.8);` | S5 |
| 17 | `distanceSensor distance (CM)` | `distanceSensor.getDistance(DistanceUnit.CM)` | S3 |

**Three differences worth stating out loud, because they cause most early frustration:**

1. **Blocks won't let you build a broken program. Java will.** Blocks physically refuses to snap a number into a boolean socket. Java lets you write `boolean targetReached = "false";` and *then* complains. *(This is exactly what `Session2-Challenge.java` teaches — keep it.)*
2. **Blocks hides the declaration; Java makes you say the type.** In Blocks a variable just exists. In Java you announce `int` or `double` up front, because Java wants to catch your mistakes before the robot moves.
3. **In Blocks you *drag*, in Java you *type* — so spelling counts.** `leftmotor` ≠ `leftMotor`. Capital letters matter. This is a feature: it's how Java catches typos you'd otherwise find during a match.

## A.3 Translation exercises, one per major concept

Each is 5–10 minutes. Run the matching one at the **start** of each session, as the "What We Already Know" step. Students translate *on paper first*, then type it.

| Concept | Session | Exercise |
|---|---|---|
| **Statements** | 1 | Coach projects a 5-block Blocks autonomous. Students write the 5 Java lines by hand. *Not run — just written.* |
| **Variables & types** | 2 | Given 6 Blocks `set` blocks, write each as a Java declaration **and choose the type.** Trap: `set motorPower to 0.5` → why not `int`? |
| **Telemetry** | 2 | Translate 4 Blocks telemetry blocks. Then: *"Which of these would actually help a driver mid-match?"* |
| **Decisions** | 3 | Translate a nested Blocks `if/else` into Java. Then translate **back** Java→Blocks on the whiteboard — reverse translation proves real understanding. |
| **Loops** | 4 | Translate `repeat 4 times [drive, turn]`. Then: *"Your Blocks program had the drive block pasted 4 times. How many Java lines replace it?"* |
| **Hardware** | 5 | **The payoff exercise.** Students find `set motor power` in their own old Blocks OpMode and write the Java. Then they **run it**, and the robot moves. Explicitly name the moment: *"You just wrote in Java what you used to drag. You're an FTC Java programmer now."* |
| **Gamepad** | 6 | Translate a Blocks TeleOp's stick-to-motor connection. Note the missing minus sign — Blocks hid it. |
| **The loop** | 7 | Compare Blocks' automatic repeat with Java's explicit `while (opModeIsActive())`. *"Blocks did this for you. Now you can see it — and control it."* |
| **Methods** | 8 | Translate a Blocks "My Block" into a Java method. **This is the closest Blocks↔Java match in the whole language** — lead with it, because students already understand methods; they just called them "My Blocks." |
| **Parameters** | 9 | Translate a My Block *with an input* into a Java method with a parameter. |
| **Classes** | 10 | No Blocks equivalent exists. **Say so plainly:** *"This is the first thing today that Blocks can't do. That's why the veteran teams use Java."* |

> **Coach guidance on A.3:** The "My Block" ↔ method mapping in Session 8 is the single most persuasive item in this table. Rookies who think they don't understand methods have usually already *built* one in Blocks. Showing them that they've been writing methods all along converts Session 8 from a new concept into a renaming — and buys enormous confidence for Sessions 9–10.

---

# Appendix B — Fixes (all applied)

Ordered by value per minute of coach effort. **All ten have now been implemented** — see Appendix C for what changed and how it was verified.

| # | Fix | Status | Why |
|---|---|---|---|
| 1 | Rename `Session8-FTCMiniSW` → proper `.java` file | ✅ done | Currently cannot compile at all |
| 2 | Restructure to `solutions/` + `challenges/` with class-matching filenames | ✅ done | **No file in the repo compiled as named**; 7 class-name collisions |
| 3 | Fix `Session5-RobotCommandLibrary.java` movement — and **convert the bug into a debugging exercise** | ✅ done | Mission 5 reported success without moving (verified); taught that robot state is meaningless |
| 4 | Move `basicDashboard()` in `Session7:336` out of the unconditional path | ✅ done | Shadowed every challenge's output |
| 5 | Port the `COLLECT TARGET` fix into the Session 3 teaching file; obstacle → `AVOID` | ✅ done | Teaching file was the weaker of the two |
| 6 | Add `leftMotor.setPower(drivePower)` to the TeleOp lesson | ✅ done | **Turns the curriculum from Java-with-robot-names into FTC programming.** Highest value in the table. |
| 7 | Write the missing `hardwareMap` session | ✅ done | The keystone; nothing students wrote could reach the robot without it |
| 8 | Split Session 4 into 4A/4B/4C; merge its duplicate files | ✅ done | 8 concepts / 24 exercises in one sitting |
| 9 | Convert Session 2's `println` dashboard to `telemetry` | ✅ done | Pulls the FTC framework 5 sessions earlier |
| 10 | Add one deliberate bug to each of Sessions 4–8 | ✅ done | Debugging previously stopped after Session 3 |

---

# Appendix C — What changed, and how it was verified

## Structure

Every file now compiles under its own name. `javac <ClassName>.java` works for all of them.

```
console/solutions/     coach's working code      (10 files)
console/challenges/    student TODOs + bugs      (9 files)
TeamCode/src/main/java/org/firstinspires/ftc/teamcode/
                       real FTC OpModes          (7 files)
README.md              curriculum index, robot config table, coach notes
```

Sessions 2 and 3 now exist in **both** a console form (learn the idea) and an OpMode form (do it on the Driver Station), so the FTC framework arrives in Session 2 without blocking Session 1 on a working robot.

## New files that close the critical gaps

| File | Closes |
|---|---|
| `Robot.java` | Finding 1, 10 — real `Robot` owning hardware, used by both TeleOp and autonomous |
| `S05_FirstMotor.java` | **Finding 1** — the missing `hardwareMap` keystone; students write `setPower` |
| `S06_DriverControl.java` | **Finding 9** — `gamepad → motor`, the line the curriculum never wrote |
| `S02_TelemetryDashboard.java` | Finding 12 — telemetry in Session 2, not Session 7 |
| `S03_SensorDecisions.java` | Finding, S3 — a *real* distance sensor drives the `if/else` |
| `S08_AutonomousStateMachine.java` | Finding 10 — a real `enum`/`switch` state machine, top-level `Robot` |
| `SearchBot4A/4B/4C` (+ challenges) | Finding 4 — the 24-exercise monolith split three ways |
| `DebugDeliveryMission.java` | Finding 6 — the verified Session 5 bug, now a taught exercise |

## Verification performed

Nothing here is asserted from reading alone.

- **All 10 console solutions compile** (`javac *.java`, clean).
- **All 9 console challenges compile individually** — except `MissionControlChallenge` and `RobotDashboardChallenge`, which fail *by design*; finding those errors is the lesson.
- **All 7 FTC files typecheck** against hand-built FTC SDK stubs (`LinearOpMode`, `DcMotor`, `Servo`, `DistanceSensor`, `HardwareMap`, `Telemetry`, `Gamepad`, `ElapsedTime`, `@TeleOp`, `@Autonomous`) with `-Xlint:all`, clean. Stubs mirror the real SDK signatures, so this catches API misuse — it does not replace deploying to a robot.
- **Session 5 fix confirmed by execution.** Mission 5 now drives `(0,0) → (0,3) → (2,3)`, delivers, returns home, total distance 10. Turning now affects where "forward" goes. `At home: true` is *earned* rather than never-left.
- **Every deliberate bug was run and produces exactly the symptom its comment promises:**

  | Exercise | Documented symptom | Measured |
  |---|---|---|
  | `DebugDeliveryMission` | moves 5, position `(0,0)`, `At home: true` | ✅ exact |
  | 4A off-by-one (`i <= 4`) | drives 5 sides, not 4 | ✅ 5 sides |
  | 4C break trap | checks 14 squares, should be 6 | ✅ 14, and fix yields 6 |
  | S6 "break `this`" | position resets to `(0,0)`, heading/battery still fine | ✅ exact |

- **4C efficiency figures verified:** target at `[0][0]`→1 square, `[0][3]`→4, `[2][2]`→11, `[3][3]`→16. Full sweep = 16.

## Design decisions that differ from the review's first draft

Flagging these because they are judgment calls, not corrections:

1. **`Robot` has two constructors, not one.** The review specified `Robot(HardwareMap)`. Implemented as `Robot(HardwareMap, Telemetry)` for TeleOp *plus* `Robot(LinearOpMode)` for autonomous. Reason: blocking encoder moves need `isStopRequested()` to abort when the driver hits STOP, which requires the OpMode. Keeping the simple constructor preserves the teaching clarity of "hand the robot its hardware map," and the second one doubles as the worked example of overloading taught in S9.

2. **Session 6's theory-only challenges were converted, not deleted.** The review said cut Challenges 1, 5, and 10. Rather than remove them they became hands-on experiments: C1 is now predict-then-verify, C5 has students *delete* `this.` and observe the silent failure, C10 has students attempt `robot.battery = 200;` and read the compiler's refusal. Same content, but students now write and break code instead of reading `println` definitions. A coach note recommends running C9 (Two Robots) first.

3. **4C is marked optional rather than merely moved.** Nested loops are not required for a working TeleOp or autonomous, so the file says so explicitly and tells coaches to skip it when the team is tired.

4. **Turbo became a real toggle.** Session 7 now uses edge detection (`bWasPressedLastLoop`) rather than momentary `turboMode = gamepad1.b`, with the ~50-loops-per-tap problem explained. This was listed as an extension in the review; it earned promotion because it's the first time students need to remember state *between* loops, which is exactly the idea a state machine builds on.

## Still open

- **Sessions 11–14 of the revised ramp are not written** (protect the robot, encoder calibration, and the graduation challenge as standalone lessons). The *capabilities* exist — `Robot.java` has clamping, encoder driving with timeouts, and the state machine — but they are not yet broken out as their own lesson plans with checkoffs.
- **Nothing has been run on real hardware.** The stubs prove the code typechecks against the SDK's shapes; they cannot prove motor directions, servo positions (`CLAW_OPEN = 0.7`), the turn durations in `S08`, or `TICKS_PER_INCH` are right for your robot. Calibrate those with the team — the README says where.
- **`@Autonomous` on `S05_FirstMotor`** is deliberate (it runs a fixed 2-second drive, no driver input), but it will appear in the autonomous list on the Driver Station. Move it to a `"Training"` group you can ignore during competition, or add `@Disabled` before an event.
