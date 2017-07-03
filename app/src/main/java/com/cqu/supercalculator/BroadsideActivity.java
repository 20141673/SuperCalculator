package com.cqu.supercalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class BroadsideActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //简单计算器计算结果字符串
    private String strSimpleResult="";
    //保存显示结果字符串
    private String strSimpleResultShow="";
    //显示计算过程
    private TextView tvSimpleRecord;
    //显示计算结果
    private TextView tvSimpleResult;

    //存储科学计算器的操作数
    ArrayList scienceNumList=new ArrayList();
    //存储简单计算器的操作数
    ArrayList simpleNumList=new ArrayList();

    private String strScienceResult="";
    //保存显示结果字符串
    private String strScienceResultShow="";
    //显示计算过程
    private TextView tvScienceRecord;
    //显示计算结果
    private TextView tvScienceResult;
    //错误提示
    String strError="输入错误";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadside);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //初始化控件
        initView();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*************Tab标签****************/
        TabHost tabHost=(TabHost)findViewById(R.id.tabhost);
        tabHost.setup();
        //增加标准计算器的Tab
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("标准",null).setContent(R.id.simplelayout));
        //增加科学计算器的Tab
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("科学",null).setContent(R.id.sciencelayout));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.broadside, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_exchagerate) {
            // Handle the camera action
            Intent intent=new Intent(BroadsideActivity.this,ExchageRateActivity.class);
            BroadsideActivity.this.startActivity(intent);
        } else if (id == R.id.nav_houseloan) {
            Intent intent=new Intent(BroadsideActivity.this,HouseLoanActivity.class);
            BroadsideActivity.this.startActivity(intent);
        } else if (id == R.id.nav_personincometax) {
            Intent intent=new Intent(BroadsideActivity.this,PersonIncomeTaxActivity.class);
            BroadsideActivity.this.startActivity(intent);
        } else if (id == R.id.nav_relativescall) {
            Intent intent=new Intent(BroadsideActivity.this,RelativesCallActivity.class);
            BroadsideActivity.this.startActivity(intent);
        }else if(id==R.id.nav_lengthexchange){
            Intent intent=new Intent(BroadsideActivity.this,LengthExchangeActivity.class);
            BroadsideActivity.this.startActivity(intent);

        }else if(id==R.id.nav_weightexchange){
            Intent intent=new Intent(BroadsideActivity.this,WeightExchangeActivity.class);
            BroadsideActivity.this.startActivity(intent);
        }else if(id==R.id.nav_capitalexchange){
            Intent intent=new Intent(BroadsideActivity.this,CapitalExchangeActivity.class);
            BroadsideActivity.this.startActivity(intent);
        }else if(id==R.id.nav_speedexchange){
            Intent intent=new Intent(BroadsideActivity.this,SpeedActivity.class);
            BroadsideActivity.this.startActivity(intent);
        }else if(id==R.id.nav_temperatureexchange){
            Intent intent=new Intent(BroadsideActivity.this,TemperatureActivity.class);
            BroadsideActivity.this.startActivity(intent);
        }else if(id==R.id.nav_Volumeexchange) {
            Intent intent=new Intent(BroadsideActivity.this,VolumeExchangeActivity.class);
            BroadsideActivity.this.startActivity(intent);
        } else if(id==R.id.nav_Areaexchange) {
            Intent intent=new Intent(BroadsideActivity.this,AreaExchangeActivity.class);
            BroadsideActivity.this.startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //初始化控件
    public void initView(){
        tvSimpleRecord=(TextView)findViewById(R.id.simplerecord);
        tvSimpleResult=(TextView)findViewById(R.id.simpleresult);
        tvScienceRecord=(TextView)findViewById(R.id.sciencerecord);
        tvScienceResult=(TextView)findViewById(R.id.scienceresult);
    }
//计算器的按键的响应函数
public void clickButton(View v) {
    int id = v.getId();

    if (v.getTag().toString().indexOf("Simple") != -1) {
        strSimpleResultShow+=((Button)v).getText().toString();
        tvSimpleRecord.setText(strSimpleResultShow);
        strSimpleResult += v.getTag().toString().replace("Simple", "");
        if(id==R.id.simpleminus){
            if(strSimpleResult.length()==1){
                strSimpleResult="0"+strSimpleResult;
            }
        }
        //判断输入的第一个数是不是双目运算符，是则报错
        if(strSimpleResult.length()==1){
           if(IsOP(strSimpleResult.charAt(0))==0){
               strSimpleResultShow="";
                tvSimpleRecord.setText("");
               strSimpleResult="";
               tvSimpleResult.setText("请输入操作数");
            }
        }else if(!strSimpleResult.isEmpty()){//判断是否连续输入双目运算符
            if(IsOP(strSimpleResult.charAt(strSimpleResult.length()-1))==0&&IsOP(strSimpleResult.charAt(strSimpleResult.length()-2))==0){
                int temp=strSimpleResultShow.length()-2;
                strSimpleResultShow=strSimpleResultShow.substring(0,temp)+strSimpleResultShow.substring(temp+1,temp+2);
                temp=strSimpleResult.length()-2;
                strSimpleResult=strSimpleResult.substring(0,temp)+strSimpleResult.substring(temp+1,temp+2);
                tvSimpleRecord.setText(strSimpleResultShow);
            }
        }

        if(id==R.id.simplereturn){
            if(!strSimpleResult.isEmpty()) {
                strSimpleResultShow = strSimpleResultShow.substring(0, strSimpleResultShow.length() - 3);
                strSimpleResult = strSimpleResult.substring(0, strSimpleResult.length() - 1);
                tvSimpleRecord.setText(strSimpleResultShow);
            }else {
                strSimpleResultShow="";
                tvSimpleRecord.setText(strSimpleResultShow);
                tvSimpleResult.setText("请输入操作数");
            }

        }else if(id==R.id.simpleclear){
            strSimpleResultShow="";
            strSimpleResult="";
            tvSimpleResult.setText(strSimpleResult);
            tvSimpleRecord.setText(strSimpleResultShow);
            simpleNumList.clear();
        }else if(id==R.id.simpleequal) {
            String temp=strSimpleResult;//暂存数据
            //strSimpleResult="pi";
            //tvSimpleResult.setText(strSimpleResult);
            strSimpleResult=ResolveBracket(strSimpleResult,simpleNumList);
            tvSimpleResult.setText(strSimpleResult);

            if(!strSimpleResult.equals(strError)) {
                //保存计算结果
                strSimpleResultShow=strSimpleResult;
            }else {
                strSimpleResult=temp;
                strSimpleResultShow=strSimpleResultShow.replace("=","");
            }

        }
    }
    /********************************试验代码02****************************************************/
    else if (v.getTag().toString().indexOf("Science") != -1) {
        strScienceResultShow+=((Button)v).getText().toString();
        tvScienceRecord.setText(strScienceResultShow);
        strScienceResult += v.getTag().toString().replace("Science", "");
        if(id==R.id.scienceminus){
            if(strScienceResult.length()==1){
                strScienceResult="0"+strScienceResult;
            }
        }
        //判断输入的第一个数是不是双目运算符，是则报错
        if(strScienceResult.length()==1){
            if(IsOP(strScienceResult.charAt(0))==0){
                strScienceResultShow="";
                tvScienceRecord.setText("");
                strScienceResult="";
                tvScienceResult.setText("请输入操作数");
            }
        }else if(!strScienceResult.isEmpty()){//判断是否连续输入双目运算符
            if(IsOP(strScienceResult.charAt(strScienceResult.length()-1))==0&&IsOP(strScienceResult.charAt(strScienceResult.length()-2))==0){
                int temp=strScienceResultShow.length()-2;
                strScienceResultShow=strScienceResultShow.substring(0,temp)+strScienceResultShow.substring(temp+1,temp+2);
                temp=strScienceResult.length()-2;
                strScienceResult=strScienceResult.substring(0,temp)+strScienceResult.substring(temp+1,temp+2);
                tvScienceRecord.setText(strScienceResultShow);
            }
        }

        if(id==R.id.sciencepi){

           char cLastTemp=strScienceResult.charAt(strScienceResult.length()-3);
            if(IsOP(cLastTemp)==-1&&cLastTemp!='('){
                strScienceResult=strScienceResult.substring(0,strScienceResult.length()-2)+"*"+strScienceResult.substring(strScienceResult.length()-2,strScienceResult.length());
                //tvScienceResult.setText("请输入操作符");
                //tvScienceResult.setText(strScienceResult);
            }

        }else if(id==R.id.sciencesin||id==R.id.sciencecos||id==R.id.sciencetan){
            strScienceResultShow=strScienceResultShow+"(";
            strScienceResult=strScienceResult+"(";
            tvScienceRecord.setText(strScienceResultShow);
        }else if(id==R.id.sciencepercent){
            if(strScienceResult.length()==1){
                strScienceResult="";
                strScienceResultShow="";
                tvScienceRecord.setText(strScienceResultShow);
            }
        }

        //判断百分号后面是不是数字
        if(strScienceResult.lastIndexOf("%")!=-1) {
            if(strScienceResult.length()>strScienceResult.lastIndexOf("%")+1) {
                char cAfterPercent = strScienceResult.charAt(strScienceResult.lastIndexOf("%") + 1);
                if(cAfterPercent!='+'&&cAfterPercent!='-'&&cAfterPercent!='*'&&cAfterPercent!='/'&&cAfterPercent!=')') {
                    strScienceResult =strScienceResult.substring(0,strScienceResult.lastIndexOf("%")+1);
                    strScienceResultShow=strScienceResultShow.substring(0,strScienceResultShow.lastIndexOf("%")+1);
                    tvScienceRecord.setText(strScienceResultShow);
                }
            }

        }

        if(id==R.id.sciencereturn){
            if(!strScienceResult.isEmpty()) {
                strScienceResultShow = strScienceResultShow.substring(0, strScienceResultShow.length() - 3);
                strScienceResult = strScienceResult.substring(0, strScienceResult.length() - 1);
                tvScienceRecord.setText(strScienceResultShow);
            }else {
                strScienceResultShow="";
                tvScienceRecord.setText(strScienceResultShow);
                tvScienceResult.setText("请输入操作数");
            }
        }else if(id==R.id.scienceclear){
            strScienceResultShow="";
            strScienceResult="";
            tvScienceResult.setText(strScienceResult);
            tvScienceRecord.setText(strScienceResultShow);
            scienceNumList.clear();
        }else if(id==R.id.scienceequal) {
            //strScienceResult="pi";
            //tvScienceResult.setText(strScienceResult);
            String temp=strScienceResult;//暂存数据

            int fbracket=0;
            int bbracket=0;
           for(int i=0;i<strScienceResult.length();i++){
               if(strScienceResult.charAt(i)=='('){
                    fbracket+=1;
               }else if(strScienceResult.charAt(i)=='('){
                   bbracket+=1;
               }
           }

           if(fbracket<bbracket){
               strScienceResult="前括号个数不能小于后括号个数";

           }else if(fbracket>bbracket){
               int inumTemp=fbracket-bbracket;
               for(int i=0;i<inumTemp;i++){
                   strScienceResult+=")";
                   strScienceResultShow+=")";
               }
               tvScienceRecord.setText(strScienceResultShow);
           }

            strScienceResult = ResolveBracket(strScienceResult, scienceNumList);


            //strScienceResult=ResolveJJCC(strScienceResult,scienceNumList);
            tvScienceResult.setText(strScienceResult);
            if(!strScienceResult.equals(strError)) {
                strScienceResultShow = strScienceResult;
            }else {
                strScienceResult=temp;
                strScienceResultShow=strScienceResultShow.replace("=","");
            }
        }
    }
    /********************************试验代码02****************************************************/
}
//递归解决括号问题
    public String ResolveBracket(String str,ArrayList numList){
        String strProcess="";//用于存储过程结果
        //判断是否传入的字符串中是否含有括号，如果有则将最里面的括号中的字符串用ResolveJJCC处理得到运算结果字符串，并去掉最里面的括号
        // 直到字符串中没有字符串则跳出while
        while(str.indexOf("(")!=-1){
            String strTemp=str.substring(str.lastIndexOf("(")+1,str.indexOf(")"));
            strProcess=ResolveJJCC(strTemp,numList);
            if(!strProcess.equals(strError)) {
                str = str.substring(0, str.lastIndexOf("(")) + strProcess + str.substring(str.indexOf(")") + 1, str.length());
            }else {
                str=strError;
            }
        }
        if(!str.equals(strError)) {
            str = ResolveJJCC(str, numList);
        }
        return str;
    }
    //实现运算功能
    public  String ResolveJJCC(String str,ArrayList numList){
        //判断错误运算
        //保存最终结果
        String FinalResult="";
        //保存操作符
        String strop="";
        //保存操作数字符串
        String strnum="";
        //记录操作数的起点
        int lastLocation=-1;
        try{
        //调用IsOP函数，得到操作数字符串
        for(int i=0;i<str.length();i++){
            if(IsOP(str.charAt(i))>=0){
                strop+=str.charAt(i);
            }
        }
        //得到操作数，并将其保存在numList里
        for(int i=0;i<str.length();i++){
            //找到被操作符分割的操作数
            if(IsOP(str.charAt(i))>=0){
                if(IsOP(str.charAt(i))==0) {
                    //当操作符为双目运算符时
                    //将操作符之前的操作数的字符串
                    strnum = str.substring(lastLocation + 1, i);
                    try{
                        numList.add(Double.parseDouble(strnum));//将字符串转化为Double并存入numList
                    }catch (Exception e){
                        //如果遇到异常，则判断操作数中是否存在%，或操作数是否是，PI或E
                        if((strnum.charAt(strnum.length()-1))=='%'){
                            strnum=strnum.substring(0,strnum.length()-1);
                            numList.add(Double.parseDouble(strnum)*0.01);
                        }else if(strnum.equals("pi")){
                            numList.add(Math.PI);
                        }else if(strnum.equals("e")){
                            numList.add(Math.E);
                        }
                    }
                }
                    lastLocation = i;

           }else if(i==str.length()-1){//找到最后一个操作数
               strnum=str.substring(lastLocation+1,i+1);
                try{
                    numList.add(Double.parseDouble(strnum));
                }catch (Exception e) {
                    if ((strnum.charAt(strnum.length() - 1)) == '%') {
                        strnum = strnum.substring(0, strnum.length() - 1);
                        numList.add(Double.parseDouble(strnum) * 0.01);
                    }else if(strnum.equals("pi")){
                        numList.add(Math.PI);
                    }else if(strnum.equals("e")){
                        numList.add(Math.E);
                    }
                }
           }
        }

            /*************************************************************处理运算逻辑************************************************************/
            //处理sin()逻辑
            while ((strop.indexOf("s") != -1)) {
                Double dTemp = Math.sin((Double) numList.get(strop.indexOf("s")));

                if (Math.abs(dTemp) < Math.pow(10, -15)) {
                    dTemp = 0.0;
                }

                numList.set(strop.indexOf("s"), dTemp);
                strop = strop.substring(0, strop.indexOf("s")) + strop.substring(strop.indexOf("s") + 1, strop.length());
            }
            //处理cos()逻辑
            while ((strop.indexOf("c") != -1)) {
                Double dTemp = Math.cos((Double) numList.get(strop.indexOf("c")));

                if (Math.abs(dTemp) < Math.pow(10, -15)) {
                    dTemp = 0.0;
                }

                numList.set(strop.indexOf("c"), dTemp);
                strop = strop.substring(0, strop.indexOf("c")) + strop.substring(strop.indexOf("c") + 1, strop.length());
            }
            //处理tan()逻辑
            while ((strop.indexOf("t") != -1)) {
                Double dTemp = Math.tan((Double) numList.get(strop.indexOf("t")));

                if (Math.abs(dTemp) < Math.pow(10, -15)) {
                    dTemp = 0.0;
                }

                numList.set(strop.indexOf("t"), dTemp);
                strop = strop.substring(0, strop.indexOf("t")) + strop.substring(strop.indexOf("t") + 1, strop.length());
            }
            //处理ln()逻辑
            while ((strop.indexOf("n") != -1)) {
                Double dTemp = 0.0;
                if ((Double) numList.get(strop.indexOf("n")) > 0.0) {
                    dTemp = Math.log((Double) numList.get(strop.indexOf("n")));
                } else {
                    FinalResult = strError;
                }
                numList.set(strop.indexOf("n"), dTemp);
                strop = strop.substring(0, strop.indexOf("n")) + strop.substring(strop.indexOf("n") + 1, strop.length());
            }
            //处理lg()逻辑
            while ((strop.indexOf("g") != -1)) {
                Double dTemp = Math.log((Double) numList.get(strop.indexOf("g"))) / Math.log(10);
                numList.set(strop.indexOf("g"), dTemp);
                strop = strop.substring(0, strop.indexOf("g")) + strop.substring(strop.indexOf("g") + 1, strop.length());
            }
            //处理开平方逻辑
            while ((strop.indexOf("q") != -1)) {
                Double dTemp = Math.sqrt((Double) numList.get(strop.indexOf("q")));

                numList.set(strop.indexOf("q"), dTemp);
                strop = strop.substring(0, strop.indexOf("q")) + strop.substring(strop.indexOf("q") + 1, strop.length());
            }
            //处理乘方逻辑
            while ((strop.indexOf("^") != -1)) {
                Double dTemp = 1.0;
                for (int i = 0; i < (Double) numList.get(strop.indexOf("^") + 1); i++) {
                    dTemp = dTemp * (Double) numList.get(strop.indexOf("^"));
                }
                numList.set(strop.indexOf("^"), dTemp);
                numList.remove(strop.indexOf("^") + 1);
                strop = strop.substring(0, strop.indexOf("^")) + strop.substring(strop.indexOf("^") + 1, strop.length());
            }
            //处理四则运算逻辑
            //先处理乘除逻辑
            while ((strop.indexOf("*") != -1) || (strop.indexOf("/") != -1)) {
                for (int i = 0; i < strop.length(); i++) {
                    switch (strop.charAt(i)) {
                        case '*':
                            numList.set(i, (Double) numList.get(i) * (Double) numList.get(i + 1));
                            strop = strop.substring(0, i) + strop.substring(i + 1, strop.length());
                            numList.remove(i + 1);
                            i = i - 1;
                            break;
                        case '/':
                            if ((Double) numList.get(i + 1) != 0) {
                                numList.set(i, (Double) numList.get(i) / (Double) numList.get(i + 1));
                                strop = strop.substring(0, i) + strop.substring(i + 1, strop.length());
                                numList.remove(i + 1);
                                i = i - 1;
                            } else {
                                strop = "";
                                FinalResult = strError;
                            }
                            break;
                    }
                }
            }
            //处理完乘除逻辑后再处理加减逻辑
            while ((strop.indexOf("+") != -1) || (strop.indexOf("-") != -1)) {
                for (int i = 0; i < strop.length(); i++) {
                    switch (strop.charAt(i)) {
                        case '+':
                            numList.set(i, (Double) numList.get(i) + (Double) numList.get(i + 1));
                            strop = strop.substring(0, i) + strop.substring(i + 1, strop.length());
                            numList.remove(i + 1);
                            i = i - 1;
                            break;
                        case '-':
                            numList.set(i, (Double) numList.get(i) - (Double) numList.get(i + 1));
                            strop = strop.substring(0, i) + strop.substring(i + 1, strop.length());
                            numList.remove(i + 1);
                            i = i - 1;
                            break;
                    }
                }
            }
        }catch (Exception e){
            FinalResult=strError;
        }
        //得到最终结果的字符串表示
        if(!FinalResult.equals(strError)) {
            FinalResult = numList.get(0).toString();
        }
        //Test= stringTokenizer.nextToken();
        //Test=((Integer)stringTokenizer.countTokens()).toString();
        //Test=strnum;
        //Test=strop;
        //清除numList中保存的操作数
        numList.clear();
        return FinalResult;
    }
    //判断是否是操作符，并判断操作符是单目运算符，还是双目运算符
    public int IsOP(char c){
       int judge=-1;
        switch (c){
            case '+':judge=0;break;
            case '-':judge=0;break;
            case '*':judge=0;break;
            case '/':judge=0;break;
            case '^':judge=0;break;
            case 's':judge=1;break;
            case 'c':judge=1;break;
            case 't':judge=1;break;
            case 'n':judge=1;break;
            case 'g':judge=1;break;
            case 'q':judge=1;break;
        }
        return judge;
    }
}
