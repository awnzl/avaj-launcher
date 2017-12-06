find . -name "*.java" > sources.txt
mkdir bin 2> /dev/null
javac -d bin -sourcepath src @sources.txt
