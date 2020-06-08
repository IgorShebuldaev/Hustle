package org.hustle;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

import static org.hustle.utils.Network.getResponseFromURL;

public class QueryTask extends AsyncTask<URL, Void, JSONArray> {

    private RecyclerViewAdapter adapter;

    public QueryTask(RecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected JSONArray doInBackground(URL... urls) {
        JSONArray response = null;
        try {
            response = getResponseFromURL(urls[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(JSONArray response) {
        for (int i = 0; i < response.length(); i++) {
            try {
                adapter.addJsonObject((JSONObject)response.get(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
