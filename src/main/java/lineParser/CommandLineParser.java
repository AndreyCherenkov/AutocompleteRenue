package lineParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandLineParser {

    private static final List<String> commands = new ArrayList<>();

    public CommandLineParser (String[] args) {
        commands.addAll(Arrays.asList(args));
    }

    private static String getParam(String command) throws IndexOutOfBoundsException {
        return commands.get(indexOfCommand(command) + 1).trim();
    }

    private static int indexOfCommand(String command) {
        return commands.indexOf(command);
    }

    private static boolean isCorrectCommand(String command) {
        return commands.get(indexOfCommand(command)).equals(command.trim());
    }


    //default return: "/home/test/airports.csv"
    public String getPathToSourceFile() {
        if (commands.get(indexOfCommand(LineCommands.DATA_FILE_PATH)) == null) {
            return "";
        }

        if (isCorrectCommand(LineCommands.DATA_FILE_PATH)) {
            try {
                return getParam(LineCommands.DATA_FILE_PATH);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return "/home/test/airports.csv";
    }

    //default return: 2
    public int getColumnForSearching() {

        if (commands.get(indexOfCommand(LineCommands.COLUMN_ID)) == null) {
            return -1;
        }

        if (isCorrectCommand(LineCommands.COLUMN_ID)) {
            try {
                return Integer.parseInt(getParam(LineCommands.COLUMN_ID));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return 2;
    }

    //default return: "src/main/resources/input1.txt"
    public String getPathToInputFile() {

        if (commands.get(indexOfCommand(LineCommands.INPUT_FILE_PATH)) == null) {
            return "";
        }

        if (isCorrectCommand(LineCommands.INPUT_FILE_PATH)) {
            try {
                return getParam(LineCommands.INPUT_FILE_PATH);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return "src/main/resources/input1.txt";
    }

    //default return: "src/main/resources/result1.json"
    public String getPathToOutputFile() {

        if (commands.get(indexOfCommand(LineCommands.OUTPUT_FILE_PATH)) == null) {
            return "";
        }

        if (isCorrectCommand(LineCommands.OUTPUT_FILE_PATH)) {
            try {
                return getParam(LineCommands.OUTPUT_FILE_PATH);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }
        return "src/main/resources/result1.json";
    }
}
