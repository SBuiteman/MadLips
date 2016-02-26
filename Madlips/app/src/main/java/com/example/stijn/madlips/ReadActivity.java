/**
 * WriteActivity Madlips
 * Stijn Buiteman
 */
package com.example.stijn.madlips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * This class contains a Button to return the MainActivity. The other object is a TextView
 * storyDisplay in which the finished story send via intent startReadActivity from the
 * WriteActivity.
 */
public class ReadActivity extends AppCompatActivity implements View.OnClickListener {

    // Properties of the class: Btn and a TV.
    private Button makeAnotherStory;

    private TextView storyDisplay;

    /**
     * Constructs the Button and TV used in this class.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        // Call to init method
        init();
    }

    /**
     * This method initializes the objects belonging to the ReadActivity class.
     */
    public void init() {
        makeAnotherStory = (Button) findViewById(R.id.makeAnotherStory);

        storyDisplay = (TextView) findViewById(R.id.storyDisplay);

        // Get the final story send via intent from WriteActivity and display
        Bundle extras = getIntent().getExtras();
        storyDisplay.setText(extras.getString("Story"));

        // set onClickListener for button
        makeAnotherStory.setOnClickListener(this);

    }

    /**
     * Clicking makeAnotherStory takes user back to homescreen to select another story.
     */
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.makeAnotherStory:
                Intent startMainActivity = new Intent(ReadActivity.this, MainActivity.class);
                startActivity(startMainActivity);
                break;
            default:
                break;
        }
    }
}
