package FieldProcessors;

import DataStructures.Field;
import SourceProgramReader.Utility;

import java.util.ArrayList;
import java.util.List;

/**
 * [011000 000000 CCCCC]
 * .0    5 6    1 1   1
 * .            1 2   5
 */
public class TrapFieldProcessor extends AbstractFieldProcessor {
    public static final int CODE_SIZE = 5;
    public static final int BLANK_SIZE = 5;

    public ArrayList<Field> process(String fields) {
        /*
        Traps to memory address 0, which contains the address of a table in memory.
        Stores the PC+1 in memory location 2.
        The table can have a maximum of 16 entries representing 16 routines for
        user-specified instructions stored elsewhere in memory.
        Trap code contains an index into the table, e.g. it takes values 0 – 15.
        When a TRAP instruction is executed, it goes to the routine whose address is
        in memory location 0, executes those instructions, and returns to the instruction
        stored in memory location 2. The PC+1 of the TRAP instruction is stored
        in memory location 2.
         */
        /*
         * The code field should be of size 10
         */
        List<String> fieldTokens = super.splitFields(fields);
        if (fieldTokens.size() != 1) {
            throw new IllegalArgumentException("TRAP instruction with unexpected number of fields: " + fields);
        }
        String codeStr = fieldTokens.getFirst();
        if (!Utility.isNumeric(codeStr)) {
            throw new IllegalArgumentException("TRAP instruction with non numeric code field: " + codeStr);
        }

        Field codeField = new Field(fieldTokens.get(0), CODE_SIZE);
        Field blankField = new Field(0, BLANK_SIZE);

        return new ArrayList<>() {{
            add(blankField);
            add(codeField);
        }};
    }
}
