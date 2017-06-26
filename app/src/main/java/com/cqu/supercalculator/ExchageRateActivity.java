package com.cqu.supercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchageRateActivity extends AppCompatActivity {
    private Spinner currencyupspinner;
    private Spinner currencydownspinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangerate);
        currencyupspinner = (Spinner) findViewById(R.id.currencyupspinner);
        currencydownspinner = (Spinner) findViewById(R.id.currencydownspinner);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getListData(), R.layout.spinneritem, new String[]{"CurrencyPic", "CurrencyText"}, new int[]{R.id.ivCurrency, R.id.tvCurrency});
        currencyupspinner.setAdapter(simpleAdapter);
        currencydownspinner.setAdapter(simpleAdapter);
    }
    public List<Map<String,Object>> getListData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("CurrencyPic",R.drawable.chineseflag);
        map.put("CurrencyText","人民币");
        list.add(map);
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("CurrencyPic",R.drawable.macau);
        map1.put("CurrencyText","澳元");
        list.add(map1);
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("CurrencyPic",R.drawable.hongkong);
        map2.put("CurrencyText","港币");
        list.add(map2);
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("CurrencyPic",R.drawable.america);
        map3.put("CurrencyText","美元");
        list.add(map3);
        return list;
    }

    public void clickText(View v){

    }
}
