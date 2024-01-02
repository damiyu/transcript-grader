compile:
	javac ./src/*.java

cmd: compile
	java ./src/Grader.java cmd

frame: compile
	java ./src/Grader.java frame