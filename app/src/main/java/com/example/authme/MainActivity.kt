package com.example.authme

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.rememberNavController
import com.example.authme.nav.NavigationHost
import com.example.authme.ui.theme.AuthMeTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AuthMeApp()
        }
    }
}

@Composable
fun AuthMeApp() {
    AuthMeTheme {
        val navController = rememberNavController()
        Scaffold { innerPadding ->
            NavigationHost(navController = navController, modifier = Modifier.padding(innerPadding))
        }
    }
}