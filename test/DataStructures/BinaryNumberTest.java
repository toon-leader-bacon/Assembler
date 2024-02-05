package DataStructures;

import org.junit.jupiter.api.Test;
import DataStructures.BinaryNumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryNumberTest {

    @Test
    void toString_Octal() {
        BinaryNumber underTest = new BinaryNumber("111");
        assertEquals("7", underTest.toString_Octal());

        underTest.setValue("010");
        assertEquals("2", underTest.toString_Octal());

        underTest.setValue("0_010");
        assertEquals("02", underTest.toString_Octal());

        underTest.setValue("100_010");
        assertEquals("42", underTest.toString_Octal());

        underTest.setValue("111_100_010");
        assertEquals("742", underTest.toString_Octal());
        underTest.setValue("11_100_010");
        assertEquals("342", underTest.toString_Octal());
        underTest.setValue("1_100_010");
        assertEquals("142", underTest.toString_Octal());
    }
}