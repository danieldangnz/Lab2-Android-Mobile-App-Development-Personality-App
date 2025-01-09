package project.ames.ac.nz.lab_personalitydifferenceapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class DownloadFromInternet {
    //////////////////////////////////////////////////////////////////////////////////////////////
    //Add downloadImage() method  that takes in a string of URL where to download image on Internet
    public static Bitmap DownloadImage(String downloadImageURL) {
        ///////////////////////////////////////////////////////////////
        //Step 1: Declare variables
        Bitmap bitmap = null;//This variable to store downloaded image from Internet
        InputStream inputStream = null;//Image will be downloaded in "input-stream" form from Internet
        int response = -1;//Store the resulted "status" when connecting app to Internet
        String urlString = downloadImageURL;//Unique Resource Locator:  web address which is a reference
                                             //to a web resource that specifies its location on a computer network

        ///////////////////////////////////////////////////////////////
        //Step 2: Call OpenHttpConnection() method to open a connection to a HTTP server by using
        //        "try ...catch" to detect error if the connection fails to establish
        try {
            //1: Create URL or link of image to be downloaded
            URL imageURL = new URL(urlString);
            //2: Establish or open an "Internet connection"
            URLConnection connection = imageURL.openConnection();
            //3: Check whether the "internet connection" is valid? If not, throw an Exception "Not an HTTP  connection"
            if (!(connection instanceof HttpsURLConnection)) {
                throw new IOException("URL is not an HTTP  URL");
            }
            //4: When the "internet connection" is valid, download image from Internet in the stream format by
            //    creating a httpsURLConnect object is cast into an HttpURLConnection object a
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connection;
            //5: Set properties of the HTTP connection
            httpsURLConnection.setAllowUserInteraction(false);
            httpsURLConnection.setInstanceFollowRedirects(true);
            httpsURLConnection.setRequestMethod("GET");//Type of Request: GET
            httpsURLConnection.connect();
            //6: Connect to the HTTP server and get a response from the server
            response = httpsURLConnection.getResponseCode();
            //7: If the "response" code is HTTP_OK, you then get the InputStream object from the connection
            //    so that you can begin to read incoming data from the server
            if (response == httpsURLConnection.HTTP_OK) {
                //If the connection is OKAY, read incoming data from the server
                inputStream = httpsURLConnection.getInputStream();
            }

        } catch (Exception e) {
            //In case of error detected, display a message "Error Connecting" on "Logcat" screen
            Log.e("OpenHttpConnection", "Error connecting");
        }

        ///////////////////////////////////////////////////////////////
        //Step 3: Decode the downloaded image by using decodeStream() method of the BitmapFactory class
        try {
            //8: Use decodeStream() method to decode the downloaded "InputStream" into a bitmap image
            bitmap = BitmapFactory.decodeStream(inputStream);
            //9: Close the inputStream when finishing decode process
            inputStream.close();

        } catch (IOException ex) {
            //In case of error detected, display the type of error on "Logcat" screen
            ex.printStackTrace();
        }

        ///////////////////////////////////////////////////////////////
        //Step 4: Return the decoded bitmap as output
        return bitmap;
    }

}
