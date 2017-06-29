package com.cqu.supercalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class AreaExchangeActivity extends AppCompatActivity {
    private Spinner areaUpSpinner;
    private Spinner areaDownSpinner;
    private ArrayAdapter areaAdapter;
    private TextView areaUpText;
    private TextView areaDownText;
    private TextView areaNowText;  //现在活动的TextView
    private double areaNowNum;     //现在活动的数字
    private double areaUpPower,areaDownPower;   //设置权重
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_exchange);
        initView();
    }
    private void initView(){
        /*需要初始化spinner和adapter*/
        areaUpSpinner=(Spinner)findViewById(R.id.areaupspinner);
        areaDownSpinner=(Spinner)findViewById(R.id.areadownspinner);
        areaAdapter=ArrayAdapter.createFromResource(this, R.array.AreaUnit, android.R.layout.simple_spinner_item);
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaUpSpinner.setAdapter(areaAdapter);
        areaDownSpinner.setAdapter(areaAdapter);
        areaUpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=areaAdapter.getItem(i).toString();
                if(str.equals("平方米m²")){
                    areaUpPower=100;
                }else if(str.equals("平方英尺sf")){
                    areaUpPower=9.2903;
                }else if(str.equals("平方分米dm²")){
                    areaUpPower=1;
                }else if(str.equals("平方厘米cm²")){
                    areaUpPower=0.01;
                }else if(str.equals("平方毫米mm²")){
                    areaUpPower=0.0001;
                }else if(str.equals("平方码sy")){
                    areaUpPower=83.6127;
                } else if(str.equals("平方英寸si<")){
                    areaUpPower=0.0645;
                } else if(str.equals("公顷ha")){
                    areaUpPower=1000000;
                }
                areaUpText.setText("0");
                areaDownText.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        areaDownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str = areaAdapter.getItem(i).toString();
                if(str.equals("平方米m²")){
                    areaDownPower=100;
                }else if(str.equals("平方英尺sf")){
                    areaDownPower=9.2903;
                }else if(str.equals("平方分米dm²")){
                    areaDownPower=1;
                }else if(str.equals("平方厘米cm²")){
                    areaDownPower=0.01;
                }else if(str.equals("平方毫米mm²")){
                    areaDownPower=0.0001;
                }else if(str.equals("平方码sy")){
                    areaDownPower=83.6127;
                } else if(str.equals("平方英寸si<")){
                    areaDownPower=0.0645;
                } else if(str.equals("公顷ha")){
                    areaDownPower=1000000;
                }
                areaUpText.setText("0");
                areaDownText.setText("0");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        areaUpText=(TextView)findViewById(R.id.areauptext);
        areaDownText=(TextView)findViewById(R.id.areadowntext);
        areaUpText.setTextColor(0xFF64C1C9);
        areaNowText=areaUpText;  //默认活动窗口是volumeuptext,上方窗口
        areaNowText.setText("0");
    }
    public void clickButton(View v){

        int id=v.getId();
        if(areaNowText.getText().toString().equals("0")){  //如果第一位是0，消除第一位
            areaNowText.setText("");
        }
        if(areaNowText.getText().toString().equals(".")){  //如果第一位是0，消除第一位
            areaNowText.setText("0.");
        }
        if(id==R.id.areaac){                 //AC键和回退键的特殊判断
            areaUpText.setText("0");
            areaDownText.setText("0");
        }else if(id==R.id.areareturn){
           /*字符串的回退处理*/
            if(areaNowText.getText().toString().equals("")){
                areaNowText.setText("0");
            }else{
                String str=areaNowText.getText().toString();
                str=str.substring(0,str.length()-1);
                areaNowText.setText(str);
                if(areaNowText.getText().toString().equals("")){   //防止回退键将字符串设为空值
                    areaNowText.setText("0");
                }
            }
        }
        if(areaNowText.length()>=10){
            id=0;
        }

        if(id==R.id.areaone){
            areaNowText.append("1");
        }else if(id==R.id.areatwo){
            areaNowText.append("2");
        }else if(id==R.id.areathree){
            areaNowText.append("3");
        }else if(id==R.id.areafour){
            areaNowText.append("4");
        }else if(id==R.id.areafive){
            areaNowText.append("5");
        }else if(id==R.id.areasix){
            areaNowText.append("6");
        }else if(id==R.id.areaseven){
            areaNowText.append("7");
        }else if(id==R.id.areaeight){
            areaNowText.append("8");
        }else if(id==R.id.areanine){
            areaNowText.append("9");
        }else if(id==R.id.areazero){  //zero需要特殊处理
            if(!areaNowText.getText().equals("0")){
                areaNowText.append("0");
            }
        }
        else if(id==R.id.areapoint){ //point需要特殊处理
            int i=-2;
            i=areaNowText.getText().toString().indexOf(".");
            if(i<0){
                areaNowText.append(".");
            }
        }
        try{
            areaNowNum =Double.parseDouble(areaNowText.getText().toString());
        }catch(Exception e){}
        areaExchange();                   //对字符处理成数字进行长度转换的运算,对响应窗口进行操作
    }

    public void areaExchange(){           //*****
        double arearate;
        double lastNum;
        TextView areaLastText;                //设置区别于活动窗口的响应窗口文本域
        if(areaNowText==areaUpText){
            areaLastText=areaDownText;
            lastNum=areaNowNum*areaUpPower/areaDownPower;
        }else{
            areaLastText=areaUpText;
            lastNum=areaNowNum*areaDownPower/areaUpPower;
        }
        areaLastText.setText(String.valueOf(lastNum));
        String lastText=areaLastText.getText().toString();
        if(lastText.substring(lastText.length()-2).equals(".0")){
            areaLastText.setText(lastText.substring(0,lastText.length()-2));
        }else{
            areaLastText.setText(lastText);
        }
    }

    public void clickText(View v){
        int id=v.getId();
        if(id==R.id.areauptext){
            areaNowText=areaUpText;
            areaUpText.setTextColor(0xFF64C1C9);
            areaDownText.setTextColor(Color.BLACK);
        }else if(id==R.id.areadowntext){
            areaNowText=areaDownText;
            areaUpText.setTextColor(Color.BLACK);
            areaDownText.setTextColor(0xFF64C1C9);
        }
        try{
            areaNowNum =Double.parseDouble(areaNowText.getText().toString());
        }catch(Exception e){}

    }
}
