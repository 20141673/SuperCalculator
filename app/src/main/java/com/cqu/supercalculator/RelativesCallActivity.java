package com.cqu.supercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RelativesCallActivity extends AppCompatActivity {
    private TextView relativeRecord,relativeResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatives_call);
        relativeRecord=(TextView)findViewById(R.id.relativerecord);
        relativeResult=(TextView)findViewById(R.id.relativeresult);
    }

    public void clickButton(View v){
        int id=v.getId();

    }
}
