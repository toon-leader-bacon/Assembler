package FieldProcessors;

import org.junit.jupiter.api.Test;
import DataStructures.Field;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RXAIFieldProcessorTest {

    @Test
    void process__Test() {
        RXAIFieldProcessor underTest = new RXAIFieldProcessor(true, true, true);

        ArrayList<Field> expected = new ArrayList<>() {{
            add(new Field(0, 2));
            add(new Field(0, 2));
            add(new Field(0, 1));
            add(new Field(0, 5));
        }};
        //assertEquals(expected, underTest.process__("0,0,0"));
        //assertEquals(expected, underTest.process__("0,0,0,0"));

        underTest = new RXAIFieldProcessor(false, false, false);
        assertEquals(expected, underTest.process(""));
        assertEquals(expected, underTest.process("0")); // indirection bit
    }
}