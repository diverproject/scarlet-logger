# Change Log

## Example

```
{VERSIONING}
{CHANGES}

{VERSIONING}
{CHANGES}
...
```

## VERSIONING

```
{MAJOR.MINOR.BUGFIX[.BUILD]}
```

- **MAJOR:** major version
- **MINOR:** minor version
- **BUGFIX:** bug fix number
- **BUILD:** build number (optional/complementary)

## CHANGES:

```
- {VERB/ACTION}
	- {MAIN CHANGE}
	- {MAIN CHANGE}
	- {MAIN CHANGE}
		[- {ADDITIONAL COMMENTS}]
	...
```

- **Verb/Action:** added, fixed, upped, removed, switched, improved, updated, renamed
- **Main Change:** a short list describe the main changes applied in ther version.
- **Additional Comments:** when the change is large and/or complex.

# Change Log

## 0.1.0-ALPHA
- Initial project version.
- Added logger system with system output and/or file output.
- Added structure to customize logger message format output.
- Added support to language system of scarlet-base.
- JUnit tests included.