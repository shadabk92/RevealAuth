package com.revealauth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;

public class Home extends AppCompatActivity {

    @BindView(R.id.btn_start)
    Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_start.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(),FillParagraphActivity.class);
            startActivity(intent);
        });

    }

}
