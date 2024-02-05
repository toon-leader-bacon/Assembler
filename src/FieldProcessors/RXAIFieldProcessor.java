package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;
import java.util.List;

/**
 * [OPOPOP RR XX I AAAAA]
 *  0    5 67 89 1 1   1
 *               0 1   5
 */
public class RXAIFieldProcessor extends AbstractFieldProcessor {
    public RXAIFieldProcessor(boolean expectR_,
                              boolean expectIX_,
                              boolean expectAddress_
                              //boolean expectI_
    ) {
        this.expectR = expectR_;
        this.expectIX = expectIX_;
        this.expectAddress = expectAddress_;
        //this.expectI = expectI_;
    }

    public static final int R_SIZE = 2;
    public static final int IX_SIZE = 2;
    public static final int I_SIZE = 1;
    public static final int ADDRESS_SIZE = 5;

    public boolean expectR;
    public boolean expectIX;
    public boolean expectAddress;
    public boolean expectI;

    protected int expectedTokenSize() {
        int result = 0;
        if (expectR) {
            result++;
        }
        if (expectIX) {
            result++;
        }
        if (expectAddress) {
            result++;
        }
        if (expectI) {
            result++;
        }
        return result;
    }

    public ArrayList<Field> process(String fields) {

        List<String> fieldTokens = super.splitFields(fields);

        // The Indirection bit I is always optional. So we have 1 grace token.
        // Just assume the extra token is the Indirection bit
        this.expectI = fieldTokens.size() == this.expectedTokenSize() + 1;

        // After addressing the optional indirection bit, re-check the expected token size
        if (fieldTokens.size() != this.expectedTokenSize()) {
            throw new IllegalArgumentException("Instruction received unexpected number of fields: " + fields +
                    "\nExpected " + this.expectedTokenSize());
        }

        // We get the tokens in a particular order:
        // [R, IX, Address, I]
        // The above example is if every field is expected. If a field is NOT expected
        // Then simply remove it from the list and that's the expected order.
        // For example, if R was not expected the /expected/ field entry should be:
        // [IX, Address, I]
        int nextTokenIndex = 0;
        String value = (expectR) ? fieldTokens.get(nextTokenIndex++) : "0";
        Field rField = new Field(value, R_SIZE);

        value = (expectIX) ? fieldTokens.get(nextTokenIndex++) : "0";
        Field ixField = new Field(value, IX_SIZE);

        value = (expectAddress) ? fieldTokens.get(nextTokenIndex++) : "0";
        Field addressField = new Field(value, ADDRESS_SIZE);

        value = (expectI) ? fieldTokens.get(nextTokenIndex++) : "0";
        Field iField = new Field(value, I_SIZE);

        return new ArrayList<>(4) {{
            add(rField);
            add(ixField);
            add(iField);
            add(addressField);
        }};
    }
}
