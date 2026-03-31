Gradle build

This project has a Gradle build file at `build.gradle`.

Quick commands:

- Build and run tests with system Gradle:
  - `gradle test`

- Recommended: generate and commit the Gradle wrapper locally then use the wrapper in CI:
  - `gradle wrapper` (run once locally)
  - Commit the generated `gradlew`, `gradlew.bat` and the `gradle/wrapper` directory.
  - Then CI and developers can run `./gradlew test` or `gradlew.bat test`.

Notes:
- The Gradle build declares JUnit 4.13.2 for tests.
- If you prefer, I can add the Gradle wrapper files here for full reproducibility.
