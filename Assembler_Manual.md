**********************************
# Part 0: Assembler
## Group 1:
    Arthur Bacon
    Ganesh Ghimire
    Ari-Jit Majumdar
    Assitan Tandia
**********************************
System Requirements:
Java JDK21+
Documentation viewable in markdown
**********************************


## Instructions/How to Run:
1. Open terminal/cmd shell
2. Navigate to root directory of this Project. <br>
   The folder will have the `Assembler.jar` file, and two subfolders `src`, and `InputFiles`. <br>
   (Additional details in `DesignDocument.md`)
3. Run the following command:
```bash
 java -jar Assembler.jar InputFiles/SourceProgram.txt
```

## Debugging/Issues:
If step 3 does not work, you may need modify the directories based on where your files are stored.<br>
Maintain the format below:
```bash
java -jar directory/of/artifacts/with/Assembler.jar \
          directory/of/test/codeToAssemble.txt
```

## Expected Output:
Output file is called `output.txt` and will be stored in the root directory (same folder as `Assembler.jar`)
`output.txt` contains the listing format of the instructions passed into the assembler: <br>
`xxxxxx    xxxxxx    ALL_TEXT_FROM_ORIGINAL_INSTRUCTION_LINE` <br>
*Note* - individual runs of our assembler will delete and replace previously generated output files

## Additional Info:
We have two programs to run on our assembler:
1. SourceProgram.txt <br>
   Example code copied from project specification. <br>
   *Note* - There are three columns of numbers, but only the first two columns reflect the Assembler's processing. <br>
   The third column of numbers are an artifact of the original instruction strings.
```bash
 java -jar Assembler.jar InputFiles/SourceProgram.txt
```

2. AllCommands.txt <br>
   Example code for assembling every instruction type
```bash
  java -jar Assembler.jar InputFiles/AllCommands.txt
```

To run alternative programs, simply alter the file names. 
All programs will be stored in the InputFiles directory.

