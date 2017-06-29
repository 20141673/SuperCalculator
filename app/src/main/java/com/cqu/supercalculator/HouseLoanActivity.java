package com.cqu.supercalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class HouseLoanActivity extends AppCompatActivity {
    private EditText etcommercialamount,etcommmercialpercent,etcommercialpercenttime;
    private TextView tvcommercialpercent;

    private EditText etprofundamount,etprofundpercent,etprofundpercenttime;
    private TextView tvprofunpercent;

    private Spinner houseloanrepayway,houseloanyearcount,houseloanyear,houseloanmonth,houseloanday;
    private ArrayAdapter repaywayadapter,yearcountadapter;
    private ArrayAdapter<String> yearadapter,monthadapter,dayadapter;
    private int repayway,yearcount,year,month,day;
    private double commercialamount=0,        //
                   profundamount=0,
                   commercialpercent=4.9,
                   commercialpertime=1,
                   profundpercent=3.25,
                   profundpertime=1;
    private double result=0;
    private ArrayList<String> dataYear = new ArrayList<String>();
    private ArrayList<String> dataMonth = new ArrayList<String>();
    private ArrayList<String> dataDay = new ArrayList<String>();
    private ArrayList<String> countYear=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_loan);
        initView();
        initSpinner();//初始化spinner
    }

    private void initView(){
        //商业贷款初始化
        etcommercialamount=(EditText)findViewById(R.id.etcommercialamount);
        etcommmercialpercent=(EditText)findViewById(R.id.etcommercialpercent);
        etcommercialpercenttime=(EditText)findViewById(R.id.etcommercialpercenttime);
        tvcommercialpercent=(TextView)findViewById(R.id.tvcommercialpercent);

        //公积金贷款的初始化
        etprofundamount=(EditText)findViewById(R.id.etprofundamount);
        etprofundpercent=(EditText)findViewById(R.id.etprofundpercent);
        etprofundpercenttime=(EditText)findViewById(R.id.etprofundpercenttime);
        tvprofunpercent=(TextView)findViewById(R.id.tvprofundpercent);

    }

    private void initSpinner(){
        //可重用的spinner组件初始化并设置adapter
        houseloanrepayway=(Spinner)findViewById(R.id.houseloanrepayway);
        houseloanyearcount=(Spinner)findViewById(R.id.houseloanyearcount);
        houseloanday=(Spinner)findViewById(R.id.houseloanday);
        houseloanmonth=(Spinner)findViewById(R.id.houseloanmonth);
        houseloanyear=(Spinner)findViewById(R.id.houseloanyear);

        repaywayadapter=ArrayAdapter.createFromResource(this,R.array.HouseRepayWay,android.R.layout.simple_spinner_item);
        houseloanrepayway.setAdapter(repaywayadapter);
        houseloanrepayway.setSelection(0);
        for(int i=1;i<21;i++){
            countYear.add(Integer.toString(i));
        }
        yearcountadapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,countYear);
        houseloanyearcount.setAdapter(yearcountadapter);

        houseloanrepayway.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3){
               repayway=arg2;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){}
        });

        houseloanyearcount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3){
                yearcount=arg2+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){}
        });

        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 30; i++) {
            dataYear.add("" + (cal.get(Calendar.YEAR) - 15 + i));
        }
        yearadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataYear);
        yearadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        houseloanyear.setAdapter(yearadapter);
        houseloanyear.setSelection(0);

        for (int i = 1; i <= 12; i++) {
            dataMonth.add("" + (i < 10 ? "0" + i : i));
        }
        monthadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataMonth);
        monthadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        houseloanmonth.setAdapter(monthadapter);

        dayadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataDay);
        dayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        houseloanday.setAdapter(dayadapter);

        houseloanyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0,View arg1,int arg2,long arg3){
                year=2002+arg2;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        houseloanmonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                dataDay.clear();
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, Integer.valueOf(houseloanyear.getSelectedItem().toString()));
                cal.set(Calendar.MONTH, arg2);
                int dayofm = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int i = 1; i <= dayofm; i++) {
                    dataDay.add("" + (i < 10 ? "0" + i : i));
                }
                dayadapter.notifyDataSetChanged();
                month=arg2+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });

        houseloanday.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                day=i+1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0){}
        });

    }

    public void clickButton(View v){
        countData();
        Intent intent=new Intent(HouseLoanActivity.this,HouseLoanResultActivity.class);
        Bundle bundle=new Bundle();
        bundle.putDouble("ComAmount",commercialamount);
        bundle.putDouble("ProAmount",profundamount);
        bundle.putInt("RePayWay",repayway);
        bundle.putInt("YearCount",yearcount);
        bundle.putInt("Year",year);
        bundle.putInt("Month",month);
        bundle.putInt("Day",day);
        bundle.putDouble("ComPercent",commercialpercent*commercialpertime);
        bundle.putDouble("ProPercent",profundpercent*profundpertime);
        bundle.putDouble("YearCount",yearcount);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void countData(){
        /*获得计算必要的数据项*/
        if(!etcommercialamount.getText().toString().equals("")){
            commercialamount=Double.parseDouble(etcommercialamount.getText().toString());
        }
        if(!etprofundamount.getText().toString().equals("")){
            profundamount=Double.parseDouble(etprofundamount.getText().toString());
        }
        if(!etcommmercialpercent.getText().toString().equals("")){
            commercialpercent=Double.parseDouble(etcommmercialpercent.getText().toString());
        }
        if(!etcommercialpercenttime.getText().toString().equals("")){
            commercialpertime=Double.parseDouble(etcommmercialpercent.getText().toString());
        }
        if(!etprofundpercent.getText().toString().equals("")){
            profundpercent=Double.parseDouble(etprofundpercent.getText().toString());
        }
        if(!etprofundpercenttime.getText().toString().equals("")){
            profundpertime=Double.parseDouble(etprofundpercenttime.getText().toString());
        }
    }
}
