# Phase 02: User Registration

## 1. Phase 2 Objective
Implement the User Registration feature end-to-end without using any frameworks, strictly following TDD principles. Ensure passwords are securely hashed and validation is enforced at the backend.

## 2. Starting state inherited from Phase 1
- Gradle build system initialized.
- JUnit 5 configured.
- Minimal `App.java` and `AppTest.java` boilerplate.
- Database `schema.sql` started.
- Frontend base HTML/CSS/JS boilerplate existing.
- Plain Java/JDBC/MySQL architecture established.

## 3. Final outcome
The User Registration feature is successfully implemented and verified end-to-end. A user can submit the HTML registration form, which posts JSON to the Java backend. The backend validates the data, hashes the password using jBCrypt, and securely saves it into MySQL using JDBC PreparedStatements.

## 4. Architecture used
Framework-free, cleanly separated architecture:
- **Frontend**: HTML5, Vanilla JS (`fetch`), raw CSS.
- **Controller**: Built-in Java `com.sun.net.httpserver.HttpHandler`.
- **Service**: Plain Java `RegistrationService` handling business logic and validation.
- **Repository**: Interface-driven pattern (`UserRepository`, `JdbcUserRepository`, `FakeUserRepository`).
- **Database**: Plain JDBC with `PreparedStatement`.

## 5. Complete registration flow
`register.html` -> `register.js` -> `fetch()` -> `POST /api/register` -> `RegistrationController` -> `RegistrationService` -> `PasswordUtil.hash()` -> `JdbcUserRepository` -> `PreparedStatement` -> MySQL `users` table.

## 6. Chronological implementation journey
1. Fixed database schema to explicitly define the `users` table.
2. Added `jBCrypt` and `Gson` to `build.gradle`.
3. Created `PasswordUtilTest` (RED) -> Implemented `PasswordUtil` (GREEN).
4. Created `RegistrationServiceTest` testing missing fields and duplicate emails (RED).
5. Created `User` model, `UserRepository` interface, `FakeUserRepository`, and `RegistrationService` (GREEN).
6. Implemented real `DatabaseConnection` and `JdbcUserRepository` for MySQL connectivity.
7. Created `RegistrationController` and wired all dependencies into `App.java`'s built-in `HttpServer`.
8. Created frontend `register.html` and `register.js`.
9. Setup local MySQL dedicated user (`myapp_user`) and verified end-to-end integration via local python server.

## 7. TDD RED -> GREEN -> REFACTOR history
- **Password Hashing**: Wrote `testPasswordIsHashed()` asserting BCrypt string generation. Test failed (Missing class). Implemented `PasswordUtil`. Test passed.
- **Service Validation**: Wrote tests for missing inputs and duplicate emails using a `FakeUserRepository`. Tests failed. Implemented `RegistrationService` validation rules. Tests passed.

## 8. Files created
- `backend/src/test/java/com/example/PasswordUtilTest.java`
- `backend/src/test/java/com/example/RegistrationServiceTest.java`
- `backend/src/test/java/com/example/FakeUserRepository.java`
- `backend/src/main/java/com/example/User.java`
- `backend/src/main/java/com/example/UserRepository.java`
- `backend/src/main/java/com/example/JdbcUserRepository.java`
- `backend/src/main/java/com/example/PasswordUtil.java`
- `backend/src/main/java/com/example/RegistrationService.java`
- `backend/src/main/java/com/example/RegistrationController.java`
- `backend/src/main/java/com/example/DatabaseConnection.java`
- `frontend/register.html`
- `frontend/js/register.js`

## 9. Files modified
- `build.gradle` (added `org.mindrot:jbcrypt:0.4` and `com.google.code.gson:gson:2.10.1`)
- `backend/src/main/java/com/example/App.java` (wired HTTP Server)
- `database/schema.sql` (added `users` table)

## 10. Files intentionally untouched
- Phase 1 context file
- `frontend/css/styles.css`

## 11. Important classes and responsibilities
- `RegistrationController`: Handles HTTP POST parsing and JSON responses.
- `RegistrationService`: Validates business rules (empty fields, valid emails, duplicates).
- `PasswordUtil`: Wraps jBCrypt hashing.
- `JdbcUserRepository`: Executes raw SQL `INSERT` and `SELECT` queries against MySQL safely.

## 12. Database schema changes
Created the `users` table with `id`, `name`, `phone_number`, `email` (UNIQUE), `password_hash`, and `created_at` timestamp.

## 13. API endpoint
`POST http://localhost:8080/api/register`

## 14. Request/response behavior
- **Valid Request**: Payload contains name, phone, email, password. Returns `HTTP 201 Created` with success message.
- **Invalid Request**: Missing/invalid fields or duplicate email. Returns `HTTP 400 Bad Request` with specific validation error message.

## 15. Dependencies and versions actually present
- `org.mindrot:jbcrypt:0.4`
- `com.google.code.gson:gson:2.10.1`
- `mysql:mysql-connector-java:8.0.33`
- `org.junit.jupiter:junit-jupiter:5.10.0`

## 16. Commands executed
- `./gradlew test` (multiple times for TDD)
- `./gradlew build`
- `mysql -u root -p` (to create DB and user)
- `python3 -m http.server 3000` (to serve frontend without CORS/file permission issues)
- `java -cp ... com.example.App` (to run the backend server)

## 17. Test results
`BUILD SUCCESSFUL`. All 8 tests (including `AppTest`, `PasswordUtilTest`, `RegistrationServiceTest`) pass reliably.

## 18. Build results
`BUILD SUCCESSFUL`

## 19. Integration verification results
Successfully verified that saving a user writes a new row to the local MySQL `users` table via JDBC (`IMPLEMENTED` and `VERIFIED`).

## 20. Frontend verification results
Successfully verified that filling out `register.html` sends a payload that returns a 201 Success status, rendering a green success message in the browser UI (`VERIFIED`).

## 21. Failures encountered
- `ERROR 1698`: MySQL access denied for `root`.
- `ERR_FILE_NOT_FOUND`: Browser blocked loading local CSS/JS when running `register.html` natively from Ubuntu file explorer.
- JVM `spawn helper` exit code 1 during Gradle tests.
- SQL Syntax error due to missing space in comment (`--simple`).
- Classpath errors during manual `java -cp` execution.

## 22. Root causes
- Ubuntu MySQL default installs lock `root` behind `auth_socket` (requires `sudo`).
- Strict browser sandboxing blocked relative file resolution on `file:///` URLs.
- Gradle on local OS struggled with process forking for test executors.

## 23. Fixes
- Created a dedicated `myapp_user` for the app to connect without `sudo`.
- Used Python's built-in HTTP server to serve the frontend folder.
- Skipped Gradle tests when starting the app or ran the server directly using `java -cp`.
- Fixed SQL comment spacing (`-- simple`).

## 24. Security considerations
- Raw passwords are never stored; they are hashed via BCrypt before hitting JDBC.
- PreparedStatement is used exclusively to prevent SQL injection.
- Credentials are theoretically handled via Environment Variables (defaulting to local configs for ease of testing).

## 25. Known limitations
- The Gradle `application` plugin should be added to simplify starting the backend server instead of using `java -cp`.

## 26. Deferred work
- Login functionality.
- JWT/Sessions.
- Password resets.

## 27. Git branch/commit information
Working on `main`, currently at `cc14c80`.

## 28. Exact resume point for Phase 3
Start Phase 3 by implementing the Login Endpoint (`POST /api/login`) and the Frontend Login page (`login.html`), leveraging the existing `PasswordUtil` and `UserRepository` to verify credentials.
