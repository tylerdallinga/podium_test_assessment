###Tyler Dallinga's Test Podium Project

This is essentially all the tools, properties and dependencies that I used:

1. Programing Language: Java, I downloaded the most recent JDK version, 14

2. IDE: intelliJ. I have a pc, so I installed the pc version, but the mac version should work just the same.

3. Software project management tool: Maven, was used in the creation of the project, and molds well with intelliJ.

4. Browser Drivers: I used mainly chrome for the browser in this project, so I of course used chromedriver. 
In set up of the tests you need to make sure that in the "@BeforeClass" you need to point the path to the downloaded chrome driver.

@BeforeClass
    public void startClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", <mark>"C:/Users/tyler/Desktop/chromedriver.exe"<mark>);
        driver = new ChromeDriver();
    }

5 Testing Framework: TestNG I will include the dependancy in the Depandancy part of this README.

6. Dependancies: For the set up of the pom.xml file:

~~~Java
 <?xml version="1.0" encoding="UTF-8"?>
 <project xmlns="http://maven.apache.org/POM/4.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
     <modelVersion>4.0.0</modelVersion>
 
     <groupId>org.podium</groupId>
     <artifactId>podium_test_assessment</artifactId>
     <version>1.0</version>
 
 
     <properties>
         <maven.compiler.source>1.8</maven.compiler.source>
         <maven.compiler.target>1.8</maven.compiler.target>
         <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
     </properties>
 
     <dependencies>
 
         <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
         <dependency>
             <groupId>org.seleniumhq.selenium</groupId>
             <artifactId>selenium-java</artifactId>
             <version>3.141.59</version>
         </dependency>
 
         <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-server -->
         <dependency>
             <groupId>org.seleniumhq.selenium</groupId>
             <artifactId>selenium-server</artifactId>
             <version>3.141.59</version>
         </dependency>
 
         <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver -->
         <dependency>
             <groupId>org.seleniumhq.selenium</groupId>
             <artifactId>selenium-chrome-driver</artifactId>
             <version>3.141.59</version>
         </dependency>
 
 
         <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-firefox-driver -->
         <dependency>
             <groupId>org.seleniumhq.selenium</groupId>
             <artifactId>selenium-firefox-driver</artifactId>
             <version>3.141.59</version>
         </dependency>
 
 
         <!-- https://mvnrepository.com/artifact/org.testng/testng -->
         <dependency>
             <groupId>org.testng</groupId>
             <artifactId>testng</artifactId>
             <version>7.3.0</version>
             <scope>test</scope>
         </dependency>
 
         <!-- https://mvnrepository.com/artifact/junit/junit -->
         <dependency>
             <groupId>junit</groupId>
             <artifactId>junit</artifactId>
             <version>4.13</version>
             <scope>test</scope>
         </dependency>
 
         <!-- https://mvnrepository.com/artifact/org.openjfx/javafx-controls -->
         <dependency>
             <groupId>org.openjfx</groupId>
             <artifactId>javafx-controls</artifactId>
             <version>16-ea+1</version>
         </dependency>
 
         <!-- https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api -->
         <dependency>
             <groupId>org.junit.jupiter</groupId>
             <artifactId>junit-jupiter-api</artifactId>
             <version>5.7.0-M1</version>
             <scope>test</scope>
         </dependency>
 
 
 
     </dependencies>
 
 
 
 
 
 
 </project>
~~~

Changes that need to happen to make the tests run:

In Project structure, Projects: make sure Poject language level is 14-switch expressions
In Project structure, Modules: make sure that language level is 8-Lambdas,type annotations etc.

