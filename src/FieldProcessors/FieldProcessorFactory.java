package FieldProcessors;

public class FieldProcessorFactory {

    public AbstractFieldProcessor getFieldProcessor(String opCode) {
        opCode = opCode.toLowerCase().replace(":", "");
        return switch (opCode) {
            /* Undocumented Instructions */
            case "data" -> {
                yield new DataFieldProcessor();
            }
            case  "end" -> {
                yield new LocFieldProcessor();
            }
            case "loc" -> {
                yield new DataFieldProcessor();
            }

            /* Miscellaneous Instructions. Pg 11*/
            case "hlt" -> {
                yield new HltFieldProcessor();
            }
            case "trap" -> {
                yield new TrapFieldProcessor();
            }
            /* Load/Store Instructions. Pg 14*/
            case "ldr", // LoaD Register from memory
                    "str", // STore Register to memory
                    "lda" // Load Register with Address
                    -> {
                yield new RXAIFieldProcessor(true, true, true);
            }
            case "ldx", // Load inDeX register from memory
                    "stx" // STore indeX register to memory
                    -> {
                yield new RXAIFieldProcessor(false, true, true);
            }
            /* Transfer Instructions pg 15*/
            case "setcce" // Condition Code
                    -> {
                yield new RXAIFieldProcessor(true, false, false);
            }
            case "jz", //  06oct: Jump if Zero
                    "jma", // 11oct: unconditional JuMp to Address
                    "jsr" // 12oct: Jump and Save Return
                    -> {
                yield new RXAIFieldProcessor(false, true, true);
            }
            case "jne", // 07oct: Jump if Not Equal
                    "jcc", // 10oct: Jump if Condition Code
                    "sob", // 14oct: Subtract One and Branch
                    "jge" // 15oct: Jump Greater than or Equal to
                    -> {
                yield new RXAIFieldProcessor(true, true, true);
            }
            case "rfs" // 14oct: Return From Subroutine
                    -> {
                // TODO: address is actually optional here
                yield new RXAIFieldProcessor(false, false, true);
            }
            /* Arithmetic and Logical Instructions pg 16 */
            case "amr", // 16oct:  Add Memory to Register
                    "smr" // Subtract Memory from Register
                    -> {
                yield new RXAIFieldProcessor(true, true, true);
            }
            case "air", // Add Immediate to Register
                    "sir" // Subtract Immediate from Registers
                    -> {
                yield new RXAIFieldProcessor(true, false, true);
            }
            /* Register to Register operations  pg 18*/
            case "mlt", // 22oct: Multiply register by register
                    "dvd", // 23oct: divide register by register
                    "trr", // test the equality of register and register
                    "and", // Logical and of register and register
                    "orr" // logical or of register and register
                    -> {
                yield new RegisterToRegisterFieldProcessor();
            }
            case "not" // logical not of register
                    -> {
                yield new RegisterToRegisterFieldProcessor_Not();
            }
            /* Shift/Rotate Operations gp 18 */
            case "src", // Shift Register by Count
                    "rrc" // Rotate Register by Count
                    -> {
                yield new BitwiseOperationFieldProcessor();
            }
            /* I/O Operations */
            case "in", // Input Character to Register from Device
                    "out", // Output Character to Device from Register
                    "chk" // Check Device Status to Register
                    -> {
                yield new IOFieldProcessor();
            }
            /* Floating Point Instructions/Vector Operations pg 21 */
            case "fadd", // Floating add memory to register
                    "fsub", // Floating subtract memory from register
                    "vadd", // vector add
                    "vsub", // vector subtract
                    "cnvrt", // convert to fixed/floating point
                    "ldfr", // Load floating register from memory
                    "stfr" // store floating register to memory
                    -> {
                yield new RXAIFieldProcessor(true, true, true);
            }
            default -> {
                throw new IllegalStateException("Unexpected op code: " + opCode);
            }
        };
    }

}
