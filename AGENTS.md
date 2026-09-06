# Agent Instructions

## Project Overview

Solidvessel is a shopping application built as
microservices.

Services:

- `account`
- `inventory`
- `order`
- `payment`
- `shared`

Each service generally contains:

- `domain`: business rules, models, ports, and domain services
- `infra`: Spring Boot application, adapters, controllers, persistence, and
  messaging

Read the relevant files under `.docs/` before changing architecture, deployment,
testing, or communication behavior.

## Architecture Rules

- Preserve the hexagonal architecture.
- Keep business logic in the service's `domain` module.
- Keep Spring, persistence, REST, messaging, and external-service concerns in
  the `infra` module.
- Depend on ports from the domain rather than infrastructure implementations.
- Avoid introducing direct dependencies between domain modules unless the
  existing design clearly requires it.
- Prefer asynchronous communication through RabbitMQ where the existing design
  uses events.
- Treat REST and Feign contracts as compatibility-sensitive.
- Use the `shared` modules only for genuinely cross-service concerns.

## Development

- Use the Gradle wrapper: `./gradlew`.
- The project targets Java 21.
- Dependencies are managed through `gradle/libs.versions.toml`.
- Do not commit generated build output, IDE metadata, credentials, or local
  environment files.

Useful commands:

```sh
./gradlew test
./gradlew check
./gradlew build
```

Some integration and database tests may require Docker and Testcontainers.
Refer to `.docs/testing.md` and `.docs/run-options.md`.

## Testing Expectations

- Add or update tests with behavior changes.
- Put domain business-logic tests in the relevant `domain` module.
- Use the existing controller, database, and contract-test base classes where
  appropriate.
- Preserve contract tests when changing REST request or response behavior.
- Run the narrowest relevant test task first, then run `./gradlew check`
  when practical.
- Do not weaken coverage rules or disable tests to make a build pass.

## Code Changes

- Make the smallest change that satisfies the requirement.
- Follow the existing package structure and naming conventions.
- Do not rewrite unrelated code or perform broad formatting changes.
- Inspect existing implementations and tests before introducing new abstractions.
- Update relevant `.docs/` documentation when behavior or operational procedures
  change.

## Safety

- Never expose or copy credentials from documentation, environment files, logs,
  or configuration into source code or commits.
- Do not modify deployment, infrastructure, database schema, or messaging
  behavior without checking the relevant documentation and tests.
- Do not delete or revert unrelated working-tree changes.
