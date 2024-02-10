import SourceProgramReader.SourceProgramReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String filename = args[0];

        SourceProgramReader reader = new SourceProgramReader();
        reader.OpenAndReadSourceProgramFile(filename, true);
    }
}