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
    String[] strWuXianYiJin;
    Double[] dWuXianYiJin=new Double[6];
    String[] sTemp=new String[6];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_income_tax);
        Init();
       spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
               String[] strCity=getResources().getStringArray(R.array.city);
               SetWuXianYiJin(strCity[pos]);
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
        btnCalculatePersontax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double dPersonIncomeBeforeTax;
                dPersonIncomeBeforeTax=Double.parseDouble(editIncome.getText().toString());
            CalculateInCome(dPersonIncomeBeforeTax);
            }
        });
    }

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
        DecimalFormat df=new DecimalFormat("#.00");
        for(int i=0;i<6;i++){
            Double dTemp;
           dTemp=Double.parseDouble(strWuXianYiJin[i])*100;
            sTemp[i]=df.format(dTemp)+"%";
        }
        tvYanglao.setText("养老保险金："+sTemp[0]);
        tvYiLiao.setText("医疗保险金："+sTemp[1]);
        tvShiYe.setText("失业保险金："+sTemp[2]);
        tvGongShang.setText("工伤保险金："+sTemp[3]);
        tvShengYu.setText("生育保险金："+sTemp[4]);
        tvZhuFang.setText("住房保险金："+sTemp[5]);
    }
    public void CalculateInCome(Double dBeforeTax){
        DecimalFormat df=new DecimalFormat("#.00");
        Double dAfterTax=dBeforeTax;
        Double dTax=dBeforeTax;
        for(int i=0;i<6;i++) {
            Double dTemp=Double.parseDouble(strWuXianYiJin[i]);
            dWuXianYiJin[i] = dBeforeTax * dTemp;
            dTax-=dWuXianYiJin[i];
            dAfterTax-=dWuXianYiJin[i];
        }

        tvYanglao.setText("养老保险金："+sTemp[0]+"           "+df.format(dWuXianYiJin[0])+"元");
        tvYiLiao.setText("医疗保险金："+sTemp[1]+"           "+df.format(dWuXianYiJin[1])+"元");
        tvShiYe.setText("失业保险金："+sTemp[2]+"           "+df.format(dWuXianYiJin[2])+"元");
        tvGongShang.setText("工伤保险金："+sTemp[3]+"           "+df.format(dWuXianYiJin[3])+"元");
        tvShengYu.setText("生育保险金："+sTemp[4]+"           "+df.format(dWuXianYiJin[4])+"元");
        tvZhuFang.setText("住房保险金："+sTemp[5]+"         "+df.format(dWuXianYiJin[5])+"元");

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
        if(dTax!=0) {
            dAfterTax -= dTax;
        }

        tvAfterTax.setText(dAfterTax.toString());
        tvTax.setText(dTax.toString());
    }



}

