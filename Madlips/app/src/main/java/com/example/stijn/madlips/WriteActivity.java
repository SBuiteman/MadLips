/**
 * WriteActivity Madlips
 * Stijn Buiteman
 */
package com.example.stijn.madlips;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Scanner;

/**
 * This class takes in the string send from MainActivity and passes it to the ReadStory activity
 * in which the string is read. The object readStory allows for calls to the ReadStory activity
 * to get placeHoldersRemaining, getNextPlaceholder and fillInPlaceholder.
 */
public class WriteActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "writeactivity";
    // Properties of the class: button, TV and ET.
    private Button submitWordButton;

    private TextView instructionText;
    private TextView wordsLeft;
    private TextView hintForUser;

    private EditText userInputText;

    private ReadStory readStory;

    private Scanner scannedStory;

    private String madlipsText;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * Construct all views and a scanner to read text.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // if there is a savedInstance, the readStory object is set to it.
        if (savedInstanceState != null) {

            // call to function to initialize objects.
            init();

            readStory = (ReadStory) (savedInstanceState.getSerializable("currentLibText"));

            // get Wordcount and hints for user.
            generateHint();

        }
        // if there is no savedInstance, readStory is object of class ReadStory.
        else {
            // call to function to initialize objects.
            init();

            // initialize class ReadStory as object to make calls to its methods
            readStory = new ReadStory();

            // Scan madlip text to pass it to ReadStory class and read it.
            Bundle extras = getIntent().getExtras();
            madlipsText = (extras.getString("EmptyStory"));
            scannedStory = new Scanner(madlipsText);
            readStory.read(scannedStory);

            // get Wordcount and hints for user.
            generateHint();
        }
    }

    /**
     * This method initializes the objects belonging to the WriteActivity class.
     */
    public void init() {

        // Textviews
        instructionText = (TextView) findViewById(R.id.instructionText);
        wordsLeft = (TextView) findViewById(R.id.wordsLeft);
        hintForUser = (TextView) findViewById(R.id.hintForUser);

        // EditTexts
        userInputText = (EditText) findViewById(R.id.userInputText);

        // Buttons
        submitWordButton = (Button) findViewById(R.id.submitWordButton);

        // set OnClickListeners for buttons
        submitWordButton.setOnClickListener(this);
    }

    /**
     * When a user has submitted some text and presses submit, the input is send to
     * ReadStory until all required fields are complete. Then, the ReadActivity is
     * started.
     */
    @Override
    public void onClick(View v) {

        // If some input is made the user can submit and will receive a new hint.
        if (userInputText.getText() != null) {
            readStory.fillInPlaceholder(userInputText.getText().toString());
            userInputText.setText("");
            userInputText.clearFocus();
            generateHint();
        }
    }

    /**
     * Calls readStory class for remaining words and what kind of word must be submitted.
     */
    public void generateHint() {
        userInputText.setHint(readStory.getNextPlaceholder());
        wordsLeft.setText(readStory.getPlaceholderRemainingCount() + " word(s) left");

        // show request if there are words left, else start ReadActivity and pass completed string.
        if (readStory.getPlaceholderRemainingCount() > 0) {
            hintForUser.setText("Please type a(n) " + readStory.getNextPlaceholder());
        }
        else {
            Intent startReadActivity = new Intent(WriteActivity.this, ReadActivity.class);
            startReadActivity.putExtra("Story", readStory.toString());
            startActivity(startReadActivity);
        }

    }

    /**
     * Saves the input already given by the user.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("currentLibText", readStory);
    }

}


