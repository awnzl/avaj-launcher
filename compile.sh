find . -name "*.java" > sources.txt
mkdir bin
javac -d bin -sourcepath src @sources.txt
