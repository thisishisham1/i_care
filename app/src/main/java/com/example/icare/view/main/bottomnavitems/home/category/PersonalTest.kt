package com.example.icare.view.main.bottomnavitems.home.category

import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
fun WebViewScreen(url: String, title: String, navController: NavController) {
    Scaffold(
        topBar = {
            DefaultTopAppBar(title = title, navController = navController)
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
fun WebViewComposable(url: String) {
    var uploadMessage: ValueCallback<Array<Uri>>? = null
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
            uploadMessage?.onReceiveValue(uris.toTypedArray())
            uploadMessage = null
        }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.allowFileAccess = true // Allow file access for image uploads
                settings.allowContentAccess = true
                webChromeClient = object : WebChromeClient() {
                    override fun onShowFileChooser(
                        webView: WebView?,
                        filePathCallback: ValueCallback<Array<Uri>>?,
                        fileChooserParams: FileChooserParams?
                    ): Boolean {
                        uploadMessage = filePathCallback
                        launcher.launch("image/*")
                        return true
                    }
                }
                webViewClient = MyWebViewClient()
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
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        Log.e("WebViewError", "Error: ${error?.description}")
    }

    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (url != null) {
            view?.loadUrl(url)
        }
        return true // Handle the URL loading within the WebView, not in an external browser.
    }
}