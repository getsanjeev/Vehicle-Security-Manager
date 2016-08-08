package com.alpha.sanjeev.vehiclesecuritymanager;

/**
 * Created by sanjeev on 16/7/16.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class parse_history
{
    public static String[] times;
    public static String[] lattitudes;
    public static String[] longitudes;
    public static String[] my_data;

    public static final String JSON_ARRAY = "result";
    public static final String KEY_TIME = "time";
    public static final String KEY_LATTITUDE = "latitude";
    public static final String KEY_LONGITUDE = "longitude";

    private JSONArray positions_array = null;

    private String json;

    public parse_history (String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            positions_array = jsonObject.getJSONArray(JSON_ARRAY);

            times = new String[positions_array.length()];
            lattitudes = new String[positions_array.length()];
            longitudes = new String[positions_array.length()];
            my_data = new String[positions_array.length()];

            for(int i=0;i<positions_array.length();i++){
                JSONObject jo = positions_array.getJSONObject(i);
                times[i] = jo.getString(KEY_TIME);
                lattitudes[i] = jo.getString(KEY_LATTITUDE);
                longitudes[i] = jo.getString(KEY_LONGITUDE);
                my_data[i] = times[i] + "\n" + lattitudes[i] + "\n" + longitudes[i];
            }
        }

        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}