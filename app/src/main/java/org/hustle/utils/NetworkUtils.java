package org.hustle.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    public static URL getURL() throws MalformedURLException {
        return new URL("http://10.0.2.2:3000/");
    }

    public static String getResponseFromURL(URL url) throws Exception {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
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
