It took around 4h for me to write this application. I spent more time in reworking the result storage before making use of PriorityQueue of size limited to 10.
The number of result needs to be shown is in constant file which can be configured if needed.
This program recursively searches the given directory for any text files.
Searching is done with ignored cases.
Searching is done by ignoring any special character like "inter-com" ~ "intercom" is success search


Build instructions(Gradle)
./gradlew clean build

>> jar will be generated in build/lib

Run jar using :
java -jar search-engine.jar "{directory-location}"



Example :


anupak-mac:SearchEngine anupakkumar$ ./gradlew clean build
BUILD SUCCESSFUL in 1s
6 actionable tasks: 6 executed
anupak-mac:SearchEngine anupakkumar$ java -jar build/libs/search-engine.jar  /Users/anupakkumar/Desktop/data/
14 Files read in directory /Users/anupakkumar/Desktop/data/
search> nomatches
No matches found
search> fourteen five one
txt4.txt:66%
txt5.txt:66%
txt2.txt:66%
txt12.txt:66%
txt13.txt:66%
txt3.txt:66%
txt7.txt:66%
txt10.txt:33%
txt9.txt:33%
txt8.txt:33%
search>
