package org.hustle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hustle.utils.Network.*;

public class MainActivity extends AppCompatActivity {
    MyRecyclerViewAdapter adapter;
    SwipeRefreshLayout refreshLayout;

    class QueryTask extends AsyncTask<URL, Void, JSONArray> {

        @Override
        protected JSONArray doInBackground(URL... urls) {
            refreshLayout.setRefreshing(true);
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
            refreshLayout.setRefreshing(false);
            for (int i = 0; i < response.length(); i++) {
                try {
                    adapter.addJsonObject((JSONObject)response.get(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshLayout = findViewById(R.id.refreshLayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateData();
            }
        });

        updateData();


    }

    private void updateData() {
        try {
            RecyclerView recyclerView = findViewById(R.id.rcView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new MyRecyclerViewAdapter(this);
            recyclerView.setAdapter(adapter);
            new QueryTask().execute(getURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
