package com.cqu.supercalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;
import java.lang.String;

public class CapitalExchangeActivity extends AppCompatActivity {
    private TextView capitalUpText;
    private TextView capitalDownText;
    private double capitalNowNum;
    private static final String[] NUMBERS = { "零", "壹", "贰", "叁", "肆", "伍","陆",
            "柒", "捌", "玖" };
    private static final String[] IUNIT = { "元", "拾", "佰", "仟", "万", "拾","佰",
            "仟", "亿", "拾", "佰", "仟", "万", "拾", "佰", "仟" };
    private static final String[] DUNIT = { "角", "分", "厘" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capital_exchange);
        /******初始化界面的组件*******/
        initView();
    }

    /**
     * 得到大写金额。
     */
    public String toChinese(String str) {
        str = str.replaceAll(",", "");// 去掉","
        String integerStr;// 整数部分数字
        String decimalStr;// 小数部分数字
        if(str.equals("0")){
            capitalDownText.setText("零");
        }

        // 初始化：分离整数部分和小数部分
        if (str.indexOf(".") > 0) {
            integerStr = str.substring(0, str.indexOf("."));
            decimalStr = str.substring(str.indexOf(".") + 1);
        } else if (str.indexOf(".") == 0) {
            integerStr = "";
            decimalStr = str.substring(1);
        } else {
            integerStr = str;
            decimalStr = "";
        }
        // integerStr去掉首0，不必去掉decimalStr的尾0(超出部分舍去)
        if (!integerStr.equals("")) {
            integerStr = Long.toString(Long.parseLong(integerStr));
            if (integerStr.equals("0")) {
                integerStr = "";
            }
        }
        // overflow超出处理能力，直接返回
        if (integerStr.length() > IUNIT.length) {
            System.out.println(str + ":超出处理能力");
            return str;
        }


        int[] integers = toArray(integerStr);// 整数部分数字
        boolean isMust5 = isMust5(integerStr);// 设置万单位
        int[] decimals = toArray(decimalStr);// 小数部分数字
        return getChineseInteger(integers, isMust5) + getChineseDecimal(decimals);
    }
    /**
     * 整数部分和小数部分转换为数组，从高位至低位
     */
    private static int[] toArray(String number) {
        int[] array = new int[number.length()];
        for (int i = 0; i < number.length(); i++) {
            array[i] = Integer.parseInt(number.substring(i, i + 1));
        }
        return array;
    }
    /**
     * 得到中文金额的整数部分。
     */
    private static String getChineseInteger(int[] integers, boolean isMust5) {
        StringBuffer chineseInteger = new StringBuffer("");
        int length = integers.length;
        for (int i = 0; i < length; i++) {
            // 0出现在关键位置：1234(万)5678(亿)9012(万)3456(元)
            // 特殊情况：10(拾元、壹拾元、壹拾万元、拾万元)
            String key = "";
            if (integers[i] == 0) {
                if ((length - i) == 13)// 万(亿)(必填)
                    key = IUNIT[4];
                else if ((length - i) == 9)// 亿(必填)
                    key = IUNIT[8];
                else if ((length - i) == 5 && isMust5)// 万(不必填)
                    key = IUNIT[4];
                else if ((length - i) == 1)// 元(必填)
                    key = IUNIT[0];
                // 0遇非0时补零，不包含最后一位
                if ((length - i) > 1 && integers[i + 1] != 0)
                    key += NUMBERS[0];
            }
            chineseInteger.append(integers[i] == 0 ? key
                    : (NUMBERS[integers[i]] + IUNIT[length - i - 1]));
        }
        return chineseInteger.toString();
    }
    /**
     * 得到中文金额的小数部分。
     */
    private static String getChineseDecimal(int[] decimals) {
        StringBuffer chineseDecimal = new StringBuffer("");
        for (int i = 0; i < decimals.length; i++) {
            // 舍去3位小数之后的
            if (i == 3)
                break;
            chineseDecimal.append(decimals[i] == 0 ? ""
                    : (NUMBERS[decimals[i]] + DUNIT[i]));
        }
        return chineseDecimal.toString();
    }
    /**
     * 判断第5位数字的单位"万"是否应加。
     */
    private static boolean isMust5(String integerStr) {
        int length = integerStr.length();
        if (length > 4) {
            String subInteger = "";
            if (length > 8) {
                // 取得从低位数，第5到第8位的字串
                subInteger = integerStr.substring(length - 8, length - 4);
            } else {
                subInteger = integerStr.substring(0, length - 4);
            }
            return Integer.parseInt(subInteger) > 0;
        } else {
            return false;
        }
    }

    private void initView(){

        capitalUpText=(TextView)findViewById(R.id.capitaluptext);
        capitalDownText=(TextView)findViewById(R.id.capitaldowntext);
        capitalUpText.setTextColor(0xFF64C1C9);
        capitalUpText.setText("0");
    }
    public void clickButton(View v) {

        int id = v.getId();


        if (capitalUpText.getText().toString().equals("0")) {
            //如果第一位是0，则消除0.
            capitalUpText.setText("");
            capitalDownText.setText("");
        }
        if (capitalUpText.getText().toString().equals(".")) {
            //如果第二位是“.”,则添加“0”
            capitalUpText.setText("0.");
        }
        //
        String str1 = capitalUpText.getText().toString();
        int a = str1.indexOf(".");
        int b = str1.length();
        int c=0;

        if (a==-1) c=0;
        else c=b-a;

        if (c <= 3&&b<=15) {
            if (id == R.id.capitalone) {
                capitalUpText.append("1");

            } else if (id == R.id.capitaltwo) {
                capitalUpText.append("2");
            } else if (id == R.id.capitalthree) {
                capitalUpText.append("3");
            } else if (id == R.id.capitalfour) {
                capitalUpText.append("4");
            } else if (id == R.id.capitalfive) {
                capitalUpText.append("5");
            } else if (id == R.id.capitalsix) {
                capitalUpText.append("6");
            } else if (id == R.id.capitalseven) {
                capitalUpText.append("7");
            } else if (id == R.id.capitaleight) {
                capitalUpText.append("8");
            } else if (id == R.id.capitalnine) {
                capitalUpText.append("9");
            } else if (id == R.id.capitalzero) {  //zero需要特殊处理
                if (!capitalUpText.getText().equals("0")) {
                    capitalUpText.append("0");
                }
            }
        }
            if (id == R.id.capitalac) {
                capitalUpText.setText("0");
                capitalDownText.setText("零");
            } else if (id == R.id.capitalpoint) { //point需要特殊处理
                int i = -2;
                i = capitalUpText.getText().toString().indexOf(".");
                if (i < 0) {
                    capitalUpText.append(".");
                }
            } else if (id == R.id.capitalreturn) {
           /*字符串的回退处理*/

                String str = capitalUpText.getText().toString();
                if (str.length() >= 1) {
                    str = str.substring(0, str.length() - 1);
                    capitalUpText.setText(str);
                }
                if (str.equals(""))
                    capitalUpText.setText("0");
                capitalDownText.setText("零");
            }
            try {
                capitalNowNum = Double.parseDouble(capitalUpText.getText().toString());
            } catch (Exception e) {
            }
            String capitalNum = capitalUpText.getText().toString();
            capitalDownText.setText("");
            String Capital = toChinese(capitalNum);
            capitalDownText.append(Capital);
        }

}

