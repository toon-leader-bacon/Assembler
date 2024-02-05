package DataStructures;

import java.util.ArrayList;

/**
 * An Instruction represents the actual command of the program.
 * Each Instruction has a:
 *  - Op Code
 *  - Collection of fields
 *
 * Each Instruction can be thought of as a 16-bit binary number, where
 * the first 6 bits correspond to the OpCode and the remaining bits
 * are the values/ fields. Each Op Code has a different way of parsing the
 * remaining 10 bits into 'Field's
 */
public class Instruction {

    public Instruction(OpCode opCode, ArrayList<Field> fields_) {
        this.operationCode = opCode;
        this.fields = fields_;
    }

    public OpCode operationCode;
    public ArrayList<Field> fields;

    public String assemble() {
        BinaryNumber instructionInBinary = new BinaryNumber(this.mergeFieldsToBinary());
        return instructionInBinary.toString_Octal();
    }

    protected String mergeFieldsToBinary() {
        StringBuilder sb = new StringBuilder();
        sb.append(operationCode.toBinString());
        for (Field f : this.fields) {
            sb.append(f.toBinString());
        }
        return sb.toString();
    }

}
