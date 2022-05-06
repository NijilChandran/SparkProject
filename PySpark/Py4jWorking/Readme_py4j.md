# What is py4j?

+ Py4j is a java library that allows Python code to call java objects.
+ Py4J enables Python programs running in a Python interpreter to dynamically access Java objects in a Java Virtual Machine. Methods are called as if the Java objects resided in the Python interpreter. 
+ Py4J also enables Java programs to call back Python objects. Py4J is distributed under the BSD license. https://www.py4j.org/

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
