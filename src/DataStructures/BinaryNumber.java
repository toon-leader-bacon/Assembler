package DataStructures;

import SourceProgramReader.Utility;

import java.util.ArrayList;

/**
 * A class to represent a Binary Number
 */
public class BinaryNumber {

    public BinaryNumber() {
        this.setValue(new ArrayList<>(1) {
            {
                add(false);
            }
        });
    }

    public BinaryNumber(int base10Value) {
        this(Integer.toBinaryString(base10Value));
    }

    public BinaryNumber(ArrayList<Boolean> value) {
        this.setValue(value);
    }

    public BinaryNumber(String value) {
        this.setValue(value);
    }

    protected static ArrayList<Boolean> buildArrayList(String str) {
        ArrayList<Boolean> result = new ArrayList<>(str.length());
        for (char c : str.toCharArray()) {
            switch (c) {
                case '0':
                    result.add(false);
                    break;
                case '1':
                    result.add(true);
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    // A binary number is represented by booleans. The 0-index (left most)
    // boolean is the most significant, while the last index (right most)
    // is the least significant.
    // false => 0
    // true => 1
    protected ArrayList<Boolean> value = new ArrayList<>();

    public void setValue(String newValue) {
        this.setValue(buildArrayList(newValue));
    }

    public void setValue(ArrayList<Boolean> newValue) {
        this.value = newValue;
    }

    public ArrayList<Boolean> getValue() {
        return this.value;
    }

    /**
     * Convert this Binary Number into a base 10 representation
     */
    public int toIntBase10() {
        int result = 0;
        // Loop over every bit backwards
        for (int i = this.value.size() - 1; i >= 0; i--) {
            boolean currentBit = this.value.get(i);
            int powerOfTwo = this.value.size() - i - 1;
            if (currentBit) {
                // If current bit == 1
                result += (1 << powerOfTwo);
            }
            // Else current bit == 0
        }
        return result;
    }

    /**
     * Convert this Binary number into an Octal representation
     */
    public String toString_Octal() {
        ArrayList<Boolean> octalNumber = new ArrayList<>(this.getValue());
        // Pad this Binary number until it's a multiple of 3 in length
        while ((octalNumber.size() % 3) != 0) {
            octalNumber.addFirst(false);
        }
        StringBuilder sb = new StringBuilder();
        // Loop over each chunk of 3 bits in the number, convert to octal and add all together
        for (int i = 0; i < octalNumber.size(); i += 3) {
            sb.append(threeBitsToOctal(octalNumber.get(i), octalNumber.get(i + 1), octalNumber.get(i + 2)));
        }
        return sb.toString();
    }

    /**
     * Convert this binary number into an Octal value number with
     * a minimum length of stringLength.
     * 0s will be appended to the front (left) of the resultant string
     * to get to the minimum size.
     *
     * @param stringLength The expected size of the output string
     */
    public String toString_Octal(int stringLength) {
        StringBuilder result = new StringBuilder();
        result.append(this.toString_Octal());
        while (result.length() < stringLength) {
            result.insert(0, '0');
        }
        return result.toString();
    }

    private int threeBitsToOctal(boolean fours, boolean twos, boolean ones) {
        int result = 0;
        if (fours) {
            result += 4;
        }
        if (twos) {
            result += 2;
        }
        if (ones) {
            result += 1;
        }
        return result;
    }

    public String toString_Binary() {
        return this.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(Utility.WORD_SIZE);
        // Loop over every bit backwards
        for (int i = this.value.size() - 1; i >= 0; i--) {
            boolean currentBit = this.value.get(i);
            if (currentBit) {
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        // Reverse the string builder so the leftmost character is the most
        // significant value bit
        return sb.reverse().toString();
    }

    @Override
    public int hashCode() {
        return this.toIntBase10();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BinaryNumber)) {
            return false;
        }
        return this.hashCode() == other.hashCode();
    }

}
