package com.example.androidandh5;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class JsCallJavaVideoActivity extends AppCompatActivity {

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
        webview.addJavascriptInterface(new AndroidAndJsInterface(), "android");
        webview.loadUrl("file:///android_asset/RealNetJSCallJavaActivity.htm");
//        setContentView(webView);
    }

    class AndroidAndJsInterface {

        @JavascriptInterface
        public void playVideo(int id , String videoUrl , String title) {
            //1.把所有的播放器调动起来
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(videoUrl), "video/*");
            startActivity(intent);
            Toast.makeText(JsCallJavaVideoActivity.this, "videoUrl" + videoUrl, Toast.LENGTH_SHORT).show();
        }
    }
}
