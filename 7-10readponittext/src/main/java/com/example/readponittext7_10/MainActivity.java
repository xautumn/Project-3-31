package com.example.readponittext7_10;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private TestTextView testTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testTextView = (TestTextView) findViewById(R.id.test_textview);
    }
    public void test(View v) {
        testTextView.setText("");
    }
}
