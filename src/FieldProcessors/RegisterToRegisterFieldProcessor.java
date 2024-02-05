package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * [OPOPOP XX YY 0000000]
 * .0    5 67 89 1     1
 * .             0     5
 */
public class RegisterToRegisterFieldProcessor extends AbstractFieldProcessor {
    public static final int EXPECTED_FIELD_COUNT = 2;

    public static final int RX_SIZE = 2;
    public static final int RY_SIZE = 2;
    public static final int BLANK_SIZE = 6;

    @Override
    public ArrayList<Field> process(String fields) {
        // 2 required provided fields, plus one blank
        List<String> fieldTokens = super.splitFields(fields);
        if (fieldTokens.size() != EXPECTED_FIELD_COUNT) {
            throw new IllegalArgumentException("Unexpected number of fields for instruction: " + fields);
        }

        Field rxField = new Field(fieldTokens.get(0), RX_SIZE);
        Field ryField = new Field(fieldTokens.get(1), RY_SIZE);

        Field blank = new Field(0, BLANK_SIZE);

        return new ArrayList<>(4) {
            {
                add(rxField);
                add(ryField);
                add(blank);
            }
        };

    }
}

