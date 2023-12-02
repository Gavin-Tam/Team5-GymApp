package com.suhaib.gymlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.view.get
import com.suhaib.gymlist.R.id.web_view

class ShouldersExercise : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shoulders_exercise)

        val webView: WebView = findViewById<WebView>(R.id.web_view)

        val video : String = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/2vl18gjB7R4?si=Tta6BptuhX-TCc31\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"

        webView.loadData(video,"text/html","utf-8")

        webView.getSettings().javaScriptEnabled = true

        webView.webChromeClient = WebChromeClient()

    }
}