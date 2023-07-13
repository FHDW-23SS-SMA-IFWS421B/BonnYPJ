package Interface;

public interface ControlPanel {
    String getInput();

    String getUserInput();

    void output(String output);

    void output(String output, boolean nextLine);
}
