package com.example.android.myquiz;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * The following app is a simple South Park quiz
 */
public class MainActivity extends AppCompatActivity {

    int score = 0;
    boolean is1BoxChecked, is2BoxChecked, is3BoxChecked, is4BoxChecked;
    CheckBox firstAnswerCheckBox, secondAnswerCheckBox, thirdAnswerCheckBox, fourthAnswerCheckBox;
    EditText question2Text, question4Text, question7Text, question8Text;
    MediaPlayer backgroundSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //adds some background music
        backgroundSong= MediaPlayer.create(this, R.raw.southparkendcredits);
        backgroundSong.start();
    }

    //this method stops the background music when the app closes
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (backgroundSong != null) backgroundSong.release();
    }
    /**
     * This method is called when the result button is clicked
     */
    public void submitResult(View view) {
        //check whether the right radioButton was chosen in  the 5th question
        RadioButton question1RadioButton = (RadioButton) findViewById(R.id.question1);
        boolean is1Checked = question1RadioButton.isChecked();

        //get the answer that was given by the user in the 2nd question
        question2Text = (EditText) findViewById(R.id.question2);
        String answer2 = question2Text.getText().toString();

        //check which (multiple) answers were given for the 3rd question
        firstAnswerCheckBox = (CheckBox) findViewById(R.id.check1);
        is1BoxChecked = firstAnswerCheckBox.isChecked();
        secondAnswerCheckBox = (CheckBox) findViewById(R.id.check2);
        is2BoxChecked = secondAnswerCheckBox.isChecked();
        thirdAnswerCheckBox = (CheckBox) findViewById(R.id.check3);
        is3BoxChecked = thirdAnswerCheckBox.isChecked();
        fourthAnswerCheckBox = (CheckBox) findViewById(R.id.check4);
        is4BoxChecked = fourthAnswerCheckBox.isChecked();

        //get the answer that was given in the 4th question
        question4Text = (EditText) findViewById(R.id.question4);
        String answer4 = question4Text.getText().toString();

        //check whether the right radioButton was chosen in  the 5th question
        RadioButton question5RadioButton = (RadioButton) findViewById(R.id.question5);
        boolean is5Checked = question5RadioButton.isChecked();

        //check whether the right radioButton was chosen in  the 6th question
        RadioButton question6RadioButton = (RadioButton) findViewById(R.id.question6);
        boolean is6Checked = question6RadioButton.isChecked();

        //check whether the right radioButton was chosen in  the 7th question
        question7Text = (EditText) findViewById(R.id.question7);
        String answer7 = question7Text.getText().toString();

        //check whether the right radioButton was chosen in  the 8th question
        question8Text = (EditText) findViewById(R.id.question8);
        String answer8 = question8Text.getText().toString();

        int finalScore = calculateScore(is1Checked, answer2, is1BoxChecked, is2BoxChecked, is3BoxChecked, is4BoxChecked, answer4, is5Checked, is6Checked, answer7, answer8);

        //Toast message with the result, different message according to the score
        if (finalScore<4)
            Toast.makeText(getApplicationContext(), "You only got " + finalScore + ", you're wasting my time!", Toast.LENGTH_SHORT).show();
        else if(finalScore<7)
            Toast.makeText(getApplicationContext(), "Not bad, you got " + finalScore +", I guess we can become friends...", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(), "Well done! You got " + finalScore +"! I RESPECT YOUR AUTHORITAH! ", Toast.LENGTH_SHORT).show();

    }

    /**
     * this method calculates the result
     * @param is1Checked check whether the correct answer to 1st question was checked
     * @param answer2 check whether 2nd answer is correct
     * @param is1BoxChecked check whether 1st option was chosen in 3rd question
     * @param is2BoxChecked check whether 2nd option was chosen in 3rd question
     * @param is3BoxChecked check whether 3rd option was chosen in 3rd question
     * @param is4BoxChecked check whether 4th option was chosen in 3rd question
     * @param answer4 check whether 4th answer is correct
     * @param is5Checked check whether correct answer to 5th question was chosen
     * @param is6Checked check whether correct answer to 6th question was chosen
     * @param answer7 check whether 7th answer is correct
     * @param answer8 check whether 8th answer is correct
     * @return score the final score the user gets
     */
    private int calculateScore(boolean is1Checked, String answer2, boolean is1BoxChecked, boolean is2BoxChecked, boolean is3BoxChecked, boolean is4BoxChecked, String answer4, boolean is5Checked, boolean is6Checked, String answer7, String answer8) {

        if (score != 0)
            score = 0;

        if (is1Checked)
            score += 1;

        if (answer2.equalsIgnoreCase("Barbra Streisand") || answer2.equalsIgnoreCase("Barbara Streisand"))
            score += 1;

        if (is1BoxChecked && is2BoxChecked && !is3BoxChecked && !is4BoxChecked)
            score += 1;

        if (answer4.equalsIgnoreCase("Randy"))
            score += 1;

        if (is5Checked)
            score += 1;

        if (is6Checked)
            score += 1;

        if (answer7.equalsIgnoreCase("Primus"))
            score += 1;

        if (answer8.equalsIgnoreCase("George Clooney"))
            score += 1;

        return score;

    }

    /**
     * this method is called to reset the score and all answers
     */
    public void resetScore(View view) {
        //clear the radioBox that was selected in the 1st question
        RadioGroup Answer1RadioGroup = (RadioGroup) (findViewById(R.id.radioGroup1));
        Answer1RadioGroup.clearCheck();

        /* clear answers given on all questions that required text
        *  if statements to do so only if the EditTexts are not null,
        *  or else it throws NullPointerException
        */
        if (question2Text!=null)
            question2Text.getText().clear();

        if (question4Text!=null)
            question4Text.getText().clear();

        if (question7Text!=null)
            question7Text.getText().clear();

        if (question8Text!=null)
            question8Text.getText().clear();


        /* un-check each checkBox that may have been selected
        *  if statements to do so only if CheckBoxes have been checked,
        *  or else it throws NullPointerException
        */
        if (firstAnswerCheckBox!=null)
            firstAnswerCheckBox.setChecked(false);

        if(secondAnswerCheckBox!=null)
            secondAnswerCheckBox.setChecked(false);

        if(thirdAnswerCheckBox!=null)
            thirdAnswerCheckBox.setChecked(false);

        if(fourthAnswerCheckBox!=null)
            fourthAnswerCheckBox.setChecked(false);

        //clear the radioBox that was selected in the 5th question
        RadioGroup Answer5RadioGroup = (RadioGroup) (findViewById(R.id.radioGroup5));
        Answer5RadioGroup.clearCheck();

        //clear the radioBox that was selected in the 6th question
        RadioGroup Answer6RadioGroup = (RadioGroup) (findViewById(R.id.radioGroup6));
        Answer6RadioGroup.clearCheck();

        score = 0;

    }
}
