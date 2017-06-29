package com.cqu.supercalculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class WeightExchangeActivity extends AppCompatActivity {
    private Spinner weightUpSpinner;
    private Spinner weightDownSpinner;
    private ArrayAdapter weightAdapter;
    private TextView weightUpText;
    private TextView weightDownText;
    private TextView weightNowText;  //现在活动的TextView
    private double weightNowNum;     //现在活动的数字
    private double weightUpPower,weightDownPower;   //设置权重
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_exchange);
        initView();
    }
    private void initView(){
        /*需要初始化spinner和adapter*/
        weightUpSpinner=(Spinner)findViewById(R.id.firstWeightspinner);
        weightDownSpinner=(Spinner)findViewById(R.id.secondWeightspinner);
        weightUpText=(TextView)findViewById(R.id.firstWeighttextView);
        weightDownText=(TextView)findViewById(R.id.secondWeighttextView);
        weightUpText.setTextColor(0xFF64C1C9);
        weightNowText=weightUpText;  //默认活动窗口是weightuptext,上方窗口
        weightNowText.setText("");
        weightAdapter=ArrayAdapter.createFromResource(this, R.array.WeightUnit, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weightUpSpinner.setAdapter(weightAdapter);
        weightDownSpinner.setAdapter(weightAdapter);
        weightUpSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=weightAdapter.getItem(i).toString();
                if(str.equals("微克ug")){
                    weightUpPower=1;
                }else if(str.equals("毫克mg")){
                    weightUpPower=1000;
                }else if(str.equals("克g")){
                    weightUpPower=1000000;
                }else if(str.equals("千克kg")){
                    weightUpPower=1000000000;
                }else if(str.equals("吨t")){
                    weightUpPower=1e12;
                }else if(str.equals("磅lb")){
                    weightUpPower=453592370;
                }else if(str.equals("克拉ct")){
                    weightUpPower=200000;
                }else if(str.equals("打兰dr")){
                    weightUpPower=1771845.2;
                }else if(str.equals("格令gr")){
                    weightUpPower=64798.91;
                }else if(str.equals("担dan")){
                    weightUpPower=5e10;
                }else if(str.equals("钱qian")){
                    weightUpPower=5e6;
                }else if(str.equals("两liang")){
                    weightUpPower=5e7;
                }else if(str.equals("公担q")){
                    weightUpPower=1e11;
                }else if(str.equals("长吨l.t")){
                    weightUpPower=1.01604691e12;
                }else if(str.equals("短吨sh.t")){
                    weightUpPower=9.0718474e11;
                }else if(str.equals("英担cwt")){
                    weightUpPower=5.08023454e10;
                }else if(str.equals("美担cwt")){
                    weightUpPower=4.5359237e10;
                }
                else if(str.equals("英石uk.st")){
                    weightUpPower=6.35029318e9;
                }
                weightUpText.setText("0");
                weightDownText.setText("0");

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        weightDownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String str=weightAdapter.getItem(i).toString();
                if(str.equals("微克ug")){
                    weightDownPower=1;
                }else if(str.equals("毫克mg")){
                    weightDownPower=1e3;
                }else if(str.equals("克g")){
                    weightDownPower=1e6;
                }else if(str.equals("千克kg")){
                    weightDownPower=1e9;
                }else if(str.equals("吨t")){
                    weightDownPower=1e12;
                }else if(str.equals("磅lb")){
                    weightDownPower=453592370;
                }else if(str.equals("克拉ct")){
                    weightDownPower=200000;
                }else if(str.equals("打兰dr")){
                    weightDownPower=1771845.2;
                }else if(str.equals("格令gr")){
                    weightDownPower=64798.91;
                }else if(str.equals("担dan")){
                    weightDownPower=5e10;
                }else if(str.equals("钱qian")){
                    weightDownPower=5e6;
                }else if(str.equals("两liang")){
                    weightDownPower=5e7;
                }else if(str.equals("公担q")){
                    weightDownPower=1e11;
                }else if(str.equals("长吨l.t")){
                    weightDownPower=1.01604691e12;
                }else if(str.equals("短吨sh.t")){
                    weightDownPower=9.0718474e11;
                }else if(str.equals("英担cwt")){
                    weightDownPower=5.08023454e10;
                }else if(str.equals("美担cwt")){
                    weightDownPower=4.5359237e10;
                }
                else if(str.equals("英石uk.st")){
                    weightUpPower=6.35029318e9;
                }
                weightUpText.setText("0");
                weightDownText.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
    public void clickButton(View v){

        int id=v.getId();
        if(weightNowText.getText().toString().equals("0")){  //如果第一位是0，消除第一位
            weightNowText.setText("");
        }
        if(weightNowText.getText().toString().equals(".")){  ///如果第二位是“.”,则添加“0”
            weightNowText.setText("0.");
        }
        String str1 = weightUpText.getText().toString();
        int a = str1.length();
        if(a<=15) {
            if (id == R.id.weightone) {
                weightNowText.append("1");
            } else if (id == R.id.weighttwo) {
                weightNowText.append("2");
            } else if (id == R.id.weightthree) {
                weightNowText.append("3");
            } else if (id == R.id.weightfour) {
                weightNowText.append("4");
            } else if (id == R.id.weightfive) {
                weightNowText.append("5");
            } else if (id == R.id.weightsix) {
                weightNowText.append("6");
            } else if (id == R.id.weightseven) {
                weightNowText.append("7");
            } else if (id == R.id.weighteight) {
                weightNowText.append("8");
            } else if (id == R.id.weightnine) {
                weightNowText.append("9");
            } else if (id == R.id.weightzero) {  //zero需要特殊处理
                if (!weightNowText.getText().equals("0")) {
                    weightNowText.append("0");
                }
            }
        }
        if(id==R.id.weightac){                 //AC键和回退键的特殊判断
            weightUpText.setText("0");
            weightDownText.setText("0");
        }else if(id==R.id.weightreturn){
           /*字符串的回退处理*/
            if(weightNowText.getText().toString().equals("")){
                weightNowText.setText("0");
            }else{
                String str=weightNowText.getText().toString();
                str=str.substring(0,str.length()-1);
                weightNowText.setText(str);
                if(weightNowText.getText().toString().equals("")){   //防止回退键将字符串设为空值
                    weightNowText.setText("0");
                }
            }
        }
        else if(id==R.id.weightpoint){ //point需要特殊处理
            int i=-2;
            i=weightNowText.getText().toString().indexOf(".");
            if(i<0){
                weightNowText.append(".");
            }
        }
        try{
            weightNowNum =Double.parseDouble(weightNowText.getText().toString());
        }catch(Exception e){}
        speedExchange();                   //对字符处理成数字进行长度转换的运算,对响应窗口进行操作
    }

    public void speedExchange(){           //*****
        double speedrate;
        double lastNum;
        TextView weightLastText;                //设置区别于活动窗口的响应窗口文本域
        if(weightNowText==weightUpText){
            weightLastText=weightDownText;
            lastNum=weightNowNum*weightUpPower/weightDownPower;
        }else{
            weightLastText=weightUpText;
            lastNum=weightNowNum*weightDownPower/weightUpPower;
        }
        weightLastText.setText(String.valueOf(lastNum));
        String lastText=weightLastText.getText().toString();
        if(lastText.substring(lastText.length()-2).equals(".0")){
            weightLastText.setText(lastText.substring(0,lastText.length()-2));
        }else{
            weightLastText.setText(lastText);
        }
    }

    public void clickText(View v){
        int id=v.getId();
        if(id==R.id.firstWeighttextView){
            weightNowText=weightUpText;
            weightUpText.setTextColor(0xFF64C1C9);
            weightDownText.setTextColor(Color.BLACK);
        }else if(id==R.id.secondWeighttextView){
            weightNowText=weightDownText;
            weightUpText.setTextColor(Color.BLACK);
            weightDownText.setTextColor(0xFF64C1C9);
        }
        try{
            weightNowNum =Double.parseDouble(weightNowText.getText().toString());
        }catch(Exception e){}

    }
}
