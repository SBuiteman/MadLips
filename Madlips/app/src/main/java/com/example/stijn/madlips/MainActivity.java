/**
 * MainActivity Madlips
 * Stijn Buiteman
 */
package com.example.stijn.madlips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class handles any of five button clicks and starts the Write activity with a story
 * to be filled in depending on the button clicked.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Properties of the class: button, TV and IV.
    private Button simpleButton;
    private Button tarzanButton;
    private Button universityButton;
    private Button clothesButton;
    private Button danceButton;

    private TextView welcomeText;

    private ImageView frontPageImage;

    public String madlipsText;

    /**
     * On create the init method is called and the storytext is set to empty.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call to method that initiates the class objects
        init();

        // clear madlipsText for new story.
        madlipsText = "";
    }

    /**
     * Initializes object belong to this class, text, image, and buttons.
     */
    public void init() {
        welcomeText = (TextView) findViewById(R.id.welcomeText);

        frontPageImage = (ImageView) findViewById(R.id.frontPageImage);

        simpleButton = (Button) findViewById(R.id.simpleButton);
        tarzanButton = (Button) findViewById(R.id.tarzanButton);
        universityButton = (Button) findViewById(R.id.universityButton);
        clothesButton = (Button) findViewById(R.id.clothesButton);
        danceButton = (Button) findViewById(R.id.danceButton);

        simpleButton.setOnClickListener(this);
        tarzanButton.setOnClickListener(this);
        universityButton.setOnClickListener(this);
        clothesButton.setOnClickListener(this);
        danceButton.setOnClickListener(this);
    }

    /**
     * Switch for the five buttons, each starts the WriteActivity with a different story by passing
     * a different string via intent..
     */
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simpleButton:
                madlipsText = getResources().getString(R.string.simpleText);
                Intent startsimpleWriteActivity = new Intent(MainActivity.this, WriteActivity.class);
                startsimpleWriteActivity.putExtra("EmptyStory", madlipsText);
                startActivity(startsimpleWriteActivity);
                break;
            case R.id.tarzanButton:
                madlipsText = getResources().getString(R.string.tarzanText);
                Intent starttarzanWriteActivity = new Intent(MainActivity.this, WriteActivity.class);
                starttarzanWriteActivity.putExtra("EmptyStory", madlipsText);
                startActivity(starttarzanWriteActivity);
                break;
            case R.id.universityButton:
                madlipsText = getResources().getString(R.string.universityText);
                Intent startuniversityWriteActivity = new Intent(MainActivity.this, WriteActivity.class);
                startuniversityWriteActivity.putExtra("EmptyStory", madlipsText);
                startActivity(startuniversityWriteActivity);
                break;
            case R.id.clothesButton:
                madlipsText = getResources().getString(R.string.clothesText);
                Intent startclothesWriteActivity = new Intent(MainActivity.this, WriteActivity.class);
                startclothesWriteActivity.putExtra("EmptyStory", madlipsText);
                startActivity(startclothesWriteActivity);
                break;
            case R.id.danceButton:
                madlipsText = getResources().getString(R.string.danceText);
                Intent startdanceWriteActivity = new Intent(MainActivity.this, WriteActivity.class);
                startdanceWriteActivity.putExtra("EmptyStory", madlipsText);
                startActivity(startdanceWriteActivity);
                break;
            default:
                break;

        }
    }
}
