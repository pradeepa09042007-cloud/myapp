# Phase 1: Boilerplate Foundation

## 1. Phase Title and Purpose
This document captures the completed state of the first implementation phase (Boilerplate Foundation). The goal was to establish the foundational structure for a web application built as a learning-oriented, framework-free project.

## 2. Starting State
The project began as an empty directory `myapp/` without source control initialization (there is currently no `.git` repository present).

## 3. Technology Constraints
- **Frontend**: Plain HTML, CSS, Vanilla JavaScript
- **Backend**: Plain Java
- **Build Tool**: Gradle
- **Database**: MySQL
- **Database Access**: JDBC
- **Testing**: JUnit 5
- **Excluded**: Spring, Spring Boot, Hibernate/JPA, React, Node.js, Express, or any other application framework.

## 4. Architecture
The architecture strictly enforces separation of concerns without using frameworks. 
- The backend serves as a standalone Java application.
- The frontend is composed of static HTML/CSS/JS files.
- The database schema is managed via raw SQL scripts.

## 5. Current Repository State & Final Project Structure
The repository structure currently reflects the initial boilerplate, though some inconsistencies exist (noted below).

```text
myapp/
├── backend/
│   └── src/
│       ├── main/
│       │   └── java/com/example/App.java
│       └── test/
│           └── java/com/example/AppTest.java
├── database/
│   └── schema.sql
├── frontend/
│   ├── css/
│   │   └── styles.css
│   ├── index.html
│   └── js/
│       └── app.js
├── .gitignore
├── build.gradle
├── settings.gradle
└── gradle/
    └── wrapper/
        └── gradle-wrapper.properties
```

## 6. Files Created
- `settings.gradle`
- `build.gradle`
- `gradle/wrapper/gradle-wrapper.properties` (generated and modified)
- `.gitignore`
- `backend/src/test/java/com/example/AppTest.java`
- `backend/src/main/java/com/example/App.java`
- `frontend/index.html`
- `frontend/css/styles.css`
- `frontend/js/app.js`
- `database/schema.sql`

## 7. Files Intentionally Untouched
No existing files were modified as this was a greenfield setup.

## 8. Gradle/Build Configuration
- **Plugins**: Java plugin only (`id 'java'`).
- **SourceSets**: Overridden to match `backend/src/main/java` and `backend/src/test/java`.
- **Dependencies**:
  - `mysql:mysql-connector-java:8.0.33`
  - JUnit 5 (`org.junit:junit-bom:5.10.0`, `org.junit.jupiter:junit-jupiter`)

## 9. TDD Sequence
The backend was developed using Test-Driven Development (TDD):
1. **RED**: `AppTest.java` was written to expect an `App` class that returns a greeting. Running `./gradlew test` failed intentionally because `App` did not exist.
2. **GREEN**: `App.java` was implemented. Running `./gradlew test` successfully passed.

## 10. Failures, Debugging History, and Fixes
- **Gradle Version Compatibility**: The initial `gradle wrapper` command generated Gradle 4.4.1, which threw `Could not determine java version from '21.0.12'`. This was fixed by manually updating `gradle/wrapper/gradle-wrapper.properties` to download Gradle `8.10`.
- **Filename Typos**: Early attempts resulted in typos like `settting.gradle`, `setting.gradle`, and `bulid.gradle`. These were caught and corrected to `settings.gradle` and `build.gradle`.
- **Gradle Syntax Typo**: `mavencentral()` was corrected to `mavenCentral()`.
- **Java Class Naming**: `App.java` was accidentally created as `AppTest.java` in the main source set before being corrected.

## 11. Known Inconsistencies (Contradictions between History and Repository)
- **Frontend File References**: The implementation history instructed the user to add `<link rel="stylesheet" href="css/styles.css">` and `<script src="js/app.js"></script>` to `frontend/index.html`. **However, inspecting the current repository reveals these tags are missing from `index.html`.** The frontend files exist, but they are not linked.
- **CSS Filename**: The history shows ambiguity between `style.css` and `styles.css`. The actual file in the repository is `frontend/css/styles.css`.
- **Git Status**: The implementation implicitly assumed a git repository might exist, but the current directory `myapp` is not initialized as a git repository (`git status` fails).

## 12. Verification & Testing
- **Backend**: `./gradlew test` was executed and confirmed passing. The project compiles successfully.
- **Database**: The `schema.sql` file was created, but **database connectivity (JDBC) has NOT been tested or verified.**
- **Frontend**: The files exist, but due to the missing link tags in `index.html`, the frontend is not currently functional.

## 13. Deferred / Out-of-Scope Work
Authentication and user management were intentionally deferred. The following are NOT implemented:
- Registration, login endpoints, and frontend forms.
- Password hashing and session management (JWT/Cookies).
- User tables in the database schema.

## 14. Architectural Decisions & Future Considerations
- **HTTP Server**: An HTTP server implementation must be chosen (e.g., standard Java Servlets, `com.sun.net.httpserver`, Javalin) since Spring Boot is prohibited.
- **Password Hashing**: A library like `jBcrypt` or `Argon2` will be needed.
- **Session Management**: Custom session tokens and management will be required.
- **Database Access**: All queries will be written via raw `PreparedStatement`s.

## 15. Commit Linkage
- **Status**: There are no commits. The `myapp/` directory is not currently initialized as a git repository.

## 16. Resume-From-Here
For the next phase:
1. Initialize the git repository.
2. Fix the `frontend/index.html` file to properly link `css/styles.css` and `js/app.js`.
3. Choose and implement the barebones HTTP server routing.
4. Begin implementing the registration endpoint (which will require password hashing and JDBC setup).
