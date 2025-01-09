package project.ames.ac.nz.lab_personalitydifferenceapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

public class FunnyGif extends AppCompatActivity {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //1: Declare variables
    private WebView webView;//Webview displays gif images or video clips
    private VideoView videoView;
    private TextView captionText;//Caption below each illustration image
    private Button nextBtn, previousBtn;//Buttons allow users to navigate illustrations back and fort
    //Declare an array of 12 "illustration" images, type: integer (int)
    private int[] images = {R.drawable.image01, R.drawable.image02, R.drawable.image03,
            R.drawable.image04, R.drawable.image05, R.drawable.image06, R.drawable.image07,
            R.drawable.image08, R.drawable.image09, R.drawable.image10, R.drawable.image11,
            R.drawable.image12};
    //Declare an array of 12 captions, type: String
    private String[] caption_array = new String[11];
    //Declare an "index" variable indicates current displaying illustration & capture
    private int index = 0;// initial value = 0 means the first illustration when launching the app

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funny_gif);

        //2: Find references and do casting for the UI Widgets
        //webView = (WebView) findViewById(R.id.gifView);
        videoView = (VideoView) findViewById(R.id.gifView);
        captionText = (TextView) findViewById(R.id.caption);
        nextBtn = (Button) findViewById(R.id.nextBtn);
        previousBtn = (Button) findViewById(R.id.previousBtn);

        //Retrieve the "caption_array" declared in "strings.xml" file by using getResource() method
        caption_array = getResources().getStringArray(R.array.caption_array1);

        //Display video clip (mpeg4 format) on VideoView
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test);
        videoView.setVideoURI(uri);
        videoView.start();

        //captionText.setText((index + 1) + ": " + captions[index]);
        captionText.setText((index + 1) + ": " + caption_array[index]);


        //3: Display the first image and first caption when launching the app
        /*
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl("file:///android_asset/rash.gif");

        //MediaController mediaController = new MediaController(this);
        // mediaController.setAnchorView(videoView);
        //videoView.setMediaController(mediaController);
        //videoView.setVideoPath("/sdcard/blonde_secretary.3gp");
        */
    }
}
