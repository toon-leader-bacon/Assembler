package DataStructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {
    @org.junit.jupiter.api.Test
    void toBinStringTests() {
        Field underTest = new Field((short) 1, (short) 1);
        assertEquals(underTest.toBinString(), "1");

        underTest = new Field((short) 1, (short) 6);
        assertEquals(underTest.toBinString(), "000001");

        underTest = new Field((short) 2, (short) 6);
        assertEquals(underTest.toBinString(), "000010");

        underTest = new Field((short) 4, (short) 6);
        assertEquals(underTest.toBinString(), "000100");

        underTest = new Field((short) 16, (short) 6);
        assertEquals(underTest.toBinString(), "010000");
    }
}