package DataStructures;

/**
 * An OpCode is a Field of size 6.
 * Operation Codes are the first part of an Instruction statement
 */
public class OpCode extends Field {
    public static final int OPERATION_CODE_SIZE = 6;

    public OpCode(String name) {
        super(opCodeToValue(name), OPERATION_CODE_SIZE);
        this.name = name;
    }

    /**
     * Convert an Op Code name into it's associated value
     *
     * @param opCode The Operation Code name
     * @return The value of the op code in base 10
     */
    public static int opCodeToValue(String opCode) {
        opCode = opCode.toLowerCase().replace(":", "");
        switch (opCode) {
            /* Undocumented Instructions */
            case "data" -> {
                return 0;
            }
            case "loc", "end" -> {
                return 0;
            }

            /* Miscellaneous Instructions */
            case "hlt" -> {
                return 0;
            }
            case "trap" -> {
                return 37; // 45 octal
            }
            /* Load/Store Instructions */
            case "ldr" -> {
                return 1; // 1 octal
            }
            case "str" -> {
                return 2; // 2 octal
            }
            case "lda" -> {
                return 3; // 3 octal
            }
            case "ldx" -> {
                return 4; // 4 octal
            }
            case "stx" -> {
                return 5; // 5 octal
            }
            /* Transfer Instructions */
            case "setcce" -> {
                return 36; // 44 octal
            }
            case "jz" -> {
                return 6; // 6 octal
            }
            case "jne" -> {
                return 7; // 7 octal
            }
            case "jcc" -> {
                return 8; // 10 octal
            }
            case "jma" -> {
                return 9; // 11 octal
            }
            case "jsr" -> {
                return 10; // 12 octal
            }
            case "rfs" -> {
                return 11; // 13 octal
            }
            case "sob" -> {
                return 12; // 14 octal
            }
            case "jge" -> {
                return 13; // 15 octal
            }
            /* Arithmetic and Logical Instructions */
            case "amr" -> {
                return 14; // 16 octal
            }
            case "smr" -> {
                return 15; // 17 octal
            }
            case "air" -> {
                return 16; // 20 octal
            }
            case "sir" -> {
                return 17; // 21 octal
            }
            /* Register to Register */
            case "mlt" -> {
                return 18; // 22 octal
            }
            case "dvd" -> {
                return 19; // 23 octal
            }
            case "trr" -> {
                return 20; // 24 octal
            }
            case "and" -> {
                return 21; // 25 octal
            }
            case "orr" -> {
                return 22; // 26 octal
            }
            case "not" -> {
                return 23; // 27 octal
            }

            /* Shift/Rotate Operations */
            case "src" -> {
                return 24; // 30 octal
            }
            case "rrc" -> {
                return 25; // 32 octal
            }

            /* I/O Operations */
            case "in" -> {
                return 26; // 32 octal
            }
            case "out" -> {
                return 27; // 33 octal
            }
            case "chk" -> {
                return 28; // 34 octal
            }

            /* Floating Point Instructions/Vector Operations */
            case "fadd" -> {
                return 29; // 35 octal
            }
            case "fsub" -> {
                return 30; // 36 octal
            }
            case "vadd" -> {
                return 31; // 37 octal
            }
            case "vsub" -> {
                return 32; // 40 octal
            }
            case "cnvrt" -> {
                return 33; // 42 octal
            }
            case "ldfr" -> {
                return 34; // 42 octal
            }
            case "stfr" -> {
                return 35; // 43 octal
            }
            default -> throw new IllegalArgumentException("Unknown Operation Code: " + opCode);
        }
    }

    public String name;

}
