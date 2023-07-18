package Interface;

public interface ControlPanel {
    String getInput();

    String prefixInput(String prefix);

    void output(String output);

    void output(String output, boolean nextLine);
}
