package FieldProcessors;

import DataStructures.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Field Processor is a function wrapper class that represents
 * validating, parsing and internalizing the fields associated
 * with an instruction.
 * OpCodes are 6 bits long, then the remaining 10 bits are all
 * fields. Each OpCode has a special structure for the expected
 * fields.
 */
public abstract class AbstractFieldProcessor {

    /**
     * Take the field string from an instruction and split it
     * into an array of Field objects.
     * <p>
     * For example the string "1,2,3" will be split into
     * [ Field[value=1,size=...]
     * Field[value=2,size=...]
     * Field[value=3,size=...] ]
     * The size of each field is determined by the specific opcode
     * instruction.
     *
     * @param fields The fields from the input file. Should be
     *               values separated by a `,` comma deliminator.
     * @return An ordered collection of Field objects that represent
     * the same fields string.
     */
    public abstract ArrayList<Field> process(String fields);

    /**
     * Internal function that takes a fields string
     * and splits it on the deliminator `,` comma character
     *
     * @param fields The fields string "1,2,3"
     * @return An ordered array of strings.
     */
    List<String> splitFields(String fields) {
        if (fields.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(fields.split(","));
    }
}
