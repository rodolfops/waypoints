## Waypoints

Use the command `mvn package` to create the runnable jar. It will create two jar files under target directory after a successful build. 

Run it by the command `java -jar PROJECT_NAME`, eg. `java -jar waypoints-0.0.1-SNAPSHOT.jar`. Provide json file path as argument to the java command.

OR

Use the command `mvn clean install` to build the project and use the command `mvn spring-boot:run -q -Dspring-boot.run.arguments=<json file>` changing <json file> for the json file path you want to process.
