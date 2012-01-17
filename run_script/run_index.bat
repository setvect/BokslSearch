SET JAVA_EXE="C:\Program Files\Java\jdk1.6.0_21\bin\java.exe"
SET CLASSPATH=..\build\class
SET CLASSPATH=%CLASSPATH%;..\build\lib\lucene-core-3.5.0.jar
SET CLASSPATH=%CLASSPATH%;..\build\lib\lucene-demo-3.5.0.jar
SET CLASSPATH=%CLASSPATH%;..\build\lib\lucene-analyzers-3.5.0.jar

%JAVA_EXE% -classpath %CLASSPATH% org.apache.lucene.demo.IndexFiles

pause