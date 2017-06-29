package com.cqu.supercalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class VolumeExchangeActivity extends AppCompatActivity {
    private Spinner volumeUpSpinner;
    private Spinner volumeDownSpinner;
    private ArrayAdapter volumeAdapter;
    private TextView volumeUpText;
    private TextView volumeDownText;
    private TextView volumeNowText;  //现在活动的TextView
    private double volumeNowNum;     //现在活动的数字
    private double volumeUpPower,volumeDownPower;   //设置权重
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_exchange);
        initView();
    }
    private void initView(){
        /*需要初始化spinner和adapter*/
        volumeUpSpinner=(Spinner)findViewById(R.id.volumeupspinner);
        volumeDownSpinner=(Spinner)findViewById(R.id.volumedownspinner);
        volumeAdapter=ArrayAdapter.createFromResource(this, R.array.VolumeUnit, android.R.layout.simple_spinner_item);
        volumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        volumeUpSpinner.setAdapter(volumeAdapter);
        volumeDownSpinner.setAdapter(volumeAdapter);
        volumeUpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=volumeAdapter.getItem(i).toString();
                if(str.equals("立方米m³")){
                    volumeUpPower=1000;
                }else if(str.equals("升L")){
                    volumeUpPower=1;
                }else if(str.equals("加仑gal")){
                    volumeUpPower=3.7854;
                }else if(str.equals("立方英尺cf")){
                    volumeUpPower=28.3168;
                }else if(str.equals("公石hl")){
                    volumeUpPower=100;
                }else if(str.equals("立方分米dm³")){
                    volumeUpPower=1;
                }else if(str.equals("立方厘米cm³")){
                    volumeUpPower=0.001;
                }
                else if(str.equals("毫升ml")){
                    volumeUpPower=0.001;
                }
                else if(str.equals("立方毫米mm³")){
                    volumeUpPower=0.000001;
                }
                volumeUpText.setText("0");
                volumeDownText.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        volumeDownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str = volumeAdapter.getItem(i).toString();
                if (str.equals("立方米m³")) {
                    volumeDownPower = 1000;
                } else if (str.equals("升L")) {
                    volumeDownPower = 1;
                } else if (str.equals("加仑gal")) {
                    volumeDownPower = 3.7854;
                } else if (str.equals("立方英尺cf")) {
                    volumeDownPower = 28.3168;
                } else if (str.equals("公石hl")) {
                    volumeDownPower = 100;
                } else if (str.equals("立方分米dm³")) {
                    volumeDownPower = 1;
                } else if (str.equals("立方厘米cm³")) {
                    volumeDownPower = 0.001;
                } else if (str.equals("毫升ml")) {
                    volumeDownPower = 0.001;
                } else if (str.equals("立方毫米mm³")) {
                    volumeDownPower = 0.000001;
                }
                volumeUpText.setText("0");
                volumeDownText.setText("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        volumeUpText=(TextView)findViewById(R.id.volumeuptext);
        volumeDownText=(TextView)findViewById(R.id.volumedowntext);
        volumeUpText.setTextColor(0xFF64C1C9);
        volumeNowText=volumeUpText;  //默认活动窗口是volumeuptext,上方窗口
        volumeNowText.setText("0");
    }
    public void clickButton(View v){

        int id=v.getId();
        if(volumeNowText.getText().toString().equals("0")){  //如果第一位是0，消除第一位
            volumeNowText.setText("");
        }
        if(volumeNowText.getText().toString().equals(".")){  //如果第一位是0，消除第一位
            volumeNowText.setText("0.");
        }
        if(id==R.id.volumeac){                 //AC键和回退键的特殊判断
            volumeUpText.setText("0");
            volumeDownText.setText("0");
        }else if(id==R.id.volumereturn){
           /*字符串的回退处理*/
            if(volumeNowText.getText().toString().equals("")){
                volumeNowText.setText("0");
            }else{
                String str=volumeNowText.getText().toString();
                str=str.substring(0,str.length()-1);
                volumeNowText.setText(str);
                if(volumeNowText.getText().toString().equals("")){   //防止回退键将字符串设为空值
                    volumeNowText.setText("0");
                }
            }
        }
        if(volumeNowText.length()>=10){
            id=0;
        }

        if(id==R.id.volumeone){
            volumeNowText.append("1");
        }else if(id==R.id.volumetwo){
            volumeNowText.append("2");
        }else if(id==R.id.volumethree){
            volumeNowText.append("3");
        }else if(id==R.id.volumefour){
            volumeNowText.append("4");
        }else if(id==R.id.volumefive){
            volumeNowText.append("5");
        }else if(id==R.id.volumesix){
            volumeNowText.append("6");
        }else if(id==R.id.volumeseven){
            volumeNowText.append("7");
        }else if(id==R.id.volumeeight){
            volumeNowText.append("8");
        }else if(id==R.id.volumenine){
            volumeNowText.append("9");
        }else if(id==R.id.volumezero){  //zero需要特殊处理
            if(!volumeNowText.getText().equals("0")){
                volumeNowText.append("0");
            }
        }
        else if(id==R.id.volumepoint){ //point需要特殊处理
            int i=-2;
            i=volumeNowText.getText().toString().indexOf(".");
            if(i<0){
                volumeNowText.append(".");
            }
        }
        try{
            volumeNowNum =Double.parseDouble(volumeNowText.getText().toString());
        }catch(Exception e){}
        volumeExchange();                   //对字符处理成数字进行长度转换的运算,对响应窗口进行操作
    }

    public void volumeExchange(){           //*****
        double volumerate;
        double lastNum;
        TextView volumeLastText;                //设置区别于活动窗口的响应窗口文本域
        if(volumeNowText==volumeUpText){
            volumeLastText=volumeDownText;
            lastNum=volumeNowNum*volumeUpPower/volumeDownPower;
        }else{
            volumeLastText=volumeUpText;
            lastNum=volumeNowNum*volumeDownPower/volumeUpPower;
        }
        volumeLastText.setText(String.valueOf(lastNum));
        String lastText=volumeLastText.getText().toString();
        if(lastText.substring(lastText.length()-2).equals(".0")){
            volumeLastText.setText(lastText.substring(0,lastText.length()-2));
        }else{
            volumeLastText.setText(lastText);
        }
    }

    public void clickText(View v){
        int id=v.getId();
        if(id==R.id.volumeuptext){
            volumeNowText=volumeUpText;
            volumeUpText.setTextColor(0xFF64C1C9);
            volumeDownText.setTextColor(Color.BLACK);
        }else if(id==R.id.volumedowntext){
            volumeNowText=volumeDownText;
            volumeUpText.setTextColor(Color.BLACK);
            volumeDownText.setTextColor(0xFF64C1C9);
        }
        try{
            volumeNowNum =Double.parseDouble(volumeNowText.getText().toString());
        }catch(Exception e){}

    }
}


