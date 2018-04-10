# itea
Sample Selenium Java project
Project contains best practices of usage OOP and PageObject/PageFactory patterns for Java/WebDriver/TestNG project.

How to run tests from this project:

Install JDK 1.8 or above corresponding to your OS
Install Maven command line utility corresponding to your OS
Make sure that following command line commands mvn -version and java -version give you information on mvn and java installed in your OS. Otherwise go back to #1 and #2 and google for proper installation guides.
Clone repository with source code via Git
In command line to cd to project root folder
To run test from command line use following command:
mvn clean install -DsuiteName=single_test_example.xml
where single_test_example.xml is a name of specific TestNG xml file with tests.