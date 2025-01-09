package project.ames.ac.nz.lab_personalitydifferenceapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //1: Declare variables
    private ImageView imageView;//ImageView displays illustration images
    private TextView captionText;//Caption below each illustration image
    private Button nextBtn, previousBtn;//Buttons allow users to navigate illustrations back and fort
    //Declare an array of 12 "illustration" image urls, type: String
    private String[] imageURLs = new String[12];
    /*
    //Declare an array of 12 "illustration" images, type: integer (int)
    private int[] images = {R.drawable.image01, R.drawable.image02, R.drawable.image03,
            R.drawable.image04, R.drawable.image05, R.drawable.image06, R.drawable.image07,
            R.drawable.image08, R.drawable.image09, R.drawable.image10, R.drawable.image11,
            R.drawable.image12};
    */

    //Declare an array of 12 captions, type: String
    private String[] caption_array = new String[12];
    /*
    private String[] captions = {"To extroverts and introverts, parties are completely different events",//caption1
            "A conversation with a stranger can be a challenge to an introvert, whereas an extrovert takes up any opportunity to socialize",//caption2
            "This is true of conversations both face-to-face and over the telephone",//caption3
            "Meeting a chatty person in the elevator is a great start to the day for an extrovert. But an introvert dreams of riding it alone",//caption4
            "A large office space is like heaven to an extrovert, but it’s like being in a zoo for an introvert",//caption5
            "Both have their own strengths at work",//caption6
            "They understand leadership differently",//caption7
            "Home for an introvert is the best place on Earth. If you’re an extrovert, it’s just a place to catch your breath",//caption8
            "After an entire day of socializing, an extrovert is still full of energy. An introvert doesn’t feel the same",//caption9
            "Which is why they see the subway trip home somewhat differently",//caption10
            "The same is true of their ideal evening after a hard day",//caption11
            "On the other hand, both look forward to relaxing at the weekend...but in their own way!"};//caption12
     */
    //Declare an "index" variable indicates current displaying illustration & capture
    private int index = 0;// initial value = 0 means the first illustration when launching the app

    //Declare a Hanlder or Thread
    private Handler imageDownload_Handler = new Handler();
    private Bitmap bitmap;//This variable to store downloaded image from Internet

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //2: Find references and do casting for the UI Widgets
        imageView = (ImageView) findViewById(R.id.illustrationImage);
        captionText = (TextView) findViewById(R.id.caption);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        previousBtn = (Button) findViewById(R.id.previousBtn);

        //Retrieve the "caption_array" declared in "strings.xml" file by using getResource() method
        caption_array = getResources().getStringArray(R.array.caption_array1);

        //Populate populate the imageURLs array with 12 illustration image URLs
        imageURLs[0] = "https://files.brightside.me/files/news/part_23/239260/12550610-24329505-1-1474470168-650-9-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[1] = "https://files.brightside.me/files/news/part_23/239260/12550660-24329605-2-1474470185-650-19-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[2] = "https://files.brightside.me/files/news/part_23/239260/12550710-24329705-3-1474470203-650-11-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[3] = "https://files.brightside.me/files/news/part_23/239260/12550760-24329805-4-1474470223-650-12-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[4] = "https://files.brightside.me/files/news/part_23/239260/12550810-24329905-5-1474470240-650-7-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[5] = "https://files.brightside.me/files/news/part_23/239260/12550860-24330205-6-1474470289-650-8-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[6] = "https://files.brightside.me/files/news/part_23/239260/12550910-24330105-7-1474470272-650-8-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[7] = "https://files.brightside.me/files/news/part_23/239260/12550960-24330305-8-1474470302-650-18-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[8] = "https://files.brightside.me/files/news/part_23/239260/12551010-24330605-9-1474470375-650-10-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[9] = "https://files.brightside.me/files/news/part_23/239260/12551060-24330705-10-1474470387-650-27-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[10] = "https://files.brightside.me/files/news/part_23/239260/12551110-24330505-11-1474470362-650-9-1488295420-650-d81043295b-1490686715.jpg";
        imageURLs[11] = "https://files.brightside.me/files/news/part_23/239260/12551160-24330405-12-1474470342-650-6-1488295420-650-d81043295b-1490686715.jpg";

        //3: Display the first image and first caption when launching the app
        //imageView.setImageResource(images[index]);
        //downloadImageFromInternet(imageURLs[index], imageView);

        //Calls DownloadImage() method of DownloadFromInternet class in a THREAD
        new Thread(new Runnable() {
            @Override
            public void run() {
                imageDownload_Handler.post(new Runnable() {
                    //Perform loading image from Internet
                    Bitmap bitmap = DownloadFromInternet.DownloadImage(imageURLs[index]);

                    @Override
                    public void run() {
                        //Display the download image on the ImageView
                        imageView.setImageBitmap(bitmap);
                    }
                });
            }
        }).start();


        //captionText.setText((index + 1) + ": " + captions[index]);
        captionText.setText((index + 1) + ": " + caption_array[index]);

        //4: Set click listener for "NEXT" button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //5: Check if index is equal to 11 (12nd illustration)? If yes, set index=0 to start over
                if (index == 11) {
                    index = 0;//start over slide show from beginning

                    //imageView.setImageResource(images[index]);
                    //Calls DownloadImage() method of DownloadFromInternet class in a THREAD
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            imageDownload_Handler.post(new Runnable() {
                                //Perform loading image from Internet
                                Bitmap bitmap = DownloadFromInternet.DownloadImage(imageURLs[index]);

                                @Override
                                public void run() {
                                    //Display the download image on the ImageView
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }).start();

                    //captionText.setText((index + 1) + ": " + captions[index]);
                    captionText.setText((index + 1) + ": " + caption_array[index]);

                } else {
                    index++;//Increase index by 1 to move to the next illustration
                    //imageView.setImageResource(images[index]);
                    //Calls DownloadImage() method of DownloadFromInternet class in a THREAD
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            imageDownload_Handler.post(new Runnable() {
                                //Perform loading image from Internet
                                Bitmap bitmap = DownloadFromInternet.DownloadImage(imageURLs[index]);

                                @Override
                                public void run() {
                                    //Display the download image on the ImageView
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }).start();
                    //captionText.setText((index + 1) + ": " + captions[index]);
                    captionText.setText((index + 1) + ": " + caption_array[index]);
                }
            }
        });

        //6: Set click listener for "PREVIOUS" button
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //7: Check if index is equal to 0? If yes, set index=11 to display the 12th illustration
                if (index == 0) {
                    index = 11;//set the 12nd illustration

                    //imageView.setImageResource(images[index]);
                    //Calls DownloadImage() method of DownloadFromInternet class in a THREAD
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            imageDownload_Handler.post(new Runnable() {
                                //Perform loading image from Internet
                                Bitmap bitmap = DownloadFromInternet.DownloadImage(imageURLs[index]);

                                @Override
                                public void run() {
                                    //Display the download image on the ImageView
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }).start();

                    //captionText.setText((index + 1) + ": " + captions[index]);
                    captionText.setText((index + 1) + ": " + caption_array[index]);
                } else {
                    index--;//Decrease index by 1 to move back an illustration

                    //imageView.setImageResource(images[index]);
                    //Calls DownloadImage() method of DownloadFromInternet class in a THREAD
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            imageDownload_Handler.post(new Runnable() {
                                //Perform loading image from Internet
                                Bitmap bitmap = DownloadFromInternet.DownloadImage(imageURLs[index]);

                                @Override
                                public void run() {
                                    //Display the download image on the ImageView
                                    imageView.setImageBitmap(bitmap);
                                }
                            });
                        }
                    }).start();

                    //captionText.setText((index + 1) + ": " + captions[index]);
                    captionText.setText((index + 1) + ": " + caption_array[index]);
                }
            }
        });
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    //Add downloadImage() method  that takes in a string of URL where to download image on Internet
    public void downloadImageFromInternet(String imageURLString, final ImageView imageView) {
        ///////////////////////////////////////////////////////////////
        //Step 1: Declare variables
        //Bitmap bitmap = new Bitmap();//This variable to store downloaded image from Internet
        InputStream inputStream = null;//Image will be downloaded in "input-stream" form from Internet
        int response = -1;//Store the resulted "status" when connecting app to Internet
        //imageURL = "Unique Resource Locator"  web address which is a reference
        //to a web resource that specifies its location on a computer network

        ///////////////////////////////////////////////////////////////
        //Step 2: Call OpenHttpConnection() method to open a connection to a HTTP server by using
        //        "try ...catch" to detect error if the connection fails to establish
        try {
            //1: Convert imageURL String into an URL (link of image) to be downloaded
            URL imageURL = new URL(imageURLString);
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
        //Step 4: Load the downloaded imaage into ImageView
        //imageView.setImageResource(images[index]);
        //Calls DownloadImage() method of DownloadFromInternet class in a THREAD
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.setImageBitmap(bitmap);
                //imageView.setImageBitmap(bitmap);
            }
        });

    }

}
