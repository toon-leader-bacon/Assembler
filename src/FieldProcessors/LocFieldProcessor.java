package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;

public class LocFieldProcessor extends AbstractFieldProcessor {

    public static final int BLANK_SIZE = 10;

    @Override
    public ArrayList<Field> process(String fields) {
        // LOC instruction is just all 16 bits of 0s
        // One big blank field is to be returned

        return new ArrayList<>(1) {
            {
                add(new Field(0, BLANK_SIZE));
            }
        };
    }
}
