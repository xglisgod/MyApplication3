package cn.edu.bistu.cs.se.myapplication3;


import android.app.Application;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TimeCount timeCount;
    EditText text1;
    Button button1;
    Button button2;
    TextView timecounting;
    int i;//定义一个记录输入数据的整形

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Click();
    }

    public void Click() {
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                input();
            }
        });

        }

    private void input() {
        text1 = (EditText) findViewById(R.id.editText1);
        String time = text1.getText().toString();
        if(time.equals("")){
            Toast.makeText(MainActivity.this, "请输入秒数再点击开始！", Toast.LENGTH_LONG).show();
            Click();
        }else{
            i = Integer.parseInt(time);
            initData();
        }

    }

    private void initData() {
        setContentView(R.layout.run);
        //暂停按钮
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stop();
            }
        });
        timecounting = (TextView) findViewById(R.id.timecounting);
        timeCount = new TimeCount(i * 1000, 1000);
        timeCount.start();
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            timecounting.setText((millisUntilFinished / 1000) + "秒");
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            dialog();

        }
    }

//暂停方法
    public void stop(){
        timecounting.setText(timecounting.getText().toString());
        timeCount.cancel();
        AlertDialog.Builder builder = new Builder(this);
        builder.setMessage("\n已暂停\n");

        builder.setTitle("提醒");

        builder.setPositiveButton("重新开始",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.activity_main);
                        Click();
                    }
                });
        builder.setNegativeButton("退出程序",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.create().show();
    }


  //结束时方法
    public void dialog() {
        AlertDialog.Builder builder = new Builder(this);
        builder.setMessage("\n时间到！！！！！！\n");

        builder.setTitle("提醒");

        builder.setPositiveButton("重新计时",
                new android.content.DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setContentView(R.layout.activity_main);
                        Click();
                    }
                });
        builder.setNegativeButton("退出程序",
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        builder.create().show();
    }

}