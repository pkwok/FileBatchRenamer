# File Names Changer

This is a simple executable for Windows that easily changes batches of file names for the user.

## Functionality

The program allows users to:
- choose the directory of the folder, or use the current directory the executable is in
- view the files that will be changed
- preview the changes before commiting to them.
- append/strip from beginning and/or end of files
- enter a number of characters to be added/stripped
- enter the string that wants to be added/stripped

## Building
1. Build the file into a jar file eg. changeFileNames.jar. Alternatively, use the included jar file if your not changing any of the code.
2. Create an executable file such as changeFileNames.bat and put the following inside the file:
         ```
         java -cp "PathOfTheJarFile\changeFileNames.jar" changeFileNames
          ```
    Alternatively, use the included changeFileNames.bat file and just modify the path of the jar file to reflect where it is on your system.