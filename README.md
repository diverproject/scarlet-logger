# Scarlet Base

A library to add a log system and enable specify how the log will works, on console, file and dispatch.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

You need have [Maven](https://maven.apache.org/) to install Scarlet Base and you can found it on [Maven Repository](https://mvnrepository.com/).

### Installing

Specify the maven import as the following example on your [pom.xml](https://maven.apache.org/pom.html) inside of your project.

```
<!-- https://mvnrepository.com/artifact/org.diverproject/scarlet-base -->
<dependency>
    <groupId>org.diverproject</groupId>
    <artifactId>scarlet-logger</artifactId>
    <version>0.1.0-ALPHA</version>
</dependency>
```

1. To create a logger object you just need call Logger Singleton instance and specify the logger name.

```java
ScarletLogger scarletLogger = ScarletLogger.getInstance();
Logger logger = scarletLoggger.get("myLoggerName");
```

By default the logger will set system output as your logger message output.

2. You can change the default class instance for any new loggers.

```java
ScarletLogger scarletLogger = ScarletLogger.getInstance();
scarletLogger.setDefaultClassLogger(MyLoggerClass.class);
MyLoggerClass myLoggerInstance = (MyLoggerClass) scarletLogger.get("aNewLogger");
```

3. Change the default message structure format overriding **internalLogger()** method.

```java
public class MyLoggerClass extends DefaultLogger
{
	public MyLoggerClass(String name)
	{
		super(name);
	}

	@Override
	protected void internalLogger(LoggerLevel level, String message, StackTraceElement origin)
	{
		if (level != null && level != NONE)
			this.feedLine();

		if (origin == null)
			origin = this.buildOrigin(1);

		String now = this.getCurrentDateFormatted();
		MessageOutput messageOutput = new MyMessageOutput(now, level, message, origin);
		this.log(messageOutput);
	}
}
```

4. The language system is allocated on **org.diverproject.scarlet.logger.language** package.

```java
File file = new File("languages/en-us/LoggerLanguage.ini");
LanguageLoader.loadEnumeration(file, LoggerLanguage.class);
```

## Running the tests

The test can be execute just running the project with JUnit Test 5.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/diverproject/diamond-lang/tags).

*The revision log have somethings different because it's used developers found more easy changes made and make github commit messages more **clean***.

## Authors

* **Andrew Mello da Silva** - *Developer* - [Driw](https://github.com/Driw)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* **Billie Thompson** - *[Readme template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2)* - [PurpleBooth](https://github.com/PurpleBooth)
