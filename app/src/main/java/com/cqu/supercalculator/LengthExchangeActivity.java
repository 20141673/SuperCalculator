package com.cqu.supercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class LengthExchangeActivity extends AppCompatActivity {
    private Spinner lengthUpSpinner;
    private Spinner lengthDownSpinner;
    private TextView lengthUpText;
    private TextView lengthDownText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_exchange);

        /******初始化界面的组件*******/
        initView();
    }

    private void initView(){
        lengthUpSpinner=(Spinner)findViewById(R.id.lengthupspinner);
        lengthDownSpinner=(Spinner)findViewById(R.id.lengthdownspinner);
        lengthUpText=(TextView)findViewById(R.id.lengthuptext);
        lengthDownText=(TextView)findViewById(R.id.lengthdowntext);
        lengthUpText.setText(" ");
        lengthDownText.setText(" ");
    }

    public void clickButton(View v){
        int id=v.getId();
        TextView lengthNowText=lengthUpText;

        if(id==R.id.lengthone){
            lengthNowText.setText("7");
        }else if(id==R.id.lengthtwo){
            lengthNowText.append("2");
        }else if(id==R.id.lengththree){

        }
    }
}
