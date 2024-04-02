import invertedIndex.InvertedIndex;
import jsonResult.JsonResult;
import lineParser.CommandLineParser;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final List<String> searchingList = new ArrayList<>();

    private static long before;
    private static long after;

    private static String pathToSourceFile;
    private static int columnForSearching;
    private static String pathToInputFile;
    private static String pathToOutputString;

    public static void main(String[] args) {
       testSystem(args);
    }

    private static void testSystem(String[] args) {
        CommandLineParser testParser = new CommandLineParser(args);

        before = System.currentTimeMillis();
        Main.initParam(testParser);
        Main.initSearchingList(pathToInputFile);
        InvertedIndex index = new InvertedIndex(pathToSourceFile, columnForSearching);
        after = System.currentTimeMillis();

        JsonResult jsonResult = new JsonResult((int)(after - before));


        for(String searchStr : searchingList) {
            List<Integer> resultIds = new ArrayList<>();
            before = System.currentTimeMillis();
            for (String str : index.searchKeysByPrefix(searchStr.toLowerCase())) {
                for (String row : index.getRowsList(str)) {
                    resultIds.add(Integer.parseInt(row));
                }
            }
            after = System.currentTimeMillis();
            jsonResult.addSearchResult(searchStr, resultIds, (int)(after - before));
        }

        Main.writeResult(pathToOutputString, JsonResult.generateJson(jsonResult));
    }

    private static void initParam(CommandLineParser commandLineParser) {
        pathToSourceFile = commandLineParser.getPathToSourceFile();
        columnForSearching = commandLineParser.getColumnForSearching();
        pathToInputFile = commandLineParser.getPathToInputFile();
        pathToOutputString = commandLineParser.getPathToOutputFile();
    }

    private static void initSearchingList(String pathToInputFile) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(pathToInputFile))) {
            while (bufferedReader.ready()) {
                searchingList.add(bufferedReader.readLine().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeResult (String pathToOutputString, String jsonResult) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(pathToOutputString))) {
            bufferedWriter.write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
