package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;
import java.util.List; /**
 * [OPOPOP XX 000000000]
 * .0    5 67 8        1
 * .                   5
 */
public class RegisterToRegisterFieldProcessor_Not extends AbstractFieldProcessor {
    public static final int EXPECTED_FIELD_COUNT = 1;

    public static final int RX_SIZE = 2;
    public static final int BLANK_SIZE = 8;

    @Override
    public ArrayList<Field> process(String fields) {
        // 2 required provided fields, plus one blank
        List<String> fieldTokens = super.splitFields(fields);
        if (fieldTokens.size() != EXPECTED_FIELD_COUNT) {
            throw new IllegalArgumentException("Unexpected number of fields for instruction: " + fields);
        }

        Field rxField = new Field(fieldTokens.get(0), RX_SIZE);

        Field blank = new Field(0, BLANK_SIZE);

        return new ArrayList<>(4) {
            {
                add(rxField);
                add(blank);
            }
        };

    }
}
