package com.app.hasnain.youtubeweb

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings.LOAD_CACHE_ELSE_NETWORK
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView
    private lateinit var progressBar: ProgressBar
    private lateinit var noInternetText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView = findViewById(R.id.webView)
        progressBar = findViewById(R.id.progressBar)
        noInternetText = findViewById(R.id.noInternetText)


        initSettings()

        // Configure WebView settings
        with(webView.settings) {
            javaScriptEnabled = true // Required for YouTube
            domStorageEnabled = true // Enable DOM storage for better performance
            cacheMode = LOAD_CACHE_ELSE_NETWORK // Use cache if available, else network
            databaseEnabled = true // Enable database storage
            loadWithOverviewMode = true // Fit content to screen
            useWideViewPort = true // Enable responsive layout
        }

        // Show loader until page is loaded
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    progressBar.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                } else {
                    progressBar.visibility = View.VISIBLE
                    webView.visibility = View.GONE
                }
            }
        }

        // Ensure links open within WebView and handle errors
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return if (url.startsWith("intent:") || url.startsWith("market:")) {
                    true
                } else {
                    view.loadUrl(url)
                    false
                }
            }


            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                if (!isNetworkAvailable()) {
                    webView.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    noInternetText.visibility = View.VISIBLE
                }
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                if (isNetworkAvailable()) {
                    noInternetText.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                }
            }
        }

        // Load YouTube URL if network is available, else show error
        if (savedInstanceState != null) {
            webView.restoreState(savedInstanceState)
        } else {
            if (isNetworkAvailable()) {
                noInternetText.visibility = View.GONE
                webView.loadUrl("https://www.youtube.com")
            } else {
                webView.visibility = View.GONE
                progressBar.visibility = View.GONE
                noInternetText.visibility = View.VISIBLE
            }
        }

    }

    private fun initSettings() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.youtube)
            progressBar.indeterminateTintList = ContextCompat.getColorStateList(this, R.color.youtube)
        }
        else{
            val drawable = ContextCompat.getDrawable(this, R.color.youtube)
            progressBar.indeterminateDrawable = drawable
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        webView.saveState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        webView.restoreState(savedInstanceState)
    }


    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack() // Navigate back in WebView
        } else {
            super.onBackPressed() // Default back behavior
        }
    }



    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}