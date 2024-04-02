package invertedIndex;

import exceptions.NumberColumnException;

import java.io.*;
import java.util.*;

public final class InvertedIndex {

    public InvertedIndex(String pathToSource, int column) {
        try {
            this.initIndex(pathToSource, column);
        } catch (NumberColumnException e ) {
            e.printStackTrace();
        }
    }

    //key - слово/слова в столбце, value - массив с номерами строк
    private final Map<String, List<String>> index = new TreeMap<>();

    public void initIndex(String pathToSource, int column) throws NumberColumnException {

        if (column < 1 || column > InputInfo.NUM_OF_COLUMNS) {
            throw new NumberColumnException();
        }

        String typeOfColumn;
        if (column >= 7 && column <= 10 || column == 1) {
            typeOfColumn = InputInfo.NUM_TYPE;
        } else {
            typeOfColumn = InputInfo.STRING_TYPE;
        }

        File file = new File(pathToSource);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String[] line;
            String word;
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine().split(",");

                if (typeOfColumn.equals(InputInfo.STRING_TYPE)) {
                    word = line[column - 1].substring(1, line[column - 1].length() - 1);
                } else {
                    word = line[column - 1].trim();
                    try {
                        Double.parseDouble(word);
                    } catch (NumberFormatException e) {
                        continue;
                    }
                }

                if (!index.containsKey(word)) {
                    index.put(word, new ArrayList<>(List.of(line[0])));
                } else {
                    index.get(word).add(line[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> searchKeysByPrefix(String prefix) {
        List<String> keysWithPrefix = new ArrayList<>();
        for (String key : this.getInvertedIndex().keySet()) {
            if (key.startsWith(prefix)) {
                keysWithPrefix.add(key);
            }
        }

        return keysWithPrefix;
    }

    public List<String> getRowsList(String word) {
        return index.get(word);
    }

    public Map<String, List<String>> getInvertedIndex() {
        return index;
    }
}