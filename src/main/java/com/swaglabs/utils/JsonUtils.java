package com.swaglabs.utils;

import com.jayway.jsonpath.JsonPath;
import io.qameta.allure.internal.shadowed.jackson.core.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class JsonUtils {
    private static final String JSON_FILE_PATH = "src/test/resources/";
    String jsonReader;
    String jsonFileName;
    public JsonUtils(String jsonFileName){
        this.jsonFileName = jsonFileName;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(JSON_FILE_PATH + jsonFileName + ".json"));
            jsonReader = data.toJSONString();
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
        }
    }

    public String getJsonData(String jsonPath){
        String testData = "";
        try {
            testData = JsonPath.read(jsonReader , jsonPath);
        } catch (Exception e) {
            LogsUtils.error( e.getMessage() + "No result for this jsonPath" + jsonPath + " in this json file");
        }
        LogsUtils.info("JsonPath: '"+ jsonPath + "' in the json file '" + jsonFileName + "' has value equals '"+ testData + "' ");
        return testData;
    }
}
