This folder contains the two Graph Challenges code, Intellij IDEA project and maven project.

For maven, I used the Intellij IDEA support and for command line I used version 3.3.9.

There is no main for this methods, just a collection of unit test.
	
To build and run test from command line use Maven commands:

- mvn compile
- mvn test

After building, you can run from a command line using run.bat in the project root.

This main will run a single pass of both functions using a simple graph:
	
  A
     B
       E
       F
     C
       G
       H
       I
     D
       J

NOTE: If your Intellij isn't configured to auto-refresh pom.xml files a reimport may be required. 