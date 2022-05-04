# Demonstrate how python code can call Java class

+ Py4J does not start a JVM. 
+ So start the java application before running the python code

# Open a terminal and compile the java code in the JavaCode directory 
>cd JavaCode

>javac -cp py4j-0.10.9.jar AdditionApplication.java

# Run the java code and keep it running
>java -cp py4j-0.10.9.jar; AdditionApplication 

# Open a new terminal and execute the pythonTester script
>cd PythonCode

>python Py4JTester.py 
