A. Service Description:
"itemservice" is a micro-service that provides the following RESTful API:
1. Add a new Item, (assuming the creation date is updated while adding the item)
2. Change the description of an Item
3. Mark an item status as “done” or “not done” (assuming the item status “done” can be marked as “not done”)
4. Get all the Items
5. Get details of a specific item

B. Tech Stack used:
Below are the Tech Stack used in developing this micro-service:
    a. Spring Boot version 2.7.2
    b. Java version 1.8
    c. H2 Database runtime
    d. Eclipse version 2019-09
    e. Postman for testing RESTful API
    f. Docker Desktop version 20.10.17

C. How to build the “itemservice-v1.0.jar” service:
    a. Set the "Environmental Variables"
       JAVA_HOME = D:\Downloaded-Software\openjdk-8u262-x64
       JDK_HOME = D:\Downloaded-Software\openjdk-8u262-x64
       Path --> Add --> D:\Downloaded-Software\openjdk-8u262-x64\bin
    b. Goto the Command Prompt	
       java –version
       openjdk version "1.8.0-262"	
    c. Open GIT CMD, git clone https://github.com/pkumar52/mitigant.git
    c. open Eclipse, File/Import/Existing Maven Projects
       Select the "itemservice" directory, and click on Finish	
       Right click on project -> Build Path -> Configure Build Path
	                         Add Library --> JRE System Library --> Installed JREs --> Add 
                                 --> D:\Downloaded-Software\openjdk-8u262-x64
       Right click on “itemservice“ project --> Maven -> Update Project	
       Right click on “itemservice“ project --> Run As -> Run Configuration	
          Double Click on "Maven Build"
          Name: itemservice
          click on Workspace -> select the work space
          Goals: clean compile install
          Select "Skip Tests", "Resolve Workspace artifacts"
          Click on Apply, Run

D. How to run automated tests:
   Please find below the steps to run the JUnit test cases:
      a. Open the "itemservice" project in Eclipse
      b. Click on “src/test/java“
      c. Right click on “com.itservice.java./ ItemControllerMockitoTests.java” file
      d. Click on “Run As”, and the “JUnit Test”

E. How to run the “itemservice” service locally:
   Please find below the steps to run the service:
      a. Install Docker Desktop
      b. Open power shell and Goto directory where DockerFile is present
      c. Enter “docker login”, Enter username and password
      d. Execute the following commands
         • docker build -t itemservicev1.0 .
         • docker run -p 8083:8083 itemservicev1.0
      e. The service is available at http://localhost:8083
