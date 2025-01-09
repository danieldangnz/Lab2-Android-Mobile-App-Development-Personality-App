package daniel.project.ames.ac.a2_personalitydiffapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Step 1: Declare variables
    private ImageView imageView;//ImageView displays illustration images
    private TextView captionText;//Caption below each illustration image
    //Declare an array of 12 "illustration" images, type: integer (int)
    private int[] images = {R.drawable.image01, R.drawable.image02, R.drawable.image03,
            R.drawable.image04, R.drawable.image05, R.drawable.image06, R.drawable.image07,
            R.drawable.image08, R.drawable.image09, R.drawable.image10, R.drawable.image11,
            R.drawable.image12};
    //Declare an array of 12 captions, type: String
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
    //Declare an "index" variable indicates current displaying illustration & capture
    private int index = 0;// initial value = 0 means the first illustration when launching the app

    //"Next" button - 1: Declare a variable "nextBtn"
    private Button nextBtn;//Buttons allow users to navigate illustrations back and fort
    private Button previousBtn;//Buttons allow users to navigate illustrations back and fort

    ////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2: Find references and do casting for the UI Widgets
        imageView = (ImageView) findViewById(R.id.illustrationImage);
        captionText = (TextView) findViewById(R.id.caption);

        //"Next" button - 2: Find references and do casting for "nextBtn" Button
        nextBtn = (Button) findViewById(R.id.nextBtn);
        previousBtn = (Button)findViewById(R.id.previousBtn);

        //"Next" button - 3: Set click listener for "nextBtn" Button
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //5: Check if index is equal to 11 (12nd illustration)? If yes, set index=0 to start over
                if (index == 11) {
                    index = 0;//start over slide show from beginning
                    imageView.setImageResource(images[index]);
                    captionText.setText((index + 1) + ": " + captions[index]);
                } else {
                    index++;//Increase index by 1 to move to the next illustration
                    imageView.setImageResource(images[index]);
                    captionText.setText((index + 1) + ": " + captions[index]);
                }
            }
        });

        //Set click click listener for "PREVIOUS" button
        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //7: Check if index is equal to 0? If yes, set index=11 to display the 12th illustration
                if (index == 0) {
                    index = 11;//set the 12nd illustration
                    imageView.setImageResource(images[index]);
                    captionText.setText((index + 1) + ": " + captions[index]);
                } else {
                    index--;//Decrease index by 1 to move back an illustration
                    imageView.setImageResource(images[index]);
                    captionText.setText((index + 1) + ": " + captions[index]);
                }
            }
        });

    }
}
