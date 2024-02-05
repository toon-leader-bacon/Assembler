package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * [OPOPOP RR A R 00 CCCC]
 * .0    5 67 8 9 11 1  1
 * .              01 2  5
 */
public class BitwiseOperationFieldProcessor extends AbstractFieldProcessor {

    public static final int EXPECTED_FIELD_COUNT = 4;

    public static final int R_SIZE = 2;
    public static final int COUNT_SIZE = 4;
    public static final int LR_SIZE = 1;
    public static final int AL_SIZE = 1;
    public static final int BLANK_SIZE = 2;

    @Override
    public ArrayList<Field> process(String fields) {
        /*
         * There should be 4 provided field tokens (plus one blank one)
         */
        List<String> fieldTokens = super.splitFields(fields);
        if (fieldTokens.size() != EXPECTED_FIELD_COUNT) {
            throw new IllegalArgumentException("Unexpected number of fields for instruction: " + fields);
        }

        Field rField = new Field(fieldTokens.get(0), R_SIZE);
        Field countField = new Field(fieldTokens.get(1), COUNT_SIZE);
        Field lrField = new Field(fieldTokens.get(2), LR_SIZE);
        Field alField = new Field(fieldTokens.get(3), AL_SIZE);

        Field blank = new Field(0, BLANK_SIZE);

        return new ArrayList<>(4) {
            {
                add(rField);
                add(alField);
                add(lrField);
                add(blank);
                add(countField);
            }
        };
    }
}
