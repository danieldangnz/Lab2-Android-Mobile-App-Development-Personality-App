package project.ames.ac.nz.lab_personalitydifferenceapp;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ScammingTrick extends AppCompatActivity {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //1: Declare variables
    private ImageView imageView;//ImageView displays illustration images
    private TextView trickTitle;//TextView to display trick title
    private TextView captionText;//Caption below each illustration image
    private Button nextBtn, previousBtn;//Buttons allow users to navigate illustrations back and fort
    //Declare an array of 10 "illustration" images, type: integer (int)
    private int[] images = {R.drawable.trick1, R.drawable.trick2, R.drawable.trick3,
            R.drawable.trick4, R.drawable.trick5, R.drawable.trick6, R.drawable.trick7,
            R.drawable.trick8, R.drawable.trick9, R.drawable.trick10};
    //Declare an array of 10 captions, type: String
    private String[] caption_array = new String[10];
    //Declare an array of 10 trick title, type: String
    private String[] trick_title_array = new String[10];

    //Declare an "index" variable indicates current displaying illustration & capture
    private int index = 0;// initial value = 0 means the first illustration when launching the app

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scamming_trick);

        //2: Find references and do casting for the UI Widgets
        imageView = (ImageView) findViewById(R.id.illustrationImage);
        captionText = (TextView) findViewById(R.id.caption);
        trickTitle = (TextView) findViewById(R.id.trickTitle);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        previousBtn = (Button) findViewById(R.id.previousBtn);

        //Retrieve the "caption_array" declared in "strings.xml" file by using getResource() method
        caption_array = getResources().getStringArray(R.array.caption_array);

        //Retrieve the "trick_title_array" declared in "strings.xml" file by using getResource() method
        trick_title_array = getResources().getStringArray(R.array.trick_title_array);

        //3: Display the first image, first title & first caption when launching the app
        trickTitle.setText(trick_title_array[index]);
        imageView.setImageResource(images[index]);
        //captionText.setText((index + 1) + ": " + captions[index]);
        captionText.setText((index + 1) + ": " + caption_array[index]);

        //4: Set click listener for "NEXT" button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //5: Check if index is equal to 11 (12nd illustration)? If yes, set index=0 to start over
                if (index == 9) {
                    index = 0;//start over slide show from beginning
                    trickTitle.setText(trick_title_array[index]);
                    imageView.setImageResource(images[index]);
                    //captionText.setText((index + 1) + ": " + captions[index]);
                    captionText.setText((index + 1) + ": " + caption_array[index]);

                } else {
                    index++;//Increase index by 1 to move to the next illustration
                    trickTitle.setText(trick_title_array[index]);
                    imageView.setImageResource(images[index]);
                    //captionText.setText((index + 1) + ": " + captions[index]);
                    captionText.setText((index + 1) + ": " + caption_array[index]);
                }
            }
        });

        //6: Set click click listener for "PREVIOUS" button
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //7: Check if index is equal to 0? If yes, set index=11 to display the 12th illustration
                if (index == 0) {
                    index = 11;//set the 12nd illustration
                    trickTitle.setText(trick_title_array[index]);
                    imageView.setImageResource(images[index]);
                    //captionText.setText((index + 1) + ": " + captions[index]);
                    captionText.setText((index + 1) + ": " + caption_array[index]);
                } else {
                    index--;//Decrease index by 1 to move back an illustration
                    trickTitle.setText(trick_title_array[index]);
                    imageView.setImageResource(images[index]);
                    //captionText.setText((index + 1) + ": " + captions[index]);
                    captionText.setText((index + 1) + ": " + caption_array[index]);
                }
            }
        });
    }

    /*
    private Bitmap LoadImage(String URL, BitmapFactory.Options options)
    {
        Bitmap bitmap = null;
        InputStream in = null;
        try {
            in = OpenHttpConnection(URL);
            bitmap = BitmapFactory.decodeStream(in, null, options);
            in.close();
        } catch (IOException e1) {
        }
        return bitmap;
    }

    private InputStream OpenHttpConnection(String strURL) throws IOException{
        InputStream inputStream = null;
        URL url = new URL(strURL);
        URLConnection conn = url.openConnection();

        try{
            HttpURLConnection httpConn = (HttpURLConnection)conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();

            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
            }
        }
        catch (Exception ex)
        {
        }
        return inputStream;
    }
    */
}
