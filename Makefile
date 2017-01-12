SPRING_APP_PORT=9090
JAR_FILE=MoM.jar

.PHONY: clean

clean:
	mvn clean
	
compile:
	mvn clean install

start: compile
	java -Dserver.port=$(SPRING_APP_PORT) -jar target/$(JAR_FILE)
