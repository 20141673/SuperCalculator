package com.cqu.supercalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class SpeedActivity extends AppCompatActivity {
    private Spinner speedUpSpinner;
    private Spinner speedDownSpinner;
    private ArrayAdapter speedAdapter;
    private TextView speedUpText;
    private TextView speedDownText;
    private TextView speedNowText;  //现在活动的TextView
    private double speedNowNum;     //现在活动的数字
    private double speedUpPower,speedDownPower;   //设置权重
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);
      initView();
    }
    private void initView(){
        /*需要初始化spinner和adapter*/
        speedUpSpinner=(Spinner)findViewById(R.id.speedupspinner);
        speedDownSpinner=(Spinner)findViewById(R.id.speeddownspinner);
        speedAdapter=ArrayAdapter.createFromResource(this, R.array.SpeedUnit, android.R.layout.simple_spinner_item);
        speedAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        speedUpSpinner.setAdapter(speedAdapter);
        speedDownSpinner.setAdapter(speedAdapter);
        speedUpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=speedAdapter.getItem(i).toString();
                if(str.equals("千米/时km/h")){
                    speedUpPower=1;
                }else if(str.equals("米/秒m/s")){
                    speedUpPower=3.6;
                }else if(str.equals("英里/时mph")){
                    speedDownPower=1.609344;
                }else if(str.equals("海里/时kn")){
                    speedUpPower=1.852;
                }else if(str.equals("千米/秒km/s")){
                    speedUpPower=3600;
                }else if(str.equals("光速c")){
                    speedUpPower=1.07925285e9;
                }else if(str.equals("马赫Ma")){
                    speedUpPower=1225.08;
                }else if(str.equals("英寸/秒ips")){
                    speedUpPower=0.09144;
                }
                speedUpText.setText("0");
                speedDownText.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        speedDownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=speedAdapter.getItem(i).toString();
                if(str.equals("千米/时km/h")){
                    speedDownPower=1;
                }else if(str.equals("米/秒m/s")){
                    speedDownPower=3.6;
                }else if(str.equals("英里/时mph")){
                    speedDownPower=1.609344;
                }else if(str.equals("海里/时kn")){
                    speedDownPower=1.852;
                }else if(str.equals("千米/秒km/s")){
                    speedDownPower=3600;
                }else if(str.equals("光速c")){
                    speedDownPower=1.07925285e9;
                }else if(str.equals("马赫Ma")){
                    speedDownPower=1225.08;
                }else if(str.equals("英寸/秒ips")){
                    speedDownPower=0.09144;
                }
                speedUpText.setText("0");
                speedDownText.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        speedUpText=(TextView)findViewById(R.id.speeduptext);
        speedDownText=(TextView)findViewById(R.id.speeddowntext);
        speedUpText.setTextColor(0xFF64C1C9);
        speedNowText=speedUpText;  //默认活动窗口是lengthuptext,上方窗口
        speedNowText.setText("0");
    }
    public void clickButton(View v){

        int id=v.getId();
        if(speedNowText.getText().toString().equals("0")){  //如果第一位是0，消除第一位
           speedNowText.setText("");
        }
        if(speedNowText.getText().toString().equals(".")){  //如果第一位是0，消除第一位
            speedNowText.setText("0.");
        }
        if(id==R.id.speedac){                 //AC键和回退键的特殊判断
            speedUpText.setText("0");
            speedDownText.setText("0");
    }else if(id==R.id.speedreturn){
           /*字符串的回退处理*/
        if(speedNowText.getText().toString().equals("")){
            speedNowText.setText("0");
        }else{
            String str=speedNowText.getText().toString();
            str=str.substring(0,str.length()-1);
            speedNowText.setText(str);
            if(speedNowText.getText().toString().equals("")){   //防止回退键将字符串设为空值
                speedNowText.setText("0");
            }
        }
    }
        if(speedNowText.length()>=10){
            id=0;
        }

        if(id==R.id.speedone){
            speedNowText.append("1");
        }else if(id==R.id.speedtwo){
            speedNowText.append("2");
        }else if(id==R.id.speedthree){
            speedNowText.append("3");
        }else if(id==R.id.speedfour){
            speedNowText.append("4");
        }else if(id==R.id.speedfive){
            speedNowText.append("5");
        }else if(id==R.id.speedsix){
            speedNowText.append("6");
        }else if(id==R.id.speedseven){
            speedNowText.append("7");
        }else if(id==R.id.speedeight){
            speedNowText.append("8");
        }else if(id==R.id.speednine){
            speedNowText.append("9");
        }else if(id==R.id.speedzero){  //zero需要特殊处理
            if(!speedNowText.getText().equals("0")){
                speedNowText.append("0");
            }
        }
        else if(id==R.id.speedpoint){ //point需要特殊处理
            int i=-2;
            i=speedNowText.getText().toString().indexOf(".");
            if(i<0){
                speedNowText.append(".");
            }
        }
        try{
            speedNowNum =Double.parseDouble(speedNowText.getText().toString());
        }catch(Exception e){}
        speedExchange();                   //对字符处理成数字进行长度转换的运算,对响应窗口进行操作
    }

    public void speedExchange(){           //*****
        double speedrate;
        double lastNum;
        TextView speedLastText;                //设置区别于活动窗口的响应窗口文本域
        if(speedNowText==speedUpText){
            speedLastText=speedDownText;
            lastNum=speedNowNum*speedUpPower/speedDownPower;
        }else{
            speedLastText=speedUpText;
            lastNum=speedNowNum*speedDownPower/speedUpPower;
        }
        speedLastText.setText(String.valueOf(lastNum));
        String lastText=speedLastText.getText().toString();
        if(lastText.substring(lastText.length()-2).equals(".0")){
            speedLastText.setText(lastText.substring(0,lastText.length()-2));
        }else{
            speedLastText.setText(lastText);
        }
    }

    public void clickText(View v){
        int id=v.getId();
        if(id==R.id.speeduptext){
            speedNowText=speedUpText;
            speedUpText.setTextColor(0xFF64C1C9);
            speedDownText.setTextColor(Color.BLACK);
        }else if(id==R.id.speeddowntext){
            speedNowText=speedDownText;
            speedUpText.setTextColor(Color.BLACK);
            speedDownText.setTextColor(0xFF64C1C9);
        }
        try{
            speedNowNum =Double.parseDouble(speedNowText.getText().toString());
        }catch(Exception e){}

    }
}
