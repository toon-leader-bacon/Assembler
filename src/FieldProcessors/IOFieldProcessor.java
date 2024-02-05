package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * [OPOPOP RR 0000 DDDD]
 * .0    5 67 8  1 1  1
 * .             0 1  5
 */
public class IOFieldProcessor extends AbstractFieldProcessor {
    public static final int EXPECTED_FIELD_COUNT = 2;

    public static final int R_SIZE = 2;
    public static final int DEVID_SIZE = 5;
    public static final int BLANK_SIZE = 3;

    @Override
    public ArrayList<Field> process(String fields) {
        /**
         * There should be 2 provided field tokens (plus one blank one)
         */
        List<String> fieldTokens = super.splitFields(fields);
        if (fieldTokens.size() != EXPECTED_FIELD_COUNT) {
            throw new IllegalArgumentException("Unexpected number of fields for instruction: " + fields);
        }

        Field rField = new Field(fieldTokens.get(0), R_SIZE);
        Field devIdField = new Field(fieldTokens.get(1), DEVID_SIZE);

        Field blank = new Field(0, BLANK_SIZE);

        return new ArrayList<>(4) {
            {
                add(rField);
                add(blank);
                add(devIdField);
            }
        };
    }
}
