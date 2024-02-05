package FieldProcessors;

import DataStructures.Field;
import SourceProgramReader.Utility;

import java.util.ArrayList;
import java.util.List;

public class DataFieldProcessor extends AbstractFieldProcessor {
    public static final int END_LOCATION = 1024;
    public static final int LOCATION_SIZE = 10; // 10 bits for the address location value

    public ArrayList<Field> process(String fields) {
        /*
        Fields should have 1 token. Either a number or "End"
         */
        List<String> fieldTokens = super.splitFields(fields);
        if (fieldTokens.size() != 1) {
            throw new IllegalArgumentException("DATA field has unexpected number of tokens: " + fields);
        }

        String locationString = fieldTokens.getFirst();
        int locationValue = 0;
        if (locationString.equalsIgnoreCase("end")) {
            locationValue = END_LOCATION;
        } else if (Utility.isNumeric(locationString)) {
            locationValue = Integer.parseInt(locationString);
        } else {
            throw new IllegalArgumentException("DATA field is NOT a number: " + locationString);
        }

        int finalLocationValue = locationValue;
        return new ArrayList<>() {{
            add(new Field(finalLocationValue, LOCATION_SIZE));
        }};


    }
}
