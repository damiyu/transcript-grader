compile:
	javac ./src/*.java

text: compile
	java ./src/Grader.java

frame: compile
	java ./src/ToolFrame.java