# JabberPoint

JabberPoint is a small Java presentation app that was improved with a focused redesign. The current codebase applies three design patterns where they fit the problem best:

- Composite for slide content structure
- Observer for presentation-to-view updates
- Command for controller actions

The code was also cleaned up to better match the NHL Stenden coding style used in the course: clear naming, small focused classes, readable structure, and consistent comments around public behavior.

## What was changed

- Added Composite support for hierarchical slide content through `SlideItem` and `CompositeSlideItem`
- Kept the Observer-based presentation update flow between `Presentation` and `SlideViewerComponent`
- Added Command-based actions for next, previous, goto, and exit operations
- Added unit tests and JaCoCo coverage reporting
- Added CI/CD support so the project can be built and tested automatically

## Build and test

This project uses the Gradle wrapper, so you do not need a local Gradle install when `gradlew` / `gradlew.bat` is available in the repo.

Run the test suite:

```powershell
.\gradlew.bat test
```

Generate the coverage report:

```powershell
.\gradlew.bat test jacocoTestReport
```

## Run the project locally

Compile the application classes:

```powershell
.\gradlew.bat classes
```

Start the app:

```powershell
java -cp build/classes/java/main JabberPoint
```

Note: the built-in demo presentation expects image files from the original JabberPoint materials. If those images are missing, run the app with your own XML presentation that only uses text items, or add the missing image files.

If the wrapper scripts are not available for some reason, install Gradle locally and run the same tasks with `gradle` instead.

## CI/CD

The GitHub Actions pipeline runs on pushes and pull requests to `main`. It compiles the project, runs the unit tests, and publishes JaCoCo/JUnit artifacts for review.
