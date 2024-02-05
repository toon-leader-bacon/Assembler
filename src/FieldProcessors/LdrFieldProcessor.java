package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;
import java.util.List;

public class LdrFieldProcessor extends AbstractFieldProcessor {

    public static final int R_SIZE = 2;
    public static final int IX_SIZE = 2;
    public static final int I_SIZE = 1;
    public static final int ADDRESS_SIZE = 5;

    public ArrayList<Field> process(String fields) {
        /* Fields should have 3 or 4 values
         * The 'I' field below is optional
         * Example:
         *   LDR
         *   r, x, address[,I]
         *
         * The bit size of each field is shown below
         * R	IX	I	Address
         * 11 	00 	0 	01111
         */

        List<String> fieldTokens = super.splitFields(fields);
        if (fieldTokens.size() == 3) {
            String r = fieldTokens.get(0);
            String ix = fieldTokens.get(1);
            String address = fieldTokens.get(2);
            String i = "0";
            return process(r, ix, i, address);
        } else if (fieldTokens.size() == 4) {
            String r = fieldTokens.get(0);
            String ix = fieldTokens.get(1);
            String address = fieldTokens.get(2);
            String i = fieldTokens.get(3);
            return process(r, ix, i, address);
        } else {
            throw new IllegalArgumentException("Instruction processing did NOT have the expected fields: " + fields);
        }
    }

    ArrayList<Field> process(String r, String ix, String i, String address) {
        Field rField = new Field(r, R_SIZE);
        Field ixField = new Field(ix, IX_SIZE);
        Field iField = new Field(i, I_SIZE);
        Field addressField = new Field(address, ADDRESS_SIZE);

        return new ArrayList<>() {{
            add(rField);
            add(ixField);
            add(iField);
            add(addressField);
        }};
    }

}
