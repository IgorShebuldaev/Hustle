package org.hustle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.hustle.utils.NetworkUtils.*;

public class MainActivity extends AppCompatActivity {
    TextView textView;

    class QueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response = null;
            try {
                response = getResponseFromURL(urls[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return response;
        }

        @Override
        protected void onPostExecute(String response) {
            textView.setText(response);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        try {
            new QueryTask().execute(getURL());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
