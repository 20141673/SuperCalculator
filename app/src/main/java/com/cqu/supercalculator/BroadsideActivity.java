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
    private String strSimpleResultShow="";
    private TextView tvSimpleRecord;
    private TextView tvSimpleResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadside);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("标准",null).setContent(R.id.simplelayout));
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
    public void initView(){
        tvSimpleRecord=(TextView)findViewById(R.id.simplerecord);
        tvSimpleResult=(TextView)findViewById(R.id.simpleresult);
    }
    String strNum="";
    String strop="";
    boolean ifHavaSecond=false;
    ArrayList num=new ArrayList();

    public void clickButton(View v) {
        int id = v.getId();
        //String strTag= (String) v.getTag();
        strSimpleResultShow+= ((Button) v).getText().toString();
        tvSimpleRecord.setText(strSimpleResultShow);

        if(v.getTag().equals("number")){
            strNum+=((Button) v).getText().toString();
        }
        if(id==R.id.simplemultiply){
            num.add(Double.parseDouble(strNum));
            strNum="";
            strop+="*";
        }else if(id==R.id.simpledevide){
            num.add(Double.parseDouble(strNum));
            strNum="";
            strop+="/";
        }else if(id==R.id.simpleplus){
            num.add(Double.parseDouble(strNum));
            strNum="";
            strop+="+";
        }else if(id==R.id.simpleminus){
            num.add(Double.parseDouble(strNum));
            strNum="";
            strop+="-";
        }else if(id==R.id.simpleclear){
            num.clear();
            strNum="";
            strSimpleResultShow="";
            strop="";
            tvSimpleResult.setText("");
           tvSimpleRecord.setText("");
        }
        if(id==R.id.simpleequal){
            num.add(Double.parseDouble(strNum));

            while ((strop.indexOf("+")!=-1)||(strop.indexOf("-")!=-1)||(strop.indexOf("*")!=-1)||(strop.indexOf("/")!=-1)){
                while ((strop.indexOf("*")!=-1)||(strop.indexOf("/")!=-1)){
                    if(strop.indexOf("/")==-1){
                        num.set(strop.indexOf("*"), (Double) num.get(strop.indexOf("*")) * (Double) num.get(strop.indexOf("*") + 1));
                        num.remove(strop.indexOf("*") + 1);
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
                    if(strop.indexOf("*")>strop.indexOf("/")&&strop.indexOf("/")!=-1){
                        num.set(strop.indexOf("/"), (Double) num.get(strop.indexOf("/")) / (Double) num.get(strop.indexOf("/") + 1));
                        num.remove(strop.indexOf("/") + 1);
                        if (strop.indexOf("/") != 0) {
                            strop = strop.substring(0, strop.indexOf("/")) + strop.substring(strop.indexOf("/") + 1);
                        } else strop = strop.substring(strop.indexOf("/") + 1);
                    }else if(strop.indexOf("*")<strop.indexOf("/")&&strop.indexOf("*")!=-1){
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
            tvSimpleResult.setText((String)num.get(0).toString());
        }
    }
}
