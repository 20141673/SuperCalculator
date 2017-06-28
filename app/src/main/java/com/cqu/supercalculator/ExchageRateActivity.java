package com.cqu.supercalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("NewApi")
public class ExchageRateActivity extends AppCompatActivity {
    private Spinner currencyupspinner;
    private Spinner currencydownspinner;
    private TextView currencyuptext,currencydowntext;
    private String currencyscur,currencytcur;   //需要转化的货币
    private String APPKEY="26412";              //配置APPKEY和sign
    private String APPsign="8f76a7939052549a1f3f1040591aadf4";
    private double currencyrate;                //货币汇率
    private double currencyupnum,currencydownnum;   //货币数字
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchangerate);

        /*防止NetworkOnMainThreadException的出现*/
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        /*初始化界面组件*/
        currencyuptext=(TextView)findViewById(R.id.currencyuptext);
        currencydowntext=(TextView)findViewById(R.id.currencydowntext);
        currencyupspinner = (Spinner) findViewById(R.id.currencyupspinner);
        currencydownspinner = (Spinner) findViewById(R.id.currencydownspinner);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getListData(), R.layout.spinneritem,
                new String[]{"CurrencyPic", "CurrencyText"},
                new int[]{R.id.ivCurrency, R.id.tvCurrency});
        currencyupspinner.setAdapter(simpleAdapter);
        currencydownspinner.setAdapter(simpleAdapter);
        currencyupspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    currencyscur="CNY";
                }else if(position==1) {
                    currencyscur="EUR";
                }else if(position==2) {
                    currencyscur="HKD";
                }else if(position==3){
                    currencyscur="USD";
                }else if(position==4){
                    currencyscur="JPY";
                }else if(position==5){
                    currencyscur="KRW";
                }else if(position==6) {
                    currencyscur = "GBP";
                }
                currencyuptext.setText("0");
                currencydowntext.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        currencydownspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    currencytcur="CNY";
                }else if(position==1) {
                    currencytcur="EUR";
                }else if(position==2) {
                    currencytcur="HKD";
                }else if(position==3){
                    currencytcur="USD";
                }else if(position==4){
                    currencytcur="JPY";
                }else if(position==5){
                    currencytcur="KRW";
                }else if(position==6){
                    currencytcur="GBP";
                }
                currencyuptext.setText("0");
                currencydowntext.setText("0");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public List<Map<String,Object>> getListData(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("CurrencyPic",R.drawable.china);
        map.put("CurrencyText","人民币");
        list.add(map);
        HashMap<String, Object> map1 = new HashMap<String, Object>();
        map1.put("CurrencyPic",R.drawable.european);
        map1.put("CurrencyText","欧元");
        list.add(map1);
        HashMap<String, Object> map2 = new HashMap<String, Object>();
        map2.put("CurrencyPic",R.drawable.hongkong);
        map2.put("CurrencyText","港币");
        list.add(map2);
        HashMap<String, Object> map3 = new HashMap<String, Object>();
        map3.put("CurrencyPic",R.drawable.america);
        map3.put("CurrencyText","美元");
        list.add(map3);
        HashMap<String, Object> map5 = new HashMap<String, Object>();
        map5.put("CurrencyPic",R.drawable.japan);
        map5.put("CurrencyText","日元");
        list.add(map5);
        HashMap<String, Object> map6 = new HashMap<String, Object>();
        map6.put("CurrencyPic",R.drawable.korea);
        map6.put("CurrencyText","韩元");
        list.add(map6);
        HashMap<String, Object> map7 = new HashMap<String, Object>();
        map7.put("CurrencyPic",R.drawable.unitedkingdom);
        map7.put("CurrencyText","英镑");
        list.add(map7);
        return list;
    }

    /*需要联网并有时次限制，这里调用了Nowapi的接口*/
    private void getDataFromWeb() throws Exception{
        URL u=new URL("http://api.k780.com/?app=finance.rate&scur="+currencyscur+"&tcur="+currencytcur+"&appkey="+APPKEY+"&sign="+APPsign+"&format=xml");
        InputStream in=u.openStream();   //出错
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try {
            byte buf[]=new byte[1024];
            int read = 0;
            while ((read = in.read(buf)) > 0) {
                out.write(buf, 0, read);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
        byte b[]=out.toByteArray( );
        String strXml=new String(b,"utf-8");
        int ratebegin=strXml.indexOf("<rate>");
        int rateend=strXml.indexOf("</rate>");
        String rate=strXml.substring(ratebegin+6,rateend);
        currencyrate=Double.parseDouble(rate);
    }

    private void currencyExchange(){
        if(currencytcur.equals(currencyscur)){
            currencyrate=1;
        }else{
            try{
                getDataFromWeb();   //从网络获取
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        currencydownnum=currencyupnum*currencyrate;
        currencydowntext.setText(String.valueOf(currencydownnum));
        String downtext=currencydowntext.getText().toString();
        if(downtext.substring(downtext.length()-2).equals(".0")){
            currencydowntext.setText(downtext.substring(0,downtext.length()-2));
        }
    }

    public void clickButton(View v){
        int id=v.getId();
        if(currencyuptext.getText().toString().equals("0")){  //如果第一位是0，消除第一位
            currencyuptext.setText("");
        }
        if(id==R.id.currencyac){                 //AC键和回退键的特殊判断
            currencyuptext.setText("0");
            currencydowntext.setText("0");
        }else if(id==R.id.currencyreturn){
           /*字符串的回退处理*/
            if(currencyuptext.getText().toString().equals("")){
                currencyuptext.setText("0");
            }else{
                String str=currencyuptext.getText().toString();
                str=str.substring(0,str.length()-1);
                currencyuptext.setText(str);
                if(currencyuptext.getText().toString().equals("")){   //防止回退键将字符串设为空值
                    currencyuptext.setText("0");
                }
            }
        }
        if(currencyuptext.length()>=10){
            id=0;
        }else if(id==R.id.currencyone){
            currencyuptext.append("1");
        }else if(id==R.id.currencytwo){
            currencyuptext.append("2");
        }else if(id==R.id.currencythree){
            currencyuptext.append("3");
        }else if(id==R.id.currencyfour){
            currencyuptext.append("4");
        }else if(id==R.id.currencyfive){
            currencyuptext.append("5");
        }else if(id==R.id.currencysix){
            currencyuptext.append("6");
        }else if(id==R.id.currencyseven){
            currencyuptext.append("7");
        }else if(id==R.id.currencyeight){
            currencyuptext.append("8");
        }else if(id==R.id.currencynine){
            currencyuptext.append("9");
        }else if(id==R.id.currencyzero){  //zero需要特殊处理
            if(!currencyuptext.getText().equals("0")){
                currencyuptext.append("0");
            }
        }else if(id==R.id.currencypoint){ //point需要特殊处理
            int i=-2;
            i=currencyuptext.getText().toString().indexOf(".");
            if(i<0){
                currencyuptext.append(".");
            }
        }
        try{
            currencyupnum =Double.parseDouble(currencyuptext.getText().toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        currencyExchange();
    }
}
