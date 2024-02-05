package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * [OPOPOP 0000 0000000]
 * .0    5 6  9 1     1
 * .            0     5
 */
public class HltFieldProcessor extends AbstractFieldProcessor {

    public static final int BLANK_SIZE = 10;

    @Override
    public ArrayList<Field> process(String fields) {
        // Stops the machine.
        // Should have 1 field of all 0s, and a blank field.
        // Kinda weird, but that's what the documentation says
        // On the other hand, the example clearly shows that the
        // end: HLT instruction has no fields. Soooo...
        List<String> fieldTokens = super.splitFields(fields);
        if (!fieldTokens.isEmpty()) {
            throw new IllegalArgumentException("HLT operation has unexpected fields: " + fields);
        }
        Field blank = new Field(0, BLANK_SIZE);
        return new ArrayList<>() {{
            add(blank);
        }};
    }
}
