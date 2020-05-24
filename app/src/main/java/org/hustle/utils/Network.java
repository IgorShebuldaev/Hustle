package org.hustle.utils;

import org.json.JSONArray;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Network {
    public static URL getURL() throws MalformedURLException {
        return new URL("http://10.0.2.2:5000/api/news");
    }

    public static JSONArray getResponseFromURL(URL url) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                String json = scanner.next();
                return new JSONArray(json);
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception();
        }
        finally {
            urlConnection.disconnect();
        }
    }
}
