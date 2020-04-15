package com.example.androidandh5;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JavaAndJsCallActivity extends Activity implements View.OnClickListener {

    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();
        initWebView();

    }


    private EditText etNumber;
    private EditText etPassword;
    private Button btnLogin;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2020-04-14 15:48:56 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        setContentView(R.layout.activity_java_and_js_call);
        etNumber = (EditText) findViewById(R.id.et_number);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2020-04-14 15:48:56 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btnLogin) {
            login();
        }
    }

    private void login() {
        //1.得到账号和密码
        String number = etNumber.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(number) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "账号和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            //账号和密码不为空
            Toast.makeText(this, "登录", Toast.LENGTH_SHORT).show();
            login(number, password);

        }


        //2.判断密码是否为null
    }

    private void login(String number, String password) {
        webView.loadUrl("javascript:javaCallJs('" + number + "')");
        setContentView(webView);
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        //1.加载网页H5 html 自定义浏览器
        webView = new WebView(this);
        WebSettings webSettings = webView.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //不调用浏览器
        webView.setWebViewClient(new WebViewClient());
        //加载网络的网页 和本地的网页
//        webView.loadUrl("http://www.atguigu.com/teacher.shtml");
        //加载本地html页面
        //添加 JavascriptInterface
        //js 通过Android字段 这个类的中的任何方法
        webView.addJavascriptInterface(new AndroidAndJsInterface(), "Android");
        webView.loadUrl("file:///android_asset/JavaAndJavaScriptCall.html");
//        setContentView(webView);
    }

    class AndroidAndJsInterface {

        @JavascriptInterface
        public void showToast() {
            Toast.makeText(JavaAndJsCallActivity.this, "android 被调用了", Toast.LENGTH_SHORT).show();
        }
    }


}
