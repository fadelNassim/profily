package com.example.profily

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.text.font.FontWeight
import com.example.common.ui.theme.AppTheme
import com.example.profily.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
              Scaffold( topBar = {
                  TopAppBar(
                      title = {
                          Text(text = "Profily", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
                      }
                  )
              }) { paddingValues ->
                  AppNavigation(appPadding = paddingValues)
              }
            }
        }
    }
}