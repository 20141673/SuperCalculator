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
        //String relation="妻夫";
        //String relation="ffw";
        //GetRelationByXml(relation);
        //Try(relation);
    }

    public void clickButton(View v){
        int id=v.getId();
        String strRelative="";
        strRelative +=v.getTag().toString();
        if(id==R.id.relativeequal){
            //strRelative="ff";
            Try(strRelative);
            //relativeResult.setText(strRelative);
            strRelative="";
        }
        
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
            }else if(strRelation.length()==2){
                index=GetRelationIndex(strRelation.charAt(0));
                strTemp=getResources().getStringArray(index0[index]);

                strRelation=strRelation.substring(1,strRelation.length());
                index=GetRelationIndex(strRelation.charAt(0));
                relativeResult.setText(strTemp[index]);
            }else if(strRelation.length()>2){

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

        public void IsBack(String strRelation){

            if(strRelation.indexOf("夫妻")!=-1){
               strRelation=strRelation.replace("夫妻","");
            }else if(strRelation.indexOf("妻夫")!=-1){
                strRelation=strRelation.replace("妻夫","");
            }


        }




    public void Try(String str){
        String[] strRelative = {
                "me",
                //本族
                "f",
                "ff",
                "fff",
                "ffff",
                "ffffob",
                "fffflb",
                "fffm",
                "fffob",
                "fffobw",
                "ffflb",
                "ffflbw",
                "fffxbs&o",
                "fffxbs&ow",
                "fffxbs&l",
                "fffxbs&lw",
                "fffxbss&o",
                "fffxbss&ow",
                "fffxbss&l",
                "fffxbss&lw",
                "fffxbsss&o",
                "fffxbsss&l",
                "fffxs",
                "fffxsh",
                "fffxss&o",
                "fffxss&ow",
                "fffxss&l",
                "fffxss&lw",
                "ffm",
                "ffmxb",
                "ffmxbw",
                "ffmxbs&o",
                "ffmxbs&ow",
                "ffmxbs&l",
                "ffmxbs&lw",
                "ffmxs",
                "ffmxsh",
                "ffmxss&o",
                "ffmxss&ow",
                "ffmxss&l",
                "ffmxss&lw",
                "ffxb",
                "ffxbw",
                "ffxbs&o",
                "ffxbs&ow",
                "ffxbs&l",
                "ffxbsw",
                "ffxbss&o",
                "ffxbss&ow",
                "ffxbss&l",
                "ffxbss&lw",
                "ffxbsss",
                "ffxbsssw",
                "ffxbssss",
                "ffxbsssd",
                "ffxbssd",
                "ffxbssdh",
                "ffxbsd&o",
                "ffxbsd&oh",
                "ffxbsd&l",
                "ffxbsd&lh",
                "ffxbd",
                "ffxbdh",
                "ffob",
                "ffobw",
                "fflb",
                "fflbw",
                "ffxs",
                "ffxsh",
                "ffxss&o",
                "ffxss&ow",
                "ffxss&l",
                "ffxss&lw",
                "ffxsd",
                "ffxsdh",
                "fm",
                "fmf",
                "fmm",
                "fmxb",
                "fmxbw",
                "fmxbs&o",
                "fmxbs&ow",
                "fmxbs&l",
                "fmxbs&lw",
                "fmxbd",
                "fmxbdh",
                "fmxs",
                "fmxsh",
                "fmxss&o",
                "fmxss&ow",
                "fmxss&l",
                "fmxss&lw",
                "fmxsd",
                "fmxsdh",
                "fxbs&o",
                "fxbs&ow",
                "fxbs&l",
                "fxbs&lw",
                "fxbss",
                "fxbssw",
                "fxbsss",
                "fxbsssw",
                "fxbssd",
                "fxbssdh",
                "fxbsd",
                "fxbsdh",
                "fxbd&o",
                "fxbd&oh",
                "fxbd&l",
                "fxbd&lh",
                "fxbds",
                "fxbdd",
                "fob",
                "fobw",
                "flb",
                "flbw",
                //
                "fxs",
                "fxsh",
                "fxss&o",
                "fxss&ow",
                "fxss&l",
                "fxss&lw",
                "fxsss",
                "fxssss",
                "fxssssw",
                "fxsssd",
                "fxsssdh",
                "fxssd",
                "fxssds",
                "fxssdsw",
                "fxssdd",
                "fxssddh",
                "fxsd&o",
                "fxsd&oh",
                "fxsd&l",
                "fxsd&lh",
                "fxsds",
                "fxsdd",
                "fos",
                "fls",
                //母族
                "m",
                "mf",
                "mff",
                "mffxb",
                "mffxbw",
                "mffxs",
                "mffxsh",
                "mffxss&o",
                "mffxss&ow",
                "mffxss&l",
                "mffxss&lw",
                "mfm",
                "mfmxb",
                "mfmxbw",
                "mfmxs",
                "mfmxsh",
                "mfxb",
                "mfxbs",
                "mfxbsw",
                "mfxbd",
                "mfxbdh",
                "mfob",
                "mfobw",
                "mflb",
                "mflbw",
                "mfxs",
                "mfxsh",
                "mfxss",
                "mfxssw",
                "mfxsd",
                "mfxsdh",
                "mm",
                "mmf",
                "mmm",
                "mmxb",
                "mmxbw",
                "mmxbs",
                "mmxbsw",
                "mmxbd",
                "mmxbdh",
                "mmxs",
                "mmxsh",
                "mmxss",
                "mmxssw",
                "mmxsd",
                "mmxsdh",
                //
                "mxb",
                "mxbw",
                "mxbs&o",
                "mxbs&ow",
                "mxbs&l",
                "mxbs&lw",
                "mxbss",
                "mxbsss",
                "mxbsssw",
                "mxbssd",
                "mxbssdh",
                "mxbsd",
                "mxbsds",
                "mxbsdsw",
                "mxbsdd",
                "mxbsddh",
                "mxbd&o",
                "mxbd&oh",
                "mxbd&l",
                "mxbd&lh",
                "mxbds",
                "mxbdd",
                "mob",
                "mobw",
                "mlb",
                "mlbw",
                //
                "mxs",
                "mxsh",
                "mxss&o",
                "mxss&ow",
                "mxss&l",
                "mxss&lw",
                "mxsss",
                "mxssss",
                "mxssssw",
                "mxsssd",
                "mxsssdh",
                "mxssd",
                "mxssds",
                "mxssdsw",
                "mxssdd",
                "mxssddh",
                "mxsd&o",
                "mxsd&oh",
                "mxsd&l",
                "mxsd&lh",
                "mxsds",
                "mxsdd",
                "mos",
                "mosh",
                "mls",
                "mlsh",
                //夫族
                "h",
                "hf",
                "hff",
                "hfff",
                "hffm",
                "hfm",
                "hfob",
                "hfobw",
                "hflb",
                "hflbw",
                "hfxbs&o",
                "hfxbs&ow",
                "hfxbs&l",
                "hfxbs&lw",
                "hm",
                "hmxb",
                "hmxbw",
                "hmxs",
                "hmxsh",
                "hxbs",
                "hxbsw",
                "hxbss",
                "hxbssw",
                "hxbsd",
                "hxbsdh",
                "hxbd",
                "hxbdh",
                "hxbds",
                "hxbdsw",
                "hxbdd",
                "hxbddh",
                "hob",
                "hobw",
                "hlb",
                "hlbw",
                "hxss",
                "hxssw",
                "hxsss",
                "hxsssw",
                "hxssss",
                "hxsssd",
                "hxssd",
                "hxssdh",
                "hxssds",
                "hxssdd",
                "hxsd",
                "hxsdh",
                "hxsds",
                "hxsdsw",
                "hxsdss",
                "hxsdsd",
                "hxsdd",
                "hxsddh",
                "hxsdds",
                "hxsddd",
                "hos",
                "hosh",
                "hls",
                "hlsh",
                //妻族
                "w",
                "wf",
                "wff",
                "wffob",
                "wffobw",
                "wfflb",
                "wfflbw",
                "wffxbs&o",
                "wffxbs&ow",
                "wffxbs&l",
                "wffxbs&lw",
                "wffxs",
                "wffxsh",
                "wfm",
                "wfmxb",
                "wfmxbw",
                "wfmxs",
                "wfmxsh",
                "wfxbs&o",
                "wfxbs&l",
                "wfxbd&o",
                "wfxbd&l",
                "wfob",
                "wfobw",
                "wflb",
                "wflbw",
                "wfxs",
                "wfxss&o",
                "wfxss&l",
                "wfxsd&o",
                "wfxsd&l",
                "wm",
                "wmf",
                "wmm",
                "wmxb",
                "wmxbw",
                "wmxbs&o",
                "wmxbs&l",
                "wmxbd&o",
                "wmxbd&l",
                "wmxs",
                "wmxsh",
                "wmxss&o",
                "wmxss&l",
                "wmxsd&o",
                "wmxsd&l",
                "wxbs",
                "wxbsw",
                "wxbss",
                "wxbssw",
                "wxbsd",
                "wxbsdh",
                "wxbd",
                "wxbdh",
                "wxbds",
                "wxbdsw",
                "wxbdd",
                "wxbddh",
                "wob",
                "wobw",
                "wlb",
                "wlbw",
                "wxss",
                "wxssw",
                "wxsss",
                "wxsssw",
                "wxssd",
                "wxssdh",
                "wxsd",
                "wxsdh",
                "wxsds",
                "wxsdsw",
                "wxsdd",
                "wxsddh",
                "wos",
                "wosh",
                "wls",
                "wlsh",
                //旁支
                "xb",
                "xbwf",
                "xbwm",
                "xbs",
                "xbsw",
                "xbss",
                "xbssw",
                "xbsss",
                "xbssd",
                "xbsd",
                "xbsdh",
                "xbd",
                "xbdh",
                "xbds",
                "xbdsw",
                "xbdd",
                "xbddh",
                "ob",
                "obw",
                "obwf",
                "obwm",
                "lb",
                "lbw",
                "lbwf",
                "lbwm",
                "xs",
                "xshf",
                "xshm",
                "xss",
                "xssw",
                "xsss",
                "xsssw",
                "xssss",
                "xsssd",
                "xssd",
                "xssdh",
                "xssds",
                "xssdd",
                "xsd",
                "xsdh",
                "xsds",
                "xsdsw",
                "xsdss",
                "xsdsd",
                "xsdd",
                "xsddh",
                "xsdds",
                "xsddd",
                "os",
                "osh",
                "ls",
                "lsh",
                //自
                "s",
                "sw",
                "swxb",
                "swxs",
                "ss",
                "ssw",
                "sss",
                "sssw",
                "ssss",
                "sssd",
                "sssss",
                "ssd",
                "ssdh",
                "ssds",
                "ssdd",
                "sd",
                "sdh",
                "sds",
                "sdd",
                "d",
                "dh",
                "dhxb",
                "dhxs",
                "ds",
                "dsw",
                "dss",
                "dsd",
                "dd",
                "ddh",
                "dds",
                "ddd",
                //
                "swm",
                "swf",
                "swff",
                "swfm",
                "swfob",
                "swflb",
                "dhm",
                "dhf",
                "dhff",
                "dhfm",
                "dhfob",
                "dhflb",
        };
        String[] strRelativeTrue = {
                "自己,我",
                //本家
                "爸爸",
                "爷爷",
                "曾祖父",
                "高祖父",
                "伯高祖父",
                "叔高祖父",
                "高祖母",
                "伯曽祖父",
                "叔曽祖母",
                "伯曽祖父",
                "叔曽祖母",
                "堂伯祖父",
                "堂伯祖母",
                "堂叔祖父",
                "堂叔祖母",
                "从伯父",
                "从伯母",
                "从叔父",
                "从叔母",
                "族兄",
                "族弟",
                "曾祖姑母",
                "曾祖姑丈",
                "表伯祖父",
                "表伯祖母",
                "表叔祖父",
                "表叔祖母",
                "曾祖母",
                "太舅公",
                "太舅婆",
                "表伯祖父",
                "表伯祖母",
                "表叔祖父",
                "表叔祖母",
                "太姨奶",
                "太姨爷",
                "表伯祖父",
                "表伯祖母",
                "表叔祖父",
                "表叔祖母",
                "堂祖父",
                "堂祖母",
                "堂伯",
                "堂伯母",
                "堂叔",
                "堂叔母",
                "从兄弟",
                "从嫂",
                "从兄弟",
                "从弟妹",
                "从侄子",
                "从侄媳妇",
                "从侄孙",
                "从侄孙女",
                "从侄女",
                "从侄女婿",
                "从姐妹",
                "从姐夫",
                "从姐妹",
                "从妹夫",
                "堂姑",
                "堂姑丈",
                "伯祖父",
                "伯祖母",
                "叔祖父",
                "叔祖母",
                "姑婆",
                "姑丈公",
                "表伯",
                "表伯母",
                "表叔",
                "表婶",
                "表姑",
                "表姑丈",
                "奶奶",
                "曾外祖父",
                "曾外祖母",
                "舅公",
                "舅婆",
                "表伯",
                "表伯母",
                "表叔,表叔父",
                "表婶,表叔母",
                "表姑",
                "表姑丈",
                "姨婆,姨奶奶,姨祖父",
                "姨丈公,姨爷爷,姨祖母,姨爷,姨老爷,姨奶爷",
                "表伯,表伯父",
                "表伯母",
                "表叔,表叔父",
                "表婶,表叔母",
                "表姑",
                "表姑丈",
                "堂哥,堂兄",
                "堂嫂",
                "堂弟",
                "堂弟媳",
                "堂侄,堂侄子",
                "堂侄媳妇",
                "堂侄孙",
                "堂侄孙媳妇",
                "堂侄孙女",
                "堂侄孙女婿",
                "堂侄女",
                "堂侄女婿",
                "堂姐",
                "堂姐夫",
                "堂妹",
                "堂妹夫",
                "堂外甥",
                "堂外甥女",
                "伯父,伯伯,大伯",
                "伯母,大娘",
                "叔叔,叔父,阿叔,叔,仲父",
                "婶婶,婶母,阿婶,家婶,婶,季母",
                //姑家
                "姑妈,姑母,姑姑,姑",
                "姑丈,姑父,姑夫",
                "表哥(姑家),表哥",
                "表嫂(姑家),表嫂",
                "表弟(姑家),表弟",
                "表弟媳(姑家),表弟媳",
                "表侄子",
                "表侄孙",
                "表侄孙媳妇",
                "表侄孙女",
                "表侄孙女婿",
                "表侄女",
                "外表侄孙",
                "外表侄孙媳妇",
                "外表侄孙女",
                "外表侄孙女婿",
                "表姐(姑家),表姐",
                "表姐夫(姑家),表姐夫,表姐丈",
                "表妹(姑家),表妹",
                "表妹夫(姑家),表妹夫",
                "表外甥",
                "表外甥女",
                "姑母",
                "姑姐",
                //外家
                "妈妈,母亲,老妈,阿妈,老母,老妈子,娘,娘亲,妈咪",
                "外公,姥爷,阿公",
                "外曾祖父,外太祖父,太外祖父,太姥爷,外太公",
                "外太伯公",
                "外太伯母",
                "外太姑婆",
                "外太姑丈公",
                "外表伯祖父",
                "外表伯祖母",
                "外表叔祖父",
                "外表叔祖母",
                "外曾祖母,外太祖母,太外祖母,太姥姥,外太婆",
                "外太舅公",
                "外太舅母,外太舅婆",
                "外太姨婆",
                "外太姑姨公",
                "小姥爷",
                "堂舅,堂舅父",
                "堂舅妈,堂舅母",
                "堂姨",
                "堂姨丈",
                "外伯祖父,伯姥爷,大姥爷,外伯祖",
                "外伯祖母,伯姥姥,大姥姥,外姆婆",
                "外叔祖父,叔姥爷,小姥爷,外叔祖",
                "外叔祖母,叔姥姥,小姥姥,外姆婆",
                "外姑祖母,姑姥姥,外太姑母",
                "外姑祖父,姑姥爷,外太姑父",
                "表舅,表舅父",
                "表舅妈,表舅母",
                "表姨,表姨母,表姨妈,表阿姨",
                "表姨丈,表姨父",
                "外婆,姥姥,阿婆",
                "外曾外祖父,外太外公,太姥爷",
                "外曾外祖母,外太外婆,太姥姥",
                "外舅公,外舅祖父,舅姥爷,舅外祖父,舅外公,舅公",
                "外舅婆,外舅祖母,舅姥姥",
                "表舅,表舅父",
                "表舅妈,表舅母",
                "表姨,表姨母,表姨妈,表阿姨",
                "表姨丈,表姨父",
                "外姨婆,外姨祖母,姨姥姥,姨婆",
                "外姨丈公,外姨祖父,姨姥爷",
                "表舅,表舅父",
                "表舅妈,表舅母",
                "表姨,表姨母,表姨妈,表阿姨",
                "表姨丈,表姨父",
                //舅家
                "舅舅,舅父,舅,娘舅,舅仔",
                "舅妈,舅母,妗妗,妗母,阿妗",
                "表哥(舅家),表哥",
                "表嫂(舅家),表嫂",
                "表弟(舅家),表弟",
                "表弟媳(舅家),表弟媳",
                "表侄子",
                "表侄孙",
                "表侄孙媳妇",
                "表侄孙女",
                "表侄孙女婿",
                "表侄女",
                "外表侄孙",
                "外表侄孙媳妇",
                "外表侄孙女",
                "外表侄孙女婿",
                "表姐",
                "表姐夫",
                "表妹",
                "表妹夫",
                "表外甥",
                "表外甥女",
                "大舅",
                "大舅妈",
                "小舅",
                "小舅妈",
                //姨家
                "姨妈",
                "姨丈",
                "表哥",
                "表嫂",
                "表弟",
                "表弟媳",
                "表侄子",
                "表侄孙",
                "表侄孙媳妇",
                "表侄孙女",
                "表侄孙女婿",
                "表侄女",
                "外表侄孙",
                "外表侄孙媳妇",
                "外表侄孙女",
                "外表侄孙女婿",
                "表姐",
                "表姐夫",
                "表妹",
                "表妹夫",
                "表外甥",
                "表外甥女",
                "大姨",
                "大姨父",
                "小姨",
                "小姨父",
                //婆家
                "老公",
                "公公",
                "祖翁",
                "太公翁",
                "太奶亲",
                "祖婆",
                "伯翁",
                "伯婆",
                "叔翁",
                "叔婆",
                "堂大伯",
                "堂嫂",
                "堂叔仔",
                "堂小弟",
                "婆婆",
                "舅公",
                "舅婆",
                "姨婆",
                "姨公",
                "侄子",
                "侄媳",
                "侄孙",
                "侄孙媳",
                "侄孙女",
                "侄孙女婿",
                "侄女",
                "侄女婿",
                "外侄孙",
                "外侄媳妇",
                "外侄孙女",
                "外侄孙女婿",
                "大伯子",
                "大婶子",
                "小叔子",
                "小婶子",
                "外甥",
                "外甥媳妇",
                "外甥孙",
                "外甥孙媳妇",
                "外曾甥孙",
                "外曾甥孙女",
                "外甥孙女",
                "外甥孙女婿",
                "外曾甥孙",
                "外曾甥孙女",
                "外甥女",
                "外甥女婿",
                "外甥孙",
                "外甥孙媳妇",
                "外曾甥孙",
                "外曾甥孙女",
                "外甥孙女",
                "外甥孙女婿",
                "外曾甥孙",
                "外曾甥孙女",
                "大姑子",
                "大姑夫",
                "小姑子",
                "小姑夫",
                //岳家
                "老婆",
                "岳父",
                "太岳父",
                "太伯岳",
                "太伯岳母",
                "太叔岳",
                "太叔岳母",
                "姻伯",
                "姻姆",
                "姻叔",
                "姻婶",
                "太姑岳母",
                "太姑岳父",
                "太岳母",
                "太舅岳父",
                "太舅岳母",
                "太姨岳母",
                "太姨岳父",
                "堂大舅",
                "堂舅仔",
                "堂大姨",
                "堂姨仔",
                "伯岳",
                "伯岳母",
                "叔岳",
                "叔岳母",
                "姑岳母",
                "表大舅",
                "表舅仔",
                "表大姨",
                "表姨仔",
                "岳母",
                "外太岳父",
                "外太岳母",
                "舅岳父",
                "舅岳母",
                "表大舅",
                "表舅仔",
                "表大姨",
                "表姨仔",
                "姨岳母",
                "姨岳父",
                "表大舅",
                "表舅仔",
                "表大姨",
                "表姨仔",
                "内侄",
                "内侄媳妇",
                "侄孙",
                "侄孙媳妇",
                "侄孙女",
                "侄孙女婿",
                "内侄女",
                "内侄女婿",
                "外侄孙",
                "外侄孙媳妇",
                "外侄孙女",
                "外侄孙女婿",
                "大舅哥",
                "舅嫂",
                "小舅子",
                "舅弟媳",
                "姨甥",
                "姨甥媳妇",
                "姨甥孙",
                "姨甥孙媳妇",
                "姨甥孙女",
                "姨甥孙女婿",
                "姨甥女",
                "姨甥女婿",
                "姨甥孙",
                "姨甥孙媳妇",
                "姨甥孙女",
                "姨甥孙女婿",
                "大姨子",
                "大姨夫",
                "小姨子",
                "小姨夫",
                //旁支
                "兄弟",
                "姻世伯",
                "姻伯母",
                "侄子",
                "侄媳",
                "侄孙",
                "侄孙媳",
                "侄曾孙",
                "侄曾孙女",
                "侄孙女",
                "侄孙女婿",
                "侄女",
                "侄女婿",
                "外侄孙",
                "外侄孙媳妇",
                "外侄孙女",
                "外侄孙女婿",
                "哥哥",
                "嫂子",
                "姻伯父",
                "姻伯母",
                "弟弟",
                "弟妹",
                "姻叔父",
                "姻叔母",
                "姐妹",
                "姻世伯",
                "姻伯母",
                "外甥",
                "外甥媳妇",
                "外甥孙",
                "外甥孙媳妇",
                "外曾甥孙",
                "外曾甥孙女",
                "外甥孙女",
                "外甥孙女婿",
                "外曾甥孙",
                "外曾甥孙女",
                "外甥女",
                "外甥女婿",
                "外甥孙",
                "外甥孙媳妇",
                "外曾甥孙",
                "外曾甥孙女",
                "外甥孙女",
                "外甥孙女婿",
                "外曾甥孙",
                "外曾甥孙女",
                "姐姐",
                "姐夫",
                "妹妹",
                "妹夫",
                //自家
                "儿子",
                "儿媳妇",
                "姻侄",
                "姻侄女",
                "孙子",
                "孙媳妇",
                "曾孙",
                "曾孙媳妇",
                "玄孙",
                "玄孙女",
                "来孙",
                "曾孙女",
                "曾孙女婿",
                "外玄孙",
                "外玄孙女",
                "孙女",
                "孙女婿",
                "曾外孙",
                "曾外孙女",
                "女儿",
                "女婿",
                "姻侄",
                "姻侄女",
                "外孙",
                "外孙媳",
                "外曾孙",
                "外曾孙女",
                "外孙女",
                "外孙女婿",
                "外曾外孙",
                "外曾外孙女",
                //亲家
                "亲家母",
                "亲家公",
                "太姻翁",
                "太姻姆",
                "姻兄",
                "姻弟",
                "亲家母",
                "亲家公",
                "太姻翁",
                "太姻姆",
                "姻兄",
                "姻弟"
    };
    String strShow="";
    for(int i=0;i<strRelative.length;i++){
        String strTemp=strRelative[i];

        if(str.equals(strRelative[i])){
            strShow=strRelativeTrue[i];
        }
        if(strTemp.indexOf("x")!=-1&&str.equals(strTemp.replace("x","o"))){
            strShow=strRelativeTrue[i];
        }else if(strTemp.indexOf("x")!=-1&&str.equals(strTemp.replace("x","l"))){
            strShow=strRelativeTrue[i];
        }
        if(strTemp.indexOf("&")!=-1&&str.equals(strTemp.substring(0,strTemp.indexOf("&"))+strTemp.substring(strTemp.indexOf("&")+2,strTemp.length()))){
            strShow=strRelativeTrue[i];
        }else if(strTemp.indexOf("&")!=-1&&str.equals(strTemp.substring(0,strTemp.indexOf("&")-1)+strTemp.substring(strTemp.indexOf("&")+1,strTemp.length()))){
            strShow=strRelativeTrue[i];
        }
    }
    if(!strShow.isEmpty()){
        relativeResult.setText(strShow);
    }else {
        relativeResult.setText("亲戚关系太远");
    }
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
