package com.example.administrator.json;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ParseContent {

    private final String KEY_SUCCESS = "status";
    private final String KEY_MSG = "message";
    private Activity activity;

    ArrayList<HashMap<String, String>> arraylist;

    public ParseContent(Activity activity) {
        this.activity = activity;
    }

    public boolean isSuccess(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.optString(KEY_SUCCESS).equals("true")) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(KEY_MSG);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public ArrayList<TemplateModel> getInfo(String response) {
        ArrayList<TemplateModel> templateModelArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getString(KEY_SUCCESS).equals("true")) {

                arraylist = new ArrayList<HashMap<String, String>>();
                JSONArray dataArray = jsonObject.getJSONArray("data");

                for (int i = 0; i < dataArray.length(); i++) {
                    TemplateModel templateModel = new TemplateModel();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    templateModel.setCategories(dataobj.getString(AndyConstants.Params.CATEGORIES));
                    templateModel.setTitle(dataobj.getString(AndyConstants.Params.TITLE));
                    templateModel.setAuthor(dataobj.getString(AndyConstants.Params.AUTHOR));
                    templateModel.setDate(dataobj.getString(AndyConstants.Params.DATE));
                    templateModelArrayList.add(templateModel);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return templateModelArrayList;
    }

}
