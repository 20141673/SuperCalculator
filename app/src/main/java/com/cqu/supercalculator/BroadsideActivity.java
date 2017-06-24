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
import java.util.Collections;

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
    String strNum="";
    //暂存操作数
    String strNumTemp="";
    //保存所有操作符的字符串
    String strop="";
    //保存所有操作数的数组
    ArrayList num=new ArrayList();

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

        } else if (id == R.id.nav_personincometax) {

        } else if (id == R.id.nav_relativescall) {

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
    }
//计算器的按键的响应函数
    public void clickButton(View v) {
        int id = v.getId();
        //String strTag= (String) v.getTag();
        strSimpleResultShow+= ((Button) v).getText().toString();
        tvSimpleRecord.setText(strSimpleResultShow);


        if(v.getTag().equals("number")){
            //当按键的标签为number时，把按键的text加入到strNum
            strNum+=((Button) v).getText().toString();
        }else if(v.getTag().equals("percent")){
            //当按键的标签为percent时，把strNum转化为double后乘以0.01后再存入strNum
            Double dtemp=Double.parseDouble(strNum)*0.01;
            strNum=dtemp.toString();
        }else if(v.getTag().equals("return")){
            //当按键的标签为return时
            //用strBeDeleted保存被删除的字符
            String strBeDeleted=strSimpleResultShow.substring(strSimpleResultShow.length()-3,strSimpleResultShow.length()-2);
            //显示字符去掉<-，并刷新显示
            strSimpleResultShow=strSimpleResultShow.substring(0,strSimpleResultShow.length()-3);
            tvSimpleRecord.setText(strSimpleResultShow);
            //判断是删掉的是操作符还是操作数
            if(strBeDeleted.equals("+")||strBeDeleted.equals("-")||strBeDeleted.equals("÷")||strBeDeleted.equals("×")){
                //当删除的是操作符时，删除操作符，并把已存入num的操作数删除
                strop=strop.substring(0,strop.length()-1);
                //从strNumTemp中取出操作数
                strNum=strNumTemp;
                //把已存入num的操作数删除
                num.remove(num.size()-1);
            } else {
                //当删除的是操作数时，删除在strNum的被删除的数字
                strNum = strNum.substring(0, strNum.length() - 1);
            }

        }
        if(id==R.id.simplemultiply){
            //按下乘键时的响应程序
            //将前一个操作数转化为Double，存入num
            num.add(Double.parseDouble(strNum));
            //用strNumTemp暂存操作数
            strNumTemp=strNum;
            //清除strNum
            strNum="";
            //在strop中存入“*”
            strop+="*";
        }else if(id==R.id.simpledevide){
            num.add(Double.parseDouble(strNum));
            strNumTemp=strNum;
            strNum="";
            strop+="/";
        }else if(id==R.id.simpleplus){
            num.add(Double.parseDouble(strNum));
            strNumTemp=strNum;
            strNum="";
            strop+="+";
        }else if(id==R.id.simpleminus){
            num.add(Double.parseDouble(strNum));
            strNumTemp=strNum;
            strNum="";
            strop+="-";
        }else if(id==R.id.simpleclear){
            //按下清零键的响应程序
            //清空num中存储的操作数
            num.clear();
            //使strNum，strSimpleResultShow，strop为空
            strNum="";
            strSimpleResultShow="";
            strop="";
            //刷新显示
            tvSimpleResult.setText("");
            tvSimpleRecord.setText("");
        }
        if(id==R.id.simpleequal){
            //按下“=”键时的响应程序
            //将最后一个操作数存入num
            num.add(Double.parseDouble(strNum));
            //判断是否存在操作符
            while ((strop.indexOf("+")!=-1)||(strop.indexOf("-")!=-1)||(strop.indexOf("*")!=-1)||(strop.indexOf("/")!=-1)){
                //先计算乘除运算
                while ((strop.indexOf("*")!=-1)||(strop.indexOf("/")!=-1)){
                    if(strop.indexOf("/")==-1){//当不存在除运算时，说明只存在乘运算
                        //将乘运算符前后的操作数存入前一个操作数的位置
                        num.set(strop.indexOf("*"), (Double) num.get(strop.indexOf("*")) * (Double) num.get(strop.indexOf("*") + 1));
                        //移除第二个操作数
                        num.remove(strop.indexOf("*") + 1);
                        //移除用过的乘操作符
                        if (strop.indexOf("*") != 0) {
                            strop = strop.substring(0, strop.indexOf("*")) + strop.substring(strop.indexOf("*") + 1);
                        } else strop = strop.substring(strop.indexOf("*") + 1);
                    }else if(strop.indexOf("*")==-1){
                        num.set(strop.indexOf("/"), (Double) num.get(strop.indexOf("/")) / (Double) num.get(strop.indexOf("/") + 1));
                        num.remove(strop.indexOf("/") + 1);
                        if (strop.indexOf("/") != 0) {
                            strop = strop.substring(0, strop.indexOf("/")) + strop.substring(strop.indexOf("/") + 1);
                        } else strop = strop.substring(strop.indexOf("/") + 1);
                    }
                    if(strop.indexOf("*")>strop.indexOf("/")&&strop.indexOf("/")!=-1){//如果两个运算符都存在，且除在乘前
                        //计算除前后两个操作数相除后的结果，并保存在第一个操作数的位置
                        num.set(strop.indexOf("/"), (Double) num.get(strop.indexOf("/")) / (Double) num.get(strop.indexOf("/") + 1));
                        //移除第二个操作数
                        num.remove(strop.indexOf("/") + 1);
                        //移除用过的除运算符
                        if (strop.indexOf("/") != 0) {
                            strop = strop.substring(0, strop.indexOf("/")) + strop.substring(strop.indexOf("/") + 1);
                        } else strop = strop.substring(strop.indexOf("/") + 1);
                    }else if(strop.indexOf("*")<strop.indexOf("/")&&strop.indexOf("*")!=-1){//如果两个运算符都存在，且乘在除前
                        num.set(strop.indexOf("*"), (Double) num.get(strop.indexOf("*")) * (Double) num.get(strop.indexOf("*") + 1));
                        num.remove(strop.indexOf("*") + 1);
                        if (strop.indexOf("*") != 0) {
                            strop = strop.substring(0, strop.indexOf("*")) + strop.substring(strop.indexOf("*") + 1);
                        } else strop = strop.substring(strop.indexOf("*") + 1);
                    }

                }
                while ((strop.indexOf("+")!=-1)||(strop.indexOf("-")!=-1)){
                    if(strop.indexOf("-")==-1){
                        num.set(strop.indexOf("+"), (Double) num.get(strop.indexOf("+")) + (Double) num.get(strop.indexOf("+") + 1));
                        num.remove(strop.indexOf("+") + 1);
                        if(strop.indexOf("+")!=0){
                            strop = strop.substring(0, strop.indexOf("+")) + strop.substring(strop.indexOf("+") + 1);
                        } else strop = strop.substring(1);
                    }else if(strop.indexOf("+")==-1){
                        num.set(strop.indexOf("-"), (Double) num.get(strop.indexOf("-")) - (Double) num.get(strop.indexOf("-") + 1));
                        num.remove(strop.indexOf("-") + 1);
                        if (strop.indexOf("-") != 0) {
                            strop = strop.substring(0, strop.indexOf("-")) + strop.substring(strop.indexOf("-") + 1);
                        } else strop = strop.substring(strop.indexOf("-") + 1);
                    }
                    if(strop.indexOf("+")>strop.indexOf("-")&&strop.indexOf("-")!=-1){
                        num.set(strop.indexOf("-"), (Double) num.get(strop.indexOf("-")) - (Double) num.get(strop.indexOf("-") + 1));
                        num.remove(strop.indexOf("-") + 1);
                        if (strop.indexOf("-") != 0) {
                            strop = strop.substring(0, strop.indexOf("-")) + strop.substring(strop.indexOf("-") + 1);
                        } else strop = strop.substring(strop.indexOf("-") + 1);
                    }else if(strop.indexOf("+")<strop.indexOf("-")&&strop.indexOf("+")!=-1){
                        num.set(strop.indexOf("+"), (Double) num.get(strop.indexOf("+")) + (Double) num.get(strop.indexOf("+") + 1));
                        num.remove(strop.indexOf("+") + 1);
                        if(strop.indexOf("+")!=0){
                            strop = strop.substring(0, strop.indexOf("+")) + strop.substring(strop.indexOf("+") + 1);
                        } else strop = strop.substring(1);
                    }
                }
            }
            //显示结果
            tvSimpleResult.setText((String)num.get(0).toString());
            //清空数据
            num.clear();
            strNum="";
            strSimpleResultShow="";
            strop="";

        }
    }

}
