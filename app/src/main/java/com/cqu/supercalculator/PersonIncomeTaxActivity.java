package com.cqu.supercalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PersonIncomeTaxActivity extends AppCompatActivity {
    //创建控件对象
    TextView tvYanglao;
    TextView tvYiLiao;
    TextView tvShiYe;
    TextView tvShengYu;
    TextView tvZhuFang;
    TextView tvGongShang;
    TextView tvAfterTax;
    TextView tvTax;
    Button btnCalculatePersontax;
    EditText editIncome;
    Spinner spinnerCity;
    //保存五险一金比例的字符串
    String[] strWuXianYiJin;
    //保存五险一金的大小
    Double[] dWuXianYiJin=new Double[6];
    //保存五险一金的百分数格式
    String[] sTemp=new String[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_income_tax);
        //初始化控件
        Init();
        //Spinner控件的响应程序
       spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
               //strCity保存城市名称
               String[] strCity=getResources().getStringArray(R.array.city);
               //按所选的城市来获取五险一金的比例并显示
               SetWuXianYiJin(strCity[pos]);
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
           }
       });
        //计算按钮响应程序
        btnCalculatePersontax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dPersonIncomeBeforeTax为税前月薪
                Double dPersonIncomeBeforeTax;
                //从输入框获取税前月薪
                dPersonIncomeBeforeTax=Double.parseDouble(editIncome.getText().toString());
                //调用CalculateInCome函数计算税后工资及所需缴纳的税款，并显示出来
                CalculateInCome(dPersonIncomeBeforeTax);
            }
        });
    }
//初始化控件
    public void Init(){
        spinnerCity=(Spinner)findViewById(R.id.spiCity);
        tvShengYu=(TextView)findViewById(R.id.tvShengYu);
        tvShiYe=(TextView)findViewById(R.id.tvShiYe);
        tvYanglao=(TextView)findViewById(R.id.tvYangLao);
        tvYiLiao=(TextView)findViewById(R.id.tvYiLiao);
        tvZhuFang=(TextView)findViewById(R.id.tvZhuFang);
        tvGongShang=(TextView)findViewById(R.id.tvGongShang);
        tvAfterTax=(TextView)findViewById(R.id.tvIncomeAfterTax) ;
        tvTax=(TextView)findViewById(R.id.tvTax);
        editIncome=(EditText)findViewById(R.id.editIncome);
        btnCalculatePersontax=(Button)findViewById(R.id.btnCalculatePersonTax);
    }
//根据所选城市来获取五险一金的比例，并显示
    public void SetWuXianYiJin(String city){
        switch(city){
            case "北京":strWuXianYiJin=getResources().getStringArray(R.array.beijing);break;
            case "上海":strWuXianYiJin=getResources().getStringArray(R.array.shanghai);break;
            case "广州":strWuXianYiJin=getResources().getStringArray(R.array.guangzhou);break;
            case "重庆":strWuXianYiJin=getResources().getStringArray(R.array.chongqing);break;
            case "深圳":strWuXianYiJin=getResources().getStringArray(R.array.shenzhen);break;
            case "武汉":strWuXianYiJin=getResources().getStringArray(R.array.wuhan);break;
            case "天津":strWuXianYiJin=getResources().getStringArray(R.array.tianjin);break;
            case "西安":strWuXianYiJin=getResources().getStringArray(R.array.xian);break;
        }
        //设置Double的格式
        DecimalFormat df=new DecimalFormat("#.00");
        //将保存的五险一金的比例由小数改为百分数并保存在sTemp上
        for(int i=0;i<6;i++){
            Double dTemp;
           dTemp=Double.parseDouble(strWuXianYiJin[i])*100;
            sTemp[i]=df.format(dTemp)+"%";
        }
        //在控件上显示
        tvYanglao.setText("养老保险金："+sTemp[0]);
        tvYiLiao.setText("医疗保险金："+sTemp[1]);
        tvShiYe.setText("失业保险金："+sTemp[2]);
        tvGongShang.setText("工伤保险金："+sTemp[3]);
        tvShengYu.setText("生育保险金："+sTemp[4]);
        tvZhuFang.setText("住房保险金："+sTemp[5]);
    }
    //计算税后收入及五险一金的实际大小及所需缴纳的税款，并显示
    public void CalculateInCome(Double dBeforeTax){
        //设置Double的输出格式
        DecimalFormat df=new DecimalFormat("#.00");
        //dAfterTax为税后月薪
        Double dAfterTax=dBeforeTax;
        //dTax为所需缴纳的税款
        Double dTax=dBeforeTax;

        for(int i=0;i<6;i++) {
            //计算五险一金的实际金额
            Double dTemp=Double.parseDouble(strWuXianYiJin[i]);
            dWuXianYiJin[i] = dBeforeTax * dTemp;
            //减去五险一金的金额
            dTax-=dWuXianYiJin[i];
            dAfterTax-=dWuXianYiJin[i];
        }
//显示五险一金的实际金额
        tvYanglao.setText("养老保险金："+sTemp[0]+"           "+df.format(dWuXianYiJin[0])+"元");
        tvYiLiao.setText("医疗保险金："+sTemp[1]+"           "+df.format(dWuXianYiJin[1])+"元");
        tvShiYe.setText("失业保险金："+sTemp[2]+"           "+df.format(dWuXianYiJin[2])+"元");
        tvGongShang.setText("工伤保险金："+sTemp[3]+"           "+df.format(dWuXianYiJin[3])+"元");
        tvShengYu.setText("生育保险金："+sTemp[4]+"           "+df.format(dWuXianYiJin[4])+"元");
        tvZhuFang.setText("住房保险金："+sTemp[5]+"         "+df.format(dWuXianYiJin[5])+"元");
//根据减去起征点后的金额计算所需缴纳的税额
        dTax=dTax-3500;
        if(dTax<=0){
            Integer i=0;
            dTax=i.doubleValue();
        }else if(dTax<=1500){
            dTax*=0.03;
        }else if(dTax<=4500){
            dTax*=0.1;
        }else if(dTax<=9000){
            dTax*=0.2;
        }else if(dTax<=35000){
            dTax*=0.25;
        }else if(dTax<=55000){
            dTax*=0.3;
        }else if(dTax<=80000){
            dTax*=0.35;
        }else if(dTax>80000){
            dTax*=0.45;
        }
        //减去所需缴纳的税款，得到税后月薪
        dAfterTax -= dTax;
        //显示税后月薪及所需缴纳的税款
        tvAfterTax.setText(dAfterTax.toString());
        tvTax.setText(dTax.toString());
    }



}

