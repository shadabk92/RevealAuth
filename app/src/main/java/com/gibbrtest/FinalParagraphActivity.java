package com.gibbrtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class FinalParagraphActivity extends AppCompatActivity {

    TextView newstory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_paragraph);
        Intent intent = getIntent();

        String updatedStory = intent.getStringExtra("updatedstory");

        newstory = (TextView) findViewById(R.id.newstory);

        newstory.setText(updatedStory);

    }

}
