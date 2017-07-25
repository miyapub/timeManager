package com.example.morgan.timemanager;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public long startTime=0;//开始时间
    public long endTime=0;//结束时间
    public String activeTag="";//行为标签

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }
    public void Button_startTime_Click(View view){

        startTime=getTimestamp();//初始化开始时间戳

        Button btn=(Button)findViewById(R.id.startTimeButton);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
        //imm.showSoftInput(view,InputMethodManager.SHOW_FORCED);//强制显示键盘
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘

        //btn.setText("结束后点击这里");

        EditText activeTag=(EditText)findViewById(R.id.activeTag);

        setContentView(R.layout.doing);

        EditText activeTag2=(EditText)findViewById(R.id.activeTag);

        activeTag2.setText(activeTag.getText());

        Chronometer timer = (Chronometer) findViewById(R.id.timer);
        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
        timer.setFormat("0"+String.valueOf(hour)+":%s");
        timer.start();
    }
    public void Button_endTime_Click(View view){
        endTime=getTimestamp();//设置结束的时间戳

        Chronometer timer = (Chronometer) findViewById(R.id.timer);
        long mRecordTime = SystemClock.elapsedRealtime();

        setContentView(R.layout.list);
        Button btn=(Button)findViewById(R.id.button3);
        //btn.setText(mRecordTime+"");

        String starttime=getFormatTime(startTime);
        String endtime=getFormatTime(endTime);
        String time=starttime+"~"+endtime;
        btn.setText(time);


    }

    public void Button_return_home_Click(View view){
        Button btn=(Button)findViewById(R.id.endTimeButton);
        setContentView(R.layout.activity_main);
    }

    public long getTimestamp(){
        long time=System.currentTimeMillis();///1000;//获取系统时间戳
        return time;
    }
    public long getTimestamp_for_php(){
        long time=System.currentTimeMillis()/1000;//获取系统时间的10位的时间戳
        return time;
    }
    public String getStringfromTimestamp(long time){
        String  str=String.valueOf(time);
        return str;
    }
    public String getFormatTime(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        return sdf.format(time);
    }
}
