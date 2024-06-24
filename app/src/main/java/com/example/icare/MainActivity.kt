package com.example.icare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.icare.core.theme.ICareTheme
import com.example.icare.model.classes.ConnectivityObserver
import com.example.icare.model.network.NetworkStatusHelper
import com.example.icare.model.sharedPreferences.PreferencesHelper
import com.example.icare.view.offline.OfflineView
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity(), ConnectivityObserver {
    private lateinit var networkStatusHelper: NetworkStatusHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkStatusHelper = NetworkStatusHelper(this)
        lifecycleScope.launch {
            observe().collect { status ->
                when (status) {
                    ConnectivityObserver.Status.AVAILABLE -> {
                        setContent {
                            ICareTheme {

                                MainNavigation(context = this@MainActivity)

                            }
                        }
                    }

                    ConnectivityObserver.Status.UNAVAILABLE -> {
                        setContent {
                            ICareTheme {
                                OfflineView()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences = PreferencesHelper(this)
        sharedPreferences.getBooleanValue("onBoarding")
    }

    override fun observe(): Flow<ConnectivityObserver.Status> {
        return networkStatusHelper.isNetworkAvailable.map { isAvailable ->
            if (isAvailable) ConnectivityObserver.Status.AVAILABLE
            else ConnectivityObserver.Status.UNAVAILABLE
        }
    }
}


