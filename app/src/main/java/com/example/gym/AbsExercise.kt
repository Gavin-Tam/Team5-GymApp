package com.suhaib.gymlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.core.view.get
import com.suhaib.gymlist.R.id.web_view

class AbsExercise : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abs_exercise)

        val webView: WebView = findViewById<WebView>(web_view)

        val video : String = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/UyG1F0TvflM?si=OYfaRKGLytNOF14U\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"

        webView.loadData(video,"text/html","utf-8")

        webView.getSettings().javaScriptEnabled = true

        webView.webChromeClient = WebChromeClient()

    }
}