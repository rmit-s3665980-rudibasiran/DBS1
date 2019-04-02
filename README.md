# DBS1

Java Heap File Implementation

Steps:
rm *.java
rm *.class
rm heap.*
rm stdout
javac *.java
java dbload -p 4096 sample10000.csv
ls -l heap.*
cat stdout
java dbquery 17854 4096
cat stdout
