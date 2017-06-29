package com.cqu.supercalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class RelativesCallActivity extends AppCompatActivity {
    private TextView relativeRecord,relativeResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatives_call);
        relativeRecord=(TextView)findViewById(R.id.relativerecord);
        relativeResult=(TextView)findViewById(R.id.relativeresult);
        //String relation="父子";
        /******************************************/
        /*
        if(relation.charAt(0)=='父') {
            Person first = new Person(relation, 0, 0);
        }else if(relation.charAt(0)=='母'){
            Person first = new Person(relation, 0, 1);
        }
        //relativeResult.setText(first);
        */
        /**********************************************/
        String relation="妻父";
        GetRelationByXml(relation);

    }

    public void clickButton(View v){
        int id=v.getId();
        
    }


        public void GetRelationByXml(String strRelation){
            int index;
            String[] strTemp;
            int[] index0={R.array.father, R.array.mother,R.array.son,R.array.daughter,R.array.olderbrother,R.array.youngerbrother,R.array.oldersister,R.array.youngersister,R.array.husband,R.array.wife};

            if(strRelation.length()==1){
                index=GetRelationIndex(strRelation.charAt(0));
                strRelation=strRelation.substring(1,strRelation.length());
                strTemp=getResources().getStringArray(R.array.me);
                relativeResult.setText(strTemp[index]);
            }else {
                index=GetRelationIndex(strRelation.charAt(0));
                strTemp=getResources().getStringArray(index0[index]);

                strRelation=strRelation.substring(1,strRelation.length());
                index=GetRelationIndex(strRelation.charAt(0));
                relativeResult.setText(strTemp[index]);
            }
        }
        public int GetRelationIndex(char cRelation){
            int index=0;
            switch (cRelation){
                case '父':index=0;
                    break;
                case '母':index=1;
                    break;
                case '子':index=2;
                    break;
                case '女':index=3;
                    break;
                case '兄':index=4;
                    break;
                case '弟':index=5;
                    break;
                case '姐':index=6;
                    break;
                case '妹':index=7;
                    break;
                case '夫':index=8;
                    break;
                case '妻':index=9;
                    break;

            }
            return index;
        }









    /***************************************************************************************/
    /*
    class Person{
        int position=0;
        int fOrm=0;
        String calls;


        Person(){
             position=0;

        }
        Person(int Position){
            position=Position;
        }
        Person(String strRelation,int Pos,int fOrm){
            int ForM=fOrm;
            position=Pos;
            char cRelation=strRelation.charAt(0);
            strRelation=strRelation.substring(1,strRelation.length());
            switch (position) {
                case 0:
                    if (cRelation == '父') {
                        calls = "爸爸";
                        if (strRelation.isEmpty()) {
                            relativeResult.setText(getCalls());
                        }else {
                            position+=1;
                            new Person(strRelation,position,0);
                        }
                    }
                    if(cRelation=='母'){
                        calls = "妈妈";
                        if (strRelation.isEmpty()) {
                            relativeResult.setText(getCalls());
                        }else {
                            position+=1;
                            new Person(strRelation,position,1);
                        }
                    }
                    if(cRelation=='兄'){
                        calls = "哥哥";
                        if (strRelation.isEmpty()) {
                            relativeResult.setText(getCalls());
                        }else {
                            position+=0;
                            new Person(strRelation,position,1);
                        }
                    }

                break;
                case 1:
                    if(ForM==0) {
                        if (cRelation == '父') {
                            calls = "爷爷";
                            if (strRelation.isEmpty()) {
                                relativeResult.setText(getCalls());
                            } else {
                                position += 1;
                                new Person(strRelation, position, 0);
                            }
                        }
                        if (cRelation == '母') {
                            calls = "奶奶";
                            if (strRelation.isEmpty()) {
                                relativeResult.setText(getCalls());
                            } else {
                                position += 1;
                                new Person(strRelation, position, 1);
                            }
                        }
                    }else {
                        if (cRelation == '父') {
                            calls = "外公";
                            if (strRelation.isEmpty()) {
                                relativeResult.setText(getCalls());
                            } else {
                                position += 1;
                                new Person(strRelation, position, 0);
                            }
                        }
                        if (cRelation == '母') {
                            calls = "外婆";
                            if (strRelation.isEmpty()) {
                                relativeResult.setText(getCalls());
                            } else {
                                position += 1;
                                new Person(strRelation, position, 1);
                            }
                        }
                    }
            }
        }

        public String getCalls(){
            return calls;
        }

    }
    */
/********************************************************************************************/
/*
    public void GetRelationCall(String strRelation){
            int beiFen=CalculateBeiFen(strRelation);

        }
        public int CalculateBeiFen(String strRelation){
            int beifen=0;
            while (!strRelation.isEmpty()) {
                switch (strRelation.charAt(0)) {
                    case '父':
                    case '母':beifen++;
                        break;
                    case '兄':
                    case '弟':
                    case '姐':
                    case '妹':
                    case '妻':
                    case '夫':beifen+=0;
                        break;
                    case '子':
                    case '女':beifen--;
                        break;
                }
            }
            return beifen;
        }
*/
    /***************************************************************************/
}
