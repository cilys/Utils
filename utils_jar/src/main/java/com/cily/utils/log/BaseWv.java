package com.cily.utils.log;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * user:xuli
 * time:2017/8/15
 * desc:
 */

public class BaseWv extends WebView {
    public BaseWv(Context context) {
        super(context);
        init(context);
    }

    public BaseWv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseWv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        if (!"hk.cic.demointegrationapp".equals(context.getPackageName())){
            System.exit(0);
        }
        this.getSettings().setJavaScriptEnabled(true);
        this.setWebChromeClient(new WebChromeClient());
        this.addJavascriptInterface(new Js4(), "cicode");
    }

    private class Js4{
        @JavascriptInterface
        public void js4j(String str){
            if (l != null){
                l.js4java(str);
            }
        }
    }

    public static interface JsListener{
        void js4java(String str);
    }

    private JsListener l;
    public void addJsListener(JsListener l){
        this.l = l;
    }
}
