package com.xidian.qsf.touchauthencation.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xidian.qsf.touchauthencation.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mgetNegFea ;
    private Button mtest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mgetNegFea = (Button) findViewById(R.id.getNegtiveFeature);
        mtest = (Button) findViewById(R.id.test);

        mgetNegFea.setOnClickListener(this);
        mtest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.getNegtiveFeature:

                intent = new Intent(MainActivity.this, NegativeFea_Activity.class);
                startActivity(intent);
                break;
            case R.id.test:

                intent = new Intent(MainActivity.this, DemoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
