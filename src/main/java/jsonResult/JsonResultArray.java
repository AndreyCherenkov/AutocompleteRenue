package jsonResult;

import java.util.List;

public class JsonResultArray {

    private String search;
    private List<Integer> result;
    private int time;

    public JsonResultArray(String search, List<Integer> result, int time) {
        this.search = search;
        this.result = result;
        this.time = time;
    }

    public String getSearch() {
        return search;
    }

    public List<Integer> getResult() {
        return result;
    }

    public int getTime() {
        return time;
    }
}
