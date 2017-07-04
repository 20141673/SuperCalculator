package com.cqu.supercalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigInteger;

public class HexExchageActivity extends AppCompatActivity {
    private TextView hextag,dectag,octtag,bintag;
    private TextView hextextview,dectextview,octtextview,bintextview;
    private TextView nowtextview;
    private Button[] buttons=new Button[16];    //存储数字输入按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex_exchage);

        initTextView();
        initButton();
    }

    private void initTextView(){
        hextag=(TextView)findViewById(R.id.hextag);
        dectag=(TextView)findViewById(R.id.dectag);
        octtag=(TextView)findViewById(R.id.octtag);
        bintag=(TextView)findViewById(R.id.bintag);

        hextextview=(TextView)findViewById(R.id.hextextview);
        dectextview=(TextView)findViewById(R.id.dectextview);
        octtextview=(TextView)findViewById(R.id.octtextview);
        bintextview=(TextView)findViewById(R.id.bintextview);

        nowtextview=hextextview;                              //设置默认活动textview
        hextag.setTextColor(0xFF64C1C9);
        hextextview.setTextColor(0xFF64C1C9);
    }

    private void initButton(){
        buttons[0]=(Button)findViewById(R.id.hexexzero);
        buttons[1]=(Button)findViewById(R.id.hexexone);
        buttons[2]=(Button)findViewById(R.id.hexextwo);
        buttons[3]=(Button)findViewById(R.id.hexexthree);
        buttons[4]=(Button)findViewById(R.id.hexexfour);
        buttons[5]=(Button)findViewById(R.id.hexexfive);
        buttons[6]=(Button)findViewById(R.id.hexexsix);
        buttons[7]=(Button)findViewById(R.id.hexexseven);
        buttons[8]=(Button)findViewById(R.id.hexexeight);
        buttons[9]=(Button)findViewById(R.id.hexexnine);
        buttons[10]=(Button)findViewById(R.id.hexexa);
        buttons[11]=(Button)findViewById(R.id.hexexb);
        buttons[12]=(Button)findViewById(R.id.hexexc);
        buttons[13]=(Button)findViewById(R.id.hexexd);
        buttons[14]=(Button)findViewById(R.id.hexexe);
        buttons[15]=(Button)findViewById(R.id.hexexf);
    }

    public void clicktextview(View v){
        int id=v.getId();
        hextag.setTextColor(Color.BLACK);
        dectag.setTextColor(Color.BLACK);
        octtag.setTextColor(Color.BLACK);
        bintag.setTextColor(Color.BLACK);
        hextextview.setTextColor(Color.BLACK);
        dectextview.setTextColor(Color.BLACK);
        octtextview.setTextColor(Color.BLACK);
        bintextview.setTextColor(Color.BLACK);
        hextextview.setText("0");
        dectextview.setText("0");
        octtextview.setText("0");
        bintextview.setText("0");
        if(id==R.id.hextag||id==R.id.hextextview){
            hextag.setTextColor(0xFF64C1C9);
            hextextview.setTextColor(0xFF64C1C9);
            nowtextview=hextextview;
            setbuttonenable(16);
        }else if(id==R.id.dectag||id==R.id.dectextview){
            dectag.setTextColor(0xFF64C1C9);
            dectextview.setTextColor(0xFF64C1C9);
            nowtextview=dectextview;
            setbuttonenable(10);
        }else if(id==R.id.octtag||id==R.id.octtextview){
            octtag.setTextColor(0xFF64C1C9);
            octtextview.setTextColor(0xFF64C1C9);
            nowtextview=octtextview;
            setbuttonenable(8);
        }else if(id==R.id.bintag||id==R.id.bintextview){
            bintag.setTextColor(0xFF64C1C9);
            bintextview.setTextColor(0xFF64C1C9);
            nowtextview=bintextview;
            setbuttonenable(2);
        }
    }

    private void setbuttonenable(int way) {
        for (int i = 0; i < way; i++) {
            buttons[i].setEnabled(true);
            buttons[i].setTextColor(Color.BLACK);
        }
        for (int i = way; i < 16; i++) {
            if(i>=16) break;
            buttons[i].setEnabled(false);
            buttons[i].setTextColor(Color.GRAY);
        }
    }

    public void clickButton(View v){
        int id=v.getId();
        if(nowtextview.getText().toString().equals("0")){  //如果第一位是0，消除第一位
            nowtextview.setText("");
        }
        if(id==R.id.hexexac){                 //AC键和回退键的特殊判断
            nowtextview.setText("0");
            nowtextview.setText("0");
        }else if(id==R.id.hexexreturn){
           /*字符串的回退处理*/
            if(nowtextview.getText().toString().equals("")){
                nowtextview.setText("0");
            }else{
                String str=nowtextview.getText().toString();
                str=str.substring(0,str.length()-1);
                nowtextview.setText(str);
                if(nowtextview.getText().toString().equals("")){   //防止回退键将字符串设为空值
                    nowtextview.setText("0");
                }
            }
        }
        if(nowtextview.getText().toString().length()>=20){
            id=0;     //不够安全，也许id的值会取到0吗？
        }else if(id==R.id.hexexzero){
            if(!nowtextview.getText().equals("0")){
                nowtextview.append("0");
            }
        }else if(id==R.id.hexexone){
            nowtextview.append("1");
        }else if(id==R.id.hexextwo){
            nowtextview.append("2");
        }else if(id==R.id.hexexthree){
            nowtextview.append("3");
        }else if(id==R.id.hexexfour){
            nowtextview.append("4");
        }else if(id==R.id.hexexfive){
            nowtextview.append("5");
        }else if(id==R.id.hexexsix){
            nowtextview.append("6");
        }else if(id==R.id.hexexseven){
            nowtextview.append("7");
        }else if(id==R.id.hexexeight){
            nowtextview.append("8");
        }else if(id==R.id.hexexnine){
            nowtextview.append("9");
        }else if(id==R.id.hexexa){
            nowtextview.append("A");
        }else if(id==R.id.hexexb){
            nowtextview.append("B");
        }else if(id==R.id.hexexc){
            nowtextview.append("C");
        }else if(id==R.id.hexexd){
            nowtextview.append("D");
        }else if(id==R.id.hexexe){
            nowtextview.append("E");
        }else if(id==R.id.hexexf){
            nowtextview.append("F");
        }
        runhexexchange();
    }

    private void runhexexchange(){
        if(nowtextview==hextextview){
            BigInteger hex = new BigInteger(nowtextview.getText().toString(), 16);
            dectextview.setText(hex.toString());
            if(octtextview.getText().toString().length()>18||octtextview.getText().toString().equals("超出计算范围")){
                octtextview.setText("超出计算范围");
            }else{
                octtextview.setText(String.valueOf(Long.toOctalString(Long.parseLong(hex.toString()))));
            }
            if(bintextview.getText().toString().length()>30||bintextview.getText().toString().equals("超出计算范围")){
                bintextview.setText("超出计算范围");
            }else{
                bintextview.setText(String.valueOf(Long.toBinaryString(Long.parseLong(hex.toString()))));
            }
        }else if(nowtextview==dectextview){
            BigInteger dec=new BigInteger(nowtextview.getText().toString(),10);
            hextextview.setText(String.valueOf(Long.toHexString(Long.parseLong(dec.toString()))));
            if(octtextview.getText().toString().length()>18||octtextview.getText().toString().equals("超出计算范围")){
                octtextview.setText("超出计算范围");
            }else{
                octtextview.setText(String.valueOf(Long.toOctalString(Long.parseLong(dec.toString()))));
            }
            if(bintextview.getText().toString().length()>30||bintextview.getText().toString().equals("超出计算范围")){
                bintextview.setText("超出计算范围");
            }else{
                bintextview.setText(String.valueOf(Long.toBinaryString(Long.parseLong(dec.toString()))));
            }
        }else if(nowtextview==octtextview){
            BigInteger oct=new BigInteger(nowtextview.getText().toString(),8);
            hextextview.setText(String.valueOf(Long.toHexString(Long.parseLong(oct.toString()))));
            dectextview.setText(oct.toString());
            if(bintextview.getText().toString().length()>30||bintextview.getText().toString().equals("超出计算范围")){
                bintextview.setText("超出计算范围");
            }else{
                bintextview.setText(String.valueOf(Long.toBinaryString(Long.parseLong(oct.toString()))));
            }
        }else if(nowtextview==bintextview){
            BigInteger bin = new BigInteger(nowtextview.getText().toString(), 2);
            hextextview.setText(String.valueOf(Long.toHexString(Long.parseLong(bin.toString()))));
            dectextview.setText(bin.toString());
            octtextview.setText(String.valueOf(Long.toOctalString(Long.parseLong(bin.toString()))));
        }
    }
}
