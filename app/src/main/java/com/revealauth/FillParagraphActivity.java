package com.revealauth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FillParagraphActivity extends AppCompatActivity {
    Button btn_ok;

    EditText word;
    TextView word_count, action_text;


    String action;

    int wordcount = 0;

    String newstory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_paragraph);

        updatedStory = readTxt();


        btn_ok = (Button) findViewById(R.id.btn_ok);

        word = (EditText) findViewById(R.id.word);

        word_count = (TextView) findViewById(R.id.word_count);

        action_text = (TextView) findViewById(R.id.action_text);

        wordcount = wordcount(updatedStory
        );


        btn_ok.setOnClickListener(view -> {
            if (wordcount > 0) {
                editStory(updatedStory);

            } else {

                Toast.makeText(FillParagraphActivity.this, "Hurray!! Lets read what we wrote!!!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), FinalParagraphActivity.class);
                intent.putExtra("updatedstory", newstory);
                startActivity(intent);
            }

            word_count.setText(wordcount + " word(s) left");

            Log.v("story", updatedStory);
        });


    }

    private String readTxt() {

        InputStream inputStream = getResources().openRawResource(R.raw.gibbr_fill);
        System.out.println(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1) {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }


    public static final String newStory = "newStory";
    SharedPreferences sharedpreferences;
    String updatedStory = "";

    private void editStory(String story) {

        if (updatedStory.contentEquals("")) {

        }
        String action_replace = "<" + action + ">";
        newstory = story.replace(action_replace, word.getText().toString());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(newStory, newstory);
        editor.apply();


        updatedStory = preferences.getString("newStory", "");

        if (!updatedStory.equalsIgnoreCase("")) {
            wordcount = wordcount(updatedStory);
        }

        if (wordcount == 0) {
            btn_ok.setText("Done");
        }
        if (!updatedStory.equalsIgnoreCase("") && wordcount > 0) {
            story = updatedStory;
            wordcount = wordcount(story);
        } else {

            updatedStory = readTxt();
        }

    }

    private int wordcount(String story) {

        Pattern startpattern = Pattern.compile("\\<\\w+\\>");
        int count = 0;


        Matcher matcher = startpattern.matcher(story);

        while (matcher.find())
            count++;

        if (count > 0) {
            action = story.substring(story.indexOf("<") + 1, story.indexOf(">"));

        }
        word_count.setText(count + " word(s) left ");

        action_text.setText("please type a/an " + action);

        return count;
    }

}
