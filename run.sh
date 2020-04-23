mvn clean install
mvn exec:java -Dexec.mainClass="org.sjsu.cmpe202.run.Client" -Dexec.args="src/main/resources/Sample.csv src/main/resources/SampleOutput.csv"
mvn exec:java -Dexec.mainClass="org.sjsu.cmpe202.run.Client" -Dexec.args="src/main/resources/Sample.json src/main/resources/SampleOutput.json"
mvn exec:java -Dexec.mainClass="org.sjsu.cmpe202.run.Client" -Dexec.args="src/main/resources/Sample.xml src/main/resources/SampleOutput.xml"