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

import java.text.DecimalFormat;
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
    //保存当时操作数
    String strSimpleNum="";
    //暂存操作数
    String strSimpleNumTemp="";
    //保存所有操作符的字符串
    String strSimpleOP="";
    //保存所有操作数的数组
    ArrayList simpleNum=new ArrayList();


    private String strScienceResult="";
    //保存显示结果字符串
    private String strScienceResultShow="";
    //显示计算过程
    private TextView tvScienceRecord;
    //显示计算结果
    private TextView tvScienceResult;
    //保存当时操作数
    String strScienceNum="";
    //暂存操作数
    String strScienceNumTemp="";
    //保存所有操作符的字符串
    String strScienceOP="";
    //保存所有操作数的数组
    ArrayList ScienceNum=new ArrayList();

    int MAXLEN=20;
    double PI=3.14;
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

        } else if (id == R.id.nav_relativescall) {
            Intent intent=new Intent(BroadsideActivity.this,RelativesCallActivity.class);
            BroadsideActivity.this.startActivity(intent);
        }else if(id==R.id.nav_lengthexchange){
            Intent intent=new Intent(BroadsideActivity.this,LengthExchangeActivity.class);
            BroadsideActivity.this.startActivity(intent);

        }else if(id==R.id.nav_temperatureexchange){

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

        //String strTag= (String) v.getTag();
        strSimpleResultShow += ((Button) v).getText().toString();
        tvSimpleRecord.setText(strSimpleResultShow);


        if (v.getTag().equals("SimpleNumber")) {
            //当按键的标签为number时，把按键的text加入到strSimpleNum
            strSimpleNum += ((Button) v).getText().toString();
        } else if (v.getTag().equals("SimplePercent")) {
            if (!strSimpleNum.equals("")) {
                //当按键的标签为percent时，把strSimpleNum转化为double后乘以0.01后再存入strSimpleNum
                Double dtemp = Double.parseDouble(strSimpleNum) * 0.01;
                strSimpleNum = dtemp.toString();
            }
        } else if (v.getTag().equals("SimpleReturn")) {
            //当按键的标签为return时
            //用strBeDeleted保存被删除的字符
            String strBeDeleted = strSimpleResultShow.substring(strSimpleResultShow.length() - 3, strSimpleResultShow.length() - 2);
            //显示字符去掉<-，并刷新显示
            strSimpleResultShow = strSimpleResultShow.substring(0, strSimpleResultShow.length() - 3);
            tvSimpleRecord.setText(strSimpleResultShow);
            //判断是删掉的是操作符还是操作数
            if (strBeDeleted.equals("+") || strBeDeleted.equals("-") || strBeDeleted.equals("÷") || strBeDeleted.equals("×")) {
                //当删除的是操作符时，删除操作符，并把已存入num的操作数删除
                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.length() - 1);
                //从strSimpleNumTemp中取出操作数
                strSimpleNum = strSimpleNumTemp;
                //把已存入simpleNum的操作数删除
                simpleNum.remove(simpleNum.size() - 1);
            } else {
                //当删除的是操作数时，删除在strSimpleNum的被删除的数字
                strSimpleNum = strSimpleNum.substring(0, strSimpleNum.length() - 1);
            }

        }

        if (id == R.id.simpleminus) {
            if (simpleNum.isEmpty() && strSimpleNum.equals("")) {
                strSimpleNum += ((Button) v).getText().toString();
            } else {
                simpleNum.add(Double.parseDouble(strSimpleNum));
                strSimpleNumTemp = strSimpleNum;
                strSimpleNum = "";
                strSimpleOP += "-";
            }
        }
        if (!strSimpleResultShow.equals("+") && !strSimpleResultShow.equals("×") && !strSimpleResultShow.equals("÷")) {
            if (id == R.id.simplemultiply) {
                if (!strSimpleNum.equals("")) {
                    //按下乘键时的响应程序
                    //将前一个操作数转化为Double，存入num
                    simpleNum.add(Double.parseDouble(strSimpleNum));
                    //用strSimpleNumTemp暂存操作数
                    strSimpleNumTemp = strSimpleNum;
                    //清除strSimpleNum
                    strSimpleNum = "";
                    //在strSimpleOP中存入“*”
                    strSimpleOP += "*";
                }
            } else if (id == R.id.simpledevide) {
                if (!strSimpleNum.equals("")) {
                    simpleNum.add(Double.parseDouble(strSimpleNum));
                    strSimpleNumTemp = strSimpleNum;
                    strSimpleNum = "";
                    strSimpleOP += "/";
                }
            } else if (id == R.id.simpleplus) {
                if (!strSimpleNum.equals("")) {
                    simpleNum.add(Double.parseDouble(strSimpleNum));
                    strSimpleNumTemp = strSimpleNum;
                    strSimpleNum = "";
                    strSimpleOP += "+";
                }
            } else if (id == R.id.simpleclear) {
                //按下清零键的响应程序
                //清空num中存储的操作数
                simpleNum.clear();
                //使strSimpleNum，strSimpleResultShow，strSimpleOP为空
                strSimpleNum = "";
                strSimpleResultShow = "";
                strSimpleOP = "";
                //刷新显示
                tvSimpleResult.setText("");
                tvSimpleRecord.setText("");
            }
            if (id == R.id.simpleequal) {
                //按下“=”键时的响应程序
                //将最后一个操作数存入simpleNum
                simpleNum.add(Double.parseDouble(strSimpleNum));
                //判断是否存在操作符
                while ((strSimpleOP.indexOf("+") != -1) || (strSimpleOP.indexOf("-") != -1) || (strSimpleOP.indexOf("*") != -1) || (strSimpleOP.indexOf("/") != -1)) {
                    //先计算乘除运算
                    while ((strSimpleOP.indexOf("*") != -1) || (strSimpleOP.indexOf("/") != -1)) {
                        if (strSimpleOP.indexOf("/") == -1) {//当不存在除运算时，说明只存在乘运算
                            //将乘运算符前后的操作数存入前一个操作数的位置
                            simpleNum.set(strSimpleOP.indexOf("*"), (Double) simpleNum.get(strSimpleOP.indexOf("*")) * (Double) simpleNum.get(strSimpleOP.indexOf("*") + 1));
                            //移除第二个操作数
                            simpleNum.remove(strSimpleOP.indexOf("*") + 1);
                            //移除用过的乘操作符
                            if (strSimpleOP.indexOf("*") != 0) {
                                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.indexOf("*")) + strSimpleOP.substring(strSimpleOP.indexOf("*") + 1);
                            } else
                                strSimpleOP = strSimpleOP.substring(strSimpleOP.indexOf("*") + 1);
                        } else if (strSimpleOP.indexOf("*") == -1) {
                            simpleNum.set(strSimpleOP.indexOf("/"), (Double) simpleNum.get(strSimpleOP.indexOf("/")) / (Double) simpleNum.get(strSimpleOP.indexOf("/") + 1));
                            simpleNum.remove(strSimpleOP.indexOf("/") + 1);
                            if (strSimpleOP.indexOf("/") != 0) {
                                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.indexOf("/")) + strSimpleOP.substring(strSimpleOP.indexOf("/") + 1);
                            } else
                                strSimpleOP = strSimpleOP.substring(strSimpleOP.indexOf("/") + 1);
                        }
                        if (strSimpleOP.indexOf("*") > strSimpleOP.indexOf("/") && strSimpleOP.indexOf("/") != -1) {//如果两个运算符都存在，且除在乘前
                            //计算除前后两个操作数相除后的结果，并保存在第一个操作数的位置
                            simpleNum.set(strSimpleOP.indexOf("/"), (Double) simpleNum.get(strSimpleOP.indexOf("/")) / (Double) simpleNum.get(strSimpleOP.indexOf("/") + 1));
                            //移除第二个操作数
                            simpleNum.remove(strSimpleOP.indexOf("/") + 1);
                            //移除用过的除运算符
                            if (strSimpleOP.indexOf("/") != 0) {
                                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.indexOf("/")) + strSimpleOP.substring(strSimpleOP.indexOf("/") + 1);
                            } else
                                strSimpleOP = strSimpleOP.substring(strSimpleOP.indexOf("/") + 1);
                        } else if (strSimpleOP.indexOf("*") < strSimpleOP.indexOf("/") && strSimpleOP.indexOf("*") != -1) {//如果两个运算符都存在，且乘在除前
                            simpleNum.set(strSimpleOP.indexOf("*"), (Double) simpleNum.get(strSimpleOP.indexOf("*")) * (Double) simpleNum.get(strSimpleOP.indexOf("*") + 1));
                            simpleNum.remove(strSimpleOP.indexOf("*") + 1);
                            if (strSimpleOP.indexOf("*") != 0) {
                                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.indexOf("*")) + strSimpleOP.substring(strSimpleOP.indexOf("*") + 1);
                            } else
                                strSimpleOP = strSimpleOP.substring(strSimpleOP.indexOf("*") + 1);
                        }

                    }
                    while ((strSimpleOP.indexOf("+") != -1) || (strSimpleOP.indexOf("-") != -1)) {
                        if (strSimpleOP.indexOf("-") == -1) {
                            simpleNum.set(strSimpleOP.indexOf("+"), (Double) simpleNum.get(strSimpleOP.indexOf("+")) + (Double) simpleNum.get(strSimpleOP.indexOf("+") + 1));
                            simpleNum.remove(strSimpleOP.indexOf("+") + 1);
                            if (strSimpleOP.indexOf("+") != 0) {
                                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.indexOf("+")) + strSimpleOP.substring(strSimpleOP.indexOf("+") + 1);
                            } else strSimpleOP = strSimpleOP.substring(1);
                        } else if (strSimpleOP.indexOf("+") == -1) {
                            simpleNum.set(strSimpleOP.indexOf("-"), (Double) simpleNum.get(strSimpleOP.indexOf("-")) - (Double) simpleNum.get(strSimpleOP.indexOf("-") + 1));
                            simpleNum.remove(strSimpleOP.indexOf("-") + 1);
                            if (strSimpleOP.indexOf("-") != 0) {
                                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.indexOf("-")) + strSimpleOP.substring(strSimpleOP.indexOf("-") + 1);
                            } else
                                strSimpleOP = strSimpleOP.substring(strSimpleOP.indexOf("-") + 1);
                        }
                        if (strSimpleOP.indexOf("+") > strSimpleOP.indexOf("-") && strSimpleOP.indexOf("-") != -1) {
                            simpleNum.set(strSimpleOP.indexOf("-"), (Double) simpleNum.get(strSimpleOP.indexOf("-")) - (Double) simpleNum.get(strSimpleOP.indexOf("-") + 1));
                            simpleNum.remove(strSimpleOP.indexOf("-") + 1);
                            if (strSimpleOP.indexOf("-") != 0) {
                                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.indexOf("-")) + strSimpleOP.substring(strSimpleOP.indexOf("-") + 1);
                            } else
                                strSimpleOP = strSimpleOP.substring(strSimpleOP.indexOf("-") + 1);
                        } else if (strSimpleOP.indexOf("+") < strSimpleOP.indexOf("-") && strSimpleOP.indexOf("+") != -1) {
                            simpleNum.set(strSimpleOP.indexOf("+"), (Double) simpleNum.get(strSimpleOP.indexOf("+")) + (Double) simpleNum.get(strSimpleOP.indexOf("+") + 1));
                            simpleNum.remove(strSimpleOP.indexOf("+") + 1);
                            if (strSimpleOP.indexOf("+") != 0) {
                                strSimpleOP = strSimpleOP.substring(0, strSimpleOP.indexOf("+")) + strSimpleOP.substring(strSimpleOP.indexOf("+") + 1);
                            } else strSimpleOP = strSimpleOP.substring(1);
                        }
                    }
                }
                //显示结果
                tvSimpleResult.setText((String) simpleNum.get(0).toString());
                //清空数据
                simpleNum.clear();
                strSimpleNum = "";
                strSimpleResultShow = "";
                strSimpleOP = "";
            }
        } else {
            tvSimpleResult.setText("请输入操作数");
            strSimpleResultShow = "";
            tvSimpleRecord.setText(strSimpleResultShow);
        }
    }

    /********************************试验代码02****************************************************/
    else if (v.getTag().toString().indexOf("Science") != -1) {
        strScienceResultShow+=((Button)v).getText().toString();
        tvScienceRecord.setText(strScienceResultShow);
        strScienceResult += v.getTag().toString().replace("Science", "");
        if(id==R.id.sciencereturn){
            strScienceResultShow=strScienceResultShow.substring(0,strScienceResultShow.length()-3);
            strScienceResult=strScienceResult.substring(0,strScienceResult.length()-1);
            tvScienceRecord.setText(strScienceResultShow);
        }else if(id==R.id.scienceclear){
            strScienceResultShow="";
            strScienceResult="";
            tvScienceResult.setText(strScienceResult);
            tvScienceRecord.setText(strScienceResultShow);
            numList.clear();
        }else if(id==R.id.scienceequal) {
            //strScienceResult="pi";
            //tvScienceResult.setText(strScienceResult);
            tvScienceResult.setText(ResolveBracket(strScienceResult));
        }
    }
    /********************************试验代码02****************************************************/
}
//递归解决括号问题
    public String ResolveBracket(String str){
        //判断是否传入的字符串中是否含有括号，如果有则将最里面的括号中的字符串用ResolveJJCC处理得到运算结果字符串，并去掉最里面的括号
        // 直到字符串中没有字符串则跳出while
        while(str.indexOf("(")!=-1){
            String strTemp=str.substring(str.lastIndexOf("(")+1,str.indexOf(")"));
            str=str.substring(0,str.lastIndexOf("("))+ResolveJJCC(strTemp)+str.substring(str.indexOf(")")+1,str.length());
        }

       str=ResolveJJCC(str);
        return str;
    }
    //存储科学计算器的操作数
    ArrayList numList=new ArrayList();
    //实现运算功能
    public  String ResolveJJCC(String str){
        //保存最终结果
        String FinalResult="";
        //保存操作符
        String strop="";
        //保存操作数字符串
        String strnum="";
        //记录操作数的起点
        int lastLocation=-1;
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
        while ((strop.indexOf("s") != -1)){
            Double dTemp=Math.sin((Double)numList.get(strop.indexOf("s")));
            numList.set(strop.indexOf("s"),dTemp);
            strop=strop.substring(0,strop.indexOf("s"))+strop.substring(strop.indexOf("s")+1,strop.length());
        }
        //处理cos()逻辑
        while ((strop.indexOf("c") != -1)){
            Double dTemp=Math.cos((Double)numList.get(strop.indexOf("c")));
            numList.set(strop.indexOf("c"),dTemp);
            strop=strop.substring(0,strop.indexOf("c"))+strop.substring(strop.indexOf("c")+1,strop.length());
        }
        //处理tan()逻辑
        while ((strop.indexOf("t") != -1)){
            Double dTemp=Math.tan((Double)numList.get(strop.indexOf("t")));
            numList.set(strop.indexOf("t"),dTemp);
            strop=strop.substring(0,strop.indexOf("t"))+strop.substring(strop.indexOf("t")+1,strop.length());
        }
        //处理ln()逻辑
        while ((strop.indexOf("n") != -1)){
            Double dTemp=Math.log((Double)numList.get(strop.indexOf("n")));
            numList.set(strop.indexOf("n"),dTemp);
            strop=strop.substring(0,strop.indexOf("n"))+strop.substring(strop.indexOf("n")+1,strop.length());
        }
        //处理lg()逻辑
        while ((strop.indexOf("g") != -1)){
            Double dTemp=Math.log((Double)numList.get(strop.indexOf("g")))/Math.log(10);
            numList.set(strop.indexOf("g"),dTemp);
            strop=strop.substring(0,strop.indexOf("g"))+strop.substring(strop.indexOf("n")+1,strop.length());
        }
        //处理开平方逻辑
        while ((strop.indexOf("q") != -1)){
            Double dTemp=Math.sqrt((Double)numList.get(strop.indexOf("q")));
            numList.set(strop.indexOf("q"),dTemp);
            strop=strop.substring(0,strop.indexOf("q"))+strop.substring(strop.indexOf("q")+1,strop.length());
        }
        //处理乘方逻辑
        while ((strop.indexOf("^") != -1)) {
            Double dTemp=1.0;
            for(int i=0;i<(Double)numList.get(strop.indexOf("^")+1);i++){
                dTemp=dTemp*(Double)numList.get(strop.indexOf("^"));
            }
            numList.set(strop.indexOf("^"),dTemp);
            numList.remove(strop.indexOf("^")+1);
            strop=strop.substring(0,strop.indexOf("^"))+strop.substring(strop.indexOf("^")+1,strop.length());
        }
        //处理四则运算逻辑
        //先处理乘除逻辑
        while ((strop.indexOf("*") != -1) || (strop.indexOf("/") != -1)) {
          for(int i=0;i<strop.length();i++){
              switch (strop.charAt(i)){
                  case '*':numList.set(i,(Double)numList.get(i)*(Double)numList.get(i+1));
                      strop=strop.substring(0,i)+strop.substring(i+1,strop.length());
                      numList.remove(i+1);
                      i=i-1;
                      break;
                  case '/':
                      numList.set(i,(Double)numList.get(i)/(Double)numList.get(i+1));
                      strop=strop.substring(0,i)+strop.substring(i+1,strop.length());
                      numList.remove(i+1);
                      i=i-1;
                      break;
              }
          }
        }
        //处理完乘除逻辑后再处理加减逻辑
        while ((strop.indexOf("+") != -1) || (strop.indexOf("-") != -1)) {
            for(int i=0;i<strop.length();i++){
                switch (strop.charAt(i)){
                    case '+':numList.set(i,(Double)numList.get(i)+(Double)numList.get(i+1));
                        strop=strop.substring(0,i)+strop.substring(i+1,strop.length());
                        numList.remove(i+1);
                        i=i-1;
                        break;
                    case '-':
                        numList.set(i,(Double)numList.get(i)-(Double)numList.get(i+1));
                        strop=strop.substring(0,i)+strop.substring(i+1,strop.length());
                        numList.remove(i+1);
                        i=i-1;
                        break;
                }
            }
        }
        //得到最终结果的字符串表示
        FinalResult=numList.get(0).toString();
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
