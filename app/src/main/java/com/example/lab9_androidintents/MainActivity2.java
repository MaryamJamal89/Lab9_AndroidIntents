package com.example.lab9_androidintents;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi);

        textView = (TextView)findViewById(R.id.textView);
        Bundle bundle = getIntent().getExtras();
        textView.setText("Hi " + bundle.getString("name"));
    }
}