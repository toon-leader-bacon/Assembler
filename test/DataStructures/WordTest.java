package DataStructures;

import DataStructures.BinaryNumber;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordTest {

    @org.junit.jupiter.api.Test
    void setValue() {
        BinaryNumber underTest = new BinaryNumber(); // 0
        assertEquals(underTest.toString(), "0");

        // Set the binary value 1 (too few 1s)
        underTest.setValue(new ArrayList<>() {
            {
                add(true);
            }
        });
        assertEquals(underTest.toString(), "1");


        // Set the binary value of all 1s
        underTest.setValue(new ArrayList<>() {
            {
                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);
            }
        });
        assertEquals(underTest.toString(), "1111111111111111");


        // Set the binary value to 17 1s (one too many
        underTest.setValue(new ArrayList<>() {
            {
                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
            }
        });
        assertEquals(underTest.toString(), "11111111111111111");


        // Set the binary value to way too many 1s
        underTest.setValue(new ArrayList<>() {
            {
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);

                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
                add(true);
            }
        });
        assertEquals(underTest.toString(), "1111111111111111111111111111111111111111111111111111111111111111");
    }

    @org.junit.jupiter.api.Test
    void getValue() {
        BinaryNumber underTest = new BinaryNumber();
        ArrayList<Boolean> false_16 = new ArrayList<>() {
            {
                add(false);
            }
        };
        assertEquals(underTest.getValue(), false_16);

    }

    @org.junit.jupiter.api.Test
    void toIntBase10() {
        assertEquals(new BinaryNumber("0000_0000_0000_0000").toIntBase10(), 0);
        assertEquals(new BinaryNumber("0000_0000_0000_0001").toIntBase10(), 1);
        assertEquals(new BinaryNumber("0000_0000_0000_0010").toIntBase10(), 2);
        assertEquals(new BinaryNumber("0000_0000_0001_0000").toIntBase10(), 16);
        assertEquals(new BinaryNumber("0000_0001_0000_0000").toIntBase10(), 256);
        assertEquals(new BinaryNumber("0000_1000_0000_0000").toIntBase10(), 2048);
        assertEquals(new BinaryNumber("0100_0000_0000_0000").toIntBase10(), 16384);
        assertEquals(new BinaryNumber("1000_0000_0000_0000").toIntBase10(), 32768);
        assertEquals(new BinaryNumber("1111_1111_1111_1111").toIntBase10(), 65535);
    }

    @org.junit.jupiter.api.Test
    void toString_Binary() {
        assertEquals(new BinaryNumber("0000_0000_0000_0000").toString_Binary(), "0000000000000000");
        assertEquals(new BinaryNumber("1111_1111_1111_1111").toString_Binary(), "1111111111111111");
    }

    @org.junit.jupiter.api.Test
    void testToString() {
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
    }
}