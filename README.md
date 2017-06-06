# spring-boot-jbehave

## Versions Used:
```
Spring Boot 1.5.3.RELEASE
JBehave 4.1.1
Gradle 3.1
Maven 3.5.0
Windows 7 / Git Bash
```

Example project combining Spring Boot and JBehave for Integration Testing.

Original source: https://mindcollect.wordpress.com/2015/11/19/spring-boot-jbehave-example/


Run from the parent of `src` folder as 

## GRADLE:

`gradle cleanCompileJava cleanTest test`

JBehave Reports are at: build\classes\jbehave

Test output is at: build\reports\tests . Note: `build\reports\tests\test\classes\com.github.reeda.springbootjbehave.TestRunnerTest.html` may not show the story itself in the standard output, but the tests do execute. Verify in `build\classes\jbehave\view\stories.Test.html` 


## MAVEN:

`mvn clean install`

JBehave Reports are at: target\jbehave

Test output is at: target\surefire-reports





