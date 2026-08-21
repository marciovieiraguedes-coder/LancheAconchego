package com.example.lancheaconchego

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lancheaconchego.ui.SnackApp
import com.example.lancheaconchego.ui.theme.LancheAconchegoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LancheAconchegoTheme {
                SnackApp()
            }
        }
    }
}
