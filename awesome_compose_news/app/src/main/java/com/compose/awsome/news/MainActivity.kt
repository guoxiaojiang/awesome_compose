package com.compose.awsome.news

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.compose.awsome.news.route.RouteConfig
import com.compose.awsome.news.ui.Home
import com.compose.awsome.news.ui.Web3Page
import com.compose.awsome.news.viewmodel.AppViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPerm()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val viewModel: AppViewModel by viewModels()
        setContent {
            val navHostController = rememberNavController()
            NavHost(navController = navHostController, startDestination =  RouteConfig.ROUTE_PAGE_HOME) {
                composable(RouteConfig.ROUTE_PAGE_HOME) {
                    Home(navHostController)
                }

                composable(RouteConfig.ROUTE_PAGE_WEB3) {
                    Web3Page(viewModel(), navHostController)
                }
            }
        }
    }

    private fun checkPerm() {
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS), 1)
    }
}