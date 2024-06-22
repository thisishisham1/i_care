package com.example.icare.view.main.bottomnavitems.home.category

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.icare.core.reusablecomponent.DefaultTopAppBar

@Composable
fun WebViewScreen(url: String, navController: NavController) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(title = "Personality Test", navController = navController)
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

/**
 * This Composable function creates a WebView and loads a given URL.
 * The WebView is created using the AndroidView function, which allows you to create an Android view and use it in your Composable.
 *
 * @param url The URL to load in the WebView.
 * @param modifier The Modifier to apply to the AndroidView.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewComposable(url: String) {
    AndroidView(
        // Factory to create the WebView
        factory = {
            WebView(it).apply {
                // Set layout parameters
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                // Enable JavaScript
                settings.javaScriptEnabled = true
                // Enable DOM storage
                settings.domStorageEnabled = true
                // Allow file access
                settings.allowFileAccess = false
                // Allow content access
                settings.allowContentAccess = true
                // Set WebViewClient
                webViewClient = MyWebViewClient()
                // Load the URL
                loadUrl(url)
            }
        }
    )
}

/**
 * Custom WebViewClient class.
 * This class is used to handle various events in the WebView, such as when a page finishes loading or when an error occurs.
 */
class MyWebViewClient : WebViewClient() {
    /**
     * Called when a page finishes loading.
     * @param view The WebView that is loading the page.
     * @param url The URL of the page.
     */
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    /**
     * Called when an error occurs while loading a page.
     * @param view The WebView that is loading the page.
     * @param request The WebResourceRequest that triggered the error.
     * @param error The WebResourceError describing the error.
     */
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        // Log the error
        Log.e("WebViewError", "Error: ${error?.description}")
    }
}