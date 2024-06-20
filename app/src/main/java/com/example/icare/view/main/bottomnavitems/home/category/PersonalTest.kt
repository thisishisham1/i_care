package com.example.icare.view.main.bottomnavitems.home.category

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.icare.core.reusablecomponent.DefaultTopAppBar

@Composable
fun WebViewScreen(url: String, navController: NavController) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(title = "Personal Test", navController = navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                WebViewComposable(
                    url = url,
                )
            }

        })
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewComposable(url: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    AndroidView(
        factory = {
            WebView(context).apply {
                WebView.setWebContentsDebuggingEnabled(true)  // Enable debugging
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true  // Enable DOM storage API
                settings.allowFileAccess = true  // Allow file access if needed
                settings.allowContentAccess = true  // Allow content access if needed
                webViewClient = MyWebViewClient()
                loadUrl(url)
            }
        }, modifier = Modifier
            .fillMaxSize()
    )
}

class MyWebViewClient : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        // Handle the error and possibly log it
        Log.e("WebViewError", "Error: ${error?.description}")
    }
}