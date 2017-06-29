package com.cqu.supercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HouseLoanResultActivity extends AppCompatActivity {
    private TextView houseloanresult,tvinterestpay,tvtotalamount,tvyearamount,tvloanamount,tvmonthloss;
    private TextView textView;
    private LinearLayout repaylayout;
    private double comamount,proamount,compercent,propercent,yearcount;
    private int repayway,year,month,day;
    private double result,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_loan_result);
        houseloanresult=(TextView)findViewById(R.id.houseloanresult);
        tvinterestpay=(TextView)findViewById(R.id.tvinterestpay);
        tvyearamount=(TextView)findViewById(R.id.tvyearamount);
        tvloanamount=(TextView)findViewById(R.id.tvloanamount);
        tvtotalamount=(TextView)findViewById(R.id.tvtotalamount);
        textView=(TextView)findViewById(R.id.textView);
        repaylayout=(LinearLayout)findViewById(R.id.linearLayoutrepay);
        tvmonthloss=(TextView)findViewById(R.id.tvmonthloss);

        /*获得传递的数据*/
        Bundle bundle = this.getIntent().getExtras();
        comamount=bundle.getDouble("ComAmount");
        comamount*=10000;
        proamount=bundle.getDouble("ProAmount");
        proamount*=10000;
        compercent=bundle.getDouble("ComPercent");
        compercent=compercent/100/12;
        propercent=bundle.getDouble("ProPercent");
        propercent=propercent/100/12;
        yearcount=bundle.getDouble("YearCount");
        repayway=bundle.getInt("RePayWay");
        year=bundle.getInt("Year");
        month=bundle.getInt("Month");
        day=bundle.getInt("Day");
        countResult();

    }

    private void countResult(){
        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.000");
        if(repayway==0){  //等额本息方式
            textView.setText("每月还款(元)");
            double com=(comamount*compercent*Math.pow(1+compercent,yearcount*12))/(Math.pow(1+compercent,yearcount*12)-1);
            double pro=(proamount*propercent*Math.pow(1+propercent,yearcount*12))/(Math.pow(1+propercent,yearcount*12)-1);
            result=com+pro;
            total=result*12;
            houseloanresult.setText(String.valueOf(df.format(result)));                       //每月还款
            tvinterestpay.setText(String.valueOf(df.format(total-comamount-proamount))+"元");
            tvtotalamount.setText(String.valueOf(df.format(total/10000))+"万");
            tvyearamount.setText(String.valueOf(yearcount)+"年");
            tvloanamount.setText(String.valueOf(comamount+proamount)+"元");

        }else if(repayway==1){  //等额本金方式
            textView.setText("首月还款(元)");
            repaylayout.setVisibility(View.VISIBLE);
            double com=comamount/(yearcount*12)+comamount*compercent;
            double pro=proamount/(yearcount*12)+proamount*propercent;
            result=com+pro;
            total=result*yearcount*12;
            houseloanresult.setText(String.valueOf(df.format(result)));                       //每月还款
            tvinterestpay.setText(String.valueOf(df.format(total-comamount-proamount))+"元");
            tvtotalamount.setText(String.valueOf(df.format(total/10000))+"万");
            tvyearamount.setText(String.valueOf(yearcount)+"年");
            tvloanamount.setText(String.valueOf(comamount+proamount)+"元");
            double monthloss=comamount/(yearcount*12)*(compercent/12)+proamount/(yearcount*12)*(compercent/12);
            tvmonthloss.setText(String.valueOf(df.format(monthloss))+"元");
        }
    }

}
