# Revision Log

## Example

```
{DATE}

{COMMIT MESSAGE}
{COMMIT MESSAGE}

{DATE}

{COMMIT MESSAGE}
...
```

## Date

**Format:** YYYY-MM-DD

## COMMIT MESSAGE

```
{VERSIONING} {ISSUE}
{SUMMARY}
```

## VERSIONING

```
{MAJOR.MINOR.BUGFIX.BUILD}
```

- **MAJOR:** major version
- **MINOR:** minor version
- **BUGFIX:** bug fix number
- **BUILD:** build number is sequencial (don't make part of a real versioning)

## SUMMARY:

```
- {VERB/ACTION} {CONTENT}
	- {ADDITIONAL OBSERVATIONS/COMMENTS}
```

- **Verb/Action:** added, fixed, upped, removed, switched, improved, updated, renamed
- **Content:** a message to describe a resume about what was made.
- **Additional Observations/Comments:** a *"free"* message to specify with more details what was made, use it on large and/or complex changes.

# Logs

## 2019-10-01

### 0.1.0.4
- Renamed ScarletLogger to ScarletLoggers.
- Added Loggers to control Logger acquirement with a optional singleton implementation as ScarletLoggers.
- Added MapLogger to store Logger instances with a default implementation as DefaultMapLoggers.

### 0.1.0.3
- Added LoggerLanguage as interface for DefaultLoggerLanguage.

## 2019-09-14

### 0.1.0.2
- Update eclipse project settings.

## 2019-08-27

### 0.1.0.1
- Initial project version.
- Added logger system with system output and/or file output.
- Added structure to customize logger message format output.
- Added support to language system of scarlet-base.
- JUnit tests included.