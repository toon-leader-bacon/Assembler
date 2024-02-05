package SourceProgramReader;

import DataStructures.Field;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SourceProgramReaderTest {

    @org.junit.jupiter.api.Test
    void tokenizeLineOnWhitespaceTest() {
        SourceProgramReader underTest = new SourceProgramReader();

        String inputStr = "6\tData\t10\t\t\t;PUT 10 AT LOCATION 6\n";
        ArrayList<String> expected = new ArrayList<>() {
            {
                add("6");
                add("Data");
                add("10");
                add("");
                add("");
                add(";PUT");
                add("10");
                add("AT");
                add("LOCATION");
                add("6");
                add("");
            }
        };
        assertEquals(expected, underTest.tokenizeLineOnWhitespace(inputStr));
    }

    /*
    @org.junit.jupiter.api.Test
    void tokenizeLineTest() {
        SourceProgramReader underTest = new SourceProgramReader();

        String inputStr = "6\tData\t10\t\t\t;PUT 10 AT LOCATION 6\n";
        ArrayList<String> expected = new ArrayList<>() {
            {
                add("6");
                add("Data");
                add("10");
                add(";PUT 10 AT LOCATION 6");
            }
        };
        assertEquals(expected, underTest.tokenizeLine(inputStr));
    }
    */

    @org.junit.jupiter.api.Test
    void runExampleInputFile() {
        SourceProgramReader underTest = new SourceProgramReader();
        underTest.OpenAndReadSourceProgramFile("Assembler/test/SourceProgramReader/SourceProgram.txt",
                true);

        // 21 JZ 1,0
        //013100 -> 000101 10 01 0 00000
        //014100 -> 000110 00 01 0 00000

    }

    @org.junit.jupiter.api.Test
    void allCommandsInputFile() {
        SourceProgramReader underTest = new SourceProgramReader();
        underTest.OpenAndReadSourceProgramFile("Assembler/test/SourceProgramReader/AllCommands.txt",
                true);
    }

    @org.junit.jupiter.api.Test
    void evenMoreCommandsInputFile() {
        SourceProgramReader underTest = new SourceProgramReader();
        underTest.OpenAndReadSourceProgramFile("Assembler/test/SourceProgramReader/EvenMoreCommands.txt",
                true);
    }


    @Test
    void processFields_Data() {
        SourceProgramReader underTest = new SourceProgramReader();

        ArrayList<Field> expected = new ArrayList<>() {
            {
                add(new Field(10, 10));
            }
        };
        assertEquals(expected, underTest.processFields("data", "10"));

        expected = new ArrayList<>() {
            {
                add(new Field(1024, 10));
            }
        };
        assertEquals(expected, underTest.processFields("DATA", "END"));
    }

    @Test
    void processFields_LDR() {
        SourceProgramReader underTest = new SourceProgramReader();

        ArrayList<Field> expected = new ArrayList<>() {
            {
                add(new Field(3, 2));
                add(new Field(0, 2));
                add(new Field(0, 1));
                add(new Field(10, 5));
            }
        };
        assertEquals(expected, underTest.processFields("LDR", "3,0,10"));

        expected = new ArrayList<>() {
            {
                add(new Field(3, 2));
                add(new Field(0, 2));
                add(new Field(1, 1));
                add(new Field(10, 5));
            }
        };
        assertEquals(expected, underTest.processFields("LDR", "3,0,10,1"));
        /*
        expected = new ArrayList<>() {
            {
                add(new Field(1024, 10));
            }
        };
        assertEquals(expected, underTest.processFields("DATA", "END"));
        */

    }

}
