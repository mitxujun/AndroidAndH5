package com.example.androidandh5;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class JsCallJavaCallPhoneActivity extends AppCompatActivity {

    private WebView webview;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_call_java_video);
        webview = (WebView) findViewById(R.id.webview);


        //1.加载网页H5 html 自定义浏览器

        WebSettings webSettings = webview.getSettings();
        //设置支持js
        webSettings.setJavaScriptEnabled(true);
        //不调用浏览器
        webview.setWebViewClient(new WebViewClient());
        //加载网络的网页 和本地的网页
//        webView.loadUrl("http://www.atguigu.com/teacher.shtml");
        //加载本地html页面
        //添加 JavascriptInterface
        //js 通过Android字段 这个类的中的任何方法
        webview.addJavascriptInterface(new AndroidAndJsInterface(), "Android");
        webview.loadUrl("file:///android_asset/JsCallJavaCallPhone.html");
//        setContentView(webView);
    }

    class AndroidAndJsInterface {

        @JavascriptInterface
        public void call(String phone) {

            //拨打电话的意图
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
            if (ActivityCompat.checkSelfPermission( JsCallJavaCallPhoneActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return ;
            }

            startActivity(intent);

            //2.加拨打电话的权限


        }


        @JavascriptInterface
        public void showcontacts() {
            Toast.makeText(JsCallJavaCallPhoneActivity.this, "showcontacts", Toast.LENGTH_SHORT).show();
            //用于加载联系人的数据
            String json = "[{\"name\":\"阿福\", \"phone\":\"18600012345\"}]";
            // 调用JS中的方法
            webview.loadUrl("javascript:show('" + json + "')");


        }
    }
}
