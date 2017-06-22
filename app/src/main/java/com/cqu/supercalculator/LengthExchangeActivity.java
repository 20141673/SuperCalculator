package com.cqu.supercalculator;

import android.graphics.Color;
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
    private TextView lengthNowText;  //现在活动的TextView
    private long lengthUpNum;
    private long lengthDownNum;
    private long lengthNowNum;
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
        lengthUpText.setTextColor(0xFF64C1C9);
        lengthNowText=lengthUpText;  //默认活动窗口是uptext

    }

    public void clickButton(View v){
        int id=v.getId();
        if(lengthNowText.length()>=8){
            id=0;
        }
        if(id==R.id.lengthone){
            lengthNowText.append("1");
        }else if(id==R.id.lengthtwo){
            lengthNowText.append("2");
        }else if(id==R.id.lengththree){
            lengthNowText.append("3");
        }else if(id==R.id.lengthfour){
            lengthNowText.append("4");
        }else if(id==R.id.lengthfive){
            lengthNowText.append("5");
        }
    }

    public void clickText(View v){
        int id=v.getId();
        if(id==R.id.lengthuptext){
            lengthNowText=lengthUpText;
            lengthUpText.setTextColor(0xFF64C1C9);
            lengthDownText.setTextColor(Color.BLACK);
        }else if(id==R.id.lengthdowntext){
            lengthNowText=lengthDownText;
            lengthUpText.setTextColor(Color.BLACK);
            lengthDownText.setTextColor(0xFF64C1C9);
        }
    }
}
