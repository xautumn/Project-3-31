package com.xtc.a10_26recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class TestClickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_click);
        View viewById = findViewById(R.id.testView);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test","onClick");
                Toast.makeText(TestClickActivity.this,"clock",Toast.LENGTH_SHORT).show();
            }
        });

        View viewById1 = findViewById(R.id.testView1);
        viewById1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestClickActivity.this,"testView1",Toast.LENGTH_SHORT).show();

            }
        });
    }
}
