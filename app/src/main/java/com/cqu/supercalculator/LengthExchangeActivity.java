package com.cqu.supercalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class LengthExchangeActivity extends AppCompatActivity {
    private Spinner lengthUpSpinner;
    private Spinner lengthDownSpinner;
    private ArrayAdapter lengthAdapter;
    private TextView lengthUpText;
    private TextView lengthDownText;
    private TextView lengthNowText;  //现在活动的TextView
    private double lengthNowNum;     //现在活动的数字
    private double lengthUpPower,lengthDownPower;   //设置权重
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_length_exchange);

        /******初始化界面的组件*******/
        initView();
    }

    private void initView(){
        /*需要初始化spinner和adapter*/
        lengthUpSpinner=(Spinner)findViewById(R.id.lengthupspinner);
        lengthDownSpinner=(Spinner)findViewById(R.id.lengthdownspinner);
        lengthAdapter=ArrayAdapter.createFromResource(this, R.array.LengthUnit, android.R.layout.simple_spinner_item);
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lengthUpSpinner.setAdapter(lengthAdapter);
        lengthDownSpinner.setAdapter(lengthAdapter);
        lengthUpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=lengthAdapter.getItem(i).toString();
                if(str.equals("千米km")){
                    lengthUpPower=100000;
                }else if(str.equals("米m")){
                    lengthUpPower=100;
                }else if(str.equals("分米dm")){
                    lengthUpPower=10;
                }else if(str.equals("厘米cm")){
                    lengthUpPower=1;
                }else if(str.equals("毫米mm")){
                    lengthUpPower=0.1;
                }else if(str.equals("微米um")){
                    lengthUpPower=0.0001;
                }else if(str.equals("纳米nm")){
                    lengthUpPower=0.00000001;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        lengthDownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=lengthAdapter.getItem(i).toString();
                if(str.equals("千米km")){
                    lengthDownPower=100000;
                }else if(str.equals("米m")){
                    lengthDownPower=100;
                }else if(str.equals("分米dm")){
                    lengthDownPower=10;
                }else if(str.equals("厘米cm")){
                    lengthDownPower=1;
                }else if(str.equals("毫米mm")){
                    lengthDownPower=0.1;
                }else if(str.equals("微米um")){
                    lengthDownPower=0.0001;
                }else if(str.equals("纳米nm")){
                    lengthDownPower=0.00000001;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        lengthUpText=(TextView)findViewById(R.id.lengthuptext);
        lengthDownText=(TextView)findViewById(R.id.lengthdowntext);
        lengthUpText.setTextColor(0xFF64C1C9);
        lengthNowText=lengthUpText;  //默认活动窗口是lengthuptext,上方窗口
        lengthNowText.setText("0");
    }

    public void clickButton(View v){
        int id=v.getId();
        if(lengthNowText.getText().toString().equals("0")){  //如果第一位是0，消除第一位
            lengthNowText.setText("");
        }
        if(id==R.id.lengthac){                 //AC键和回退键的特殊判断
            lengthUpText.setText("0");
            lengthDownText.setText("0");
        }else if(id==R.id.lengthreturn){
           /*字符串的回退处理*/
           if(lengthNowText.getText().toString().equals("")){
               lengthNowText.setText("0");
           }else{
               String str=lengthNowText.getText().toString();
               str=str.substring(0,str.length()-1);
               lengthNowText.setText(str);
               if(lengthNowText.getText().toString().equals("")){   //防止回退键将字符串设为空值
                   lengthNowText.setText("0");
               }
           }
        }
        if(lengthNowText.length()>=10){
            id=0;
        }else if(id==R.id.lengthone){
            lengthNowText.append("1");
        }else if(id==R.id.lengthtwo){
            lengthNowText.append("2");
        }else if(id==R.id.lengththree){
            lengthNowText.append("3");
        }else if(id==R.id.lengthfour){
            lengthNowText.append("4");
        }else if(id==R.id.lengthfive){
            lengthNowText.append("5");
        }else if(id==R.id.lengthsix){
            lengthNowText.append("6");
        }else if(id==R.id.lengthseven){
            lengthNowText.append("7");
        }else if(id==R.id.lengtheight){
            lengthNowText.append("8");
        }else if(id==R.id.lengthnine){
            lengthNowText.append("9");
        }else if(id==R.id.lengthzero){  //zero需要特殊处理
            if(!lengthNowText.getText().equals("0")){
                lengthNowText.append("0");
            }
        }else if(id==R.id.lengthpoint){ //point需要特殊处理
            int i=-2;
            i=lengthNowText.getText().toString().indexOf(".");
            if(i<0){
                lengthNowText.append(".");
            }
        }
        try{
            lengthNowNum =Double.parseDouble(lengthNowText.getText().toString());
        }catch(Exception e){}
        lengthExchange();                   //对字符处理成数字进行长度转换的运算,对响应窗口进行操作
    }

    public void lengthExchange(){
        double lengthrate=lengthUpPower/lengthDownPower;
        double lastNum=lengthNowNum*lengthrate;
        TextView lengthLastText;                //设置区别于活动窗口的响应窗口文本域
        if(lengthNowText==lengthUpText){
            lengthLastText=lengthDownText;
        }else{
            lengthLastText=lengthUpText;
        }
        lengthLastText.setText(String.valueOf(lastNum));
        String lastText=lengthLastText.getText().toString();
        if(lastText.substring(lastText.length()-2).equals(".0")){
            lengthLastText.setText(lastText.substring(0,lastText.length()-2));
        }else{
            lengthLastText.setText(lastText);
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
        try{
            lengthNowNum =Double.parseDouble(lengthNowText.getText().toString());
        }catch(Exception e){}
    }
}
