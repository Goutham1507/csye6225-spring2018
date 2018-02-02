# csye6225-spring2018
Cloud Computing - Spring 2018


Steps :

1. Install maven : https://maven.apache.org/
2. Install Java : http://www.oracle.com/technetwork/java/javase/downloads/index.html
3. Install MySQL : https://dev.mysql.com/downloads/
4. Install JMeter : http://jmeter.apache.org/
5. Create Database in MySQL with name : cloud-native-app-db
6. Get codebase at : https://github.com/deveshkandpal24121990/csye6225-spring2018/tree/assignment2/cloud-native-app
7. Navigate to cloud-native-app root directory and run "mvn clean install". This will also run unit test
8. Make sure MySQL server is running with the created database schema
9. java -jar ./target/cloud-native-app-0.0.1-SNAPSHOT.war will bring up the Spring Boot Application
10. Access the web-app at http://localhost:8080
11. Open JMeter, import the script and press Run to execute the following script for load testing : https://github.com/deveshkandpal24121990/csye6225-spring2018/blob/assignment2/jmeter/Assignment2.jmx 


Team Info :


1. Anubhav Gupta - gupta.anu@husky.neu.edu
2. Rugvedi Kapse - kapse.r@husky.neu.edu
3. Devesh Kandpal - kandpal.d@husky.neu.edu
