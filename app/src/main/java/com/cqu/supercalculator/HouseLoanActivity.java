package com.cqu.supercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class HouseLoanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_loan);

        /*****初始化tabhost******/
        TabHost tabHost=(TabHost)findViewById(R.id.houseloantabhost);
        tabHost.setup();
        //增加商业贷款的Tab
        tabHost.addTab(tabHost.newTabSpec("commercial").setIndicator("商业贷款",null).setContent(R.id.commerciallayout));
        //增加公积金贷款的Tab
        tabHost.addTab(tabHost.newTabSpec("providentfund").setIndicator("公积金贷款",null).setContent(R.id.providentfundlayout));
        //增加混合贷款的Tab
        tabHost.addTab(tabHost.newTabSpec("mix").setIndicator("混合贷款",null).setContent(R.id.mixlayout));
    }
}
