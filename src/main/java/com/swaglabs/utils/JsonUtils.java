package com.swaglabs.utils;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class JsonUtils {
    private static final String JSON_FILE_PATH = "src/test/resources/";
    String jsonData;
    String jsonFileName;
    public JsonUtils(String jsonFileName){
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(JSON_FILE_PATH + jsonFileName + ".json"));
            jsonData = data.toJSONString();
        } catch (Exception e) {
            LogsManager.error(e.getMessage());
        }
    }

    public String getJsonData(String jsonPath){
        String testData = "";
        try {
            testData = JsonPath.read(jsonData , jsonPath);
        } catch (Exception e) {
            LogsManager.error( e.getMessage() + "No result for this jsonPath" + jsonPath + " in this json file");
        }
        LogsManager.info("JsonPath: '"+ jsonPath + "' in the json file '" + jsonFileName + "' has value equals '"+ testData + "' ");
        return testData;
    }
}
