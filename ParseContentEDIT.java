package com.example.administrator.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParseContent {

    public static void main(String[] args) throws Exception {
        File jsonFile = new File("./resource/test.json").getAbsoluteFile();
        String json = Files.readAllLines(jsonFile.toPath()).stream().collect(Collectors.joining());

        List<TemplateModel> infos = getInfo(json);
        infos.forEach(System.out::println);
    }

    private static List<TemplateModel> getInfo(String response) throws JSONException {
        JSONArray array = new JSONArray(response);
        List<TemplateModel> models = new ArrayList<>(array.length());
        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonObject = (JSONObject) array.get(i);
            TemplateModel model = new TemplateModel();
            model.setCategories(((JSONArray) jsonObject.get("categories")).get(0).toString());
            model.setTitle(((JSONObject) jsonObject.get("title")).get("rendered").toString());
            model.setAuthor(jsonObject.get("author").toString());
            model.setDate(jsonObject.get("date").toString());

            models.add(model);
        }

        return models;
    }
}