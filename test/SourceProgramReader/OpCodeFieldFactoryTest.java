package SourceProgramReader;

import org.junit.jupiter.api.Test;
import DataStructures.OpCode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpCodeFieldFactoryTest {

    @Test
    void opCodeStrToField() {
    }

    @Test
    void opCodeToValue() {
        assertEquals(6, OpCode.opCodeToValue("jz"));
    }
}