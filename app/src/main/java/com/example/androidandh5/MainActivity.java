package com.example.androidandh5;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {


    private Button btnJavaAndJs;
    private Button btnJsCallJava;
    private Button btnJsCallPhone;


    private void findViews() {
        setContentView(R.layout.activity_main);
        btnJavaAndJs = (Button) findViewById(R.id.btn_java_and_js);
        btnJsCallJava = (Button) findViewById(R.id.btn_js_call_java);
        btnJsCallPhone = (Button) findViewById(R.id.btn_js_call_phone);

        btnJavaAndJs.setOnClickListener(this);
        btnJsCallJava.setOnClickListener(this);
        btnJsCallPhone.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == btnJavaAndJs) {
            // Handle clicks for btnJavaAndJs
            // Toast.makeText(this, "android 与H5 互调", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, JavaAndJsCallActivity.class);
            startActivity(intent);
        } else if (v == btnJsCallJava) {
            // Handle clicks for btnJsCallJava
            //Toast.makeText(this, "android 播放视频", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,
                    JsCallJavaVideoActivity.class);
            startActivity(intent);
        } else if (v == btnJsCallPhone) {
            // Handle clicks for btnJsCallPhone
            //Toast.makeText(this, "android 拨打电话", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,
                    JsCallJavaCallPhoneActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViews();
    }
}
