# crime-rest-service

How to execute the JAR file:

Running the jar file directly will create a background process that would need to be terminated manually.
Its best to open up a command line in the parent directory and then run the following command:
java -jar crime-rest-service-0.0.1-SNAPSHOT.jar

You can terminate the process by using CTRL+C



How to use the program:

Input the following URLs into a web browser or use an API client like Postman:

http://localhost:8080/crime/categories

http://localhost:8080/crimes?postcode=&date=

For the second URL you must input a postcode and date (in the format yyyy-mm).
Example: http://localhost:8080/crimes?postcode=tf32hs&date=2020-01

The API response data is returned in JSON format.
