package com.bcebhagalpur.bcece.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bcebhagalpur.bcece.R
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        webView.webViewClient=MyWebViewClint()
        val array= arrayOf("https://www.mcemotihari.ac.in/")

        webView.loadUrl(array[0])

        btnBack.setOnClickListener {
            if (webView.canGoBack()){
                webView.goBack()
            }else{
                Toast.makeText(this,"no history available",Toast.LENGTH_LONG).show()
            }
        }
        btnGo.setOnClickListener {
            if (webView.canGoForward()){
                webView.goForward()
            }else{
                Toast.makeText(this,"no history available",Toast.LENGTH_LONG).show()
            }
        }

    }
    class MyWebViewClint:WebViewClient(){

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            view?.loadUrl(request?.url.toString())
            return true
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }
            }
        }
