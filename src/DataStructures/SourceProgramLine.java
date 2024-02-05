package DataStructures;

/**
 * A SourceProgramLine is a full line of input given from the Source Program.
 * It is made up of 3 main parts:
 *  - Memory Location (Binary Number)
 *  - Instruction
 *  - Comments (Strings)
 */
public class SourceProgramLine {

    public SourceProgramLine() {
        this.memoryLocation = null;
        this.instruction = null;
        this.comments = "";
    }

    public SourceProgramLine(BinaryNumber memoryLocation_, Instruction instruction_, String comments_) {
        this.memoryLocation = memoryLocation_;
        this.instruction = instruction_;
        this.comments = comments_;
    }

    public BinaryNumber memoryLocation;
    public Instruction instruction;
    public String comments;

    public String assemble() {
        return this.assemble(false);
    }

    public String assemble(boolean includeComments) {
        StringBuilder result = new StringBuilder();
        result.append(this.memoryLocation.toString_Octal(6)); // 6 character strings
        result.append('\t');
        result.append(this.instruction.assemble());
        if (includeComments) {
            result.append('\t');
            result.append(this.comments);
        }
        result.append('\n');
        return result.toString();
    }
}
