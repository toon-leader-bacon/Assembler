package SourceProgramReader;

import DataStructures.*;
import FieldProcessors.AbstractFieldProcessor;
import FieldProcessors.FieldProcessorFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.*;


public class SourceProgramReader {

    public static final String END_MEMORY_LOCATION_STR = "1024";
    private static final String OUTPUT_FILE_NAME = "output.txt";

    public SourceProgramReader() {
    }

    public void OpenAndReadSourceProgramFile(String fileToRead, boolean repeatSourceProgramLine) {
        BufferedReader reader;
        BufferedWriter writer;

        try {
            setupOutputFile(OUTPUT_FILE_NAME);
            reader = new BufferedReader(new FileReader(fileToRead));
            writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME, true)); // Append mode

            String line = reader.readLine();
            int nextMemoryLocation = 0;

            while (line != null) {
                if (line.isEmpty()) {
                    line = reader.readLine();
                    continue;
                }

                SourceProgramLine spLine = buildSourceProgramLine(line);

                if (spLine.memoryLocation == null) {
                    spLine.memoryLocation = new BinaryNumber(nextMemoryLocation);
                } else {
                    nextMemoryLocation = spLine.memoryLocation.toIntBase10();
                }
                nextMemoryLocation++;

                if (spLine.instruction.operationCode.name.equalsIgnoreCase("loc")) {
                    nextMemoryLocation = spLine.instruction.fields.getFirst().value;
                }

                String assemble = spLine.assemble();
                if (repeatSourceProgramLine) {
                    assemble += "\t" + line;
                }
                assemble = assemble.replace("\n", "");
                assemble += "\n";

                writer.write(assemble);

                line = reader.readLine();
            }

            reader.close();
            writer.close();

        } catch (IOException e) {
            System.out.println("Encountered an error when reading the file: " + e);
        }
    }


    private void setupOutputFile(String fileName) throws IOException {
        File outputFile = new File(fileName);
        if (outputFile.exists()) {
            outputFile.delete();
        }
        outputFile.createNewFile();
    }




    SourceProgramLine buildSourceProgramLine(String line) {
        ArrayList<String> tokens = tokenizeLineOnWhitespace(line);

        // Extract the memory location (if provided)
        // NOTE: The first token may be the empty string
        BinaryNumber memLocBinary = null;
        if (Utility.isNumeric(tokens.getFirst())) {
            memLocBinary = new BinaryNumber(Integer.parseInt(tokens.getFirst()));
        }

        // Extract the OpCode
        String operationCodeName = getOperationCodeName(tokens);
        String fields = (operationCodeName.equalsIgnoreCase("hlt")) ? "" : getFields(tokens);
        // Build the instruction structure
        Instruction instruction = new Instruction(
                new OpCode(operationCodeName),
                processFields(operationCodeName, fields)
        );

        // Everything else can be ignored
        String comments = (tokens.size() >= 3) ?
                processComments(tokens.subList(3, tokens.size())) :
                "";
        return new SourceProgramLine(memLocBinary, instruction, comments);

    }

    ArrayList<Field> processFields(String opCode, String fields) {
        FieldProcessorFactory fpf = new FieldProcessorFactory();
        AbstractFieldProcessor processor = fpf.getFieldProcessor(opCode);
        return processor.process(fields);
    }

    /**
     * Take the comments tokens, merge them together, strip trailing whitespace
     * including new lines, and return them all as one string.
     *
     * @param comments The comments tokens to merge & clean
     * @return The clean comments.
     */
    String processComments(List<String> comments) {
        StringBuilder result = new StringBuilder();
        for (String str : comments) {
            if (str.isEmpty()) {
                continue;
            }
            result.append(str);
            result.append(' '); // Add a space between tokens to make it readable
        }
        return result.toString().trim();
    }

    /**
     * Split a given string into tokens on whitespace characters.
     * Empty strings will be included in the list of tokens.
     *
     * @param line The line to tokenize
     * @return The tokens extracted from the line string
     */
    ArrayList<String> tokenizeLineOnWhitespace(String line) {
        String regexMatch = "[ \t\n]+"; // match on 1 or more whitespace characters

        ArrayList<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : line.toCharArray()) {
            if (c == ' ' || c == '\t' || c == '\n') {
                // If the character is a token, then flush the buffer into the result
                result.add(sb.toString());
                sb = new StringBuilder();
                continue;
            }
            // Else the character is NOT a whitespace character, add it to the buffer
            sb.append(c);
        }
        result.add(sb.toString());
        return result;

    }

    /**
     * From a list of tokens, get the operation code. It _will_ be either
     * the first or the second value.
     * If the first value is empty, then return the first non-empty token
     * Else, then return the 2nd (or next) non-empty token found
     *
     * @param tokens The tokens containing the operation code name
     * @return The operation code name
     */
    String getOperationCodeName(ArrayList<String> tokens) {
        if (tokens.getFirst().isEmpty()) {
            // If the first token is empty, then the op code is
            // expected to be the first non-empty token fond
            return getNthNonEmpty(tokens, 1);
        }

        // else the first token is _not_ empty.
        // Then get the 2nd non-empty token
        return getNthNonEmpty(tokens, 2);
    }

    /**
     * Take the tokens, and extract the fields.
     * If the first value is empty, then return the second non-empty token
     * Else, then return the 3rd (or next) non-empty token found
     *
     * @param tokens The tokens containing the fields
     * @return The fields
     */
    String getFields(ArrayList<String> tokens) {
        if (tokens.getFirst().isEmpty()) {
            // If the first token is empty,
            // then the fields are expected to be the 2nd non-empty token
            return getNthNonEmpty(tokens, 2);
        }
        // Else the first toke is /not/ empty.
        // Return the 3rd non-empty token as the fields
        return getNthNonEmpty(tokens, 3);
    }

    /**
     * Given a list of strings, get the nth non-empty element from it.
     *
     * @param tokens The strings to look through
     * @param n      The target non-empty element
     * @return The nth non-empty element
     */
    String getNthNonEmpty(ArrayList<String> tokens, int n) {
        int nonEmptySeen = 0;
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }
            if (nonEmptySeen + 1 == n) {
                return token;
            }
            nonEmptySeen++;
        }
        throw new IllegalArgumentException("Line does not have enough expected tokens");
    }

}