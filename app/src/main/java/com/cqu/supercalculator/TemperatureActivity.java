package com.cqu.supercalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity {
    private Spinner temperatureUpSpinner;
    private Spinner temperatureDownSpinner;
    private ArrayAdapter temperatureAdapter;
    private TextView temperatureUpText;
    private TextView temperatureDownText;

    private TextView temperatureNowText;  //现在活动的TextView
    private double temperatureNowNum;     //现在活动的数字
    private double temperatureUpPower,temperatureDownPower;   //设置权重
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        initView();
    }
    private void initView(){
        temperatureUpSpinner=(Spinner)findViewById(R.id.temperatureupspinner);
        temperatureDownSpinner=(Spinner)findViewById(R.id.temperaturedownspinner);
        temperatureAdapter=ArrayAdapter.createFromResource(this, R.array.TemperatureUnit, android.R.layout.simple_spinner_item);
        temperatureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        temperatureUpSpinner.setAdapter(temperatureAdapter);
        temperatureDownSpinner.setAdapter(temperatureAdapter);

        temperatureUpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=temperatureAdapter.getItem(i).toString();
                if(str.equals("开尔文K")){
                    temperatureUpPower=1;
                }else if(str.equals("摄氏度℃")){
                    temperatureUpPower=2;
                }else if(str.equals("华氏度℉")){
                    temperatureUpPower=5;
                }
                temperatureUpText.setText("0");
                temperatureDownText.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        temperatureDownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=temperatureAdapter.getItem(i).toString();
                if(str.equals("开尔文K")){
                    temperatureDownPower=1;
                }else if(str.equals("摄氏度℃")){
                    temperatureDownPower=2;
                }else if(str.equals("华氏度℉")){
                    temperatureDownPower=5;
                }
                temperatureUpText.setText("0");
                temperatureDownText.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        temperatureUpText=(TextView)findViewById(R.id.temperatureuptext);
        temperatureDownText=(TextView)findViewById(R.id.temperaturedowntext);
        temperatureUpText.setTextColor(0xFF64C1C9);
        temperatureNowText=temperatureUpText;  //默认活动窗口是lengthuptext,上方窗口
        temperatureNowText.setText("0");
    }
    public void clickButton(View v){

        int id=v.getId();
        if(temperatureNowText.getText().toString().equals("0")){  //如果第一位是0，消除第一位
            temperatureNowText.setText("");
        }

        if(temperatureNowText.getText().toString().equals(".")){  //如果第一位是0，消除第一位
            temperatureNowText.setText("0.");
        }
        if(id==R.id.temperatureac){
            temperatureUpText.setText("0");
            temperatureDownText.setText("0");
        }
        else if(id==R.id.temperaturereturn){
           /*符串的回退处理*/
            String str=temperatureNowText.getText().toString();
            if(str.length()>1) {
                str = str.substring(0, str.length() - 1);
                temperatureNowText.setText(str);
            }
            else{
                temperatureNowText.setText("0");
            }

        }

        if(temperatureNowText.length()>=10){
            id=0;
        }

        if(id==R.id.temperatureone){
            temperatureNowText.append("1");
        }else if(id==R.id.temperaturetwo){
            temperatureNowText.append("2");
        }else if(id==R.id.temperaturethree){
            temperatureNowText.append("3");
        }else if(id==R.id.temperaturefour){
            temperatureNowText.append("4");
        }else if(id==R.id.temperaturefive){
            temperatureNowText.append("5");
        }else if(id==R.id.temperaturesix){
            temperatureNowText.append("6");
        }else if(id==R.id.temperatureseven){
            temperatureNowText.append("7");
        }else if(id==R.id.temperatureeight){
            temperatureNowText.append("8");
        }else if(id==R.id.temperaturenine){
            temperatureNowText.append("9");
        }else if(id==R.id.temperaturezero){  //zero需要特殊处理
            if(!temperatureNowText.getText().equals("0")){
                temperatureNowText.append("0");
            }
        }
        else if(id==R.id.temperaturepoint){ //point需要特殊处理
            int i=-2;
            i=temperatureNowText.getText().toString().indexOf(".");
            if(i<0){
                temperatureNowText.append(".");
            }
        }
        try{
            temperatureNowNum =Double.parseDouble(temperatureNowText.getText().toString());
        }catch(Exception e){}
        temperatureExchange();                   //对字符处理成数字进行长度转换的运算,对响应窗口进行操作
    }

    public void temperatureExchange(){           //*****
      double temperaturerate = temperatureUpPower / temperatureDownPower;
       if(temperaturerate==1) {
           double lastNum;
            TextView temperatureLastText;                //设置区别于活动窗口的响应窗口文本域
            if (temperatureNowText == temperatureUpText) {
                temperatureLastText = temperatureDownText;
                lastNum=temperatureNowNum;
            } else {
                temperatureLastText = temperatureUpText;
                lastNum=temperatureNowNum;
            }
            temperatureLastText.setText(String.valueOf(lastNum));
            String lastText = temperatureLastText.getText().toString();
            if (lastText.substring(lastText.length() - 2).equals(".0")) {
                temperatureLastText.setText(lastText.substring(0, lastText.length() - 2));
            } else {
                temperatureLastText.setText(lastText);
            }
        }

        if(temperaturerate==0.5) {
            double lastNum;
            TextView temperatureLastText;                //设置区别于活动窗口的响应窗口文本域
            if (temperatureNowText == temperatureUpText) {
                temperatureLastText = temperatureDownText;
                lastNum=temperatureNowNum-273.15;
            } else {
                temperatureLastText = temperatureUpText;
                lastNum=temperatureNowNum+273.15;
            }
            temperatureLastText.setText(String.valueOf(lastNum));
            String lastText = temperatureLastText.getText().toString();
            if (lastText.substring(lastText.length() - 2).equals(".0")) {
                temperatureLastText.setText(lastText.substring(0, lastText.length() - 2));
            } else {
                temperatureLastText.setText(lastText);
            }

        }

        if(temperaturerate==0.2) {
            double lastNum;
            TextView temperatureLastText;                //设置区别于活动窗口的响应窗口文本域
            if (temperatureNowText == temperatureUpText) {
                temperatureLastText = temperatureDownText;
                lastNum=(temperatureNowNum-273.15)*1.8+32;
            } else {
                temperatureLastText = temperatureUpText;
                lastNum=(temperatureNowNum-32)/1.8+273.15;
            }
            temperatureLastText.setText(String.valueOf(lastNum));
            String lastText = temperatureLastText.getText().toString();
            if (lastText.substring(lastText.length() - 2).equals(".0")) {
                temperatureLastText.setText(lastText.substring(0, lastText.length() - 2));
            } else {
                temperatureLastText.setText(lastText);
            }
        }

        if(temperaturerate==2) {
            double lastNum;
            TextView temperatureLastText;                //设置区别于活动窗口的响应窗口文本域
            if (temperatureNowText == temperatureUpText) {
                temperatureLastText = temperatureDownText;
                lastNum=temperatureNowNum+273.15;
            } else {
                temperatureLastText = temperatureUpText;
                lastNum=temperatureNowNum-273.15;
            }
            temperatureLastText.setText(String.valueOf(lastNum));
            String lastText = temperatureLastText.getText().toString();
            if (lastText.substring(lastText.length() - 2).equals(".0")) {
                temperatureLastText.setText(lastText.substring(0, lastText.length() - 2));
            } else {
                temperatureLastText.setText(lastText);
            }
        }

        if(temperaturerate==0.4) {
            double lastNum;
            TextView temperatureLastText;                //设置区别于活动窗口的响应窗口文本域
            if (temperatureNowText == temperatureUpText) {
                temperatureLastText = temperatureDownText;
                lastNum=temperatureNowNum*1.8+32;
            } else {
                temperatureLastText = temperatureUpText;
                lastNum=(temperatureNowNum-32)/1.8;
            }
            temperatureLastText.setText(String.valueOf(lastNum));
            String lastText = temperatureLastText.getText().toString();
            if (lastText.substring(lastText.length() - 2).equals(".0")) {
                temperatureLastText.setText(lastText.substring(0, lastText.length() - 2));
            } else {
                temperatureLastText.setText(lastText);
            }
        }

        if(temperaturerate==5) {
            double lastNum;
            TextView temperatureLastText;                //设置区别于活动窗口的响应窗口文本域
            if (temperatureNowText == temperatureUpText) {
                temperatureLastText = temperatureDownText;
                lastNum=(temperatureNowNum-32)/1.8+273.15;
            } else {
                temperatureLastText = temperatureUpText;
                lastNum=(temperatureNowNum-273.15)*1.8+32;
            }
            temperatureLastText.setText(String.valueOf(lastNum));
            String lastText = temperatureLastText.getText().toString();
            if (lastText.substring(lastText.length() - 2).equals(".0")) {
                temperatureLastText.setText(lastText.substring(0, lastText.length() - 2));
            } else {
                temperatureLastText.setText(lastText);
            }
        }

        if(temperaturerate==2.5) {
            double lastNum;
            TextView temperatureLastText;                //设置区别于活动窗口的响应窗口文本域
            if (temperatureNowText == temperatureUpText) {
                temperatureLastText = temperatureDownText;
                lastNum=(temperatureNowNum-32)/1.8;
            } else {
                temperatureLastText = temperatureUpText;
                lastNum=temperatureNowNum*1.8+32;
            }
            temperatureLastText.setText(String.valueOf(lastNum));
            String lastText = temperatureLastText.getText().toString();
            if (lastText.substring(lastText.length() - 2).equals(".0")) {
                temperatureLastText.setText(lastText.substring(0, lastText.length() - 2));
            } else {
                temperatureLastText.setText(lastText);
            }
        }
    }

    public void clickText(View v){
        int id=v.getId();
        if(id==R.id.temperatureuptext){
            temperatureNowText=temperatureUpText;
            temperatureUpText.setTextColor(0xFF64C1C9);
            temperatureDownText.setTextColor(Color.BLACK);
        }else if(id==R.id.temperaturedowntext){
            temperatureNowText=temperatureDownText;
            temperatureUpText.setTextColor(Color.BLACK);
            temperatureDownText.setTextColor(0xFF64C1C9);
        }
        try{
            temperatureNowNum =Double.parseDouble(temperatureNowText.getText().toString());
        }catch(Exception e){}

    }
}
