package jsonResult;

import java.util.ArrayList;
import java.util.List;

public class JsonResult {

    private int initTime;
    private List<JsonResultArray> results;

    public JsonResult(int initTime) {
        this.initTime = initTime;
        this.results = new ArrayList<>();
    }

    public void addSearchResult(String search, List<Integer> result, int time) {
        JsonResultArray newResult = new JsonResultArray(search, result, time);
        results.add(newResult);
    }

    public int getInitTime() {
        return initTime;
    }

    public List<JsonResultArray> getResults() {
        return results;
    }

    public static String generateJson(JsonResult searchResults) {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("\"initTime\":").append(searchResults.getInitTime()).append(", ");
        json.append("\"result\":[");
        json.append("\n");

        boolean firstResult = true;
        for (JsonResultArray result : searchResults.getResults()) {
            if (!firstResult) {
                json.append(", ");
                json.append("\n");
            } else {
                firstResult = false;
            }
            json.append("\t");
            json.append("{");
            json.append("\"search\": \"").append(result.getSearch()).append("\", ");
            json.append("\"result\": ").append(result.getResult()).append(", ");
            json.append("\"time\": ").append(result.getTime());
            json.append("}");
        }

        json.append("\n");
        json.append("]");
        json.append("}");

        return json.toString();
    }
}
