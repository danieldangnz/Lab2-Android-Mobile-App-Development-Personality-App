package project.ames.ac.nz.lab_personalitydifferenceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {
    //
    private Button personalityDif, scamTrick, funnyGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        //
        personalityDif = (Button) findViewById(R.id.personalityDifference);
        personalityDif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //
        scamTrick = (Button) findViewById(R.id.scamTricks);
        scamTrick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, ScammingTrick.class);
                startActivity(intent);
            }
        });
        //
        funnyGif = (Button) findViewById(R.id.funnyGifs);
        funnyGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, FunnyGif.class);
                startActivity(intent);
            }
        });
    }
}
